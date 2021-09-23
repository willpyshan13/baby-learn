package cn.smart.utils;

import cn.smart.support.scheduler.entity.SysLock;
import cn.smart.support.scheduler.mapper.ILockMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class CacheUtil {
    private static Logger logger = LogManager.getLogger();
    private static ILockMapper lockMapper;
    @Autowired
    private ILockMapper ilockMapper;
    private static Map<String, ReentrantLock> thread = new ConcurrentHashMap<>();
    private static ExecutorService executorService = Executors.newFixedThreadPool(50);

    public CacheUtil() {
    }

    public ILockMapper getLockMapper() {
        return lockMapper;
    }

    public void setLockMapper() {
        CacheUtil.lockMapper = this.ilockMapper;
    }

    /**默认锁定一分钟*/
    public static boolean getLock(String key, String requestId) {
        return getLock(key, key, requestId);
    }

    public static boolean getLock(String key, String requestId, int seconds) {
        return getLock(key, key, requestId, seconds);
    }

    /**默认锁定一分钟*/
    public static boolean getLock(String key, String name, String requestId) {
        return getLock(key, name, requestId, 60);
    }


    public static boolean getLock(String key, String name, String requestId, int seconds) {
        logger.debug("TOLOCK : " + key);

        return getDBLock(key, name, seconds);
    }

    private static Boolean getDBLock(String key, String name, int seconds) {

        try {
            thread.put(key, new ReentrantLock());
            thread.get(key).lock();
            try {
                final SysLock param = new SysLock();
                param.setKey(key);
                SysLock lock = lockMapper.selectOne(param);
                if (lock == null) {
                    return executorService.submit(() -> {
                        CacheUtil.logger.debug("保存锁信息到数据库>" + key);
                        param.setName(name);
                        param.setExpireSecond(seconds);
                        return CacheUtil.lockMapper.save(param) == 1;
                    }).get();
                }
                return false;
            } finally {
                thread.get(key).unlock();
            }
        } catch (Exception var12) {
            logger.error("保存锁信息失败", var12);
            try {
                Thread.sleep(50L);
            } catch (InterruptedException var10) {
                logger.error("线程等待异常", var10);
            }
            return getDBLock(key, name, seconds);
        }
    }

    public static void unLock(String key) {
        logger.debug("UNLOCK : " + key);

        deleteLock(key, 1);
    }

    private static void deleteLock(String key, int times) {
        boolean success = false;

        try {
            if (thread.containsKey(key)) {
                thread.get(key).lock();

                try {
                    logger.debug("从数据库删除锁信息>" + key);
                    final Map<String, String> wrapper = new HashMap<>(16);
                    wrapper.put("lockKey", key);
                    success = executorService.submit(() -> CacheUtil.lockMapper.delete(wrapper) > 0).get();
                } finally {
                    thread.get(key).unlock();
                }
            }
        } catch (Exception var11) {
            logger.error("从数据库删除锁信息失败", var11);
        }

        if (!success) {

            if (times > 20) {
                return;
            }

            if (thread.containsKey(key)) {
                logger.warn(key + "从数据库删除锁信息失败,稍候再次尝试...");
            }

            try {
                Thread.sleep(100L);
            } catch (InterruptedException var9) {
                logger.error("线程等待异常", var9);
            }

            deleteLock(key, times + 1);
        } else {
            thread.remove(key);
        }

    }

    /*public static void refreshTimes(String key, int seconds, int frequency, String message) {
        if (getLock(key + "-LOCK", "次数限制", DateUtil.addDate(new Date(), 13, 10))) {
            try {
                Integer times = 1;
                String timesStr = (String)lockManager.get(key);
                if (StringUtils.isNotEmpty(timesStr)) {
                    times = Integer.valueOf(timesStr) + 1;
                    if (times > frequency) {
                        throw new CustomException(message);
                    }
                }

                lockManager.set(key, times.toString(), seconds);
            } finally {
                unLock(key + "-LOCK");
            }
        } else {
            refreshTimes(key, seconds, frequency, message);
        }

    }*/
}
