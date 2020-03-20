package com.ruoyi.quartz.util;

import java.io.*;

public class BatCommandUtil {
    /**
     * 写入命令并执行
     * @param str:批处理命令，多行命令以\r\n间隔
     */
    public static synchronized void writeAndExcute(String batFilePath,String str) throws UnsupportedEncodingException, IOException {
        Process p;
        Runtime rt = Runtime.getRuntime();
        String param;
        File batFile = createFile(batFilePath);
        BufferedWriter paramFile = new BufferedWriter(
                new FileWriter(batFile));
        param = new String(str.getBytes(),"GBK");
        paramFile.write(param);
        paramFile.flush();
        paramFile.close();
        p = rt.exec(batFile.getAbsolutePath());
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        while (br.readLine() != null) {
        }
    }
    /**
     * 创建文件
     * @return 返回文件对象
     */
    public static File createFile(String filepath) throws IOException {
        File f = new File(filepath);
        if (!f.getParentFile().exists() && !f.getParentFile().isDirectory()) {
            f.getParentFile().mkdirs();
        }
        if (!f.exists()) {
            f.createNewFile();
        }
        return f;
    }
}
