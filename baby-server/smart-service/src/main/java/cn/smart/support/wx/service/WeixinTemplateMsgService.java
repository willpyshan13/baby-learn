package cn.smart.support.wx.service;

import cn.smart.entity.PersonDO;
import cn.smart.entity.WeekReportDO;
import cn.smart.service.IWeekReportService;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信模版消息发送业务
 * @author Ye
 */
@Service
public class WeixinTemplateMsgService {

    private static final Logger logger = LogManager.getLogger(WeixinTemplateMsgService.class);

    @Value("#{wxProperties.h5_report_url}")
    private String reportUrl;

    @Autowired
    private IWeekReportService weekReportService;

    @Autowired
    private WeixinService weixinService;

    /**
     * 发送周报模版
     * @param person 用户信息
     * @return
     */
    public String sendWeekReportTemplateMsg(PersonDO person) {

        WxMpTemplateMessage msg = new WxMpTemplateMessage();
        List<WxMpTemplateData> data = new ArrayList<>();

        WeekReportDO weekReport = weekReportService.queryById(person.getWeekReportId());
        data.add(new WxMpTemplateData("first", "您孩子本周的学习分析报告出炉啦"));
        data.add(new WxMpTemplateData("keyword1", person.getNickName()));
        data.add(new WxMpTemplateData("keyword2", "本周共学习了" + weekReport.getDays() + "天，共学习了" + weekReport.getWordsCount() + "个单词，快来看看吧！"));
        msg.setData(data);

        msg.setToUser(person.getWxMpId());
        msg.setUrl(reportUrl + weekReport.getId());
        msg.setTemplateId("QBrdVbTHLLTOSaksgL025VCzhci5_PCaI_THY7uP8DY");

        String result = null;
        try {
            result = weixinService.getTemplateMsgService().sendTemplateMsg(msg);
        } catch (WxErrorException e) {
            logger.error(e.getError());
            e.printStackTrace();
        }

        logger.info("/n推送周报 Message ID : {}", result);

        return result;
    }
}
