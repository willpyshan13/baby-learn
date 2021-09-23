package cn.smart.support.qiniu;

import org.springframework.context.annotation.Bean;

import java.io.Serializable;

/**
 * 云存储配置信息
 * @author Ye
 * @date 2018-5-17 09:21:38
 */
public class CloudStorageConfig implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 类型 1: 七牛
     */
    private Integer type;

    /**
     * 七牛 AccessKey
     */
    private String qiniuAccessKey;

    /**
     * 七牛 SecretKey
     */
    private String qiniuSecretKey;

    /**
     * 七牛路径前缀
     */
    private String qiniuPrefix;

    /**
     * 七牛 视频文件 绑定域名
     */
    private String qiniuVideoDomain;

    /**
     * 七牛 文档文件 绑定域名
     */
    private String qiniuFileDomain;

    /**
     * 七牛 音频文件 绑定域名
     */
    private String qiniuAudioDomain;

    /**
     * 七牛 图片文件 绑定域名
     */
    private String qiniuImageDomain;

    /**
     * 七牛 视频文件 空间名
     */
    private String qiniuVideoBucketName;

    /**
     * 七牛 文档文件 空间名
     */
    private String qiniuFileBucketName;

    /**
     * 七牛 音频文件 空间名
     */
    private String qiniuAudioBucketName;

    /**
     * 七牛 图片文件 空间名
     */
    private String qiniuImageBucketName;

    /**
     * 录音文件 绑定域名
     */
    private String qiniuRecordDomain;

    /**
     * 录音文件 空间名
     */
    private String qiniuRecordBucketName;



    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getQiniuAccessKey() {
        return qiniuAccessKey;
    }

    public void setQiniuAccessKey(String qiniuAccessKey) {
        this.qiniuAccessKey = qiniuAccessKey;
    }

    public String getQiniuSecretKey() {
        return qiniuSecretKey;
    }

    public void setQiniuSecretKey(String qiniuSecretKey) {
        this.qiniuSecretKey = qiniuSecretKey;
    }

    public String getQiniuPrefix() {
        return qiniuPrefix;
    }

    public void setQiniuPrefix(String qiniuPrefix) {
        this.qiniuPrefix = qiniuPrefix;
    }

    public String getQiniuVideoDomain() {
        return qiniuVideoDomain;
    }

    public void setQiniuVideoDomain(String qiniuVideoDomain) {
        this.qiniuVideoDomain = qiniuVideoDomain;
    }

    public String getQiniuFileDomain() {
        return qiniuFileDomain;
    }

    public void setQiniuFileDomain(String qiniuFileDomain) {
        this.qiniuFileDomain = qiniuFileDomain;
    }

    public String getQiniuAudioDomain() {
        return qiniuAudioDomain;
    }

    public void setQiniuAudioDomain(String qiniuAudioDomain) {
        this.qiniuAudioDomain = qiniuAudioDomain;
    }

    public String getQiniuImageDomain() {
        return qiniuImageDomain;
    }

    public void setQiniuImageDomain(String qiniuImageDomain) {
        this.qiniuImageDomain = qiniuImageDomain;
    }

    public String getQiniuVideoBucketName() {
        return qiniuVideoBucketName;
    }

    public void setQiniuVideoBucketName(String qiniuVideoBucketName) {
        this.qiniuVideoBucketName = qiniuVideoBucketName;
    }

    public String getQiniuFileBucketName() {
        return qiniuFileBucketName;
    }

    public void setQiniuFileBucketName(String qiniuFileBucketName) {
        this.qiniuFileBucketName = qiniuFileBucketName;
    }

    public String getQiniuAudioBucketName() {
        return qiniuAudioBucketName;
    }

    public void setQiniuAudioBucketName(String qiniuAudioBucketName) {
        this.qiniuAudioBucketName = qiniuAudioBucketName;
    }

    public String getQiniuImageBucketName() {
        return qiniuImageBucketName;
    }

    public void setQiniuImageBucketName(String qiniuImageBucketName) {
        this.qiniuImageBucketName = qiniuImageBucketName;
    }

    public String getqiniuRecordBucketName() {
        return qiniuRecordBucketName;
    }

    public String getQiniuRecordDomain() {
        return qiniuRecordDomain;
    }

    public void setQiniuRecordDomain(String qiniuRecordDomain) {
        this.qiniuRecordDomain = qiniuRecordDomain;
    }

    public void setQiniuRecordBucketName(String qiniuRecordBucketName) {
        this.qiniuRecordBucketName = qiniuRecordBucketName;
    }

    @Override
    public String toString() {
        return "CloudStorageConfig{" +
                "type=" + type +
                ", qiniuAccessKey='" + qiniuAccessKey + '\'' +
                ", qiniuSecretKey='" + qiniuSecretKey + '\'' +
                ", qiniuPrefix='" + qiniuPrefix + '\'' +
                ", qiniuVideoDomain='" + qiniuVideoDomain + '\'' +
                ", qiniuFileDomain='" + qiniuFileDomain + '\'' +
                ", qiniuAudioDomain='" + qiniuAudioDomain + '\'' +
                ", qiniuImageDomain='" + qiniuImageDomain + '\'' +
                ", qiniuVideoBucketName='" + qiniuVideoBucketName + '\'' +
                ", qiniuFileBucketName='" + qiniuFileBucketName + '\'' +
                ", qiniuAudioBucketName='" + qiniuAudioBucketName + '\'' +
                ", qiniuImageBucketName='" + qiniuImageBucketName + '\'' +
                ", qiniuRecordDomain='" + qiniuRecordDomain + '\'' +
                ", qiniuRecordBucketName='" + qiniuRecordBucketName + '\'' +
                '}';
    }
}
