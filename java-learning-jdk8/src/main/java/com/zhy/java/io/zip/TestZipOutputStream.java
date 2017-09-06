package com.zhy.java.io.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class TestZipOutputStream {

	public static void main(String[] args) {
		long b_Time = System.currentTimeMillis();
		//noZip();
		zip();
		long e_Time = System.currentTimeMillis();
		System.out.println("cost:" + (e_Time - b_Time));
	}
	
	private static void noZip(){
		InputStream is = null;
		OutputStream os = null;
		
		try {
			is = new FileInputStream("D:\\deploy\\blueair.update.v1.0\\html\\video\\video.mp4");
			os = new FileOutputStream("D:\\deploy\\blueair.update.v1.0\\html\\video\\1.mp4");
			int len = -1;
			byte[] b = new byte[1024];
			while((len = is.read(b)) != -1){
				os.write(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void zip(){
		String fromFile = "D:\\deploy\\blueair.update.v1.0\\html\\video\\video.mp4";
		String toFile = "D:\\deploy\\blueair.update.v1.0\\html\\video\\1.zip";
		ZipOutputStream zos = null;
		InputStream is = null;
		try {
			
			File file = new File(fromFile);
			File zipFile = new File(toFile);
			zos = new ZipOutputStream(new FileOutputStream(zipFile));
			zos.setMethod(ZipOutputStream.DEFLATED);
			
			byte[] b = new byte[1024];
			
			ZipEntry zipEntry = new ZipEntry(file.getName());
			zos.putNextEntry(zipEntry);
			is = new FileInputStream(file);
			int len;
			while((len = is.read(b)) != -1){
				zos.write(b, 0, len);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				zos.closeEntry();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				zos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
