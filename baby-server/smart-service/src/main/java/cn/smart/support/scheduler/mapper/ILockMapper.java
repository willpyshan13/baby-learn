package cn.smart.support.scheduler.mapper;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.support.scheduler.entity.SysLock;
import org.springframework.stereotype.Repository;

/**
 * @author Admin
 */
@Repository
public interface ILockMapper extends IBaseDao{

    void cleanExpiredLock();

    SysLock selectOne(SysLock sysLock);

    int save(SysLock sysLock);

}
