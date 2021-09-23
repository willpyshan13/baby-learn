package cn.smart.controller;

import cn.smart.common.annotation.Logs;
import cn.smart.common.utils.PageUtils;
import cn.smart.common.utils.R;
import cn.smart.entity.SysLogsDO;
import cn.smart.condition.SysLogsQueryCondition;
import cn.smart.service.ISysLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping(value="/logs")
//@RequestMapping(value="/sysLogs")
public class SysLogsController extends AbstractController {

    @Autowired
	private ISysLogsService sysLogsService;


    @PostMapping(value = "/add")
    @ResponseBody
    @Logs(value = "新增日志")
    public R add(@RequestBody SysLogsDO sysLogsDO) {
		sysLogsService.save(sysLogsDO);
    	return R.ok("新增成功!");
    }

    @PostMapping(value = "/update")
    @ResponseBody
    @Logs(value = "更新日志信息")
    public R update(@RequestBody SysLogsDO sysLogsDO) {
		sysLogsService.update(sysLogsDO);
		return R.ok("更新成功!");
    }

    @PostMapping(value = "/deleteById")
    @ResponseBody
    @Logs(value = "删除日志")
    public R deleteById(Long id) {
		sysLogsService.deleteById(id);
    	return R.ok("删除成功!");
    }

    @GetMapping("/sys")
    @ResponseBody
    public R list(SysLogsQueryCondition sysLogsQueryCondition) {

    	List<SysLogsDO> sysLogsDOList = sysLogsService.queryList(sysLogsQueryCondition);

        int total = sysLogsService.queryTotal(sysLogsQueryCondition);

        PageUtils pageUtils = new PageUtils(sysLogsDOList, total, sysLogsQueryCondition.getLimit(), sysLogsQueryCondition.getPage());

        return R.ok().put("sysLog", pageUtils);
	}

    @GetMapping("/api")
    @ResponseBody
    public R apiLogList(SysLogsQueryCondition query) {

        List<SysLogsDO> apiLogsDOList = sysLogsService.queryApiLogList(query);

        int total = sysLogsService.countApiLog(query);

        PageUtils pageUtils = new PageUtils(apiLogsDOList, total, query.getLimit(), query.getPage());

        return R.ok().put("apiLog", pageUtils);
    }


}
