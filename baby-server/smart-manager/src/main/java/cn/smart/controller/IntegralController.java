package cn.smart.controller;

import cn.smart.common.annotation.Logs;
import cn.smart.common.utils.PageUtils;
import cn.smart.common.utils.R;
import cn.smart.condition.IntegralQueryCondition;
import cn.smart.entity.ExchangeDetailDO;
import cn.smart.entity.IntegralDO;
import cn.smart.service.IExchangeDetailService;
import cn.smart.service.IIntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value="/integral")
public class IntegralController extends AbstractController {

    @Autowired
	private IIntegralService integralService;

    @Autowired
    private IExchangeDetailService exchangeDetailService;


    @PostMapping(value = "/add")
    @ResponseBody
    @Logs(value = "新增积分明细")
    public R add(@RequestBody IntegralDO integralDO) {
		integralService.save(integralDO);
    	return R.ok("新增成功!");
    }

    @PostMapping(value = "/update")
    @ResponseBody
    @Logs(value = "更新积分明细")
    public R update(@RequestBody IntegralDO integralDO) {
		integralService.update(integralDO);
		return R.ok("更新成功!");
    }

    @PostMapping(value = "/deleteById")
    @ResponseBody
    @Logs(value = "删除积分明细")
    public R deleteById(Long id) {
    	return integralService.deleteIntegralById(id);
    }

    @GetMapping("/list")
    @ResponseBody
    public R list(IntegralQueryCondition integralQueryCondition) {

    	List<IntegralDO> integralDOList = integralService.queryList(integralQueryCondition);

        int total = integralService.queryTotal(integralQueryCondition);

        PageUtils pageUtils = new PageUtils(integralDOList, total, integralQueryCondition.getLimit(), integralQueryCondition.getPage());

        return R.ok().put("integral", pageUtils);
	}

	@GetMapping("/exchange/detail")
    @ResponseBody
    public R queryById(Long id) {
        ExchangeDetailDO exchangeDetail = exchangeDetailService.queryById(id);
        return R.ok().put("exchangeDetail", exchangeDetail);
    }

	@PostMapping("/exchange/goods")
    @ResponseBody
    @Logs(value = "积分兑换商品")
    public R exchangeGoods(@RequestBody ExchangeDetailDO exchangeDetail) {

        if (exchangeDetail.getPersonId() == null) {
            return R.error("请选择用户!");
        }

        if (exchangeDetail.getPoints() < 0) {
            return R.error("消耗积分数必须大于或等于0");
        }

        if (exchangeDetail.getCreateBy() == null) {
            exchangeDetail.setCreateBy(getUserId());
            exchangeDetail.setCreateTime(new Date());
        } else {
            exchangeDetail.setUpdateBy(getUserId());
            exchangeDetail.setUpdateTime(new Date());
        }
        try {
            return integralService.exchangeGoods(exchangeDetail);
        } catch (Exception e) {
            return R.error(e.getMessage());
        }
    }

}
