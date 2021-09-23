package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.SysMenuQueryCondition;
import cn.smart.entity.SysMenuDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ye
 * @date 2018-4-12 13:33:25
 */
@Repository
public interface ISysMenuMapper extends IBaseDao<SysMenuDO, SysMenuQueryCondition> {

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<SysMenuDO> queryListParentId(Long parentId);
}
