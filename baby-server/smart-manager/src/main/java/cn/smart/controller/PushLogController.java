package cn.smart.controller;

import cn.smart.common.annotation.Logs;
import cn.smart.common.utils.PageUtils;
import cn.smart.common.utils.R;
import cn.smart.condition.PushLogQueryCondition;
import cn.smart.entity.PushLogDO;
import cn.smart.service.IPushLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value="/pushLog")
public class PushLogController extends AbstractController {

    @Autowired
	private IPushLogService pushLogService;


    @PostMapping(value = "/add")
    @ResponseBody
    @Logs(value = "新增推送日志")
    public R add(@RequestBody PushLogDO pushLogDO) {
		pushLogService.save(pushLogDO);
    	return R.ok("新增成功!");
    }

    @PostMapping(value = "/update")
    @ResponseBody
    @Logs(value = "更新推送日志")
    public R update(@RequestBody PushLogDO pushLogDO) {
		pushLogService.update(pushLogDO);
		return R.ok("更新成功!");
    }

    @PostMapping(value = "/deleteById")
    @ResponseBody
    @Logs(value = "删除推送日志")
    public R deleteById(Long id) {
		pushLogService.deleteById(id);
    	return R.ok("删除成功!");
    }

    @GetMapping("/list")
    @ResponseBody
    public R list(PushLogQueryCondition pushLogQueryCondition) {

    	List<PushLogDO> pushLogDOList = pushLogService.queryList(pushLogQueryCondition);

        int total = pushLogService.queryTotal(pushLogQueryCondition);

        PageUtils pageUtils = new PageUtils(pushLogDOList, total, pushLogQueryCondition.getLimit(), pushLogQueryCondition.getPage());

        return R.ok().put("pushLog", pageUtils);
	}

}
