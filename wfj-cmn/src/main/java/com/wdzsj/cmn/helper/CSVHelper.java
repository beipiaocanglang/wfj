package com.wdzsj.cmn.helper;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import com.wdzsj.cmn.annotation.ExcelVOAttr;
import com.wdzsj.cmn.constant.Const;
import com.wdzsj.cmn.constant.Status;
import com.wdzsj.cmn.exception.WDException;
import com.wdzsj.cmn.util.DateUtil;
import com.wdzsj.cmn.util.StringUtil;


public class CSVHelper<T> {

    Class<T> clazz;

    public CSVHelper(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * 导出
     *
     * @param dataList csv文件(路径+文件名)，csv文件不存在会自动创建
     * @param out 数据
     * @return
     */
    public void exportCSV(List<T> dataList,OutputStream out){
        OutputStreamWriter osw=null;
        BufferedWriter bw=null;
        CsvWriter csvWriter=null;
        try {
            osw = new OutputStreamWriter(out, Const.CHARSET_GBK);
            bw =new BufferedWriter(osw);
            csvWriter = new CsvWriter(bw, ',');
            if(dataList!=null && !dataList.isEmpty()){

                List<Field> fields = getMappedFiled(clazz, null);

                StringBuffer headName = new StringBuffer();
                for (int i = 0; i < fields.size(); i++) {
                    Field field = fields.get(i);
                    ExcelVOAttr attr = field.getAnnotation(ExcelVOAttr.class);

                    headName.append(attr.name()).append(Const.SPLIT_ONE);
                }
                csvWriter.writeRecord(headName.toString().split(Const.SPLIT_ONE));

                StringBuffer rowData = new StringBuffer();
                for(T vo : dataList) {
                    for (int j = 0; j < fields.size(); j++) {
                        Field field = fields.get(j);// 获得field.
                        field.setAccessible(true);// 设置实体类私有属性可访问

                        Class<?> fieldType = field.getType();
                        String value = "";
                        if (Date.class == fieldType) {
                            Date date = (Date)field.get(vo);

                            value = null == date ? "" : DateUtil.formatDatetime(date);
                        } else {
                            Object obj = field.get(vo);
                            value = null == obj ? "" : String.valueOf(obj);
                        }
                        rowData.append(value).append(Const.SPLIT_ONE);
                    }
                    csvWriter.writeRecord(rowData.toString().split(Const.SPLIT_ONE));
                    rowData = new StringBuffer();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(bw!=null){
                try {
                    bw.close();
                    bw=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(osw!=null){
                try {
                    osw.close();
                    osw=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out!=null){
                try {
                    out.close();
                    out=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(csvWriter!=null){
                try {
                    csvWriter.flush();
                    csvWriter.close();
                    csvWriter=null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List importCSV(MultipartFile file) {
        List<T> list = new ArrayList<T>();

        int maxCol = 0;
        List<Field> allFields = getMappedFiled(clazz, null);
        Map<Integer, Field> fieldsMap = new HashMap<Integer, Field>();// 定义一个map用于存放列的序号和field.
        for (Field field : allFields) {
            // 将有注解的field存放到map中.
            if (field.isAnnotationPresent(ExcelVOAttr.class)) {
                ExcelVOAttr attr = field.getAnnotation(ExcelVOAttr.class);
                int col = getExcelCol(attr.column());// 获得列号
                maxCol = Math.max(col, maxCol);
                // System.out.println(col + "====" + field.getName());
                field.setAccessible(true);// 设置类的私有字段属性可访问.
                fieldsMap.put(col, field);
            }
        }

        CsvReader csvReader = null;
        String c = "";
        try {
            csvReader = new CsvReader(file.getInputStream(), ',', Charset.forName(Const.CHARSET_GBK));
            int count = 0;
            while (csvReader.readRecord()) {
                String[] rowData = csvReader.getValues();
                if(count == 0) {
                    for (int i = 0; i < allFields.size(); i++) {
                        Field field = allFields.get(i);
                        ExcelVOAttr attr = field.getAnnotation(ExcelVOAttr.class);
                        String title = attr.name();
                        String curTitle = rowData[i];

                        if(!title.equals(curTitle)) {
                            throw new WDException(Status.EXCEPTION_ERROR,"表头不正确，正确标题为："+title+"，当前表头为："+curTitle);
                        }
                    }
                } else {
                    T entity = null;
                    for(int j=0; j<rowData.length; j++) {
                        c = rowData[j];

                        entity = (entity == null ? clazz.newInstance() : entity);// 如果不存在实例则新建.
                        Field field = fieldsMap.get(j);// 从map中得到对应列的field.
                        if (field==null) {
                            continue;
                        }

                        // 取得类型,并根据对象类型设置值.
                        Class<?> fieldType = field.getType();
                        if (String.class == fieldType) {
                            String v = String.valueOf(c);
                            field.set(entity, v);
                        } else if ((Integer.TYPE == fieldType)
                                || (Integer.class == fieldType)) {
                            if(StringUtil.isEmpty(c)) continue;
                            if(!StringUtil.isDigits(c)) continue;
                            field.set(entity, Integer.parseInt(c));
                        } else if ((Long.TYPE == fieldType)
                                || (Long.class == fieldType)) {
                            if(StringUtil.isEmpty(c)) continue;
                            if(!StringUtil.isDigits(c)) continue;
                            field.set(entity, Long.valueOf(c));
                        } else if ((Float.TYPE == fieldType)
                                || (Float.class == fieldType)) {
                            if(StringUtil.isEmpty(c)) continue;
                            field.set(entity, Float.valueOf(c));
                        } else if ((Short.TYPE == fieldType)
                                || (Short.class == fieldType)) {
                            field.set(entity, Short.valueOf(c));
                        } else if ((Double.TYPE == fieldType)
                                || (Double.class == fieldType)) {
                            if(StringUtil.isEmpty(c)) continue;
                            field.set(entity, Double.valueOf(c));
                        }else if ((Date.class == fieldType)) {
                            String dt = String.valueOf(c);
                            if(dt.indexOf("/") != -1) {
                                dt = dt.replaceAll("/","-") + ":00";
                            }
                            if(StringUtil.isDateTime(dt) && dt.length() > 15) {
                                field.set(entity, DateUtil.parseDatetime(dt));
                            }
                            if(StringUtil.isDate(dt) && dt.length() < 15) {
                                field.set(entity, DateUtil.parseDate(dt));
                            }
                        } else if (Character.TYPE == fieldType) {
                            if ((c != null) && (c.length() > 0)) {
                                field.set(entity, Character
                                        .valueOf(c.charAt(0)));
                            }
                        }
                    }
                    list.add(entity);
                }
                count++;
            }
        }catch (WDException e) {
            e.printStackTrace();
            throw new WDException(Status.EXCEPTION_ERROR,e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new WDException(Status.EXCEPTION_ERROR,"数据格式不正确，错误数据为："+c);
        } finally{
            if(csvReader!=null){
                try {
                    csvReader.close();
                    csvReader=null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    /**
     * 得到实体类所有通过注解映射了数据表的字段
     *
     * @param fields
     * @return
     */
    private List<Field> getMappedFiled(Class clazz, List<Field> fields) {
        if (fields == null) {
            fields = new ArrayList<Field>();
        }

        Field[] allFields = clazz.getDeclaredFields();// 得到所有定义字段
        // 得到所有field并存放到一个list中.
        for (Field field : allFields) {
            if (field.isAnnotationPresent(ExcelVOAttr.class)) {
                fields.add(field);
            }
        }
        if (clazz.getSuperclass() != null
                && !clazz.getSuperclass().equals(Object.class)) {
            getMappedFiled(clazz.getSuperclass(), fields);
        }

        return fields;
    }

    /**
     * 将EXCEL中A,B,C,D,E列映射成0,1,2,3
     *
     * @param col
     */
    public static int getExcelCol(String col) {
        col = col.toUpperCase();
        // 从-1开始计算,字母重1开始运算。这种总数下来算数正好相同。
        int count = -1;
        char[] cs = col.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            count += (cs[i] - 64) * Math.pow(26, cs.length - 1 - i);
        }
        return count;
    }

}
