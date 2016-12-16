package com.Zxl.Commands;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandTrigger;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

import java.io.IOException;

import com.Zxl.FileHandler.FileHandler;

public class Fly extends CommandBase{

	public String getCommandName() {return "LoadMessages";}

	public String getCommandUsage(ICommandSender sender) {return "/trigger [create/add/list] <...>";}

	public int getRequiredPermissionLevel() {return 0;}
	
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		try {
			FileHandler.LoadResponses("Message.txt");
			Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("Messages have been loaded"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
