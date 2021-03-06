package com.wdzsj.cmn.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class JdbcUtil {
	
	// 表示定义数据库的用户名  
    private String USERNAME ;  
  
    // 定义数据库的密码  
    private String PASSWORD;  
  
    // 定义数据库的驱动信息  
    private String DRIVER;  
  
    // 定义访问数据库的地址  
    private String URL;  
  
    // 定义数据库的链接  
    private Connection connection;  
  
    // 定义sql语句的执行对象  
    private PreparedStatement pstmt;  
  
    // 定义查询返回的结果集合  
    private ResultSet resultSet;  
    
  
    public JdbcUtil() {  
  
    }
    
    public static JdbcUtil getInstance(){
    	return new JdbcUtil();
    }
    
    
  
     
	public Connection getMySQLConnection(String ip,String port,String dbName,String userName,String passWord) {
    	if(StringUtils.isNotBlank(ip) && StringUtils.isNotBlank(port) 
    			&& StringUtils.isNotBlank(dbName) && StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(userName)){
    		try {
    			this.DRIVER = "com.mysql.jdbc.Driver";
				this.URL = "jdbc:mysql://"+ip+":"+port+"/"+dbName+"?useUnicode=true&characterEncoding=UTF-8";
    			this.USERNAME = userName;
    			this.PASSWORD = passWord;
                Class.forName(DRIVER); // 注册驱动  
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); // 获取连接  
            } catch (Exception e) {  
                throw new RuntimeException("get connection error!", e);
            } 
    	}
        return connection;  
    }
	
	
	
	public Connection getPostgreSQLConnection(String ip,String port,String dbName,String userName,String passWord) {
    	if(StringUtils.isNotBlank(ip) && StringUtils.isNotBlank(port) 
    			&& StringUtils.isNotBlank(dbName) && StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(userName)){
    		try {
    			this.DRIVER = "org.postgresql.Driver";
				this.URL = "jdbc:postgresql://"+ip+":"+port+"/"+dbName;
    			this.USERNAME = userName;
    			this.PASSWORD = passWord;
                Class.forName(DRIVER); // 注册驱动  
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); // 获取连接  
            } catch (Exception e) {  
                throw new RuntimeException("get connection error!", e);
            } 
    	}
        return connection;  
    }
	
	
	
	public Connection getHiveConnection(String ip,String port,String dbName,String userName,String passWord) {
    	if(StringUtils.isNotBlank(ip) && StringUtils.isNotBlank(port) 
    			&& StringUtils.isNotBlank(dbName) && StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(userName)){
    		try {
    			this.DRIVER = "org.apache.hadoop.hive.jdbc.HiveDriver";
				this.URL = "jdbc:hive://"+ip+":"+port+"/"+dbName;
    			this.USERNAME = userName;
    			this.PASSWORD = passWord;
                Class.forName(DRIVER); // 注册驱动  
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); // 获取连接  
            } catch (Exception e) {  
                throw new RuntimeException("get connection error!", e);
            } 
    	}
        return connection;  
    }
  
    /** 
     * 执行更新操作 
     *  
     * @param sql 
     *            sql语句 
     * @param params 
     *            执行参数 
     * @return 执行结果 
     * @throws SQLException 
     */  
    public boolean updateByPreparedStatement(String sql, List<?> params)  
            throws SQLException {  
        boolean flag = false;  
        int result = -1;// 表示当用户执行添加删除和修改的时候所影响数据库的行数  
        pstmt = connection.prepareStatement(sql);  
        int index = 1;  
        // 填充sql语句中的占位符  
        if (params != null && !params.isEmpty()) {  
            for (int i = 0; i < params.size(); i++) {  
                pstmt.setObject(index++, params.get(i));  
            }  
        }  
        result = pstmt.executeUpdate();  
        flag = result > 0 ? true : false;  
        return flag;  
    }  
  
    /** 
     * 执行查询操作 
     *  
     * @param sql 
     *            sql语句 
     * @param params 
     *            执行参数 
     * @return 
     * @throws SQLException 
     */  
    public List<Map<String, Object>> findResult(String sql, List<?> params)  
            throws SQLException {  
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();  
        int index = 1;  
        pstmt = connection.prepareStatement(sql);  
        if (params != null && !params.isEmpty()) {  
            for (int i = 0; i < params.size(); i++) {  
                pstmt.setObject(index++, params.get(i));  
            }  
        }  
        resultSet = pstmt.executeQuery();  
        ResultSetMetaData metaData = resultSet.getMetaData();  
        int cols_len = metaData.getColumnCount();  
        while (resultSet.next()) {  
            Map<String, Object> map = new HashMap<String, Object>();  
            for (int i = 0; i < cols_len; i++) {  
                String cols_name = metaData.getColumnName(i + 1);  
                Object cols_value = resultSet.getObject(cols_name);  
                if (cols_value == null) {  
                    cols_value = "";  
                }  
                map.put(cols_name, cols_value);  
            }  
            list.add(map);  
        }  
        return list;  
    }  
  
    /** 
     * 释放资源 
     */  
    public void releaseConn() {  
        if (resultSet != null) {  
            try {  
                resultSet.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
        if (pstmt != null) {  
            try {  
                pstmt.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
        if (connection != null) {  
            try {  
                connection.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
    } 
	
}
