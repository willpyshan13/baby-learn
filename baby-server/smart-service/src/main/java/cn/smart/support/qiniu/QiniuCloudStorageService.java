package cn.smart.support.qiniu;

import cn.smart.common.utils.Constant;
import cn.smart.common.utils.Validator;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class QiniuCloudStorageService extends CloudStorageService{

    private static final Logger logger = LogManager.getLogger(QiniuCloudStorageService.class);

    private UploadManager uploadManager;

    private BucketManager bucketManager;

    private Auth auth;

    private String token;

    private String bucketName;

    private String domain;

    private Map<String, String> configMap;

    /**
     * 初始化 七牛云存储服务 (默认使用 华东
     * @param config 配置信息
     * @param type 文件类型 video image file audio
     */
    QiniuCloudStorageService(CloudStorageConfig config, int type, String fileName) {
        this.config = config;
        initAuth();
        if (Constant.FileType.VIDEO.getValue() == type) {
            initVideo(fileName);
        } else if(Constant.FileType.AUDIO.getValue() == type) {
            initAudioStorage();
        } else if(Constant.FileType.FILE.getValue() == type) {
            initFileStorage(fileName);
        } else if(Constant.FileType.IMAGE.getValue() == type) {
            initImageStorage(fileName);
        } else if(Constant.FileType.RECORD.getValue() == type) {
            initRecordStorage(fileName);
        }
    }

    private void initRecordStorage(String fileName) {
        uploadManager = new UploadManager(new Configuration(Zone.zone0()));
        bucketManager = new BucketManager(this.auth, new Configuration(Zone.zone0()));
        if (Validator.isEmpty(fileName)) {
            token = this.auth.uploadToken(config.getqiniuRecordBucketName());
        } else {
            token = this.auth.uploadToken(config.getqiniuRecordBucketName(), fileName);
        }
        domain = config.getQiniuRecordDomain();
        bucketName = config.getqiniuRecordBucketName();

        setConfigMap(bucketName, token, domain);
    }


    private void initAuth() {
        this.auth = Auth.create(config.getQiniuAccessKey(), config.getQiniuSecretKey());
    }

    private void initVideo(String fileName) {
        uploadManager = new UploadManager(new Configuration(Zone.zone0()));
        bucketManager = new BucketManager(this.auth, new Configuration(Zone.zone0()));
        if (StringUtils.isEmpty(fileName)) {
            token = this.auth.uploadToken(config.getQiniuVideoBucketName());
        } else {
            token = this.auth.uploadToken(config.getQiniuVideoBucketName(), fileName);
        }
        domain = config.getQiniuVideoDomain();
        bucketName = config.getQiniuVideoBucketName();

        setConfigMap(bucketName, token, domain);

    }

    private void initAudioStorage() {
        uploadManager = new UploadManager((new Configuration(Zone.zone0())));
        bucketManager = new BucketManager(this.auth, new Configuration(Zone.zone0()));
        token = this.auth.uploadToken(config.getQiniuAudioBucketName());
        domain = config.getQiniuAudioDomain();
        bucketName = config.getQiniuAudioBucketName();

        setConfigMap(bucketName, token, domain);

    }


    private void initImageStorage(String fileName) {
        uploadManager = new UploadManager((new Configuration(Zone.zone0())));
        bucketManager = new BucketManager(this.auth, new Configuration(Zone.zone0()));
        if (StringUtils.isEmpty(fileName)) {
            token = this.auth.uploadToken(config.getQiniuImageBucketName());
        } else {
            token = this.auth.uploadToken(config.getQiniuImageBucketName(), fileName);
        }

        domain = config.getQiniuImageDomain();
        bucketName = config.getQiniuImageBucketName();

        setConfigMap(bucketName, token, domain);

    }


    private void initFileStorage(String fileName) {
        uploadManager = new UploadManager((new Configuration(Zone.zone0())));
        bucketManager = new BucketManager(this.auth, new Configuration(Zone.zone0()));

        if (StringUtils.isEmpty(fileName)) {
            token = this.auth.uploadToken(config.getQiniuFileBucketName());
        } else {
            token = this.auth.uploadToken(config.getQiniuFileBucketName(), fileName);
        }
        domain = config.getQiniuFileDomain();
        bucketName = config.getQiniuFileBucketName();

        setConfigMap(bucketName, token, domain);

    }

    @Override
    public String upload(byte[] data, String path) {
        try {
            Response res = uploadManager.put(data, path, token);
            logger.info("七牛-上传结果: " + res);
            if (!res.isOK()) {
                logger.error("七牛-上传七牛出错: {}", res.bodyString());
            }
        } catch (QiniuException e) {
            try {
                logger.error("七牛-上传文件失败，请核对七牛配置信息: {}", e.response.bodyString());
            } catch (QiniuException e1) {
                // ignore
            }
        }
        return domain + "/" + path;
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) {
        return upload(data, getPath(config.getQiniuPrefix(), suffix));
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        byte[] data = new byte[0];
        try {
            data = IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.upload(data, path);
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix) {
        return upload(inputStream, getPath(config.getQiniuPrefix(), suffix));
    }

    @Override
    public void removeFile(String fileName) {
        try {
            // 调用删除方法
            Response res = bucketManager.delete(bucketName, fileName);
            logger.info("七牛-删除文件结果: " + res);
            if (!res.isOK()) {
                logger.error("七牛-删除文件出错: {}", res.bodyString());
            }
        } catch (QiniuException e) {
            try {
                logger.error("七牛-删除文件失败，请核对七牛配置信息: {}", e.response.bodyString());
            } catch (QiniuException e1) {
                // ignore
            }
        }
    }

    public Map<String, String> getConfigMap() {
        return configMap;
    }

    private void setConfigMap(String bucketName, String token, String domain) {
        Map<String, String> configMap = new HashMap<>(16);
        configMap.put("token", token);
        configMap.put("bucketName", bucketName);
        configMap.put("domain", domain);
        this.configMap = configMap;
    }
}
