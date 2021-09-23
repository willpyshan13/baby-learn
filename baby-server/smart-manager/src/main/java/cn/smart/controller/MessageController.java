package cn.smart.controller;

import cn.smart.common.annotation.Logs;
import cn.smart.common.utils.PageUtils;
import cn.smart.common.utils.R;
import cn.smart.entity.GoodsDO;
import cn.smart.entity.MessageDO;
import cn.smart.condition.MessageQueryCondition;
import cn.smart.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping(value="/message")
public class MessageController extends AbstractController {

    @Autowired
	private IMessageService messageService;


    @PostMapping(value = "/add")
    @ResponseBody
    @Logs(value = "新增消息")
    public R add(@RequestBody MessageDO messageDO) {
		messageService.save(messageDO);
    	return R.ok("新增成功!");
    }

    @PostMapping(value = "/update")
    @ResponseBody
    @Logs(value = "更新消息")
    public R update(@RequestBody MessageDO messageDO) {
		messageService.update(messageDO);
		return R.ok("更新成功!");
    }

    @PostMapping(value = "/deleteById")
    @ResponseBody
    @Logs(value = "删除消息")
    public R deleteById(Long id) {
		messageService.deleteById(id);
    	return R.ok("删除成功!");
    }

    @GetMapping("/queryById")
    @ResponseBody
    public R queryById(Long id) {

        MessageDO message = messageService.queryById(id);

        return R.ok().put("message", message);
    }

    @GetMapping("/list")
    @ResponseBody
    public R list(MessageQueryCondition messageQueryCondition) {

    	List<MessageDO> messageDOList = messageService.queryList(messageQueryCondition);

        int total = messageService.queryTotal(messageQueryCondition);

        PageUtils pageUtils = new PageUtils(messageDOList, total, messageQueryCondition.getLimit(), messageQueryCondition.getPage());

        return R.ok().put("messageList", pageUtils);
	}

}
