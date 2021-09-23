package cn.smart.service;

import cn.smart.common.base.service.ICrudService;
import cn.smart.condition.SysMenuQueryCondition;
import cn.smart.entity.SysMenuDO;

import java.util.List;
import java.util.Map;

/**
* @author Ye
* @date 2018-4-12 13:33:26
*/
public interface ISysMenuService extends ICrudService<SysMenuDO, SysMenuQueryCondition> {

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<SysMenuDO> queryListParentId(Long parentId);


    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     * @param menuIdList  用户菜单ID
     */
    List<SysMenuDO> queryListParentId(Long parentId, List<Long> menuIdList);


    /**
     * 获取用户菜单列表
     */
    List<SysMenuDO> getUserMenuList(Long userId);

    List<Map<String, Object>> getSysMenuTree(Long userId);

    List<Long> getUserAllMenuId(Long userId);

}