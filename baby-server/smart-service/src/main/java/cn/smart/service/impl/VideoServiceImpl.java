package cn.smart.service.impl;

import cn.smart.bo.VideoBO;
import cn.smart.bo.VideoUrlBO;
import cn.smart.common.utils.Validator;
import cn.smart.condition.VideoQueryCondition;
import cn.smart.condition.VideoTypeQueryCondition;
import cn.smart.condition.VideoUrlQueryCondition;
import cn.smart.dao.IVideoMapper;
import cn.smart.dao.IVideoTypeMapper;
import cn.smart.dao.IVideoUrlMapper;
import cn.smart.entity.VideoDO;
import cn.smart.entity.VideoTypeDO;
import cn.smart.entity.VideoTypeRecordDO;
import cn.smart.entity.VideoUrlDO;
import cn.smart.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
* @author Ye
* @date 2018-5-2 15:54:00
*/

@Service
public class VideoServiceImpl  implements IVideoService {

    @Autowired
    private IVideoMapper videoMapper;

    @Autowired
    private IVideoTypeMapper videoTypeMapper;

    @Autowired
    private IVideoUrlMapper videoUrlMapper;


    public void removeVideoBatch(Long[] ids) {
        videoMapper.removeVideoBatch(ids);
    }

    public void updateVideoIdBatch(VideoBO videoBO) {
        videoMapper.updateVideoIdBatch(videoBO);
    }

    @Override
    public List<VideoTypeDO> queryVideoType(VideoTypeQueryCondition videoTypeQueryCondition) {
        return videoTypeMapper.queryList(videoTypeQueryCondition);
    }

    @Override
    public int queryVideoTypeTotal(VideoTypeQueryCondition videoTypeQueryCondition) {
        return videoTypeMapper.queryTotal(videoTypeQueryCondition);
    }

    @Override
    public void saveVideo(VideoDO videoDO) {
        save(videoDO);

        if (Validator.isNotEmpty(videoDO.getTypeId())) {
            VideoTypeRecordDO videoTypeRecordDO = new VideoTypeRecordDO();
            videoTypeRecordDO.setTypeId(videoDO.getTypeId());
            videoTypeRecordDO.setVideoId(videoDO.getVideoId());
            videoMapper.saveVideoTypeRecord(videoTypeRecordDO);
        }
    }

    @Override
    public void updateVideo(VideoDO videoDO) {
        update(videoDO);

        if (Validator.isNotEmpty(videoDO.getTypeRecordId())) {
            VideoTypeRecordDO videoTypeRecordDO = new VideoTypeRecordDO();
            videoTypeRecordDO.setTypeRecordId(videoDO.getTypeRecordId());
            videoTypeRecordDO.setTypeId(videoDO.getTypeId());
            videoMapper.updateVideoTypeRecord(videoTypeRecordDO);
        }

    }

    @Override
    public Map<String, List<VideoUrlBO>> queryAudioList(VideoTypeQueryCondition videoTypeQueryCondition) {

        Map<String, List<VideoUrlBO>> mapAudio = new HashMap<>(16);

        List<VideoTypeDO> listVideoType = videoTypeMapper.queryList(videoTypeQueryCondition);
        for (VideoTypeDO videoType : listVideoType) {

            VideoQueryCondition videoQuery = new VideoQueryCondition();
            videoQuery.setTypeId(videoType.getTypeId());
            List<VideoDO> listVideo = videoMapper.queryList(videoQuery);

            for (VideoDO v : listVideo) {

                VideoUrlQueryCondition videoUrlQuery = new VideoUrlQueryCondition();
                videoUrlQuery.setVideoId(v.getVideoId());
                List<VideoUrlDO> listAudio = videoUrlMapper.queryList(videoUrlQuery);
                // 打乱数组
                Collections.shuffle(listAudio);

                List<VideoUrlBO> urlList = new ArrayList<>(listAudio.size());
                // 随机取10条
                int len = listAudio.size() >= 10? 10 : listAudio.size();
                VideoUrlBO audioBO;
                for(int i = 0; i < len; i++) {
                    VideoUrlDO audio = listAudio.get(i);
                    audioBO = new VideoUrlBO();
                    audioBO.setVideoThumbmailUrl(audio.getVideoPlayUrl());
                    audioBO.setVideoTitle(audio.getVideoUrlDesc());
                    audioBO.setVideoPlayUrl(audio.getVideoWebUrl());
                    audioBO.setVideoUrlIndex(audio.getVideoUrlIndex());
                    urlList.add(audioBO);
                }
                mapAudio.put(videoType.getTypeName(), urlList);
            }
        }

        return mapAudio;
    }

    @Override
    public void save(VideoDO videoDO) {
        videoMapper.save(videoDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        videoMapper.save(map);
    }

    @Override
    public void saveBatch(List<VideoDO> list) {
        videoMapper.saveBatch(list);
    }

    @Override
    public int update(VideoDO videoDO) {
        return videoMapper.update(videoDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return videoMapper.update(map);
    }

    @Override
    public int deleteById(Object id) {
        return videoMapper.deleteById(id);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return videoMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return videoMapper.deleteBatch(ids);
    }

    @Override
    public VideoDO queryById(Object id) {
        return videoMapper.queryById(id);
    }

    @Override
    public List<VideoDO> queryList(VideoQueryCondition videoQueryCondition) {
        return videoMapper.queryList(videoQueryCondition);
    }

    @Override
    public int queryTotal(VideoQueryCondition videoQueryCondition) {
        return videoMapper.queryTotal(videoQueryCondition);
    }

    @Override
    public int total() {
        return videoMapper.total();
    }
}
