package cn.smart.bo;

public class ClockRecordDetailBO {

    /**
     * 打卡日期
     */
    private String date;

    /**
     * 是否打卡
     */
    private Boolean clockIn;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getClockIn() {
        return clockIn;
    }

    public void setClockIn(Boolean clockIn) {
        this.clockIn = clockIn;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"date\":\"")
                .append(date).append('\"');
        sb.append(",\"clockIn\":")
                .append(clockIn);
        sb.append('}');
        return sb.toString();
    }
}
