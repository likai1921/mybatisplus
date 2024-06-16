package org.base.mybatis.utils;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.text.DecimalFormat;

/**
 * @author zhaowenwen
 * @date 2021/1/15
 */
public class FileDriverUtils {
    public static String FormetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * 获取硬盘的每个盘符
     */
    public static void driver() {
        // 当前文件系统类
        FileSystemView fsv = FileSystemView.getFileSystemView();
        // 列出所有windows 磁盘
        File[] fs = File.listRoots();
        // 显示磁盘卷标
        for (int i = 0; i < fs.length; i++) {
            System.out.println(fsv.getSystemDisplayName(fs[i]));

            System.out.print("总大小" + FormetFileSize(fs[i].getTotalSpace()));
            System.out.println("剩余" + FormetFileSize(fs[i].getFreeSpace()));
            System.out.println("当前的目录列表 \t");
            if (fs[i].exists() && fs[i].isDirectory()) {
                FileDriverUtils.getFileMenu(fs[i]);
            }

        }
    }

    public static void getFileMenu(File file) {
        if (file.exists() && file.isDirectory()) {
            File[] files = file.listFiles();
            if (files.length > 0 && files != null) {
                for (int i = 0; i < files.length; i++) {
                    System.out.println(files[i]);//递归调用
                }
            }
        }
        System.out.println(file);
    }

    public static void main(String[] args) {
        File file = new File("\\att");
        String absolutePath = file.getAbsolutePath();
        System.out.println(absolutePath);
        FileDriverUtils.getFileMenu(file);

        new ZipCompressor(absolutePath);
    }
}
