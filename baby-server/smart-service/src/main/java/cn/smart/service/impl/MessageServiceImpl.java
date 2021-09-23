package cn.smart.service.impl;

import cn.smart.dao.IClassInfoMapper;
import cn.smart.dao.IPersonMapper;
import cn.smart.entity.ClassInfoDO;
import cn.smart.entity.PersonDO;
import cn.smart.service.IMessageService;

import cn.smart.dao.IMessageMapper;
import cn.smart.condition.MessageQueryCondition;
import cn.smart.entity.MessageDO;
import cn.smart.support.jpush.PushComponent;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
* @author Ye
* @date 2018-8-11 15:27:46
*/

@Service
public class MessageServiceImpl  implements IMessageService {

    private static Logger logger = LogManager.getLogger(MessageServiceImpl.class);

    private static ExecutorService executorService;

    static {
        executorService = new ThreadPoolExecutor(2, 20, 5L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(20), new ThreadPoolExecutor.DiscardOldestPolicy());
    }

    @Autowired
    private PushComponent pushComponent;

    @Autowired
    private IMessageMapper messageMapper;

    @Autowired
    private IPersonMapper personMapper;

    @Autowired
    private IClassInfoMapper classInfoMapper;

    @Override
    public void save(MessageDO messageDO) {
        messageDO.setCreateTime(new Date());
        messageMapper.save(messageDO);

        executorService.submit(() -> {
            try {

                Map<String, String> extras;
                if (messageDO.getData() != null) {
                    extras = (Map<String, String>) JSONObject.parse(messageDO.getData());
                } else {
                    extras = new HashMap<>(16);
                }
                extras.put("type", String.valueOf(messageDO.getType()));
                // 个人推送
                if (messageDO.getObjectType() == 1) {
                    PersonDO person = personMapper.queryById(messageDO.getObjectId());
                    pushComponent.pushMessageAndroidWithAlias(messageDO.getTitle(), messageDO.getContent(), extras, person.getPushAlias());
                    // 班级推送
                } else if (messageDO.getObjectType() == 2) {
                    ClassInfoDO classInfo = classInfoMapper.queryById(messageDO.getObjectId());
                    pushComponent.pushMessageAndroidWithTags(messageDO.getTitle(), messageDO.getContent(), extras, classInfo.getPushTag());
                }
            } catch (Exception var1) {
                logger.error("Push message error --------> result : " + var1.getMessage());
            }
        });
    }

    @Override
    public void save(Map<String, Object> map) {
        messageMapper.save(map);
    }

    @Override
    public void saveBatch(List<MessageDO> list) {
        messageMapper.saveBatch(list);
    }

    @Override
    public int update(MessageDO messageDO) {
        return messageMapper.update(messageDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return messageMapper.update(map);
    }

    @Override
    public int deleteById(Object id) {
        return messageMapper.deleteById(id);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return messageMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return messageMapper.deleteBatch(ids);
    }

    @Override
    public MessageDO queryById(Object id) {
        return messageMapper.queryById(id);
    }

    @Override
    public List<MessageDO> queryList(MessageQueryCondition messageQueryCondition) {
        return messageMapper.queryList(messageQueryCondition);
    }

    @Override
    public int queryTotal(MessageQueryCondition messageQueryCondition) {
        return messageMapper.queryTotal(messageQueryCondition);
    }

    @Override
    public int total() {
        return messageMapper.total();
    }
}
