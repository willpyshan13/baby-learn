package cn.smart.common.utils;


public class FileType {

    private static String ARR_IMAGE[] = {"bmp", "jpg", "jpeg", "png", "tiff", "gif", "pcx", "tga", "exif", "fpx", "svg", "psd",
            "cdr", "pcd", "dxf", "ufo", "eps", "ai", "raw", "wmf"};

    private static String document[] = {"txt", "doc", "docx", "xls", "htm", "html", "jsp", "rtf", "wpd", "pdf", "ppt"};

    private static String video[] = {"mp4", "avi", "mov", "wmv", "asf", "navi", "3gp", "mkv", "f4v", "rmvb", "webm"};

    private static String  music[] = {"mp3", "wma", "wav", "mod", "ra", "cd", "md", "asf", "aac", "vqf", "ape", "mid", "ogg",
            "m4a", "vqf"};

    public static int checkType(String type) {

        if (type == null) {
            return 3;

        }

        // 获取文件后缀名并转化为写，用于后续比较
        String fileType = type.substring(type.lastIndexOf(".") + 1, type.length()).toLowerCase();
        // 创建图片类型数组
        for (int i = 0; i < ARR_IMAGE.length; i++) {
            if (ARR_IMAGE[i].equals(fileType)) {
                return 3;
            }
        }

        // 创建文档类型数组
        for (int i = 0; i < document.length; i++) {
            if (document[i].equals(fileType)) {
                return 3;
            }
        }
        // 创建视频类型数组
        for (int i = 0; i < video.length; i++) {
            if (video[i].equals(fileType)) {
                return 1;
            }
        }
        // 创建音乐类型数组
        for (int i = 0; i < music.length; i++) {
            if (music[i].equals(fileType)) {
                return 2;
            }
        }

        return 3;
    }
}