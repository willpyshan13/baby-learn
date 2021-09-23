package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.VerifyCodeQueryCondition;
import cn.smart.entity.VerifyCodeDO;
import org.springframework.stereotype.Repository;

/**
 * @author Ye
 * @date 2018-4-3 10:02:27
 */
@Repository
public interface IVerifyCodeMapper extends IBaseDao<VerifyCodeDO, VerifyCodeQueryCondition> {

}
