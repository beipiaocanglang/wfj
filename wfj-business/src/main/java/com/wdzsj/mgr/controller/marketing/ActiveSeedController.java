package com.wdzsj.mgr.controller.marketing;

import com.wdzsj.cmn.util.HttpClientUtils;
import com.wdzsj.mgr.castor.util.ExcelHelper;
import com.wdzsj.mgr.castor.util.JsonUtil;
import com.wdzsj.mgr.entity.marketing.*;
import com.wdzsj.mgr.entity.marketing.vo.*;
import com.wdzsj.mgr.service.marketing.*;
import org.apache.commons.lang.StringUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 种子人群
 */
@Controller
@RequestMapping(value="/marketing/seed")
@SuppressWarnings({"all", "rawtypes"})
public class ActiveSeedController {

    private final static String PAGE_NEWACTIVITY = "marketing/newactivity";
    private final static String PAGE_EDITACTIVE = "marketing/editActive";

    //品类服务
    @Resource
    private CategoryService categoryService;

    //活动列表服务
    @Resource
    private ActiveService activeService;

    //附件表数据
    @Resource
    private AttachmentService attachmentService;

    //门店服务
    @Resource
    private StoresService storesService;

    //活动标签关联服务
    @Resource
    private ActiveTagRelationService activeTagRelationService;

    //活动会员关联服务
    @Resource
    private ActiveMemberRelationService activeMemberRelationService;

    //会员服务
    @Resource
    private MemberService memberService;

    //交易记录服务
    @Resource
    private TradingDataService tradingDataService;

    //标签服务
    @Resource
    private ActiveLabelSpreadController labelSpreadControllerl;

    //公共部分
    @Resource
    private ActiveEchoController activeEchoController;

    /**
     * 营销活动 ~ 保存种子数据
     * /marketing/seed/saveSeedData
     * @param model
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("/saveSeedData")
    public String saveSeedData(ModelMap model, MultipartFile file, HttpServletRequest request, SeedPeopleVo seedPeopleVo) throws IOException {
        Active active = new Active();
        active.setActId(Long.parseLong(seedPeopleVo.getS_actId()));

        if (file!=null && file.getSize()>0) {//保存上传的种子用户文件
            String fileName = file.getOriginalFilename();//获取文件名称
            String extNameType = fileName.substring(fileName.lastIndexOf(".")+1);//获取文件的扩展名
            byte[] bytes = file.getBytes();//获取文件内容

            Attachment attachment = new Attachment();
            attachment.setAttachmentContent(bytes);
            attachment.setAttachmentName(fileName);
            attachment.setAttachmentUpdatetime(new Date());
            attachment.setAttachmentType(extNameType);

            if (StringUtils.isNotBlank(seedPeopleVo.getS_attachmentId())){
                attachment.setAttachmentId(Long.parseLong(seedPeopleVo.getS_attachmentId()));
                //根据主键id修改 附加表数据
                int rowNum = attachmentService.updateByPrimaryKeySelective(attachment);
            } else {
                attachment.setAttachmentCreatetime(new Date());
                //保存种子文件到  返回主键
                int rowNum = attachmentService.insertSelective(attachment);
                active.setAttachmentIdImportFile(attachment.getAttachmentId());
            }
        }

        String[] years = {seedPeopleVo.getS_startTime(),seedPeopleVo.getS_endTime()};

        if (StringUtils.isNotBlank(seedPeopleVo.getS_allPpArray())){
            String s_allPpArray = seedPeopleVo.getS_allPpArray();
            String yearsArray = JsonUtil.toJson(years);
            String ppId = seedPeopleVo.getS_ppIdArray();
            s_allPpArray = s_allPpArray + "_" + yearsArray + "_" + ppId;

            ActiveTagRelation activeTagRelation = new ActiveTagRelation();
            activeTagRelation.setActId(Long.parseLong(seedPeopleVo.getS_actId()));
            activeTagRelation.setSeedCategoryData(s_allPpArray);

            ActiveTagRelation seedActiveTagRelation = activeTagRelationService.selectByactId(Long.parseLong(seedPeopleVo.getS_actId()));
            if (seedActiveTagRelation!=null){
                activeTagRelationService.updateByIdSelective(activeTagRelation);
            } else {
                activeTagRelationService.insertSelective(activeTagRelation);
            }
        }

        Map<String,Object> params = new HashMap();
        params.put("cityids",seedPeopleVo.getS_storesId());
        params.put("ppids",seedPeopleVo.getS_ppIdArray());
        params.put("years",years);

        Map<String, Object> resultMap = activeEchoController.zhiSuanHttp(params);

        if (!resultMap.get("msg").equals("success")){
            model.addAttribute("isSelect",1);
            return PAGE_NEWACTIVITY;
        }

        String result = (String) resultMap.get("data");
        Map<String,Object> dataMap = JsonUtil.parseJson(result, Map.class);

        List<ZhiSuanVo> finalList = activeEchoController.countSeedNum(dataMap);

        int seedNum = 0;
        String userId = "";
        for (ZhiSuanVo zhiSuanVo : finalList) {
            if (!userId.equals(zhiSuanVo.getRowkey())){
                userId = zhiSuanVo.getRowkey();
                seedNum += 1;

                ActiveMemberRelation memberRelation = new ActiveMemberRelation();
                memberRelation.setActId(Long.parseLong(seedPeopleVo.getS_actId()));
                memberRelation.setCid(zhiSuanVo.getRowkey());

                ActiveMemberRelation activeMemberRelation = activeMemberRelationService.selectByActIdAndCid(Long.parseLong(seedPeopleVo.getS_actId()), zhiSuanVo.getRowkey());
                if (activeMemberRelation==null){
                    //保存 活动会员关联表数据
                    int insert = activeMemberRelationService.insert(memberRelation);
                }

                Member member = new Member();
                member.setCid(zhiSuanVo.getRowkey());
                member.setPhone(zhiSuanVo.getPhone());
                member.setAgeGroup(zhiSuanVo.getAge_group());
                member.setOpenid(zhiSuanVo.getOpenid());
                member.setSex(zhiSuanVo.getSex());
                member.setProvince(zhiSuanVo.getProvince());
                member.setMdh(zhiSuanVo.getCityId());

                List<Member>  memberResult = memberService.selectByPrimaryKey(zhiSuanVo.getRowkey());
                if (memberResult!=null && memberResult.size()>0){
                    int i = memberService.updateByCidSelective(member);
                } else {
                    int resultRowNum = memberService.insertSelective(member);
                }

                List<TradingData> tradingData = tradingDataService.selectByPrimaryKey(zhiSuanVo.getRowkey());
                if (tradingData!=null && tradingData.size()>0){
                    //先删除  再插入
                    int i = tradingDataService.deleteByCid(zhiSuanVo.getRowkey());
                }
            }

            TradingData data = new TradingData();
            data.setCid(zhiSuanVo.getRowkey());
            data.setPp(zhiSuanVo.getPpId());
            data.setStoreId(zhiSuanVo.getCityId());
            data.setWeekMoney(BigDecimal.valueOf( Double.parseDouble(zhiSuanVo.getWeekendsalemoney())));
            data.setWeekNo(Integer.parseInt(zhiSuanVo.getWeekenddays()));
            data.setWeekRate(Integer.parseInt(zhiSuanVo.getWeekendfrequency()));
            data.setYear(Integer.parseInt(zhiSuanVo.getYear()));

            int RowNum = tradingDataService.insertSelective(data);
        }

        //修改活动表中导入 种子文件 的字段 和 智算后的 种子数量
        active.setSeedNum(String.valueOf(seedNum));
        int updataResult = activeService.updateByPrimaryKeySelective(active);

        activeEchoController.selectAllCommon(seedPeopleVo.getS_storesId(), active.getActId(), model);

        model.addAttribute("isSelect",2);

        return PAGE_NEWACTIVITY;
    }

    /**
     * 智算是 通过controller调用 远程http
     * /marketing/seed/getHbaseData
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getHbaseData", method= RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String getHttpServer(String[] cityids, String[] ppids, String[] years){

        Map<String,Object> params = new HashMap();
        params.put("cityids",new String[]{"72011","81011"});
        params.put("ppids",new String[]{"00101","00104","00105"});
        params.put("years",new String[]{"2018-01-16","2018-01-16"});

        Map<String, Object> map = activeEchoController.zhiSuanHttp(params);

        if (map.get("msg").equals("success")){
            String result = (String) map.get("data");
            Map map1 = JsonUtil.parseJson(result, Map.class);

            map.put("data",map1.size()-1);
        }

        return JsonUtil.toJson(map);
    }

    /**
     * 智算完成后的下载种子结果Excel表格
     * /marketing/seed/dowloadSeedExcel?cityids=72011,81011&ppids=00101,00104,00105&years=2018-01-16,2018-01-16
     * @param model
     * @param cityids
     * @param ppids
     * @param years
     * @param response
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value="/dowloadSeedExcel",method=RequestMethod.GET)
    public String export(ModelMap model, String[] cityids, String[] ppids, String[] years,HttpServletResponse response) throws IOException{

        Map<String,Object> params = new HashMap();
        params.put("cityids",new String[]{"72011","81011"});
        params.put("ppids",new String[]{"00101","00104","00105"});
        params.put("years",new String[]{"2018-01-16","2018-01-16"});

        Map<String, Object> resultMap = activeEchoController.zhiSuanHttp(params);

        if (!resultMap.get("msg").equals("success")){
            model.addAttribute("isSelect",1);
            return PAGE_NEWACTIVITY;
        }

        String result = (String) resultMap.get("data");
        Map<String,Object> dataMap = JsonUtil.parseJson(result, Map.class);

        List<ZhiSuanVo> finalList = activeEchoController.countSeedNum(dataMap);

        SimpleDateFormat format = new SimpleDateFormat("HHmmss");
        String msg = new String(("种子用户列表_" + format.format(new Date()) + ".xlsx").getBytes(), "ISO-8859-1");

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename="+msg);
        response.flushBuffer();

        new ExcelHelper(ZhiSuanVo.class).exportExcel(finalList,"种子用户列表",response.getOutputStream()) ;

        model.addAttribute("isSelect",1);
        return PAGE_NEWACTIVITY;
    }

    /**
     * 模拟智算数据源
     * /marketing/seed/simulationZhiSuanData
     */
    @ResponseBody
    @RequestMapping("/simulationZhiSuanData")
    public Map<String, Object> simulationZhiSuanData(ModelMap modelMap, String[] cityids, String[] ppids, String[] years, HttpServletRequest request){

        Map<String, List> map = new HashMap<>();

        List<SeedCountNumVo> saleList = new ArrayList<>();
        SeedCountNumVo seedCountNumVo = new SeedCountNumVo();
        seedCountNumVo.setWeekenddays("9");
        seedCountNumVo.setWeekendfrequency("6");
        seedCountNumVo.setWeekendsalemoney("36991.27");
        saleList.add(seedCountNumVo);

        map.put("21012_00101_2017_00000044480037114", saleList);

        List<SeedCountNumVo> saleList1 = new ArrayList<>();
        SeedCountNumVo seedCountNumVo1 = new SeedCountNumVo();
        seedCountNumVo1.setWeekenddays("8");
        seedCountNumVo1.setWeekendfrequency("5");
        seedCountNumVo1.setWeekendsalemoney("8991.27");

        SeedCountNumVo seedCountNumVo2 = new SeedCountNumVo();
        seedCountNumVo2.setWeekenddays("2");
        seedCountNumVo2.setWeekendfrequency("3");
        seedCountNumVo2.setWeekendsalemoney("2391.9");

        saleList1.add(seedCountNumVo1);
        saleList1.add(seedCountNumVo2);

        map.put("61011_00102001_2017_00000044480037112", saleList1);

        List<SeedCountNumVo> saleList2 = new ArrayList<>();
        SeedCountNumVo seedCountNumVo3 = new SeedCountNumVo();
        seedCountNumVo3.setWeekenddays("7");
        seedCountNumVo3.setWeekendfrequency("4");
        seedCountNumVo3.setWeekendsalemoney("888.27");

        SeedCountNumVo seedCountNumVo4 = new SeedCountNumVo();
        seedCountNumVo4.setWeekenddays("6");
        seedCountNumVo4.setWeekendfrequency("6");
        seedCountNumVo4.setWeekendsalemoney("231.9");

        SeedCountNumVo seedCountNumVo5 = new SeedCountNumVo();
        seedCountNumVo5.setWeekenddays("25");
        seedCountNumVo5.setWeekendfrequency("10");
        seedCountNumVo5.setWeekendsalemoney("23981.6");

        saleList2.add(seedCountNumVo3);
        saleList2.add(seedCountNumVo4);
        saleList2.add(seedCountNumVo5);

        map.put("40301_00101001_2017_00000044480037113", saleList2);

        List<UserVo> userList = new ArrayList<>();
        UserVo userVo = new UserVo();
        userVo.setRowkey("00000044480037114");
        userVo.setAge_group("18-28");
        userVo.setOpenid("0108978780113");
        userVo.setPhone("1371142187");
        userVo.setProvince("重庆");
        userVo.setSex("女");

        UserVo userVo1 = new UserVo();
        userVo1.setRowkey("00000044480037112");
        userVo1.setAge_group("28-38");
        userVo1.setOpenid("0108978780114");
        userVo1.setPhone("1371142199");
        userVo1.setProvince("北京");
        userVo1.setSex("男");

        UserVo userVo2 = new UserVo();
        userVo2.setRowkey("00000044480037113");
        userVo2.setAge_group("38-48");
        userVo2.setOpenid("0108978780112");
        userVo2.setPhone("1371166699");
        userVo2.setProvince("上海");
        userVo2.setSex("女");

        userList.add(userVo);
        userList.add(userVo1);
        userList.add(userVo2);

        map.put("userinfo", userList);

        Map<String, Object> resultmap=new HashMap<>();
        resultmap.put("code","200");
        resultmap.put("msg", "success");
        resultmap.put("data", JsonUtil.toJson(map));

        return resultmap;
    }

    /**
     * 营销活动 ~ 编辑后保存种子数据
     * /marketing/seed/saveEditSeedData
     * @param model
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("/saveEditSeedData")
    public String saveEditSeedData(ModelMap model, MultipartFile file, HttpServletRequest request, SeedPeopleVo seedPeopleVo) throws IOException {
        Active active = new Active();
        active.setActId(Long.parseLong(seedPeopleVo.getS_actId()));

        if (file!=null && file.getSize()>0) {//保存上传的种子用户文件
            String fileName = file.getOriginalFilename();//获取文件名称
            String extNameType = fileName.substring(fileName.lastIndexOf(".")+1);//获取文件的扩展名
            byte[] bytes = file.getBytes();//获取文件内容

            Attachment attachment = new Attachment();
            attachment.setAttachmentContent(bytes);
            attachment.setAttachmentName(fileName);
            attachment.setAttachmentUpdatetime(new Date());
            attachment.setAttachmentType(extNameType);

            if (StringUtils.isNotBlank(seedPeopleVo.getS_attachmentId())){
                attachment.setAttachmentId(Long.parseLong(seedPeopleVo.getS_attachmentId()));
                //根据主键id修改 附加表数据
                int rowNum = attachmentService.updateByPrimaryKeySelective(attachment);
            } else {
                attachment.setAttachmentCreatetime(new Date());
                //根据主键id修改 附加表数据
                int rowNum = attachmentService.insertSelective(attachment);
                active.setAttachmentIdImportFile(attachment.getAttachmentId());
            }
        }

        String[] years={seedPeopleVo.getS_startTime(),seedPeopleVo.getS_endTime()};
        if (StringUtils.isNotBlank(seedPeopleVo.getS_allPpArray())){
            String s_allPpArray = seedPeopleVo.getS_allPpArray();
            String yearsArray = JsonUtil.toJson(years);
            String ppId = seedPeopleVo.getS_ppIdArray();
            s_allPpArray = s_allPpArray + "_" + yearsArray + "_" + ppId;

            ActiveTagRelation activeTagRelation = new ActiveTagRelation();
            activeTagRelation.setActId(Long.parseLong(seedPeopleVo.getS_actId()));
            activeTagRelation.setSeedCategoryData(s_allPpArray);

            ActiveTagRelation activeTagRelationResult = activeTagRelationService.selectByactId(Long.parseLong(seedPeopleVo.getS_actId()));
            if (activeTagRelationResult!=null){
                activeTagRelationService.updateByIdSelective(activeTagRelation);
            } else {
                activeTagRelationService.insertSelective(activeTagRelation);
            }
        }

        Map<String,Object> params = new HashMap();
        params.put("cityids",seedPeopleVo.getS_storesId());
        params.put("ppids",seedPeopleVo.getS_ppIdArray());
        params.put("years",years);

        Map<String, Object> resultMap = activeEchoController.zhiSuanHttp(params);

        if (!resultMap.get("msg").equals("success")){
            model.addAttribute("isSelect",1);
            return PAGE_NEWACTIVITY;
        }

        String result = (String) resultMap.get("data");
        Map<String,Object> dataMap = JsonUtil.parseJson(result, Map.class);

        List<ZhiSuanVo> finalList = activeEchoController.countSeedNum(dataMap);

        int seedNum = 0;
        String userId = "";
        for (ZhiSuanVo zhiSuanVo : finalList) {
            if (!userId.equals(zhiSuanVo.getRowkey())){
                userId = zhiSuanVo.getRowkey();
                seedNum += 1;

                ActiveMemberRelation memberRelation = new ActiveMemberRelation();
                memberRelation.setActId(Long.parseLong(seedPeopleVo.getS_actId()));
                memberRelation.setCid(zhiSuanVo.getRowkey());

                ActiveMemberRelation activeMemberRelation = activeMemberRelationService.selectByActIdAndCid(Long.parseLong(seedPeopleVo.getS_actId()), zhiSuanVo.getRowkey());
                if (activeMemberRelation==null){
                    //保存 活动会员关联表数据
                    int insert = activeMemberRelationService.insert(memberRelation);
                }

                Member member = new Member();
                member.setCid(zhiSuanVo.getRowkey());
                member.setPhone(zhiSuanVo.getPhone());
                member.setAgeGroup(zhiSuanVo.getAge_group());
                member.setOpenid(zhiSuanVo.getOpenid());
                member.setSex(zhiSuanVo.getSex());
                member.setProvince(zhiSuanVo.getProvince());
                member.setMdh(zhiSuanVo.getCityId());

                List<Member>  memberResult = memberService.selectByPrimaryKey(zhiSuanVo.getRowkey());
                if (memberResult!=null && memberResult.size()>0){
                    int i = memberService.updateByCidSelective(member);
                } else {
                    int resultRowNum = memberService.insertSelective(member);
                }

                List<TradingData> tradingData = tradingDataService.selectByPrimaryKey(zhiSuanVo.getRowkey());
                if (tradingData!=null && tradingData.size()>0){
                    //先删除  再插入
                    int i = tradingDataService.deleteByCid(zhiSuanVo.getRowkey());
                }
            }

            TradingData data = new TradingData();
            data.setCid(zhiSuanVo.getRowkey());
            data.setPp(zhiSuanVo.getPpId());
            data.setStoreId(zhiSuanVo.getCityId());
            data.setWeekMoney(BigDecimal.valueOf( Double.parseDouble(zhiSuanVo.getWeekendsalemoney())));
            data.setWeekNo(Integer.parseInt(zhiSuanVo.getWeekenddays()));
            data.setWeekRate(Integer.parseInt(zhiSuanVo.getWeekendfrequency()));
            data.setYear(Integer.parseInt(zhiSuanVo.getYear()));

            int RowNum = tradingDataService.insertSelective(data);
        }

        //修改活动表中导入 种子文件 的字段 和 智算后的 种子数量
        active.setSeedNum(String.valueOf(seedNum));
        int updataResult = activeService.updateByPrimaryKeySelective(active);

        activeEchoController.selectAllCommon(seedPeopleVo.getS_storesId(), active.getActId(), model);

        model.addAttribute("isSelect",2);

        return PAGE_EDITACTIVE;
    }
}
