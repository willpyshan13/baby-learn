package cn.smart.service;

import cn.smart.bo.VideoBO;
import cn.smart.bo.VideoUrlBO;
import cn.smart.common.base.service.ICrudService;
import cn.smart.condition.VideoQueryCondition;
import cn.smart.condition.VideoTypeQueryCondition;
import cn.smart.entity.VideoDO;
import cn.smart.entity.VideoTypeDO;

import java.util.List;
import java.util.Map;

/**
* @author Ye
* @date 2018-5-2 15:54:00
*/
public interface IVideoService extends ICrudService<VideoDO, VideoQueryCondition> {

    void removeVideoBatch(Long[] ids);

    void updateVideoIdBatch(VideoBO videoBO);

    List<VideoTypeDO> queryVideoType(VideoTypeQueryCondition videoTypeQueryCondition);

    int queryVideoTypeTotal(VideoTypeQueryCondition videoTypeQueryCondition);

    void saveVideo(VideoDO videoDO);

    void updateVideo(VideoDO videoDO);

    /**
     * 获取每种音频分类下 最多10个音频
     * @param videoTypeQueryCondition
     * @return
     */
    Map<String, List<VideoUrlBO>> queryAudioList(VideoTypeQueryCondition videoTypeQueryCondition);
}