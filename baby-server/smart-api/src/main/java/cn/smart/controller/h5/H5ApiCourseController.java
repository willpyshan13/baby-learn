package cn.smart.controller.h5;


import cn.smart.common.annotation.AuthIgnore;
import cn.smart.common.annotation.LoginUser;
import cn.smart.common.annotation.Logs;
import cn.smart.common.api.result.ApiResult;
import cn.smart.common.api.result.ApiStatus;
import cn.smart.common.utils.Validator;
import cn.smart.bo.GameInfoBO;
import cn.smart.service.IApiPersonService;
import cn.smart.condition.GameInfoQueryCondition;
import cn.smart.entity.PersonDO;
import cn.smart.service.ICourseInfoService;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/h5/api/course")
public class H5ApiCourseController {

    private static final Logger logger = LogManager.getLogger(H5ApiCourseController.class);

    @Autowired
    private ICourseInfoService courseInfoService;

    @Autowired
    private IApiPersonService personService;

    @AuthIgnore
    @GetMapping("/game")
    public ApiResult courseGame(@RequestParam("id") Long id,
                                @RequestParam("type") Integer type) {
        if (Validator.isEmpty(id)) {
            return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, "课程编号不能为空你");
        }

        GameInfoQueryCondition query = new GameInfoQueryCondition();
        query.setCourseId(id);
        query.setType(type);
        List<GameInfoBO> listCourseGameDetail = courseInfoService.queryCourseGameDetail(query);

        return new ApiResult(listCourseGameDetail);

    }

    @PostMapping(value = "/learnRecord")
    @ResponseBody
    @Logs(value = "H5用户学习记录")
    public ApiResult setLearnRecord(@LoginUser PersonDO personDO,
                                    @RequestParam(value = "learnId") Long learnId,
                                    @RequestParam(value = "type") Integer type) {
        if (Validator.isEmpty(personDO)) {
            return new ApiResult(-1, ApiStatus.SC_TOKEN_INVALID, "用户信息无效，请重新登录");
        }

        try{
            personService.savePersonLearnRecord(personDO, learnId, type, null, null);
            return new ApiResult("操作成功");
        } catch (Exception e) {
            logger.error(e);
            return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, "服务器异常");
        }

    }
}
