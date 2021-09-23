package cn.smart.controller;

import cn.smart.common.utils.PageUtils;
import cn.smart.common.utils.R;
import cn.smart.entity.RecordingDO;
import cn.smart.condition.RecordingQueryCondition;
import cn.smart.service.IRecordingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ye
 */
@Controller
@RequestMapping(value="/recording")
public class RecordingController extends AbstractController {

    @Autowired
	private IRecordingService recordingService;

    @PostMapping(value = "/add")
    @ResponseBody
    public R add(@RequestBody RecordingDO recordingDO) {
		recordingService.saveSelective(recordingDO);
    	return R.ok("新增成功!");
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public R update(@RequestBody RecordingDO recordingDO) {
		recordingService.update(recordingDO);
		return R.ok("更新成功!");
    }

    @PostMapping(value = "/deleteById")
    @ResponseBody
    public R deleteById(Long id) {
		recordingService.deleteById(id);
    	return R.ok("删除成功!");
    }

    @GetMapping("/list")
    @ResponseBody
    public R list(RecordingQueryCondition recordingQueryCondition) {

    	List<RecordingDO> recordingDOList = recordingService.queryList(recordingQueryCondition);

        int total = recordingService.queryTotal(recordingQueryCondition);

        PageUtils pageUtils = new PageUtils(recordingDOList, total, recordingQueryCondition.getLimit(), recordingQueryCondition.getPage());

        return R.ok().put("recordingDOList", pageUtils);
	}

}
