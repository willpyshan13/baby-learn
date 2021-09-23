package cn.smart.controller.mobile;

import cn.smart.common.annotation.AuthIgnore;
import cn.smart.common.utils.R;
import cn.smart.support.qiniu.OSSFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/mobile/config")
@RestController
public class ApiConfigController {


    @AuthIgnore
    @GetMapping("/upload")
    public R uploadConfig(String fileType,
                          @RequestParam(value = "fileName") String fileName,
                          @RequestParam(value = "operator", required = false) String operator) {

        Map<String, String> map;

        if (fileType.contains("image")) {
            map = OSSFactory.getImageConfig(fileName);
        } else if (fileType.contains("video")) {
            map = OSSFactory.getVideoConfig();
        } else if (fileType.contains("audio")) {
            map = OSSFactory.getAudioConfig();
        } else if (fileType.contains("record")) {
            map = OSSFactory.getRecordConfig(fileName);
        } else {
            map = OSSFactory.getFileConfig(fileName);
        }
        return R.ok().put("config", map);
    }

}
