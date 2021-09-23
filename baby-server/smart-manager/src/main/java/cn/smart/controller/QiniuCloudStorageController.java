package cn.smart.controller;

import cn.smart.common.utils.R;
import cn.smart.support.qiniu.OSSFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/qiniu")
public class QiniuCloudStorageController {

    @PostMapping("/upload")
    @ResponseBody
    public R uploadFile(MultipartFile file, HttpServletRequest request) {

        String url = null;
        try {
            if (file.getContentType().contains("image")) {
                url = Objects.requireNonNull(OSSFactory.buildImage()).upload(file.getBytes(), file.getOriginalFilename());
            } else if (file.getContentType().contains("video")) {
                url = Objects.requireNonNull(OSSFactory.buildVideo()).upload(file.getBytes(), file.getOriginalFilename());
            } else if (file.getContentType().contains("audio")) {
                url = Objects.requireNonNull(OSSFactory.buildAudio()).upload(file.getBytes(), file.getOriginalFilename());
            } else {
                url = Objects.requireNonNull(OSSFactory.buildFile()).upload(file.getBytes(), file.getOriginalFilename());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            assert url != null;
            return R.ok("上传成功").put("resourceUrl", URLDecoder.decode(url, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return R.error("url Encode 失败");
        }
    }

    @GetMapping("/upload/config")
    @ResponseBody
    public R uploadConfig(String fileType,
                          @RequestParam(value = "fileName", required = false) String fileName,
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

    @PostMapping("/remove")
    @ResponseBody
    public R removeFile(String fileType, String fileName) {

        OSSFactory.removeFile(fileType, fileName);
        return R.ok("删除成功");
    }

}
