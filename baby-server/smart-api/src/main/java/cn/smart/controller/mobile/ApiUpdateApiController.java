package cn.smart.controller.mobile;

import cn.smart.common.annotation.AuthIgnore;
import cn.smart.common.api.result.ApiResult;
import cn.smart.common.api.result.ApiStatus;
import cn.smart.condition.AppQueryCondition;
import cn.smart.condition.MachineUpgradeQueryCondition;
import cn.smart.entity.AppDO;
import cn.smart.entity.MachineUpgradeDO;
import cn.smart.service.IAppService;
import cn.smart.service.IMachineUpgradeService;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/mobile/app/")
public class ApiUpdateApiController {

    @Autowired
    private IAppService appService;

    @Autowired
    private IMachineUpgradeService machineUpgradeService;

    @AuthIgnore
    @RequestMapping(value = "/check")
    public ApiResult getThirdLoginInfo(@RequestParam(value = "type") int type,
                                       @RequestParam(value = "versionCode") Double versionCode,
                                       @RequestParam(value = "serialNumber", required = false) String serialNumber) {

        // app 类型 0:桌面 1:课程 2:直播 3:视频 4:个人中心 5:系统
        int system = 5;

        AppQueryCondition appQueryCondition = new AppQueryCondition();
        appQueryCondition.setAppType(type);
        List<AppDO> appList = appService.queryList(appQueryCondition);

        if (appList.size() == 0) {
            return new ApiResult("您的版本是最新版本");
        }

        AppDO app = appList.get(0);
        if (app != null) {
            if (app.getAppType() == system) {
                MachineUpgradeQueryCondition query = new MachineUpgradeQueryCondition();

                // 在黑名单中不更新
                query.setUpgrade(0);
                List<MachineUpgradeDO> listMachine = machineUpgradeService.queryList(query);
                boolean contain = false;
                for (MachineUpgradeDO machine : listMachine) {
                    // 更新机器在黑名单中, 不需要检查更新
                    if (null != serialNumber && serialNumber.equals(machine.getSerialNumber())) {
                        contain = true;
                    }
                }
                if (contain) {
                    return new ApiResult("你已经是最新版本");
                }

                // 开启白名单
                if (app.getWhite() == 1) {
                    // 查询在白名单的机器
                    query.setUpgrade(1);
                    listMachine = machineUpgradeService.queryList(query);
                    boolean update = false;
                    for(MachineUpgradeDO machine : listMachine) {
                        if (null != serialNumber && serialNumber.equals(machine.getSerialNumber())) {
                            update = true;
                        }
                    }
                    if (update) {
                        return checkVersion(versionCode, app);
                    } else {
                        return new ApiResult("您的版本是最新版本");
                    }
                }  else {
                    return checkVersion(versionCode, app);
                }

            } else {
                return checkVersion(versionCode, app);
            }
        } else {
            return new ApiResult("您的版本是最新版本");
        }
    }

    @AuthIgnore
    @PostMapping("/machine")
    public ApiResult uploadMachineInfo(@RequestParam("serialNumber") String serialNumber,
                                       @RequestParam("currentVersion") String currentVersion,
                                       @RequestParam(value = "remark", required = false) String remark) {

        if (null == serialNumber) {
            return new ApiResult(-1, ApiStatus.SC_SERIAL_NUMBER_IS_NULL, "序列号不能为空!");
        }
        if (null == currentVersion) {
            return new ApiResult(-1, ApiStatus.SC_CURRENT_VERSION_IS_NULL, "当前版本号不能为空!");
        }

        MachineUpgradeQueryCondition query = new MachineUpgradeQueryCondition();
        query.setSerialNumber(serialNumber);
        List<MachineUpgradeDO> machineList = machineUpgradeService.queryList(query);
        MachineUpgradeDO machine;

        try {
            if (machineList.size() > 0 && machineList.get(0) != null) {
                machine = machineList.get(0);
                machine.setCurrentVersion(currentVersion);
                if (null != remark) {
                    machine.setRemark(remark);
                }
                machine.setUpdateTime(new Date());
                machineUpgradeService.update(machine);
            } else {
                machine = new MachineUpgradeDO();
                machine.setSerialNumber(serialNumber);
                machine.setCurrentVersion(currentVersion);
                // 默认正常状态 可更新
                machine.setUpgrade(2);
                machine.setCreateTime(new Date());
                if (null != remark) {
                    machine.setRemark(remark);
                }
                machineUpgradeService.save(machine);
            }
        } catch (Exception e) {
            return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, "记录机器信息失败!");
        }
        return new ApiResult("记录机器信息成功!");
    }

    private ApiResult checkVersion(@RequestParam(value = "versionCode") Double versionCode, AppDO app) {
        // 检查版本
        if (app.getVersionCode() <= versionCode) {
            return new ApiResult("您的版本是最新版本");
        } else {
            return new ApiResult(app);
        }
    }
}
