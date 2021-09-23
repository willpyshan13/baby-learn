package cn.smart.controller;

import cn.smart.common.annotation.Logs;
import cn.smart.common.utils.PageUtils;
import cn.smart.common.utils.R;
import cn.smart.condition.UsageLogQueryCondition;
import cn.smart.entity.UsageLogDO;
import cn.smart.service.IUsageLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value="/use/app")
public class UsageLogController extends AbstractController {

    @Autowired
	private IUsageLogService usageLogService;

    @PostMapping(value = "/deleteById")
    @ResponseBody
    @Logs(value = "删除用户使用日志")
    public R deleteById(Long id) {
		usageLogService.deleteById(id);
    	return R.ok("删除成功!");
    }

    @GetMapping("/log")
    @ResponseBody
    public R list(UsageLogQueryCondition usageLogQueryCondition) {

    	List<UsageLogDO> usageLogDOList = usageLogService.queryList(usageLogQueryCondition);

        int total = usageLogService.queryTotal(usageLogQueryCondition);

        PageUtils pageUtils = new PageUtils(usageLogDOList, total, usageLogQueryCondition.getLimit(), usageLogQueryCondition.getPage());

        return R.ok().put("log", pageUtils);
	}

}
