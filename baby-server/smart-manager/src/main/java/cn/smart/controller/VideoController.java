package cn.smart.controller;

import cn.smart.bo.VideoBO;
import cn.smart.common.annotation.Logs;
import cn.smart.common.utils.PageUtils;
import cn.smart.common.utils.R;
import cn.smart.condition.VideoUrlQueryCondition;
import cn.smart.entity.VideoUrlDO;
import cn.smart.service.IVideoUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 视屏信息管理
 */
@Controller
@RequestMapping(value="/video")
public class VideoController extends AbstractController {

    @Autowired
	private IVideoUrlService videoUrlService;


    /**
     * 新增
     * @param videoUrlDO
     * @return
     */
    @PostMapping(value = "/add")
    @ResponseBody
    @Logs(value = "新增视频")
    public R add(@RequestBody VideoUrlDO videoUrlDO) {
        videoUrlDO.setCreateTime(new Date());
        videoUrlService.save(videoUrlDO);
    	return R.ok("新增成功!");
    }

    /**
     * 更新
     * @param videoUrlDO
     * @return
     */
    @PostMapping(value = "/update")
    @ResponseBody
    @Logs(value = "更新视频")
    public R update(@RequestBody VideoUrlDO videoUrlDO) {
		videoUrlService.update(videoUrlDO);
		return R.ok("更新成功!");
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteById")
    @ResponseBody
    @Logs(value = "删除视频")
    public R deleteById(Long id) {
		videoUrlService.deleteById(id);
    	return R.ok("删除成功!");
    }

    /**
     * 批量删除视频
     * @param videoBOList 集合
     * @return R
     */
    @PostMapping(value = "/deleteBatch")
    @ResponseBody
    @Logs(value = "批量删除视频")
    public R batchDelete(@RequestBody List<VideoBO> videoBOList) {
        return videoUrlService.deleteVideoBatch(videoBOList);
    }

    /**
     * 视频列表
     * @param videoUrlQueryCondition 查询条件
     * @return R
     */
    @GetMapping("/list")
    @ResponseBody
    public R list(VideoUrlQueryCondition videoUrlQueryCondition) {

    	List<VideoUrlDO> videoUrlDOList = videoUrlService.queryList(videoUrlQueryCondition);

        int total = videoUrlService.queryTotal(videoUrlQueryCondition);

        PageUtils pageUtils = new PageUtils(videoUrlDOList, total, videoUrlQueryCondition.getLimit(), videoUrlQueryCondition.getPage());

        return R.ok().put("video", pageUtils);
	}

}
