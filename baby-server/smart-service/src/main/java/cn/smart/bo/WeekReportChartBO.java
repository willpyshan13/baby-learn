package cn.smart.bo;

import java.util.List;

public class WeekReportChartBO {

    private List<String> xAxis;

    private List<Double> data;

    public List<String> getxAxis() {
        return xAxis;
    }

    public void setxAxis(List<String> xAxis) {
        this.xAxis = xAxis;
    }

    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }
}
