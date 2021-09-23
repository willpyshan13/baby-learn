package cn.smart.controller;

import cn.smart.common.annotation.Logs;
import cn.smart.common.utils.PageUtils;
import cn.smart.common.utils.R;
import cn.smart.condition.VideoTypeQueryCondition;
import cn.smart.entity.VideoTypeDO;
import cn.smart.service.IVideoTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value="/videoType")
public class VideoTypeController extends AbstractController {

    @Autowired
	private IVideoTypeService videoTypeService;


    @PostMapping(value = "/add")
    @ResponseBody
    @Logs(value = "新增视频类型")
    public R add(@RequestBody VideoTypeDO videoTypeDO) {
        videoTypeDO.setCreateTime(new Date());
        if (videoTypeDO.getTypeParent() == null) {
            videoTypeDO.setTypeParent(0L);
        }
		videoTypeService.save(videoTypeDO);
    	return R.ok("新增成功!");
    }

    @PostMapping(value = "/update")
    @ResponseBody
    @Logs(value = "更新视频类型")
    public R update(@RequestBody VideoTypeDO videoTypeDO) {
        if (videoTypeDO.getTypeParent() == null) {
            videoTypeDO.setTypeParent(0L);
        }
		videoTypeService.update(videoTypeDO);
		return R.ok("更新成功!");
    }

    @PostMapping(value = "/deleteById")
    @ResponseBody
    @Logs(value = "删除视频类型")
    public R deleteById(Long id) {
		videoTypeService.deleteById(id);
    	return R.ok("删除成功!");
    }

    @GetMapping("/list")
    @ResponseBody
    public R list(VideoTypeQueryCondition videoTypeQueryCondition) {

    	List<VideoTypeDO> videoTypeDOList = videoTypeService.queryList(videoTypeQueryCondition);

        int total = videoTypeService.queryTotal(videoTypeQueryCondition);

        PageUtils pageUtils = new PageUtils(videoTypeDOList, total, videoTypeQueryCondition.getLimit(), videoTypeQueryCondition.getPage());

        return R.ok().put("videoType", pageUtils);
	}

}
