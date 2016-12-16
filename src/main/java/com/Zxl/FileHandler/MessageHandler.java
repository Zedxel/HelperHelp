package com.Zxl.FileHandler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.Zxl.lib.RefStrings;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.util.ChatComponentText;

public class MessageHandler {
	public static void PublicMessage(String Message) throws IOException{
		if(Message.contains("{string[") && Message.contains("]}")){
			String strName = Message.substring(Message.indexOf("{string[")+8, Message.indexOf("]}"));
			FileHandler.GetString(strName, "./mods/ChatTriggers/strings.txt");
			Message = Message.replace("{string["+strName+"]}", RefStrings.Str);
			PublicMessage(Message);
		}
		else{Minecraft.getMinecraft().thePlayer.sendChatMessage(Message);}
	}
	
	public static void OpenChat(String Message) throws IOException{
		if(Message.contains("{string[") && Message.contains("]}")){
			String strName = Message.substring(Message.indexOf("{string[")+8, Message.indexOf("]}"));
			FileHandler.GetString(strName, "./mods/ChatTriggers/strings.txt");
			Message = Message.replace("{string["+strName+"]}", RefStrings.Str);
			OpenChat(Message);
		}
		else {Minecraft.getMinecraft().displayGuiScreen(new GuiChat(Message));}
	}
	
	public static void PrivateMessage(String Message) throws IOException{
		if(Message.contains("{string[") && Message.contains("]}")){
			String strName = Message.substring(Message.indexOf("{string[")+8, Message.indexOf("]}"));
			FileHandler.GetString(strName, "./mods/ChatTriggers/strings.txt");
			Message = Message.replace("{string["+strName+"]}", RefStrings.Str);
			PrivateMessage(Message);
		}
		else {Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(Message));}
	}
	
	public static void PrivateCycleMessage(String Message, int Remove) throws IOException{
		if(Message.contains("{string[") && Message.contains("]}")){
			String strName = Message.substring(Message.indexOf("{string[")+8, Message.indexOf("]}"));
			FileHandler.GetString(strName, "./mods/ChatTriggers/strings.txt");
			Message = Message.replace("{string["+strName+"]}", RefStrings.Str);
			PrivateCycleMessage(Message, Remove);
		}
		else {Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(new ChatComponentText(Message), Remove);}
	}
}