package cn.smart.controller;

import cn.smart.common.annotation.Logs;
import cn.smart.common.utils.PageUtils;
import cn.smart.common.utils.R;
import cn.smart.condition.GameDetailQueryCondition;
import cn.smart.condition.GameInfoQueryCondition;
import cn.smart.entity.GameDetailDO;
import cn.smart.entity.GameInfoDO;
import cn.smart.service.IGameDetailService;
import cn.smart.service.IGameInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value="/game")
public class GameInfoController extends AbstractController {

    @Autowired
	private IGameInfoService gameInfoService;

    @Autowired
    private IGameDetailService gameDetailService;

    @PostMapping(value = "/add")
    @ResponseBody
    @Logs(value = "新增游戏")
    public R add(@RequestBody GameInfoDO gameInfoDO) {
        gameInfoDO.setCreateTime(new Date());
		gameInfoService.save(gameInfoDO);
    	return R.ok("新增成功!");
    }

    @PostMapping(value = "/update")
    @ResponseBody
    @Logs(value = "更新游戏")
    public R update(@RequestBody GameInfoDO gameInfoDO) {
		gameInfoService.update(gameInfoDO);
		return R.ok("更新成功!");
    }

    @PostMapping(value = "/deleteBatch")
    @ResponseBody
    @Logs(value = "批量删除游戏")
    public R deleteGameInfoBatch(@RequestBody Long[] ids) {
		R r = gameInfoService.deleteGameInfoBatch(ids);
		if (!(0 == (int)r.get("code"))) {
		    return R.error(r.get("msg").toString());
        } else {
            return R.ok("删除成功!");
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public R list(GameInfoQueryCondition gameInfoQueryCondition) {

    	List<GameInfoDO> gameInfoDOList = gameInfoService.queryList(gameInfoQueryCondition);

        int total = gameInfoService.queryTotal(gameInfoQueryCondition);

        PageUtils pageUtils = new PageUtils(gameInfoDOList, total, gameInfoQueryCondition.getLimit(), gameInfoQueryCondition.getPage());

        return R.ok().put("game", pageUtils);
	}

    @PostMapping(value = "/detail/add")
    @ResponseBody
    @Logs(value = "新增游戏详情")
    public R add(@RequestBody GameDetailDO gameDetailDO) {

        GameDetailQueryCondition query = new GameDetailQueryCondition();
        query.setGameId(gameDetailDO.getGameId());

        List<GameDetailDO> detailList = gameDetailService.queryList(query);
        if (detailList.size() >= 3) {
            return R.error("已有3个选项, 不能继续添加!");
        }
        int countRight = 0;
        for (GameDetailDO gameDetail : detailList) {
            if (gameDetail.getRightAnswer()) {
                countRight += 1;
            }
        }
        if (countRight >= 1 && gameDetailDO.getRightAnswer()) {
            return R.error("当前已有一个正确选项, 无法继续添加正确选项!");
        }

        gameDetailDO.setCreateTime(new Date());
        gameDetailService.save(gameDetailDO);
        return R.ok("新增成功!");
    }

    @PostMapping(value = "/detail/update")
    @ResponseBody
    @Logs(value = "更新游戏详情")
    public R update(@RequestBody GameDetailDO gameDetailDO) {
        gameDetailService.update(gameDetailDO);
        return R.ok("更新成功!");
    }

    @PostMapping(value = "/detail/delete")
    @ResponseBody
    @Logs(value = "删除游戏详情")
    public R deleteGameDetail(Long id) {
        gameDetailService.deleteById(id);
        return R.ok("删除成功!");
    }

    @GetMapping("/detail")
    @ResponseBody
    public R gameDetail(GameDetailQueryCondition gameDetailQueryCondition) {

        List<GameDetailDO> gameDetailDOList = gameDetailService.queryList(gameDetailQueryCondition);

        return R.ok().put("detail", gameDetailDOList);
    }


}
