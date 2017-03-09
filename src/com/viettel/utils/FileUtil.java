package com.viettel.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileUtil {
	//write bye[] data to file
    public static void data2file(byte[] w,String fileName)throws Exception {
        FileOutputStream fos=null;
        try {
            fos=new FileOutputStream(fileName);
            fos.write(w);
            fos.close();
        } catch (Exception e) {
            if (fos!=null) fos.close();
            throw e;
        }
    }
    //read file to bye[]
    public static byte[] file2data(String fileName) throws Exception {
        int size;
        byte[] w=new byte[1024];
        FileInputStream fin=null;
        ByteArrayOutputStream out=null;
        try {
            fin=new FileInputStream(fileName);
            out=new ByteArrayOutputStream();
            while (true) {
                size=fin.read(w);
                if (size<=0) break;
                out.write(w,0,size);
            }
            fin.close();
            out.close();
            return out.toByteArray();
        } catch (Exception e) {
            try {
                if (fin!=null) fin.close();
                if (out!=null) out.close();
            } catch (Exception e2) {
            }
            throw e;
        }
    }
    /*
     * 	file=context.getFilesDir()	 -> /data
		file=context.getDir("hoge", Context.MODE_PRIVATE); ->	/data/hoge
		file=Environment.getDataDirectory(); ->	/data
		file=Environment.getDownloadCacheDirectory(); ->	/cache
		file=Environment.getExternalStorageDirectory(); ->	/sdcard
		file=Environment.getRootDirectory(); ->	/system
     */
}
