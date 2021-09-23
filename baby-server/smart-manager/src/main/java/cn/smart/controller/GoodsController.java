package cn.smart.controller;

import cn.smart.common.annotation.Logs;
import cn.smart.common.utils.PageUtils;
import cn.smart.common.utils.R;
import cn.smart.entity.GoodsDO;
import cn.smart.condition.GoodsQueryCondition;
import cn.smart.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping(value="/goods")
public class GoodsController extends AbstractController {

    @Autowired
	private IGoodsService goodsService;


    @PostMapping(value = "/add")
    @ResponseBody
    @Logs(value = "新增商品")
    public R add(@RequestBody GoodsDO goodsDO) {
		goodsService.save(goodsDO);
    	return R.ok("新增成功!");
    }

    @PostMapping(value = "/update")
    @ResponseBody
    @Logs(value = "更新商品信息")
    public R update(@RequestBody GoodsDO goodsDO) {
		goodsService.update(goodsDO);
		return R.ok("更新成功!");
    }

    @PostMapping(value = "/deleteById")
    @ResponseBody
    @Logs(value = "删除商品")
    public R deleteById(Long id) {
		goodsService.deleteById(id);
    	return R.ok("删除成功!");
    }

    @GetMapping("/list")
    @ResponseBody
    public R list(GoodsQueryCondition goodsQueryCondition) {

    	List<GoodsDO> goodsDOList = goodsService.queryList(goodsQueryCondition);

        int total = goodsService.queryTotal(goodsQueryCondition);

        PageUtils pageUtils = new PageUtils(goodsDOList, total, goodsQueryCondition.getLimit(), goodsQueryCondition.getPage());

        return R.ok().put("goodsList", pageUtils);
	}

	@GetMapping("/queryById")
    @ResponseBody
    public R queryById(Long id) {

        GoodsDO goods = goodsService.queryById(id);

        return R.ok().put("goods", goods);
    }

}
