package cn.smart.dao;

import cn.smart.bo.VideoBO;
import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.VideoQueryCondition;
import cn.smart.entity.VideoDO;
import cn.smart.entity.VideoTypeRecordDO;
import org.springframework.stereotype.Repository;

/**
 * @author Ye
 * @date 2018-5-2 15:54:00
 */
@Repository
public interface IVideoMapper extends IBaseDao<VideoDO, VideoQueryCondition> {

    void removeVideoBatch(Long[] ids);

    void updateVideoIdBatch(VideoBO videoBO);

    void saveVideoTypeRecord(VideoTypeRecordDO videoTypeRecordDO);

    void updateVideoTypeRecord(VideoTypeRecordDO videoTypeRecordDO);
}
