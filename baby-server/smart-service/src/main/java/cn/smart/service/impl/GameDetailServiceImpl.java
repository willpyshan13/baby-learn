package cn.smart.service.impl;

import cn.smart.condition.GameDetailQueryCondition;
import cn.smart.dao.IGameDetailMapper;
import cn.smart.entity.GameDetailDO;
import cn.smart.service.IGameDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author Ye
* @date 2018-4-18 16:01:13
*/

@Service
public class GameDetailServiceImpl  implements IGameDetailService {

    @Autowired
    private IGameDetailMapper gameDetailMapper;

    @Override
    public void save(GameDetailDO gameDetailDO) {
        gameDetailMapper.save(gameDetailDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        gameDetailMapper.save(map);
    }

    @Override
    public void saveBatch(List<GameDetailDO> list) {
        gameDetailMapper.saveBatch(list);
    }

    @Override
    public int update(GameDetailDO gameDetailDO) {
        return gameDetailMapper.update(gameDetailDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return gameDetailMapper.update(map);
    }

    @Override
    public int deleteById(Object id) {
        return gameDetailMapper.deleteById(id);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return gameDetailMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return gameDetailMapper.deleteBatch(ids);
    }

    @Override
    public GameDetailDO queryById(Object id) {
        return gameDetailMapper.queryById(id);
    }

    @Override
    public List<GameDetailDO> queryList(GameDetailQueryCondition gameDetailQueryCondition) {
        return gameDetailMapper.queryList(gameDetailQueryCondition);
    }

    @Override
    public int queryTotal(GameDetailQueryCondition gameDetailQueryCondition) {
        return gameDetailMapper.queryTotal(gameDetailQueryCondition);
    }

    @Override
    public int total() {
        return gameDetailMapper.total();
    }
}
