package cn.smart.controller.h5;

import cn.smart.common.annotation.LoginUser;
import cn.smart.common.annotation.Logs;
import cn.smart.common.api.result.ApiResult;
import cn.smart.common.api.result.ApiStatus;
import cn.smart.common.utils.Validator;
import cn.smart.service.IApiPersonService;
import cn.smart.entity.PersonDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/h5/api/person")
public class H5ApiPersonController {

    private final IApiPersonService personService;

    @Autowired
    public H5ApiPersonController(IApiPersonService personService) {
        this.personService = personService;
    }

    /**
     * 用户购买课阶
     * @param person 用户信息
     * @return ApiResult
     */
    @GetMapping("/bought/stage")
    public ApiResult boughtStage(@LoginUser PersonDO person) {

        if (Validator.isEmpty(person.getPersonId())) {
            return new ApiResult(-1, ApiStatus.SC_TOKEN_INVALID, "请先登录!");
        }
        return  personService.boughtCourseStage(person.getPersonId());
    }

    /**
     * 个人信息修改-注册时
     * @param person 当前用户
     * @param userName 用户名
     * @param birthday 生日
     * @param gender 性别
     * @return
     */
    @PostMapping("/detail")
    @Logs(value = "H5-修改用户信息")
    public ApiResult updatePerson(@LoginUser PersonDO person,
                                  @RequestParam("userName") String userName,
                                  @RequestParam("birthday") String birthday,
                                  @RequestParam("gender") Boolean gender) {
        if (Validator.isEmpty(userName)) {
            return new ApiResult(-1, ApiStatus.SC_USER_NAME_NULL, "姓名不能为空!");
        }
        if (Validator.isEmpty(birthday)) {
            return new ApiResult(-1, ApiStatus.SC_BIRTHDAY_NULL, "生日不能为空!");
        }
        person.setUserName(userName);
        person.setBirthday(birthday);
        person.setGender(gender);

        return personService.updatePerson(person, null, null);
    }

    /**
     * 用户个人详情
     * @param person 用户
     * @return ApiResult
     */
    @GetMapping("/detail")
    public ApiResult personalDetail(@LoginUser PersonDO person) {

        if (Validator.isEmpty(person.getPersonId())) {
            return new ApiResult(-1, ApiStatus.SC_TOKEN_INVALID, "请先登录!");
        }
        return personService.queryById(person.getPersonId());
    }

}
