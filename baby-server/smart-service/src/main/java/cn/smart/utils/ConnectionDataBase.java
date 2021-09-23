package cn.smart.utils;

import com.alibaba.druid.util.jdbc.ConnectionBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * 数据库连接类 
 * 说明:封装了 无参，有参，存储过程的调用 
 * @author zhangwenling 
 * 
 */  
public class ConnectionDataBase {
  
    /**
	 * jdbc.driverClassName=oracle.jdbc.driver.OracleDriver jdbc.url=jdbc:oracle:thin:@202.173.9.53:1521:ora9 jdbc.username=wlk_qyh
	 * jdbc.password=qyh_zz 数据库驱动类名称
	 */  
    private static final String DRIVER = "com.mysql.jdbc.Driver";
  
    /** 
     * 连接字符串 
     */  
    private String urlStr;
  
    /** 
     * 用户名 
     */  
    private String userName;
  
    /** 
     * 密码 
     */  
    private String password;
  
    /** 
     * 创建数据库连接对象 
     */  
    private Connection connnection = null;  
  
    /** 
     * 创建PreparedStatement对象 
     */  
    private PreparedStatement preparedStatement = null;  
      
    /** 
     * 创建CallableStatement对象 
     */  
    private CallableStatement callableStatement = null;  
  
    /** 
     * 创建结果集对象 
     */  
    private ResultSet resultSet = null;

    public ConnectionDataBase() {}

    public ConnectionDataBase(String urlStr, String userName, String password) {
        this.urlStr = urlStr;
        this.userName = userName;
        this.password = password;

        getConnection();
    }

    public void loadClass() {
        try {
            // 加载数据库驱动程序
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动错误");
            System.out.println(e.getMessage());
        }
    }

    /** 
     * 建立数据库连接 
     * @return 数据库连接 
     */  
    public Connection getConnection() {  
        try {  
            // 获取连接  
            connnection = DriverManager.getConnection(this.getUrlStr(), this.getUserName(),
                    this.getPassword());
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
        return connnection;  
    }  
  
    /** 
     * insert update delete SQL语句的执行的统一方法 
     * @param sql SQL语句 
     * @param params 参数数组，若没有参数则为null 
     * @return 受影响的行数 
     */  
    public int executeUpdate(String sql, Object[] params) {  
        // 受影响的行数  
        int affectedLine = 0;  
          
        try {  
            // 获得连接  
            connnection = this.getConnection();  
            // 调用SQL   
            preparedStatement = connnection.prepareStatement(sql);  
              
            // 参数赋值  
            if (params != null) {  
                for (int i = 0; i < params.length; i++) {  
                    preparedStatement.setObject(i + 1, params[i]);  
                }  
            }  
              
            // 执行  
            affectedLine = preparedStatement.executeUpdate();  
  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        } finally {  
            // 释放资源  
            closeAll();  
        }  
        return affectedLine;  
    }

    public int excuteUpdate(String sql, Object... params) {

        int result = 0;
        try {
            connnection = this.getConnection();

            preparedStatement = connnection.prepareStatement(sql);

            // 参数赋值
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    /** 
     * SQL 查询将查询结果直接放入ResultSet中 
     * @param sql SQL语句 
     * @param params 参数数组，若没有参数则为null 
     * @return 结果集 
     */  
    private ResultSet executeQueryRS(String sql, Object[] params) {  
        try {  
            // 获得连接  
            connnection = this.getConnection();  
              
            // 调用SQL  
            preparedStatement = connnection.prepareStatement(sql);  
              
            // 参数赋值  
            if (params != null) {  
                for (int i = 0; i < params.length; i++) {  
                    preparedStatement.setObject(i + 1, params[i]);  
                }  
            }  
              
            // 执行  
            resultSet = preparedStatement.executeQuery();  
  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }   
  
        return resultSet;  
    }  
      
    /** 
     * SQL 查询将查询结果：一行一列 
     * @param sql SQL语句 
     * @param params 参数数组，若没有参数则为null 
     * @return 结果集 
     */  
    public Object executeQuerySingle(String sql, Object[] params) {  
        Object object = null;  
        try {  
            // 获得连接  
            connnection = this.getConnection();  
              
            // 调用SQL  
            preparedStatement = connnection.prepareStatement(sql);  
              
            // 参数赋值  
            if (params != null) {  
                for (int i = 0; i < params.length; i++) {  
                    preparedStatement.setObject(i + 1, params[i]);  
                }  
            }  
              
            // 执行  
            resultSet = preparedStatement.executeQuery();  
  
            if(resultSet.next()) {  
                object = resultSet.getObject(1);  
            }  
              
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        } finally {  
            closeAll();  
        }  
  
        return object;  
    }


    /** 
     * 获取结果集，并将结果放在List中 
     *  
     * @param sql 
     *            SQL语句 
     * @return List 
     *                       结果集 
     */  
    public List<Object> excuteQuery(String sql, Object... params) {  
        // 执行SQL获得结果集  
        ResultSet rs = executeQueryRS(sql, params);  
          
        // 创建ResultSetMetaData对象  
        ResultSetMetaData rsmd = null;  
          
        // 结果集列数  
        int columnCount = 0;  
        try {  
            rsmd = rs.getMetaData();  
              
            // 获得结果集列数  
            columnCount = rsmd.getColumnCount();  
        } catch (SQLException e1) {  
            System.out.println(e1.getMessage());  
        }  
  
        // 创建List  
        List<Object> list = new ArrayList<>();
  
        try {  
            // 将ResultSet的结果保存到List中  
            while (rs.next()) {  
                Map<String, Object> map = new HashMap<>(16);
                for (int i = 1; i <= columnCount; i++) {  
                    map.put(rsmd.getColumnLabel(i), rs.getObject(i));  
                }  
                list.add(map);  
            }  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        } finally {  
            // 关闭所有资源  
            closeAll();  
        }  
        return list;
    }  
      
    /** 
     * 存储过程带有一个输出参数的方法 
     * @param sql 存储过程语句 
     * @param params 参数数组 
     * @param outParamPos 输出参数位置 
     * @param sqlType 输出参数类型
     * @return 输出参数的值 
     */  
    public Object excuteQuery(String sql, Object[] params,int outParamPos, int sqlType) {
        Object object = null;  
        connnection = this.getConnection();  
        try {  
            // 调用存储过程  
            callableStatement = connnection.prepareCall(sql);  
              
            // 给参数赋值  
            if(params != null) {  
                for(int i = 0; i < params.length; i++) {  
                    callableStatement.setObject(i + 1, params[i]);  
                }  
            }  
              
            // 注册输出参数  
            callableStatement.registerOutParameter(outParamPos, sqlType);
              
            // 执行  
            callableStatement.execute();  
              
            // 得到输出参数  
            object = callableStatement.getObject(outParamPos);  
              
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        } finally {  
            // 释放资源  
            closeAll();  
        }  
          
        return object;  
    }  
  
    /** 
     * 关闭所有资源 
     */  
    private void closeAll() {  
        // 关闭结果集对象  
        if (resultSet != null) {  
            try {  
                resultSet.close();  
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        }  
  
        // 关闭PreparedStatement对象  
        if (preparedStatement != null) {  
            try {  
                preparedStatement.close();  
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        }  
          
        // 关闭CallableStatement 对象  
        if (callableStatement != null) {  
            try {  
                callableStatement.close();  
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        }  
  
        // 关闭Connection 对象  
        if (connnection != null) {  
            try {  
                connnection.close();  
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        }     
    }

    public String getUrlStr() {
        return urlStr;
    }

    public void setUrlStr(String urlStr) {
        this.urlStr = urlStr;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}