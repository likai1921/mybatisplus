package org.base.mybatis.utils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author zhaowenwen
 * @date 2021/1/15
 */
public class ZipCompressor {
    private String mFileDest;
    private ZipOutputStream mZipOutputStream;
    private byte[] buffer = new byte[1024];
    private int ret = 0;
    private static final String SEPARATOR = File.separator;

    // public static void main(String[] args) {
    //
    // // ZipCompress zipCompress = new ZipCompress("E://1.zip");
    // ZipCompressor zipCompressor = new ZipCompressor("1.zip");
    //
    // zipCompressor.add("1.txt");
    // zipCompressor.add(".");
    // zipCompressor.add("2.txt");
    // zipCompressor.close();
    // }

    /**
     * path zip目标文件的名字
     */
    public ZipCompressor(String path) {
        mFileDest = new File(path).getAbsolutePath();

        FileOutputStream fos;
        try {
            fos = new FileOutputStream(mFileDest + "test", true);
            mZipOutputStream = new ZipOutputStream(fos);
        } catch (FileNotFoundException e) {
            printStackTrace(e);
        }
    }

    public void close() {
        if (mZipOutputStream != null) {
            try {
                mZipOutputStream.close();
            } catch (IOException e) {
                printStackTrace(e);
            }
            mZipOutputStream = null;
        }
    }

    /**
     * filePath 待压缩的文件或目录，可以是相对目录
     */
    public void add(String filePath) {

        try {
            File file = new File(filePath);
            String path = "";
            if (file.isDirectory()) {
                filePath = file.getAbsolutePath();
                if (filePath.endsWith("."))
                    filePath = filePath.substring(0, filePath.length() - 1);
                if (filePath.endsWith(SEPARATOR))
                    filePath = filePath.substring(0, filePath.length() - 1);
                // System.out.println("filePath:" + filePath);

                int pos = filePath.lastIndexOf(SEPARATOR);
                // System.out.println(filePath.substring(0, pos));
                if (filePath.substring(0, pos).contains(SEPARATOR))
                    path = filePath.substring(pos + 1, filePath.length())
                            + SEPARATOR;
                // System.out.println("path:" + path);
            }

            add(mZipOutputStream, path, filePath);
        } catch (IOException e) {
            printStackTrace(e);
        }
    }

    /**
     * zos zip压缩的目标文件 path 待创建的zip文件夹内的相内路径 file 待压缩的文件或目录的路径
     */
    private void add(ZipOutputStream zos, String path, String file)
            throws FileNotFoundException {

        try {
            File inputFile = new File(file);
            if (inputFile.isFile()) {
                add(zos, path, inputFile);
            } else if (inputFile.isDirectory()) {
                // System.out.println("add dir:" + inputFile.getName());

                for (File subFile : inputFile.listFiles()) {
                    if (subFile.isDirectory()) {
                        String newPath = path + subFile.getName() + SEPARATOR;
                        add(zos, newPath, subFile.getPath());
                    } else {
                        add(zos, path, subFile);
                    }
                }
            }
        } catch (IOException e) {
            printStackTrace(e);
        }
    }

    /**
     * zos zip压缩的目标文件 path 待创建的zip文件夹内的相内路径 file 待压缩的文件
     */
    private void add(ZipOutputStream zos, String path, File file) {
        FileInputStream fis = null;
        try {

            path.equalsIgnoreCase("");
            // 防止将目标zip文件打包进自己的压缩包内
            String src = file.getAbsolutePath();
            // System.out.println("src:" + src);
            if (mFileDest.equalsIgnoreCase(src)) {
                // System.out.println("Error! It‘s dest file! " + src);
                return;
            }

            try {
                ZipEntry zipEntry = new ZipEntry(path + file.getName());
                zos.putNextEntry(zipEntry);
                FileInputStream fin = new FileInputStream(file);
                while ((ret = fin.read(buffer)) != -1) {
                    zos.write(buffer, 0, ret);
                }
                fin.close();
                zos.closeEntry();
            } catch (Exception e) {
                printStackTrace(e);
            }

        } catch (Exception e) {
            printStackTrace(e);
        } finally {
            try {
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                printStackTrace(e);
            }
        }
    }

    private void printStackTrace(Exception exception) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        System.out.print(sw.toString());

        // e.printStackTrace();
    }


    static class Test {

        static final int n = 2020;

        public static void main(String[] args) {
            int[][] resource = {{0, 0}, {2020, 11}, {11, 14}, {2000, 2000}};
            boolean[][] marked = new boolean[n << 2][n << 2];
            long res = 0;
            for (int k = 0, x, y; k < 4; k++) {
                x = resource[k][0] + n;
                y = resource[k][1] + n;
                for (int i = -n; i <= n; i++)
                    for (int r = n - abs(i), j = -r; j <= r; j++) {
                        if (marked[x + i][y + j]) continue;
                        marked[x + i][y + j] = true;
                        res++;
                    }
            }
            System.out.println(res);
        }

        static int abs(int n) {
            return n > 0 ? n : -n;
        }
    }
}
