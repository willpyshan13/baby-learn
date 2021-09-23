package cn.smart.controller.mobile;

import cn.smart.bo.VideoUrlBO;
import cn.smart.common.annotation.AuthIgnore;
import cn.smart.common.api.result.ApiPageResult;
import cn.smart.condition.VideoTypeQueryCondition;
import cn.smart.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mobile/audio/")
public class ApiAudioController {

    @Autowired
    private IVideoService videoService;


    @AuthIgnore
    @GetMapping("/list")
    public ApiPageResult getAudioList(@RequestParam(value = "currentPage", required = false, defaultValue = "1") Integer currentPage,
                                      @RequestParam(value = "pageSize", required = false, defaultValue = "9") Integer pageSize) {

        VideoTypeQueryCondition videoTypeQueryCondition = new VideoTypeQueryCondition();
        videoTypeQueryCondition.setTypeParent(20L);
        videoTypeQueryCondition.setPage(currentPage);
        videoTypeQueryCondition.setLimit(pageSize);
        Map<String, List<VideoUrlBO>> videoTypeDOList = videoService.queryAudioList(videoTypeQueryCondition);

        return new ApiPageResult(videoTypeDOList);
    }

}
