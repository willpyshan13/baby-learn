package cn.smart.controller;

import cn.smart.common.annotation.Logs;
import cn.smart.common.utils.PageUtils;
import cn.smart.common.utils.R;
import cn.smart.condition.AppQueryCondition;
import cn.smart.condition.MachineUpgradeQueryCondition;
import cn.smart.entity.AppDO;
import cn.smart.entity.MachineUpgradeDO;
import cn.smart.service.IAppService;
import cn.smart.service.IMachineUpgradeService;
import cn.smart.service.impl.ReportServiceImpl;
import cn.smart.support.scheduler.job.RecordingGeneratorJob;
import cn.smart.support.wx.service.WeixinTemplateMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value="/app")
public class AppController extends AbstractController {

    @Autowired
	private IAppService appService;

    @Autowired
    private IMachineUpgradeService machineUpgradeService;

    @Autowired
    RecordingGeneratorJob recordingGeneratorJob;

    @Autowired
    WeixinTemplateMsgService weixinTemplateMsgService;

    @Autowired
    ReportServiceImpl reportService;


    @PostMapping(value = "/add")
    @ResponseBody
    @Logs(value = "新增版本信息")
    public R add(@RequestBody AppDO appDO) {
		appService.save(appDO);
    	return R.ok("新增成功!");
    }

    @PostMapping(value = "/update")
    @ResponseBody
    @Logs(value = "更新版本信息")
    public R update(@RequestBody AppDO appDO) {
        if (appDO.getAppId() == null) {
            appService.save(appDO);
        } else {
            appService.update(appDO);
        }
		return R.ok("更新成功!");
    }

    @PostMapping(value = "/deleteById")
    @ResponseBody
    @Logs(value = "删除版本信息")
    public R deleteById(Long id) {
		appService.deleteById(id);
    	return R.ok("删除成功!");
    }

    @GetMapping("/list")
    @ResponseBody
    public R list(AppQueryCondition appQueryCondition) {

//        recordingGeneratorJob.generateRecordingBackUp(1318170815L);

//        PersonDO person = new PersonDO();
//        person.setPersonId(104L);
//        person.setNickName("Eva");
//        person.setWeekReportId(11L);
//        person.setWxMpId("ol1oh1h1i5VMWoDfU2Bjfn_c8fMw");
//        weixinTemplateMsgService.sendWeekReportTemplateMsg(person);

//        Long[] courseIds = new Long[]{198L,197L,196L,195L,194L};
//        reportService.generateWeekReport(3L, courseIds, 113L);

    	List<AppDO> appDOList = appService.queryList(appQueryCondition);

        int total = appService.queryTotal(appQueryCondition);

        PageUtils pageUtils = new PageUtils(appDOList, total, appQueryCondition.getLimit(), appQueryCondition.getPage());

        return R.ok().put("appVersion", pageUtils);
	}

    @GetMapping("/machine/list")
    @ResponseBody
    public R listMachine(MachineUpgradeQueryCondition query) {

        List<MachineUpgradeDO> appDOList = machineUpgradeService.queryList(query);

        int total = machineUpgradeService.queryTotal(query);

        PageUtils pageUtils = new PageUtils(appDOList, total, query.getLimit(), query.getPage());

        return R.ok().put("machine", pageUtils);
    }

    @PostMapping("/machine/deleteBatch")
    @ResponseBody
    @Logs(value = "批量删除机器信息")
    public R deleteBatch(@RequestBody Long[] ids) {
        machineUpgradeService.deleteBatch(ids);
        return R.ok("删除成功!");
    }

    @PostMapping("/machine/updateBatch")
    @ResponseBody
    @Logs(value = "批量更新机器信息")
    public R updateBatch(@RequestBody MachineUpgradeDO machineUpgrade) {
        machineUpgradeService.updateBatch(machineUpgrade);
        return R.ok("更新成功!");
    }

}
