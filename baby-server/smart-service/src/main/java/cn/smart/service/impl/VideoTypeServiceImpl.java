package cn.smart.service.impl;

import cn.smart.condition.VideoTypeQueryCondition;
import cn.smart.dao.IVideoTypeMapper;
import cn.smart.entity.VideoTypeDO;
import cn.smart.service.IVideoTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author Ye
* @date 2018-5-16 18:25:05
*/

@Service
public class VideoTypeServiceImpl  implements IVideoTypeService {

    @Autowired
    private IVideoTypeMapper videoTypeMapper;

    @Override
    public void save(VideoTypeDO videoTypeDO) {
        videoTypeMapper.save(videoTypeDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        videoTypeMapper.save(map);
    }

    @Override
    public void saveBatch(List<VideoTypeDO> list) {
        videoTypeMapper.saveBatch(list);
    }

    @Override
    public int update(VideoTypeDO videoTypeDO) {
        return videoTypeMapper.update(videoTypeDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return videoTypeMapper.update(map);
    }

    @Override
    public int deleteById(Object id) {
        return videoTypeMapper.deleteById(id);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return videoTypeMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return videoTypeMapper.deleteBatch(ids);
    }

    @Override
    public VideoTypeDO queryById(Object id) {
        return videoTypeMapper.queryById(id);
    }

    @Override
    public List<VideoTypeDO> queryList(VideoTypeQueryCondition videoTypeQueryCondition) {
        return videoTypeMapper.queryList(videoTypeQueryCondition);
    }

    @Override
    public int queryTotal(VideoTypeQueryCondition videoTypeQueryCondition) {
        return videoTypeMapper.queryTotal(videoTypeQueryCondition);
    }

    @Override
    public int total() {
        return videoTypeMapper.total();
    }
}
