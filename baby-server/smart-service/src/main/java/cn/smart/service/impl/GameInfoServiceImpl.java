package cn.smart.service.impl;

import cn.smart.common.utils.R;
import cn.smart.condition.CourseGameQueryCondition;
import cn.smart.condition.GameDetailQueryCondition;
import cn.smart.condition.GameInfoQueryCondition;
import cn.smart.dao.ICourseGameMapper;
import cn.smart.dao.IGameDetailMapper;
import cn.smart.dao.IGameInfoMapper;
import cn.smart.entity.GameInfoDO;
import cn.smart.service.IGameInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author Ye
* @date 2018-5-4 11:34:16
*/

@Service
public class GameInfoServiceImpl  implements IGameInfoService {

    @Autowired
    private IGameInfoMapper gameInfoMapper;

    @Autowired
    private IGameDetailMapper gameDetailMapper;

    @Autowired
    private ICourseGameMapper courseGameMapper;

    @Override
    public void save(GameInfoDO gameInfoDO) {
        gameInfoMapper.save(gameInfoDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        gameInfoMapper.save(map);
    }

    @Override
    public void saveBatch(List<GameInfoDO> list) {
        gameInfoMapper.saveBatch(list);
    }

    @Override
    public int update(GameInfoDO gameInfoDO) {
        return gameInfoMapper.update(gameInfoDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return gameInfoMapper.update(map);
    }

    @Override
    public int deleteById(Object id) {
        return gameInfoMapper.deleteById(id);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return gameInfoMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return gameInfoMapper.deleteBatch(ids);
    }

    @Override
    public GameInfoDO queryById(Object id) {
        return gameInfoMapper.queryById(id);
    }

    @Override
    public List<GameInfoDO> queryList(GameInfoQueryCondition gameInfoQueryCondition) {
        return gameInfoMapper.queryList(gameInfoQueryCondition);
    }

    @Override
    public int queryTotal(GameInfoQueryCondition gameInfoQueryCondition) {
        return gameInfoMapper.queryTotal(gameInfoQueryCondition);
    }

    @Override
    public int total() {
        return gameInfoMapper.total();
    }

    @Override
    public R deleteGameInfoBatch(Long[] ids) {
        GameDetailQueryCondition gameDetailQueryCondition = new GameDetailQueryCondition();
        gameDetailQueryCondition.setGameIds(ids);
        int gameDetailTotal = gameDetailMapper.queryTotal(gameDetailQueryCondition);
        if (gameDetailTotal > 0) {
            return R.error("请先删除游戏详情内容");
        }

        CourseGameQueryCondition query = new CourseGameQueryCondition();
        query.setDeleteGameIds(ids);
        int courseGameTotal = courseGameMapper.queryTotal(query);
        if (courseGameTotal > 0) {
            return R.error("选中游戏与课程存在关联, 请先移除课程与游戏的关联");
        }
        deleteBatch(ids);

        return R.ok("删除成功");
    }
}
