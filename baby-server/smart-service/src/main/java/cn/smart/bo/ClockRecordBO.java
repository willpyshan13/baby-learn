package cn.smart.bo;

import java.util.List;

public class ClockRecordBO {

    /**
     * 打卡次数
     */
    private Integer sum;

    /**
     * 当前是否打卡
     */
    private Boolean clockIn;

    /**
     * 近期打卡记录
     */
    private List<ClockRecordDetailBO> recordList;

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public List<ClockRecordDetailBO> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<ClockRecordDetailBO> recordList) {
        this.recordList = recordList;
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
        sb.append("\"sum\":")
                .append(sum);
        sb.append(",\"clockIn\":")
                .append(clockIn);
        sb.append(",\"recordList\":")
                .append(recordList);
        sb.append('}');
        return sb.toString();
    }
}
