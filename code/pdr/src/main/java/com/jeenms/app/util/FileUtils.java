package com.jeenms.app.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhangdy on 2017/3/29.
 */

public class FileUtils {

    public String sDeviceRootDir = Environment.getExternalStorageDirectory().getPath();

    public static byte[] getAssetFileContent(Context context, String fileName) {
        byte[] bytes = null;
        //File file = new File(fileName);
        InputStream fis = null;
        try {
            fis = context.getResources().getAssets().open(fileName);
            if(fis != null) {
                int length = fis.available();
                bytes = new byte[length];
                fis.read(bytes);;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(fis);
        }
        return bytes;
    }
 }
