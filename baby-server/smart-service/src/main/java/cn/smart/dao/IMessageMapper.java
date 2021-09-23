package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.MessageQueryCondition;
import cn.smart.entity.MessageDO;
import org.springframework.stereotype.Repository;

/**
 * @author Ye
 * @date 2018-8-11 15:27:45
 */
@Repository
public interface IMessageMapper extends IBaseDao<MessageDO, MessageQueryCondition> {

}
