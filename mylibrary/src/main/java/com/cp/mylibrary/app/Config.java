package com.cp.mylibrary.app;

import android.os.Environment;

import java.io.File;

/**
 * Created by Jerry on 2016/8/17.
 */
public class Config {
     public static int PAGE_SIXE = 20;

     public static final String WEICHAT_APPID = "wxfa0c39107c4cfd92";
     public static final String WEICHAT_SECRET = "045e666ee508437f8a80bd6a2a18e33e";


     public static final String SINA_APPID = "3182205453";
     public static final String SINA_SECRET = "c5129ecd0f20bc26a64a48316d36ab04";

     public static final String QQ_APPID = "1105802898";
     public static final String QQ_APPKEY = "dw1chPVQZ9YmBAkv";

//TODO https
//     public static final String HTTP_HEAD = "https://";
//



     public final static String FILE_PROVIDER= "com.g.fileprovider";






     // 默认存放文件下载的路径
     public final static String DEFAULT_SAVE_FILE_PATH = Environment
             .getExternalStorageDirectory()
             + File.separator
             + "guwentong"
             + File.separator + "download" + File.separator;
}
