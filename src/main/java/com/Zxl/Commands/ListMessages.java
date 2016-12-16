package com.Zxl.Commands;

import com.Zxl.lib.RefStrings;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;


public class ListMessages extends CommandBase{

		public String getCommandName() {return "ListMessages";}

		public String getCommandUsage(ICommandSender sender) {return "/trigger [create/add/list] <...>";}

		public int getRequiredPermissionLevel() {return 0;}
		
		public void processCommand(ICommandSender sender, String[] args) throws CommandException {
			for (int i = 0; i < 10; i++){
				Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(RefStrings.MessageList.get(i)));
			}
			Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("Currently in development cause i\'m lazy"));
		}
}
