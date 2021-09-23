package cn.smart.controller;

import cn.smart.common.annotation.Logs;
import cn.smart.common.exception.CustomException;
import cn.smart.common.utils.PageUtils;
import cn.smart.common.utils.R;
import cn.smart.condition.SysRoleQueryCondition;
import cn.smart.entity.SysRoleDO;
import cn.smart.service.ISysRoleService;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/sys/role")
public class SysRoleController extends AbstractController {

    @Autowired
	private ISysRoleService sysRoleService;


    @PostMapping(value = "/add")
    @ResponseBody
    @Logs(value = "新增角色")
    public R add(@RequestBody SysRoleDO sysRoleDO) {
		sysRoleService.save(sysRoleDO);
    	return R.ok("新增成功!");
    }

    @PostMapping(value = "/update")
    @ResponseBody
    @Logs(value = "更新角色")
    public R update(@RequestBody SysRoleDO sysRoleDO) {
		sysRoleService.update(sysRoleDO);
		return R.ok("更新成功!");
    }

    @PostMapping(value = "/deleteById")
    @ResponseBody
    @Logs(value = "删除角色")
    public R deleteById(Long id) {
		sysRoleService.deleteById(id);
    	return R.ok("删除成功!");
    }

    @GetMapping("/list")
    @ResponseBody
    public R list(SysRoleQueryCondition sysRoleQueryCondition) {

    	List<SysRoleDO> sysRoleDOList = sysRoleService.queryList(sysRoleQueryCondition);

        int total = sysRoleService.queryTotal(sysRoleQueryCondition);

        PageUtils pageUtils = new PageUtils(sysRoleDOList, total, sysRoleQueryCondition.getLimit(), sysRoleQueryCondition.getPage());

        return R.ok().put("sysRole", pageUtils);
	}


	@PostMapping(value = "/permission")
    @ResponseBody
    @Logs(value = "更新角色权限")
    public R updatePermission(@RequestBody Map<String, Object> map, HttpServletRequest request) {

        Long roleId = ((Integer) map.get("roleId")).longValue();

        Long[] menuIds =  JSONArray.parseObject(map.get("menuIds").toString(), Long[].class);
        try {
            sysRoleService.updateRolePermission(roleId, menuIds);
            HttpSession session = request.getSession();
            session.setAttribute(getUserId() + "menuList", null);
            session.setAttribute(getUserId() + "permissions", null);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
        return R.ok();
    }
}
