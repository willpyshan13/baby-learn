package cn.smart.controller;

import cn.smart.common.utils.PageUtils;
import cn.smart.common.utils.R;
import cn.smart.condition.SysConfigQueryCondition;
import cn.smart.entity.SysConfigDO;
import cn.smart.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value="/sysConfig")
public class SysConfigController extends AbstractController {

    @Autowired
	private ISysConfigService sysConfigService;


    @PostMapping(value = "/add")
    @ResponseBody
    public R add(@RequestBody SysConfigDO sysConfigDO) {
		sysConfigService.save(sysConfigDO);
    	return R.ok("新增成功!");
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public R update(@RequestBody SysConfigDO sysConfigDO) {
		sysConfigService.update(sysConfigDO);
		return R.ok("更新成功!");
    }

    @PostMapping(value = "/deleteById")
    @ResponseBody
    public R deleteById(Long id) {
		sysConfigService.deleteById(id);
    	return R.ok("删除成功!");
    }

    @GetMapping("/list")
    @ResponseBody
    public R list(SysConfigQueryCondition sysConfigQueryCondition) {

    	List<SysConfigDO> sysConfigDOList = sysConfigService.queryList(sysConfigQueryCondition);

        int total = sysConfigService.queryTotal(sysConfigQueryCondition);

        PageUtils pageUtils = new PageUtils(sysConfigDOList, total, sysConfigQueryCondition.getLimit(), sysConfigQueryCondition.getPage());

        return R.ok().put("sysConfigDOList", pageUtils);
	}

}
