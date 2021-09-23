package cn.smart.controller.mobile;

import cn.smart.bo.GoodsBO;
import cn.smart.common.annotation.LoginUser;
import cn.smart.common.annotation.Logs;
import cn.smart.common.api.result.ApiResult;
import cn.smart.common.api.result.ApiStatus;
import cn.smart.common.exception.CustomException;
import cn.smart.common.utils.DateUtils;
import cn.smart.common.utils.PageUtils;
import cn.smart.common.utils.Validator;
import cn.smart.condition.GoodsQueryCondition;
import cn.smart.entity.GoodsDO;
import cn.smart.entity.PersonDO;
import cn.smart.service.IGoodsService;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ye
 */
@RestController
@RequestMapping("/mobile/goods")
public class ApiGoodsController {

    @Autowired
    private IGoodsService goodsService;

    /**
     * 2018年9月14日 积分相关逻辑修改 接口废弃
     * 获取商品列表
     * @param virtual   是否虚拟商品
     * @param pageIndex 当前页
     * @param pageSize  分页大小
     * @return
     */
    @GetMapping("/list")
    @Deprecated
    public ApiResult getGoodsList(@RequestParam(value = "virtual", defaultValue = "0") int virtual,
                                  @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
                                  @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize) {
        GoodsQueryCondition query = new GoodsQueryCondition();
        query.setPage(pageIndex);
        query.setLimit(pageSize);
        query.setVirtual(virtual);
        List<GoodsDO> goodsList = goodsService.queryList(query);
        int total = goodsService.queryTotal(query);

        List<GoodsBO> list = new ArrayList<>(goodsList.size());
        GoodsBO goods;
        for (GoodsDO g : goodsList) {
            goods = new GoodsBO();
            goods.setId(g.getId());
            goods.setName(g.getName());
            goods.setImageUrl(g.getImageUrl());
            goods.setPoints(g.getPoints());
            list.add(goods);
        }

        PageUtils pageUtils = new PageUtils(list, total, query.getLimit(), query.getPage());

        return new ApiResult(pageUtils);
    }

    /**
     * 2018年9月14日 积分相关逻辑修改 接口废弃
     * 获取虚拟商品列表 ( 含 是否兑换)
     * @param personDO 用户信息
     * @param pageIndex 当前页
     * @param pageSize 分页大小
     * @return
     */
    @GetMapping("/virtual")
    @Deprecated
    public ApiResult exchangeList(@LoginUser PersonDO personDO,
                                  @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
                                  @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize) {
        if (personDO == null) {
            return new ApiResult(-1, ApiStatus.SC_TOKEN_INVALID, "用户信息无效，请重新登录!");
        }
        GoodsQueryCondition query = new GoodsQueryCondition();
        query.setPage(pageIndex);
        query.setLimit(pageSize);
        query.setVirtual(1);
        List<GoodsBO> list = goodsService.queryVirtualGoodsList(personDO.getPersonId(), query);

        int total = goodsService.queryTotal(query);

        PageUtils pageUtils = new PageUtils(list, total, query.getLimit(), query.getPage());

        return new ApiResult(pageUtils);
    }

    /**
     * 2018年9月14日 积分相关逻辑修改 接口废弃
     * 商品兑换
     * @param personDO 用户
     * @param goodsId 商品id
     * @return
     */
    @PostMapping("/exchange")
    @Logs(value = "积分兑换商品")
    @Deprecated
    public ApiResult exchangeGame(@LoginUser PersonDO personDO,
                                  @RequestParam("goodsId") Long goodsId) {

        if (Validator.isEmpty(personDO)) {
            return new ApiResult(-1, ApiStatus.SC_TOKEN_INVALID, "token 无效, 请重新登录!");
        }
        try {
            return goodsService.exchange(personDO.getPersonId(), goodsId);
        } catch (CustomException e) {
            return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, "兑换失败!");
        }
    }
}

