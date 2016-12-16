package com.Zxl.FileHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import com.Zxl.lib.RefStrings;
import com.Zxl.Main.MainRegistry;

public class FileHandler {
	public static List<List<String>> Response = new ArrayList<List<String>>();
	
	public static void FileCreate(String string, String ErrorMessage) throws FileNotFoundException, UnsupportedEncodingException{
		File file = new File(string);
		if(!file.exists()){
			PrintWriter writer = new PrintWriter(string,"UTF-8");
			writer.println(ErrorMessage);
			writer.close();
		}
	}
	
	public static void saveResponses(List<String> listName, String fileName) throws IOException {
		PrintWriter writer = new PrintWriter(fileName,"UTF-8");
        for (String string : listName) {
        	int NumberResponse =+ 1;
        	writer.println(string);
		}
		writer.close();
	}
	public static void LoadResponses(String string) throws IOException{
		String line;
		RefStrings.MessageList.clear();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(string),"UTF-8"));
		while ((line = bufferedReader.readLine()) != null) {
			RefStrings.MessageList.add(line);
		}
		bufferedReader.close();
	}
	public static void GetString(String stringName, String fileName) throws IOException{
		int strExist = 0;
		List<String> stringcheck = new ArrayList<String>();
		String line;
		@SuppressWarnings("resource")
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"UTF-8"));
		while ((line = bufferedReader.readLine()) != null) {
			stringcheck.add(line);
		}
		for (int i = 1; i < stringcheck.size(); i++){
			if (stringcheck.get(i).contains(stringName)){
				String value = stringcheck.get(i+1);
				value = value.replace("  value:", "");
				RefStrings.Str = value;
				strExist = 1;
			}
		}
		if (strExist == 0){
			RefStrings.Str = "string does not exist";
		}
	}
}