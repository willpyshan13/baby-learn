package cn.smart.controller.mobile;

import cn.smart.common.annotation.AuthIgnore;
import cn.smart.common.annotation.LoginUser;
import cn.smart.common.api.result.ApiPageResult;
import cn.smart.common.api.result.ApiResult;
import cn.smart.common.utils.Validator;
import cn.smart.bo.VideoDetailBO;
import cn.smart.bo.VideoUrlBO;
import cn.smart.condition.VideoQueryCondition;
import cn.smart.condition.VideoTypeQueryCondition;
import cn.smart.condition.VideoUrlQueryCondition;
import cn.smart.entity.PersonDO;
import cn.smart.entity.VideoDO;
import cn.smart.entity.VideoTypeDO;
import cn.smart.entity.VideoUrlDO;
import cn.smart.service.IVideoService;
import cn.smart.service.IVideoUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mobile/video/")
public class ApiVideoController {

    @Autowired
    private IVideoService videoService;

    @Autowired
    private IVideoUrlService videoUrlService;

    @AuthIgnore
    @GetMapping("/typelist")
    public ApiPageResult getVideoTypeList(@RequestParam(value = "currentPage", required = false, defaultValue = "1") Integer currentPage,
                                          @RequestParam(value = "pageSize", required = false, defaultValue = "9") Integer pageSize) {

        VideoTypeQueryCondition videoTypeQueryCondition = new VideoTypeQueryCondition();
        videoTypeQueryCondition.setTypeParent(1L);
        videoTypeQueryCondition.setPage(currentPage);
        videoTypeQueryCondition.setLimit(pageSize);
        List<VideoTypeDO> videoTypeDOList = videoService.queryVideoType(videoTypeQueryCondition);
        int total = videoService.queryVideoTypeTotal(videoTypeQueryCondition);

        return new ApiPageResult(videoTypeDOList, currentPage, pageSize, total);
    }

    @AuthIgnore
    @GetMapping("/listbytype")
    public ApiResult getVideoList(@RequestParam(value = "labelid") Long labelId,
                                  @RequestParam(value = "currentPage", required = false, defaultValue = "1") Integer currentPage,
                                  @RequestParam(value = "pageSize", required = false, defaultValue = "9") Integer pageSize) {

        VideoQueryCondition videoQueryCondition = new VideoQueryCondition();
        videoQueryCondition.setTypeId(labelId);
        videoQueryCondition.setPage(currentPage);
        videoQueryCondition.setLimit(pageSize);
        List<VideoDO> videoList = videoService.queryList(videoQueryCondition);

        return new ApiResult(videoList);
    }

    @AuthIgnore
    @GetMapping("/detail")
    public ApiPageResult getVideoDetail(@LoginUser PersonDO personDO,
            @RequestParam(value = "token", required = false) String token,
            @RequestParam(value = "videoId") Long videoId) {

        VideoQueryCondition videoQueryCondition = new VideoQueryCondition();
        videoQueryCondition.setVideoId(videoId);
        VideoDO video = videoService.queryById(videoId);
        if (Validator.isEmpty(video)) {
            return new ApiPageResult("查无此视频记录");
        } else {
            VideoDetailBO videoDetailBO = new VideoDetailBO();
            videoDetailBO.setVideoId(video.getVideoId());
            videoDetailBO.setVideoActor(video.getVideoActor());
            videoDetailBO.setVideoDesc(video.getVideoDesc());
            videoDetailBO.setVideoDirector(video.getVideoDirector());
            videoDetailBO.setVideoPreviewPicUrl(video.getVideoPreviewPicUrl());
            videoDetailBO.setVideoTitle(video.getVideoTitle());
            videoDetailBO.setVideoDesc(video.getVideoDesc());
            videoDetailBO.setCollect(false);
            videoDetailBO.setPraised(false);


            // 剧集信息
            VideoUrlQueryCondition videoUrlQueryCondition = new VideoUrlQueryCondition();
            videoUrlQueryCondition.setVideoId(videoId);
            videoUrlQueryCondition.setOrderField("video_url_index");
            List<VideoUrlDO> list = videoUrlService.queryList(videoUrlQueryCondition);
            List<VideoUrlBO> urlList = new ArrayList<>(list.size());
            VideoUrlBO videoUrlBO;
            VideoUrlDO videoUrlDO;
            for(int i = 0, len = list.size(); i < len; i++) {
                videoUrlDO = list.get(i);
                videoUrlBO = new VideoUrlBO();
                videoUrlBO.setVideoThumbmailUrl(videoUrlDO.getVideoPlayUrl());
                videoUrlBO.setVideoTitle(videoUrlDO.getVideoUrlDesc());
                videoUrlBO.setVideoPlayUrl(videoUrlDO.getVideoWebUrl());
                videoUrlBO.setVideoUrlIndex(videoUrlDO.getVideoUrlIndex());
                urlList.add(videoUrlBO);
            }
            videoDetailBO.setList(urlList);

            if (!Validator.isEmpty(personDO)) {

            }
            return new ApiPageResult(videoDetailBO);
        }


    }
}
