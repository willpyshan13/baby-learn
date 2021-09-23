package cn.smart.controller;

import cn.smart.common.utils.*;
import cn.smart.condition.PageviewsQueryCondition;
import cn.smart.entity.PageviewsDO;
import cn.smart.service.IPageviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/pageviews")
public class PageviewsController extends AbstractController {

    @Autowired
	private IPageviewsService pageviewsService;


    @PostMapping(value = "/add")
    @ResponseBody
    public R add(@RequestBody PageviewsDO pageviewsDO) {
		pageviewsService.save(pageviewsDO);
    	return R.ok("新增成功!");
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public R update(@RequestBody PageviewsDO pageviewsDO) {
		pageviewsService.update(pageviewsDO);
		return R.ok("更新成功!");
    }

    @PostMapping(value = "/deleteById")
    @ResponseBody
    public R deleteById(Long id) {
		pageviewsService.deleteById(id);
    	return R.ok("删除成功!");
    }

    @GetMapping("/chart")
    @ResponseBody
    public R chartList(PageviewsQueryCondition pageviewsQueryCondition) {

        List<Map<String, Object>> listPageviews = pageviewsService.queryViewsGroupByCreateDay(pageviewsQueryCondition);

        List<String> dates = null;
        if (Validator.isNotEmpty(pageviewsQueryCondition.getStartDay()) && Validator.isNotEmpty(pageviewsQueryCondition.getEndDay())) {
            dates = findDates(pageviewsQueryCondition.getStartDay(), pageviewsQueryCondition.getEndDay());
        }

        List<Object> xAxis = new ArrayList<>(listPageviews.size());
        List<Object> data = new ArrayList<>(listPageviews.size());

        for (String date : dates) {
            Object d = 0;
            for (Map map : listPageviews) {
                if (map.get("create_day").equals(date)) {
                    d = map.get("total");
                }
            }
            data.add(d);
        }

        return R.ok().put("list", listPageviews).put("xAxis", dates).put("data", data);
    }

    @GetMapping("/count/registered")
    @ResponseBody
    public R countRegistered(PageviewsQueryCondition pageviewsQueryCondition) {

        List<Map<String, Object>> listPageviews = pageviewsService.countRegistered(pageviewsQueryCondition);
        List<String> dates = null;
        if (Validator.isNotEmpty(pageviewsQueryCondition.getStartDay()) && Validator.isNotEmpty(pageviewsQueryCondition.getEndDay())) {
            dates = findDates(pageviewsQueryCondition.getStartDay(), pageviewsQueryCondition.getEndDay());
        }
        List<Object> xAxis = new ArrayList<>(listPageviews.size());
        List<Object> data = new ArrayList<>(listPageviews.size());

        for (String date : dates) {
            Object d = 0;
            for (Map map : listPageviews) {
                if (map.get("create_day").equals(date)) {
                   d = map.get("total");
                }
            }
            data.add(d);
        }

        return R.ok().put("list", listPageviews).put("xAxis", dates).put("data", data);
    }

    @GetMapping("/list")
    @ResponseBody
    public R list(PageviewsQueryCondition pageviewsQueryCondition) {

    	List<PageviewsDO> pageviewsDOList = pageviewsService.queryList(pageviewsQueryCondition);

        int total = pageviewsService.queryTotal(pageviewsQueryCondition);

        PageUtils pageUtils = new PageUtils(pageviewsDOList, total, pageviewsQueryCondition.getLimit(), pageviewsQueryCondition.getPage());

        return R.ok().put("pageviewsDOList", pageUtils);
	}


    public static List<String> findDates(String dBegin, String dEnd) {
        List<String> lDate = new ArrayList<>();

        lDate.add(dBegin);

        String date = dBegin;

        while (DateUtil.isBetweenOfDefaultDatePattern(date, dBegin, dEnd)) {
            try {
                date = DateUtils.getNextDt(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            lDate.add(date);
        }
        return lDate;
    }

	public static void main(String[] ar) {




    }

}
