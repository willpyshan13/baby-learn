package cn.smart.service;

import cn.smart.bo.WeekReportBO;
import cn.smart.common.utils.R;
import cn.smart.entity.WeekReportDO;

public interface IReportService {

    public R generateWeekReport(Long classId, Long[] courseIds);

    public WeekReportDO generateWeekReport(Long classId, Long[] courseIds, Long personId);

    WeekReportBO queryWeekReportById(Long weekReportId);
}
