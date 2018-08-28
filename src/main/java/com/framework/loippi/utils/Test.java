package com.framework.loippi.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class Test {

	public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {
		File file = new File("D:/shuwei111.sql");
		System.out.println(file.exists());
		File parentFile = file.getParentFile();
		System.out.println(parentFile);
		new FileOutputStream(file);
//		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
		if(file.exists()){
			
		}
		
	}

}
