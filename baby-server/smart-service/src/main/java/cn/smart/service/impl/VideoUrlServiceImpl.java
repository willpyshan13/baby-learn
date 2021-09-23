package cn.smart.service.impl;

import cn.smart.bo.VideoBO;
import cn.smart.common.utils.R;
import cn.smart.common.utils.Validator;
import cn.smart.condition.VideoUrlQueryCondition;
import cn.smart.dao.IVideoUrlMapper;
import cn.smart.entity.VideoUrlDO;
import cn.smart.service.IVideoUrlService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* @author Ye
* @date 2018-4-27 16:31:57
*/

@Service
public class VideoUrlServiceImpl  implements IVideoUrlService {

    private static final Logger logger = LogManager.getLogger(VideoUrlServiceImpl.class);

    @Autowired
    private IVideoUrlMapper videoUrlMapper;

    @Override
    public R deleteVideoBatch(List<VideoBO> videoBOList) {


        String[] suffix = new String[videoBOList.size()];
        Long[] ids = new Long[videoBOList.size()];

        List<String> errorName = new ArrayList<>();

        VideoBO video;
        for (int i = 0, len = videoBOList.size(); i < len; i++) {
            video = videoBOList.get(i);
            if (Validator.isNotEmpty(video.getVideoId())) {
                return R.error("存在被动画关联的视频, 请先移除关联关系");
            }
            if (!Validator.isEmpty(video.getUrl()) && video.getUrl().lastIndexOf("/") != -1) {
                suffix[i] = video.getUrl().substring(video.getUrl().lastIndexOf("/") + 1, video.getUrl().length());
                ids[i] = video.getId();
            } else {
                errorName.add(video.getName());
            }
        }
        // 删除七牛云数据
        /*QiniuRet qiniuRet = qiniuCloudStorageService.removeFileBatch(suffix);
        for (int i = 0, len = qiniuRet.getErrorList().size(); i < len; i++) {
            for (VideoBO aVideoBOList : videoBOList) {
                if (aVideoBOList.getUrl().contains((CharSequence) qiniuRet.getErrorList().get(i))) {
                    errorName.add(aVideoBOList.getName());
                }
            }
        }
        logger.info(JSONObject.toJSONString(qiniuRet));*/

        logger.info("error delete : " + JSONObject.toJSONString(errorName));

        // 删除本地数据
         deleteBatch(ids);
         return R.ok("删除成功").put("errorList", StringUtils.join(errorName, ","));
    }

    @Override
    public void save(VideoUrlDO videoUrlDO) {
        videoUrlMapper.save(videoUrlDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        videoUrlMapper.save(map);
    }

    @Override
    public void saveBatch(List<VideoUrlDO> list) {
        videoUrlMapper.saveBatch(list);
    }

    @Override
    public int update(VideoUrlDO videoUrlDO) {
        return videoUrlMapper.update(videoUrlDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return videoUrlMapper.update(map);
    }

    @Override
    public int deleteById(Object id) {
        return videoUrlMapper.deleteById(id);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return videoUrlMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return videoUrlMapper.deleteBatch(ids);
    }

    @Override
    public VideoUrlDO queryById(Object id) {
        return videoUrlMapper.queryById(id);
    }

    @Override
    public List<VideoUrlDO> queryList(VideoUrlQueryCondition videoUrlQueryCondition) {
        return videoUrlMapper.queryList(videoUrlQueryCondition);
    }

    @Override
    public int queryTotal(VideoUrlQueryCondition videoUrlQueryCondition) {
        return videoUrlMapper.queryTotal(videoUrlQueryCondition);
    }

    @Override
    public int total() {
        return videoUrlMapper.total();
    }

}
