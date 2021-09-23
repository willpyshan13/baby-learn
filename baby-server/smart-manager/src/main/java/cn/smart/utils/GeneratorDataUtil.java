package cn.smart.utils;

import cn.smart.bo.ReportDetailBO;
import cn.smart.common.utils.DateUtil;
import cn.smart.entity.IntegralDO;
import cn.smart.entity.PersonLearnRecordDO;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class GeneratorDataUtil {

    private static Logger log = LogManager.getLogger(GeneratorDataUtil.class);

    /**
     * jdbc.driverClassName=oracle.jdbc.driver.OracleDriver jdbc.url=jdbc:oracle:thin:@202.173.9.53:1521:ora9 jdbc.username=wlk_qyh
     * jdbc.password=qyh_zz 数据库驱动类名称
     */
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    /**
     * 连接字符串
     */
//    private static final String URLSTR = "jdbc:mysql://47.99.46.250:3306/smart_client?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai";
//    private static final String URLSTR = "jdbc:mysql://192.168.20.90:3306/smart_client_dev?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai";
    private static final String URLSTR = "jdbc:mysql://192.168.20.90:3306/smart_client_test?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai";
//    private static final String URLSTR = "jdbc:mysql://118.25.11.31:3306/smart_client_dev?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai";

    /**
     * 用户名
     */
//    private static final String USERNAME = "root";
    private static final String USERNAME = "smart";
//    private static final String USERNAME = "alex";

    /**
     * 密码
     */
//    private static final String USERPASSWORD = "e9sRGaz0ei#9";
    private static final String USERPASSWORD = "123456";
//    private static final String USERPASSWORD = "Alex1103!";

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

    public static void main(String[] args) {

        // 加载数据库驱动程序
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        generateCourseInfoWeeks();


    }


    /**
     * 补充生成课程weeks 字段信息
     */
    @SuppressWarnings("unchecked")
    private static void generateCourseInfoWeeks() {
        GeneratorDataUtil t = new GeneratorDataUtil();

        String q = "select * from course_stage where id = 1";
        List<Object> courseStageList = t.excuteQuery(q, null);
        for (Object stage : courseStageList) {
            Map<String, Object> stageObject = (Map<String, Object>) stage;
            System.out.println(stageObject.get("id"));

            String sql = "SELECT * FROM `course_info` where stage_id = " + stageObject.get("id") + " ORDER BY serial_number";

            List<Object> courseinfoList = t.excuteQuery(sql, null);

            int i = 1;
            int j = 1;
            for (Object course : courseinfoList) {
                Map<String, Object> o = (Map<String, Object>) course;
                String u = "update course_info set weeks = " + j + " where id = " + o.get("id");
                t.executeUpdate(u, null);
                if (i%5 == 0) {
                    j++;
                    if (j==4) {
                        j = 1;
                    }
                }
                i++;
            }

        }
    }

    private static void runMethods(GeneratorDataUtil t, Long id ) {
        String sql = "select * FROM person_learn_record where person_id = " + id + " and type != 1 and result_data is not null";
        List<Object> st = t.excuteQuery(sql, null);


        StringBuilder integralSql = new StringBuilder();
        integralSql.append("INSERT INTO `integral`(`person_id`, `number`, `type`, `source`, `create_date`, `create_time`, `course_id`, `learn_record_id`, `exchange_detail_id`) VALUES ");

        int count = 0;

        log.info("length ................> {}", st.size());

        int k = st.size();
        for(Object p : st) {
            Map<String, Object> o = (Map<String, Object>) p;
            log.info(" Object ===========>  {} ", o);
            for (Map.Entry<String, Object> entry : o.entrySet()) {
                if (entry.getKey().equals("result_data")) {
                    if (entry.getValue() != null) {
                        ReportDetailBO json = JSONObject.parseObject(entry.getValue().toString(), ReportDetailBO.class);

                        IntegralDO integral = new IntegralDO();
                        integral.setNumber(json.getScore());
                        integral.setPersonId((Long) o.get("person_id"));
                        integral.setCourseId((Long) o.get("course_id"));
                        integral.setType(2);
                        integral.setLearnRecordId((Long) o.get("record_id"));
                        integral.setCreateDate(DateUtil.format(new Date()));
                        // 1:玩游戏初始积分 2:玩游戏加分 3:玩游戏复习积分 4:大声说初始积分 5:大声说加分 6:大声说复习积分 7:打卡积分 8 : 视频观看时长积分 9 : 兑换物品积分
                        if (2 == (int)o.get("type")) {
                            integral.setSource(1);
                        } else if (3 == (int)o.get("type")) {
                            integral.setSource(4);
                        }

                        integralSql.append("(");
                                integralSql.append(integral.getPersonId());
                        integralSql.append(",");
                                integralSql.append(integral.getNumber());
                        integralSql.append(",");
                                integralSql.append(integral.getType());
                        integralSql.append(",");
                                integralSql.append(integral.getSource());
                        integralSql.append(",");

                        integralSql.append("'").append(DateUtil.format(o.get("update_time"))).append("'");
                        integralSql.append(",");

                        String d = DateUtil.format(o.get("update_time"), "yyyy-MM-dd HH:mm:ss");
                        integralSql.append("'").append(d).append("'");

                        integralSql.append(",");
                                integralSql.append(integral.getCourseId());
                        integralSql.append(",");
                                integralSql.append(integral.getLearnRecordId());
                        integralSql.append(",");
                                integralSql.append(integral.getExchangeDetailId());
                        integralSql.append(")");

                        if (k-- > 1) {
                            integralSql.append(",");
                        }

                        count += integral.getNumber();

                        log.info(" count ++++++++++++++> {}", count);
                    }
                }
            }

        }
        if (st.size() > 0) {
            log.info(" insert sql >>>>>>>>>>>> {} ", integralSql);
            String personSql = ("UPDATE `smart_client`.`person` SET `candy_points` = "+ count +" WHERE `person_id` = " + id);
            log.info(" person sql >>>>>>>>>>>> {}", personSql);

//            t.executeUpdate(integralSql.toString(), null);
//            t.executeUpdate(personSql, null);

        } else {
            log.info("insert sql >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> {}", id);
        }

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
     * 建立数据库连接
     * @return 数据库连接
     */
    public Connection getConnection() {
        try {
            // 获取连接
            connnection = DriverManager.getConnection(URLSTR, USERNAME,
                    USERPASSWORD);
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


}
