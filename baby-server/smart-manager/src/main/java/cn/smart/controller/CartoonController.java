package cn.smart.controller;

import cn.smart.bo.VideoBO;
import cn.smart.common.annotation.Logs;
import cn.smart.common.utils.PageUtils;
import cn.smart.common.utils.R;
import cn.smart.condition.VideoQueryCondition;
import cn.smart.entity.VideoDO;
import cn.smart.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 动画管理
 */
@Controller
@RequestMapping("/cartoon")
public class CartoonController {

    @Autowired
    private IVideoService videoService;

    /**
     * 动画列表
     * @param videoQueryCondition 查询条件
     * @return R
     */
    @GetMapping("/list")
    @ResponseBody
    public R list(VideoQueryCondition videoQueryCondition) {

        List<VideoDO> videoUrlDOList = videoService.queryList(videoQueryCondition);

        int total = videoService.queryTotal(videoQueryCondition);

        PageUtils pageUtils = new PageUtils(videoUrlDOList, total, videoQueryCondition.getLimit(), videoQueryCondition.getPage());

        return R.ok().put("video", pageUtils);
    }

    @PostMapping(value = "/add")
    @ResponseBody
    @Logs(value = "新增动画")
    public R add(@RequestBody VideoDO videoDO) {
        videoDO.setCreateTime(new Date());
        videoService.saveVideo(videoDO);
        return R.ok("新增成功!");
    }

    @PostMapping(value = "/update")
    @ResponseBody
    @Logs(value = "更新动画信息")
    public R update(@RequestBody VideoDO videoDO) {
        videoService.updateVideo(videoDO);
        return R.ok("更新成功!");
    }

    @PostMapping(value = "/deleteById")
    @ResponseBody
    @Logs(value = "删除动画")
    public R deleteById(Long id) {
        videoService.deleteById(id);
        return R.ok("删除成功!");
    }

    @PostMapping(value = "/deleteBatch")
    @ResponseBody
    @Logs(value = "批量删除动画")
    public R deleteBatch(@RequestBody Long[] ids) {
        videoService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 移除视频关系
     * @param ids ids
     * @return R
     */
    @PostMapping(value = "/removeVideoBatch")
    @ResponseBody
    @Logs(value = "动画取关视频")
    public R removeVideoBatch(@RequestBody Long[] ids) {
        videoService.removeVideoBatch(ids);
        return R.ok();
    }


    /**
     * 关联视频
     * @param videoBO videoBO
     * @return R
     */
    @PostMapping(value = "/updateVideoId")
    @ResponseBody
    @Logs(value = "动画关联视频")
    public R removeVideoBatch(@RequestBody VideoBO videoBO) {
        videoService.updateVideoIdBatch(videoBO);
        return R.ok();
    }
}
