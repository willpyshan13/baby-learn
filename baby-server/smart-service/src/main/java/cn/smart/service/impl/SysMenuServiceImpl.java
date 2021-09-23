package cn.smart.service.impl;

import cn.smart.common.utils.Constant;
import cn.smart.condition.SysMenuQueryCondition;
import cn.smart.dao.ISysMenuMapper;
import cn.smart.dao.ISysUserMapper;
import cn.smart.entity.SysMenuDO;
import cn.smart.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author Ye
* @date 2018-4-12 13:33:26
*/

@Service
public class SysMenuServiceImpl  implements ISysMenuService {

    @Autowired
    private ISysMenuMapper sysMenuMapper;

    @Autowired
    private ISysUserMapper sysUserDao;


    @Override
    public List<SysMenuDO> queryListParentId(Long parentId) {
        return sysMenuMapper.queryListParentId(parentId);
    }


    @Override
    public List<SysMenuDO> queryListParentId(Long parentId, List<Long> menuIdList) {
        List<SysMenuDO> menuList = queryListParentId(parentId);
        if(menuIdList == null){
            return menuList;
        }

        List<SysMenuDO> userMenuList = new ArrayList<>();
        for(SysMenuDO menu : menuList){
            if(menuIdList.contains(menu.getMenuId())){
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<SysMenuDO> getUserMenuList(Long userId) {
        //系统管理员，拥有最高权限
        if(userId == Constant.SUPER_ADMIN){
            return getAllMenuList(null);
        }

        //用户菜单列表
        Map<String, Object> map = new HashMap<>(16);
        map.put("userId", userId);
        map.put("type", null);
        List<Long> menuIdList = sysUserDao.queryAllMenuId(map);
        return getAllMenuList(menuIdList);
    }

    @Override
    public List<Map<String, Object>> getSysMenuTree(Long userId) {
        List<SysMenuDO> sysMenuList;
        if (null == userId) {
            sysMenuList = getAllMenuList(null);
        } else {
            sysMenuList = getUserMenuList(userId);
        }

        List<Map<String, Object>> l = new ArrayList<>();
        for (SysMenuDO sysMenu : sysMenuList) {
            Map<String, Object> map = new HashMap<>(3);
            map.put("id", sysMenu.getMenuId());
            map.put("label", sysMenu.getName());

            if (sysMenu.getList().size() > 0) {
                map.put("children", getChildren(sysMenu.getList()));
            }
            l.add(map);
        }

        return l;
    }

    @Override
    public List<Long> getUserAllMenuId(Long userId) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("roleId", userId);
        map.put("type", null);
        return sysUserDao.queryAllMenuId(map);
    }

    private List<Map<String,Object>> getChildren(List<SysMenuDO> list) {
        List<Map<String, Object>> l = new ArrayList<>();
        for (SysMenuDO sysMenu : list) {
            Map<String, Object> map = new HashMap<>(3);
            map.put("id", sysMenu.getMenuId());
            map.put("label", sysMenu.getName());
            if (sysMenu.getList() != null && sysMenu.getList().size() > 0) {
                map.put("children", getChildren(sysMenu.getList()));
            }
            l.add(map);
        }
        return l;
    }


    /**
     * 获取所有菜单列表
     */
    private List<SysMenuDO> getAllMenuList(List<Long> menuIdList){
        //查询根菜单列表
        List<SysMenuDO> menuList = queryListParentId(0L, menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }

    /**
     * 递归
     */
    private List<SysMenuDO> getMenuTreeList(List<SysMenuDO> menuList, List<Long> menuIdList){
        List<SysMenuDO> subMenuList = new ArrayList<>();

        for(SysMenuDO entity : menuList){
//            if(entity.getType() == Constant.MenuType.CATALOG.getValue()){//目录
                entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
//            }
            subMenuList.add(entity);
        }
        return subMenuList;
    }


    @Override
    public void save(SysMenuDO sysMenuDO) {
        sysMenuMapper.save(sysMenuDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        sysMenuMapper.save(map);
    }

    @Override
    public void saveBatch(List<SysMenuDO> list) {
        sysMenuMapper.saveBatch(list);
    }

    @Override
    public int update(SysMenuDO sysMenuDO) {
        return sysMenuMapper.update(sysMenuDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return sysMenuMapper.update(map);
    }

    @Override
    public int deleteById(Object id) {
        return sysMenuMapper.deleteById(id);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return sysMenuMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return sysMenuMapper.deleteBatch(ids);
    }

    @Override
    public SysMenuDO queryById(Object id) {
        return sysMenuMapper.queryById(id);
    }

    @Override
    public List<SysMenuDO> queryList(SysMenuQueryCondition sysMenuQueryCondition) {
        return sysMenuMapper.queryList(sysMenuQueryCondition);
    }

    @Override
    public int queryTotal(SysMenuQueryCondition sysMenuQueryCondition) {
        return sysMenuMapper.queryTotal(sysMenuQueryCondition);
    }

    @Override
    public int total() {
        return sysMenuMapper.total();
    }
}
