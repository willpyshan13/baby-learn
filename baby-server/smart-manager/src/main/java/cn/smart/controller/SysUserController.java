package cn.smart.controller;

import cn.smart.common.ConfigConstant;
import cn.smart.common.annotation.Logs;
import cn.smart.common.utils.PageUtils;
import cn.smart.common.utils.R;
import cn.smart.common.utils.Validator;
import cn.smart.common.utils.WebUtil;
import cn.smart.condition.SysUserQueryCondition;
import cn.smart.entity.SysUserDO;
import cn.smart.service.ISysUserService;
import cn.smart.service.ISysUserTokenService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Ye
 */
@Controller
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysUserTokenService sysUserTokenService;

    /**
     * 登录
     */
    @GetMapping(value = "/login")
    @ResponseBody
    public Map<String, Object> login(String username, String password)throws IOException {

        // 用户信息
        SysUserDO user = sysUserService.queryByUserName(username);

        //账号不存在、密码错误
        if(user == null || !user.getPassword().equals(new Sha256Hash(password, user.getSalt()).toHex())) {
            return R.error("账号或密码不正确");
        }

        //账号锁定
        if(user.getStatus() == 0){
            return R.error("账号已被锁定,请联系管理员");
        }
        //生成token，并保存到数据库
        return sysUserTokenService.createToken(user.getUserId());
    }

    /**
     * 用户退出登录
     * @param request request
     * @return R
     */
    @GetMapping("/logout")
    @ResponseBody
    public R logout(HttpServletRequest request) {

        if (Validator.isEmpty(getUserId())) {
            return R.error(-1, "退出失败!");
        }

        int result = sysUserTokenService.deleteById(getUserId());
        if (1 == result) {
            request.removeAttribute(ConfigConstant.USER_KEY);
            request.getSession().invalidate();
            return R.ok("退出成功!");
        } else {
            return R.error(-1, "退出失败!");
        }
    }

    @GetMapping("/info")
    @ResponseBody
    public R detail() {
        return R.ok().put("detail", getUser());
    }


    @GetMapping("/list")
    @RequiresPermissions("sys:user:list")
    @ResponseBody
    public R list(SysUserQueryCondition sysUserQueryCondition) {

        List<SysUserDO> sysUserEntities = sysUserService.queryList(sysUserQueryCondition);

        int total = sysUserService.queryTotal(sysUserQueryCondition);

        PageUtils pageUtils = new PageUtils(sysUserEntities, total, sysUserQueryCondition.getLimit(), sysUserQueryCondition.getPage());

        return R.ok().put("sysUser",pageUtils);
    }


    @PostMapping("/add")
    @RequiresPermissions("sys:user:save")
    @ResponseBody
    @Logs(value = "新增管理员")
    public R add(@RequestBody SysUserDO sysUserDO) {
        sysUserDO.setCreateTime(new Date());
        sysUserDO.setSalt("smart");
        if (Validator.isNotEmpty(sysUserDO.getPassword())) {
            sysUserDO.setPassword(new Sha256Hash(sysUserDO.getPassword(), sysUserDO.getSalt()).toHex());
        }
        sysUserService.saveSysUser(sysUserDO);
        return R.ok();
    }

    @PostMapping("/update")
    @RequiresPermissions("sys:user:update")
    @ResponseBody
    @Logs(value = "更新管理员信息")
    public R update(@RequestBody SysUserDO sysUserDO) {
        sysUserDO.setSalt("smart");
        sysUserDO.setPassword(null);
        sysUserService.update(sysUserDO);
        return R.ok();
    }

    @PostMapping("/password")
    @RequiresPermissions("sys:user:update")
    @ResponseBody
    @Logs(value = "更新管理员密码")
    public R updatePassword(@RequestBody SysUserDO sysUserDO) {
        sysUserDO.setSalt("smart");
        sysUserDO.setPassword(new Sha256Hash("123456", sysUserDO.getSalt()).toHex());
        sysUserService.update(sysUserDO);
        return R.ok();
    }

    @PostMapping("/deleteById")
    @RequiresPermissions("sys:user:delete")
    @ResponseBody
    @Logs(value = "删除管理员")
    public R deleteById(Long id) {
        sysUserService.deleteById(id);
        return R.ok();
    }
}
