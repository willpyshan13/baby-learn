package cn.smart.support.talkcloud.business;

import cn.smart.common.ConfigConstant;
import cn.smart.common.utils.Validator;
import cn.smart.entity.CoursewareDO;
import cn.smart.support.talkcloud.app.HttpUtils;
import cn.smart.support.talkcloud.app.ParamsBean;
import cn.smart.support.talkcloud.utils.Methods;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ye
 */
@Component
public class FileBusiness {

    /**
     * 文档上传(课件上传)
     * @param coursewareDO 课件信息对象
     * @return
     */
    public Map uploadCourseware(Long serial, CoursewareDO coursewareDO) {

        String url = ConfigConstant.getBaseURL(Methods.UPLOAD_FILE);

        Map<String, String> fileMap = new HashMap<>();
        fileMap.put("filedata", coursewareDO.getResourceUrl());

        Map<String, String> textMap = new HashMap<>();
        textMap.put("key", ConfigConstant.KEY); //必填 企业id authkey

        if (!Validator.isEmpty(serial)) {
            textMap.put("serial", serial.toString()); //房间号
            textMap.put("isdefault", coursewareDO.getIsDefault().toString());//是否是缺省显示文件0:不是  1：是    只有上传到房间的文件才需要设置。
        }

        if (!Validator.isEmpty(coursewareDO.getIsOpen())) {
            textMap.put("isopen", coursewareDO.getIsOpen().toString()); //选填 是否是公开文档 0：表示非公开文档 1：表示公开文档公开文档表示公司的其他房间都可以关联此文档，非公开文档表示只对本房间
        }

        textMap.put("conversion", "1"); //必填1：转换

        textMap.put("dynamicppt", coursewareDO.getDynamicPpt().toString());//是否是动态ppt   0: 非动态ppt   1: 动态ppt

        String result = HttpUtils.formUpload(url, textMap, fileMap);

        return (Map) JSONObject.parse(result);

    }

    /**
     * 删除课件
     */
    public Map deleteFile(Long[] ids){

        String url = ConfigConstant.getBaseURL(Methods.DELETE_FILE);

        List<ParamsBean> list = new ArrayList<>();
        list.add(new ParamsBean("key", ConfigConstant.KEY)); //必填 企业id authkey
        list.add(new ParamsBean("fileidarr[]", StringUtils.join(ids, ",")));//必填，文件id
        String result = HttpUtils.httpSend(url, list);

        return (Map) JSONObject.parse(result);
    }
}
