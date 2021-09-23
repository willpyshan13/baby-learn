package cn.smart.utils;

import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class syncDatabase {

    private String oriUrl = "jdbc:mysql://119.23.33.65:3306/smartclient?serverTimezone=UTC&useSSL=true";
    private String oriUserName = "smart";
    private String oriPassword = "123456";

//    private String destUrl = "jdbc:mysql://119.23.33.65:3306/smart_client_test?serverTimezone=UTC&useSSL=true";
//    private String destUserName = "smart";
//    private String destPassword = "123456";

//    private String oriUrl = "jdbc:mysql://localhost:3306/smartclient?serverTimezone=UTC&useSSL=true";
//    private String oriUserName = "root";
//    private String oriPassword = "root";

    private String destUrl = "jdbc:mysql://localhost:3306/smart_client_dev?serverTimezone=UTC&useSSL=true";
    private String destUserName = "root";
    private String destPassword = "root";

    private ConnectionDataBase origConnection;

    private ConnectionDataBase destConnection;

    syncDatabase() {
        origConnection = getConnection(oriUrl, oriUserName, oriPassword);
        destConnection = getConnection(destUrl, destUserName, destPassword);
    }


    public static void main(String[] arge) {
        syncDatabase s = new syncDatabase();

        s.syncCourseInfo();
    }

    private void syncCourseInfo() {

        String origSql = "SELECT l.*, ld.* FROM `LearnInfoEntity` l LEFT JOIN LearnDetailEntity ld ON ld.learnId = l.Id";
        origConnection.excuteQuery(origSql, new Object());

        List<Object> executeQuery = origConnection.excuteQuery(origSql, new Object());

        destConnection.excuteUpdate("truncate table game_info");

        int i = 1;

        String insertSql = "insert into course_info (`id`, `course_name`, `stage_id`, " +
                "`teacher_id`, `status`, `type`, `start_time`, `end_time`, `duration`, `serial_number`, " +
                "`viewing_authority`, `update_time`, `create_time`, `category`, `video_url`, " +
                "`image_url`, `tts_text`, `word` ) values ";

        StringBuilder params = new StringBuilder();

        for (Object object : executeQuery) {

            Date date = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.MINUTE, -executeQuery.size());
            c.add(Calendar.MINUTE, i++);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Map<String, String> m = (Map<String, String>) object;

            params.append("(")
                    .append("'").append(String.valueOf(m.get("Id")).replace("'","\\'")).append("'").append(",")
                    .append("'").append(String.valueOf(m.get("name")).replace("'", "\\'")).append("'").append(",")
                    .append("'").append("1").append(",")
                    .append("'").append("1").append(",")
                    .append("'").append("0").append(",")
                    .append("'").append(String.valueOf(m.get("status")).replace("'","\\'")).append("'").append(",")
                    .append("'").append(String.valueOf(m.get("startTime"))).append("'").append(",")
                    .append("'").append(String.valueOf(m.get("endTime"))).append("'").append(",")
                    .append("'").append("100").append(",")
                    .append("'").append(String.valueOf(m.get("type"))).append("'").append(",")
                    .append("'").append("0").append(",")
                    .append("'").append(String.valueOf(m.get("updateTime"))).append("'").append(",")
                    .append("'").append(String.valueOf(m.get("createTime"))).append("'").append(",")
                    .append("'").append(String.valueOf(m.get("category"))).append("'").append(",")
                    .append("'").append(String.valueOf(m.get("path"))).append("'").append(",")
                    .append("'").append(String.valueOf(m.get("imagePath"))).append("'").append(",")
                    .append("'").append(String.valueOf(m.get("ttsText"))).append("'").append(",")
                    .append("'").append(String.valueOf(m.get("word"))).append("'").append(",")
                    .append(")");

            params.append(",");
        }

        String p = params.toString();

        insertSql += p.substring(0, p.lastIndexOf(","));

        System.out.println(insertSql);

        destConnection.excuteUpdate(insertSql);



    }

    /**
     * 同步 游戏
     */
    private void syncGame() {

        String origSql = "select * from LearnGameInfoEntity";
        origConnection.excuteQuery(origSql, new Object());

        List<Object> executeQuery = origConnection.excuteQuery(origSql, new Object());

        destConnection.excuteUpdate("truncate table game_info");

        int i = 1;

        String insertSql = "insert into game_info (`id`, `name`, `info`, `image_url`, `resource_url`, `status`, `type`, `serial_number`, `create_time`, `update_time` ) values ";

        StringBuilder params = new StringBuilder();

        for (Object object : executeQuery) {

            Date date = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.MINUTE, -executeQuery.size());
            c.add(Calendar.MINUTE, i++);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Map<String, String> m = (Map<String, String>) object;

            // INSERT INTO `game_info` (`id`, `name`, `info`, `image_url`, `resource_url`, `status`,
            // `type`, `serial_number`, `create_time`, `update_time` ) SELECT `infoId`, `gameName`,
            // `gameInfo`, `imagePath`, `mp3Path`, `status`, `type`, NULL, `createTime`, `updateTime`
            // FROM `smartclient`.`LearnGameInfoEntity`
            params.append("(")
                    .append("'").append(String.valueOf(m.get("infoId")).replace("'","\\'")).append("'").append(",")
                    .append("'").append(String.valueOf(m.get("gameName")).replace("'", "\\'")).append("'").append(",")
                    .append("'").append(String.valueOf(m.get("gameInfo")).replace("'","\\'")).append("'").append(",")
                    .append("'").append(String.valueOf(m.get("imagePath"))).append("'").append(",")
                    .append("'").append(String.valueOf(m.get("mp3Path"))).append("'").append(",")
                    .append("'").append(String.valueOf(m.get("status"))).append("'").append(",")
                    .append("'").append(String.valueOf(m.get("type"))).append("'").append(",")
                    .append("NULL").append(",")
                    .append("'").append(sdf.format(c.getTime())).append("'").append(",")
                    .append("'").append(String.valueOf(m.get("updateTime"))).append("'")
                    .append(")");

            params.append(",");
        }

        String p = params.toString();

        insertSql += p.substring(0, p.lastIndexOf(","));

        System.out.println(insertSql);

        destConnection.excuteUpdate(insertSql);


    }

    /**
     * 同步 课程游戏
     */
    private void syncCourseGame() {

        String origSql = "select * from LearnGameEntity";
        origConnection.excuteQuery(origSql, new Object());

        List<Object> executeQuery = origConnection.excuteQuery(origSql, new Object());

        destConnection.excuteUpdate("truncate table course_game");

        int i = 1;

        String insertSql = "insert into course_game (id, course_info_id, game_info_id, create_time) values ";

        StringBuilder params = new StringBuilder();

        for (Object object : executeQuery) {

            Date date = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.MINUTE, -executeQuery.size());
            c.add(Calendar.MINUTE, i++);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Map<String, String> m = (Map<String, String>) object;

            params.append("(")
                    .append(String.valueOf(m.get("Id")))
                    .append(",")
                    .append(String.valueOf(m.get("learnId")))
                    .append(",")
                    .append(String.valueOf(m.get("gameId")))
                    .append(",")
                    .append("'")
                    .append(sdf.format(c.getTime()))
                    .append("'")
                    .append(")");

            params.append(",");
        }

        String p = params.toString();

        insertSql += p.substring(0, p.lastIndexOf(","));

        System.out.println(insertSql);

        destConnection.excuteUpdate(insertSql);


    }

    /**
     *
     * @param url jdbc:mysql://localhost:3306/smartclient?serverTimezone=UTC
     * @param userName root
     * @param password root
     * @return
     */
    public ConnectionDataBase getConnection(String url, String userName, String password) {
        return new ConnectionDataBase(url, userName, password);
    }


}
