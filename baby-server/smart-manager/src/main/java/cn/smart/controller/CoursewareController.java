package cn.smart.controller;

import cn.smart.common.annotation.Logs;
import cn.smart.common.component.FileComponent;
import cn.smart.common.exception.CustomException;
import cn.smart.common.utils.PageUtils;
import cn.smart.common.utils.R;
import cn.smart.common.utils.Validator;
import cn.smart.condition.CoursewareQueryCondition;
import cn.smart.entity.CoursewareDO;
import cn.smart.service.ICoursewareService;
import cn.smart.support.talkcloud.business.FileBusiness;
import cn.smart.support.talkcloud.utils.ErrorCode;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/courseware")
public class CoursewareController extends AbstractController {

    private static final String SEPARATOR = File.separator;

    @Autowired
	private ICoursewareService coursewareService;
    
    @Autowired
    private FileComponent fileComponent;

    @Autowired
    private FileBusiness fileBusiness;


    @PostMapping(value = "/add")
    @ResponseBody
    @Logs(value = "新增课件")
    public R add(@RequestBody Map<String, Object> map) {
        Long roomId = null;
        if (!Validator.isEmpty(map.get("roomId"))) {
            roomId = ((Integer) map.get("roomId")).longValue();
        }
		coursewareService.saveCourseware(roomId, JSONObject.parseObject(map.get("courseware").toString(), CoursewareDO.class));
    	return R.ok("新增成功!");
    }

    @PostMapping(value = "/update")
    @ResponseBody
    @Logs(value = "更新课件")
    public R update(@RequestBody CoursewareDO coursewareDO) {
		coursewareService.update(coursewareDO);
		return R.ok("更新成功!");
    }

    @PostMapping(value = "/deleteById")
    @ResponseBody
    @Logs(value = "删除课件")
    public R deleteById(Long id) {
		coursewareService.deleteById(id);

		// 删除拓课云文件
        Long[] ids = new Long[1];
        ids[0] = id;
        fileBusiness.deleteFile(ids);

    	return R.ok("删除成功!");
    }

    @GetMapping("/list")
    @ResponseBody
    public R list(CoursewareQueryCondition coursewareQueryCondition) {

    	List<CoursewareDO> coursewareDOList = coursewareService.queryList(coursewareQueryCondition);

        int total = coursewareService.queryTotal(coursewareQueryCondition);

        PageUtils pageUtils = new PageUtils(coursewareDOList, total, coursewareQueryCondition.getLimit(), coursewareQueryCondition.getPage());

        return R.ok().put("coursewareList", pageUtils);
	}

    /**
     * 获取未分配教室的公开课件
     * @param coursewareQueryCondition
     * @return
     */
	@GetMapping("/listToRoom")
    @ResponseBody
    public R listToRoom(CoursewareQueryCondition coursewareQueryCondition) {

	    coursewareQueryCondition.setIsOpen(1);
        List<CoursewareDO> coursewareDOList = coursewareService.getCoursewareToRoom(coursewareQueryCondition);

        int total = coursewareService.countCoursewareToRoom(coursewareQueryCondition);

        PageUtils pageUtils = new PageUtils(coursewareDOList, total, coursewareQueryCondition.getLimit(), coursewareQueryCondition.getPage());

        return R.ok().put("coursewareList", pageUtils);
    }

	@PostMapping("/upload")
    @ResponseBody
    @Logs(value = "上传课件")
    public R upload(MultipartFile file, HttpServletRequest request) {

        if (null == file) {
            return R.error(-1, "请上传文件!");
        }
        String dynamicPpt = request.getParameter("dynamicPpt");
        String isOpen = request.getParameter("isOpen");

        String[] files = file.getOriginalFilename().split("\\.");
        String prefix = files[0];
        String suffix = files[1];

        String path = request.getSession().getServletContext().getRealPath("/") + SEPARATOR
                + "WEB-INF" + SEPARATOR + "courseware" + SEPARATOR + System.currentTimeMillis() + SEPARATOR + prefix + "." + suffix;

        CoursewareDO coursewareDO = new CoursewareDO();

        try {
            // 保存临时文件
            fileComponent.upload(file, path);

            coursewareDO.setName(prefix);
            coursewareDO.setResourceUrl(path);

            coursewareDO.setDynamicPpt(Integer.valueOf(dynamicPpt));
            coursewareDO.setIsOpen(Integer.valueOf(isOpen));

        } catch (IOException e) {
            e.printStackTrace();
        }

        Map result = fileBusiness.uploadCourseware(null , coursewareDO);
        // 上传课件到拓课
        Integer resultCode = (Integer) result.get("result");
        // 执行成功
        if (resultCode == 0) {
            coursewareDO.setId(((Integer) result.get("fileid")).longValue());
            coursewareDO.setResourceUrl(result.get("downloadpath").toString());
            coursewareDO.setSize(Double.valueOf(result.get("size").toString()));
            // 删除本地临时文件
            fileComponent.deleteFile(path);

            return R.ok().put("courseware", coursewareDO).put("tempUrl", path);

        } else {
            throw new CustomException("拓课云上传课件失败! code = " + resultCode.toString() + ", msg : " + ErrorCode.getErrorMessage(resultCode));
        }

    }

    @PostMapping("/deleteFile")
    @ResponseBody
    @Logs(value = "删除课件文件")
    public R deleteFile(Long fileId, String filePath) {


        // 删除拓课云上文件
        if (!Validator.isEmpty(fileId)) {
            fileBusiness.deleteFile(new Long[] {fileId});
        }

        // 删除本地文件
        Boolean success = true;
        File file = new File(filePath);
        if (!Validator.isEmpty(file)) {
            success = fileComponent.deleteFile(filePath);
        }

        if (success) {
            return R.ok("删除成功!");
        } else {
            return R.error(-1, "删除文件失败!");
        }
    }
}
