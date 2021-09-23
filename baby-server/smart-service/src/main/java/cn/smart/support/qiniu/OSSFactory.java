package cn.smart.support.qiniu;

import cn.smart.common.ConfigConstant;
import cn.smart.common.spring.SpringContextUtils;
import cn.smart.common.utils.Constant;
import cn.smart.service.ISysConfigService;

import java.util.Map;

public final class OSSFactory {

    private static ISysConfigService sysConfigService;

    private static CloudStorageConfig config;

    static {
        OSSFactory.sysConfigService = (ISysConfigService) SpringContextUtils.getBean("sysConfigService");
        OSSFactory.config = sysConfigService.getConfigObject(ConfigConstant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);

    }

    public static CloudStorageService buildVideo(){
        //获取云存储配置信息

        if(config.getType() == Constant.CloudService.QINIU.getValue()){
            return new QiniuCloudStorageService(config, Constant.FileType.VIDEO.getValue(), null);
        }
        return null;
    }

    public static CloudStorageService buildFile(){
        //获取云存储配置信息

        if(config.getType() == Constant.CloudService.QINIU.getValue()){
            return new QiniuCloudStorageService(config, Constant.FileType.FILE.getValue(), null);
        }
        return null;
    }

    public static CloudStorageService buildImage(){
        //获取云存储配置信息

        if(config.getType() == Constant.CloudService.QINIU.getValue()){
            return new QiniuCloudStorageService(config, Constant.FileType.IMAGE.getValue(), null);
        }
        return null;
    }

    public static CloudStorageService buildAudio(){
        //获取云存储配置信息

        if(config.getType() == Constant.CloudService.QINIU.getValue()){
            return new QiniuCloudStorageService(config, Constant.FileType.AUDIO.getValue(), null);
        }
        return null;
    }

    // -------------------------

    public static Map<String, String>  getVideoConfig(){
        //获取云存储配置信息

        if(config.getType() == Constant.CloudService.QINIU.getValue()){
            return new QiniuCloudStorageService(config, Constant.FileType.VIDEO.getValue(), null).getConfigMap();
        }
        return null;
    }

    public static Map<String, String>  getFileConfig(String fileName){
        //获取云存储配置信息

        if(config.getType() == Constant.CloudService.QINIU.getValue()){
            return new QiniuCloudStorageService(config, Constant.FileType.FILE.getValue(), fileName).getConfigMap();
        }
        return null;
    }

    public static Map<String, String>  getImageConfig(String fileName){
        //获取云存储配置信息

        if(config.getType() == Constant.CloudService.QINIU.getValue()){
            return new QiniuCloudStorageService(config, Constant.FileType.IMAGE.getValue(), fileName).getConfigMap();
        }
        return null;
    }

    public static Map<String, String>  getAudioConfig(){
        //获取云存储配置信息

        if(config.getType() == Constant.CloudService.QINIU.getValue()){
            return new QiniuCloudStorageService(config, Constant.FileType.AUDIO.getValue(), null).getConfigMap();
        }
        return null;
    }

    public static Map<String, String>  getRecordConfig(String fileName){
        //获取云存储配置信息

        if(config.getType() == Constant.CloudService.QINIU.getValue()){
            return new QiniuCloudStorageService(config, Constant.FileType.RECORD.getValue(), fileName).getConfigMap();
        }
        return null;
    }

    public static void removeFile(String fileType, String fileName) {

        QiniuCloudStorageService service = new QiniuCloudStorageService(config, Constant.FileType.FILE.getValue(), fileName);
        service.removeFile(fileName);

    }

    // -------------------

}
