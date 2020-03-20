package com.ruoyi.common.utils.file;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 读取目录文件并且按照时间进行【降序】排序
 * @author wujiyue
 */
public class ReadDirector {
    public static void main(String[] args){
        try {
            //读取文件
            List filenames= ReadDirector.getFiles("D:\\");
            if(filenames!=null) {
                for (int i = 0; i < filenames.size() && filenames.size() > 0; i++) {
                    String str = (String) filenames.get(i);
                    System.out.println("第" + (i < 10 ? "0" + i : i + 1) + "文件名是：" + str);
                }
            }
        }catch (Exception e) {}
    }
    /**
     * 读取目录文件
     * @param dirname 目录名称
     * @return list集合
     */
    public static List<String> getFiles(String dirname)throws Exception{
        List<String> file_names=null;
        File dir=new File(dirname);
        if(dir.exists()){
            file_names=new ArrayList<String>();
            File []files=dir.listFiles();
            //排序
            Arrays.sort(files, new CompratorByLastModified());
            for(int i=0;i<files.length;i++){
                file_names.add(files[i].getName());
            }
        }else{
            return null;
        }
        return file_names;
    }
    /**
     * 格式化时间
     * @param format 格式化显示样式
     * @param date 时间
     * @return
     */
    private static String format(String format, Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }
    /**
     * 进行文件排序时间
     * @author 谈情
     */
    private static class CompratorByLastModified implements Comparator<File>{
        @Override
        public int compare(File f1, File f2) {
            long diff = f1.lastModified()-f2.lastModified();
            if(diff<0){
                return 1;
            }
            else if(diff==0) {
                return 0;
            }
            else {
                return -1;
            }
        }
        @Override
        public boolean equals(Object obj){
            return true;
        }
    }
}

