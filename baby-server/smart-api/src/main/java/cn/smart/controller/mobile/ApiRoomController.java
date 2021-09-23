package cn.smart.controller.mobile;

import cn.smart.common.annotation.LoginUser;
import cn.smart.common.api.result.ApiResult;
import cn.smart.common.api.result.ApiStatus;
import cn.smart.entity.PersonDO;
import cn.smart.service.IRoomService;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mobile/room")
public class ApiRoomController {

    @Autowired
    private IRoomService roomService;

    @RequestMapping("/list")
    @ResponseBody
    public ApiResult getRoomList(@LoginUser PersonDO person) {

        if (ObjectUtils.isEmpty(person)) {
            return new ApiResult(-1, ApiStatus.SC_TOKEN_INVALID, "token 无效, 请重新登录!");
        }

        ApiResult result;
        try {
            result = roomService.getPersonRoomList(person.getPersonId());
        } catch (Exception e) {
            return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, "获取用户直播信息失败");
        }

        return result;
    }
}
