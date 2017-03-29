package com.jeenms.app.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhangdy on 2017/3/29.
 */

public class IOUtils {

    public static void close(InputStream input) {
        closeQuietly(input);
    }

    public static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException ioe) {
            // ignore
        }
    }

}
