package com.Zxl.Commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.Zxl.lib.RefStrings;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandTrigger;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import com.Zxl.FileHandler.FileHandler;;

public class OpenChat extends CommandBase{

	public String getCommandName() {return "SaveMessages";}

	public String getCommandUsage(ICommandSender sender) {return "Sends The Current Message";}

	public int getRequiredPermissionLevel() {return 0;}
	
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		try {
			FileHandler.saveResponses(RefStrings.MessageList, "Message.txt" );
			Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("Messages have been saved"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
