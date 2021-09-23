package cn.smart.controller;

import cn.smart.common.annotation.Logs;
import cn.smart.common.utils.PageUtils;
import cn.smart.common.utils.R;
import cn.smart.common.utils.Validator;
import cn.smart.condition.SysMenuQueryCondition;
import cn.smart.entity.SysMenuDO;
import cn.smart.service.IShiroService;
import cn.smart.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author Ye
 */
@Controller
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController {

    @Autowired
    private ISysMenuService sysMenuService;

    @Autowired
    private IShiroService shiroService;

    /**
     * 导航菜单
     */
    @GetMapping(value = "/nav")
    @ResponseBody
    public R nav(HttpServletRequest request){
        List<SysMenuDO> menuList = new ArrayList<>(16);
        Set<String> permissions = new HashSet<>(16);
        HttpSession session = request.getSession();
        if (getUserId() != null) {
            menuList = (List<SysMenuDO>) session.getAttribute(getUserId() + "menuList");
            permissions = (Set<String>) session.getAttribute(getUserId() + "permissions");
            if (menuList == null) {
                menuList = sysMenuService.getUserMenuList(getUserId());
                permissions = shiroService.getUserPermissions(getUserId());
                if (menuList.size() != 0) {
                    session.setAttribute(getUserId() + "menuList", menuList);
                    session.setAttribute(getUserId() + "permissions", permissions);
                }
            }
        }

        return R.ok().put("menuList", menuList).put("permissions", permissions);
    }

    @GetMapping(value = "/tree")
    @ResponseBody
    public R tree(Long roleId) {
        List<Map<String, Object>> menuList = sysMenuService.getSysMenuTree(null);
        List<Long> permission = sysMenuService.getUserAllMenuId(roleId);
        return R.ok().put("menu", menuList).put("permission", permission.toArray());
    }
    
    @GetMapping(value = "/getParentMenu")
    @ResponseBody
    public R getParentMenu(Long parentId) {

        List<SysMenuDO> parentMenuList = sysMenuService.queryListParentId(parentId);
        
        return R.ok().put("parentMenu", parentMenuList);
    }

    @PostMapping(value = "/add")
    @ResponseBody
    @Logs(value = "新增菜单")
    public R add(@RequestBody SysMenuDO sysMenuDO) {
        if (Validator.isEmpty(sysMenuDO.getParentId())) {
            sysMenuDO.setParentId(0L);
        }
        sysMenuService.save(sysMenuDO);
        return R.ok("新增成功!");
    }

    @PostMapping(value = "/update")
    @ResponseBody
    @Logs(value = "更新菜单")
    public R update(@RequestBody SysMenuDO sysMenuDO) {
        if (Validator.isEmpty(sysMenuDO.getParentId())) {
            sysMenuDO.setParentId(0L);
        }
        sysMenuService.update(sysMenuDO);
        return R.ok("更新成功!");
    }

    @PostMapping(value = "/deleteById")
    @ResponseBody
    @Logs(value = "删除菜单")
    public R deleteById(Long id) {
        sysMenuService.deleteById(id);
        return R.ok("删除成功!");
    }

    @GetMapping("/list")
    @ResponseBody
    public R list(SysMenuQueryCondition menuQueryCondition) {

        List<SysMenuDO> menuList = sysMenuService.queryList(menuQueryCondition);

        int total = sysMenuService.queryTotal(menuQueryCondition);

        PageUtils pageUtils = new PageUtils(menuList, total, menuQueryCondition.getLimit(), menuQueryCondition.getPage());

        return R.ok().put("menuList", pageUtils);
    }
}
