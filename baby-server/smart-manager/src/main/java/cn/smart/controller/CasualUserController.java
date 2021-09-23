package cn.smart.controller;

import cn.smart.common.annotation.Logs;
import cn.smart.common.utils.PageUtils;
import cn.smart.common.utils.R;
import cn.smart.entity.CasualUserDO;
import cn.smart.condition.CasualUserQueryCondition;
import cn.smart.service.ICasualUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping(value="/casual/user")
public class CasualUserController extends AbstractController {

    @Autowired
	private ICasualUserService casualUserService;


    @PostMapping(value = "/add")
    @ResponseBody
    @Logs(value = "新增临时用户")
    public R add(@RequestBody CasualUserDO casualUserDO) {
		casualUserService.save(casualUserDO);
    	return R.ok("新增成功!");
    }

    @PostMapping(value = "/update")
    @ResponseBody
    @Logs(value = "更新临时用户")
    public R update(@RequestBody CasualUserDO casualUserDO) {
		casualUserService.update(casualUserDO);
		return R.ok("更新成功!");
    }

    @PostMapping(value = "/deleteById")
    @ResponseBody
    @Logs(value = "删除临时用户")
    public R deleteById(Long id) {
		casualUserService.deleteById(id);
    	return R.ok("删除成功!");
    }

    @GetMapping("/list")
    @ResponseBody
    public R list(CasualUserQueryCondition casualUserQueryCondition) {

    	List<CasualUserDO> casualUserDOList = casualUserService.queryList(casualUserQueryCondition);

        int total = casualUserService.queryTotal(casualUserQueryCondition);

        PageUtils pageUtils = new PageUtils(casualUserDOList, total, casualUserQueryCondition.getLimit(), casualUserQueryCondition.getPage());

        return R.ok().put("casualUserList", pageUtils);
	}

}
