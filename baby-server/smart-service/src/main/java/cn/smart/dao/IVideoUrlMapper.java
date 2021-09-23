package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.VideoUrlQueryCondition;
import cn.smart.entity.VideoUrlDO;
import org.springframework.stereotype.Repository;

/**
 * @author Ye
 * @date 2018-4-27 16:31:57
 */
@Repository
public interface IVideoUrlMapper extends IBaseDao<VideoUrlDO, VideoUrlQueryCondition> {

}
