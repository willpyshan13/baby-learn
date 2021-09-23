package cn.smart.service;

import cn.smart.bo.VideoBO;
import cn.smart.common.base.service.ICrudService;
import cn.smart.common.utils.R;
import cn.smart.condition.VideoUrlQueryCondition;
import cn.smart.entity.VideoUrlDO;

import java.util.List;

/**
* @author Ye
* @date 2018-4-27 16:31:57
*/
public interface IVideoUrlService extends ICrudService<VideoUrlDO, VideoUrlQueryCondition> {

    R deleteVideoBatch(List<VideoBO> videoBOList);

}