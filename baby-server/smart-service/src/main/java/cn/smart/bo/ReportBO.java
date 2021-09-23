package cn.smart.bo;

import java.util.List;

public class ReportBO {

    private List<ReportDetailBO> reportDetail;

    private Integer score;

    public List<ReportDetailBO> getReportDetail() {
        return reportDetail;
    }

    public void setReportDetail(List<ReportDetailBO> reportDetail) {
        this.reportDetail = reportDetail;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "ReportBO{" +
                "reportDetail=" + reportDetail +
                ", score=" + score +
                '}';
    }
}
