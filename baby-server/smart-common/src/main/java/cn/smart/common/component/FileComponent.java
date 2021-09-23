package cn.smart.common.component;

import cn.smart.common.utils.DateUtils;
import cn.smart.common.utils.Validator;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class FileComponent {

    private static final String FILE_NAME_FORMAT_STRING = "yyyy/MM-dd/HH-mm-ssSSSS";
    private static final String DEFAULT_CHARSET_NAME = "UTF-8";
    private static final String INCLUDE_DIRECTORY = "include";
    private static final Charset DEFAULT_CHARSET = Charset.forName(DEFAULT_CHARSET_NAME);
    private static final Random random = new Random();
    private static final String DOT = ".";
    /**
     * 获取目录下文件列表
     *
     * @param dirPath
     * @return file info list
     */
    public List<FileInfo> getFileList(String dirPath) {
        List<FileInfo> fileList = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(dirPath))) {
            List<FileInfo> tempList = new ArrayList<>();
            for (Path entry : stream) {
                Path fileNamePath = entry.getFileName();
                if (null != fileNamePath) {
                    String fileName = fileNamePath.toString();
                    if (!fileName.endsWith(".data") && ! INCLUDE_DIRECTORY.equalsIgnoreCase(fileName)) {
                        BasicFileAttributes attrs = Files.readAttributes(entry, BasicFileAttributes.class);
                        if (attrs.isDirectory()) {
                            fileList.add(new FileInfo(fileName, true, attrs));
                        } else {
                            tempList.add(new FileInfo(fileName, false, attrs));
                        }
                    }
                }
            }
            fileList.addAll(tempList);
        } catch (IOException e) {
        }
        return fileList;
    }

    /**
     * 写入文件
     *
     * @param file
     * @param content
     * @return whether to create successfully
     * @throws IOException
     */
    public boolean createFile(File file, String content) throws IOException {
        if (Validator.isEmpty(file)) {
            FileUtils.writeStringToFile(file, content, DEFAULT_CHARSET_NAME);
            return true;
        }
        return false;
    }

    /**
     * 删除文件或目录
     *
     * @param filePath
     * @return whether to delete successfully
     */
    public boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (!Validator.isEmpty(file)) {
            FileUtils.deleteQuietly(file);
            try {
                FileUtils.deleteDirectory(file.getParentFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    /**
     * 修改文件内容
     *
     * @param file
     * @param content
     * @return whether to modify successfully
     * @throws IOException
     */
    public boolean updateFile(File file, String content) throws IOException {
        if (Validator.isEmpty(file) && !Validator.isEmpty(content)) {
            try (FileOutputStream outputStream = new FileOutputStream(file);) {
                outputStream.write(content.getBytes(DEFAULT_CHARSET));
            }
            return true;
        }
        return false;
    }

    /**
     * 获取文件内容
     *
     * @param filePath
     * @return file content
     */
    public String getFileContent(String filePath) {
        File file = new File(filePath);
        try {
            if (file.isFile()) {
                return FileUtils.readFileToString(file, DEFAULT_CHARSET_NAME);
            }
        } catch (IOException e) {
            return null;
        }
        return null;
    }

    /**
     * 获取文件名
     *
     * @param suffix
     * @return upload file name
     */
    public String getUploadFileName(String suffix) {
        StringBuilder sb = new StringBuilder("upload/");
        sb.append(DateUtils.format(new Date(), FILE_NAME_FORMAT_STRING));
        return sb.append(random.nextInt()).append(suffix).toString();
    }

    /**
     * 获取文件后缀
     *
     * @param originalFilename
     * @return suffix
     */
    public String getSuffix(String originalFilename) {
        return originalFilename.substring(originalFilename.lastIndexOf(DOT), originalFilename.length());
    }

    /**
     * 上传文件
     *
     * @param file
     * @param fileName
     * @return file name
     * @throws IllegalStateException
     * @throws IOException
     */
    public String upload(MultipartFile file, String fileName) throws IllegalStateException, IOException {
        File dest = new File(fileName);
        dest.getParentFile().mkdirs();
        file.transferTo(dest);
        return dest.getName();
    }

    /**
     *
     * FileInfo 文件信息封装类
     *
     */
    public class FileInfo {
        private String fileName;
        private boolean directory;
        private Date lastModifiedTime;
        private Date lastAccessTime;
        private Date creationTime;
        private long size;

        /**
         * @param fileName
         * @param directory
         * @param attrs
         */
        public FileInfo(String fileName, boolean directory, BasicFileAttributes attrs) {
            this.fileName = fileName;
            this.directory = directory;
            this.lastModifiedTime = new Date(attrs.lastModifiedTime().toMillis());
            this.lastAccessTime = new Date(attrs.lastAccessTime().toMillis());
            this.creationTime = new Date(attrs.creationTime().toMillis());
            this.size = attrs.size();
        }

        /**
         * @return file name
         */
        public String getFileName() {
            return fileName;
        }

        /**
         * @param fileName
         */
        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        /**
         * @return last modified time
         */
        public Date getLastModifiedTime() {
            return lastModifiedTime;
        }

        /**
         * @param lastModifiedTime
         */
        public void setLastModifiedTime(Date lastModifiedTime) {
            this.lastModifiedTime = lastModifiedTime;
        }

        /**
         * @return last access time
         */
        public Date getLastAccessTime() {
            return lastAccessTime;
        }

        /**
         * @param lastAccessTime
         */
        public void setLastAccessTime(Date lastAccessTime) {
            this.lastAccessTime = lastAccessTime;
        }

        /**
         * @return creation time
         */
        public Date getCreationTime() {
            return creationTime;
        }

        /**
         * @param creationTime
         */
        public void setCreationTime(Date creationTime) {
            this.creationTime = creationTime;
        }

        /**
         * @return size
         */
        public long getSize() {
            return size;
        }

        /**
         * @param size
         */
        public void setSize(long size) {
            this.size = size;
        }

        /**
         * @return directory
         */
        public boolean isDirectory() {
            return directory;
        }

        /**
         * @param directory
         */
        public void setDirectory(boolean directory) {
            this.directory = directory;
        }
    }
}
