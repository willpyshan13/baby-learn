package cn.smart.service;

import cn.smart.common.utils.R;
import cn.smart.entity.PushLogDO;

/**
 * @author Ye
 */
public interface IPushService {

    /**
     * 推送课程直播开始消息
     * @param classId 班级id
     * @return
     */
    R pushStartLearningMsg(Long classId);

    /**
     * 推送登出消息
     *
     * @param personId
     * @param pushAlias 推送设别别名
     * @return
     */
    Boolean pushLoginOutMsg(Long personId, String pushAlias);

    /**
     * 推送更新 tag 标签
     * @param personIds
     * @return
     */
    void pushUpdateTag(Long[] personIds);

    R pushMessage(PushLogDO push);

    R pushVoiceMessage(Long personId, String title, String message, String url);
}
