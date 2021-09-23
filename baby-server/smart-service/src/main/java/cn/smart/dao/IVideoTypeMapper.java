package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.VideoTypeQueryCondition;
import cn.smart.entity.VideoTypeDO;
import org.springframework.stereotype.Repository;

/**
 * @author Ye
 * @date 2018-6-4 10:33:51
 */
@Repository
public interface IVideoTypeMapper extends IBaseDao<VideoTypeDO, VideoTypeQueryCondition> {

}
