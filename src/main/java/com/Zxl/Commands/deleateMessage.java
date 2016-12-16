package com.Zxl.Commands;

import java.io.IOException;

import com.Zxl.FileHandler.FileHandler;
import com.Zxl.lib.RefStrings;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class deleateMessage extends CommandBase {

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return "DeleteMessage";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "/deleteMessage \"The Message You Want To delete\"";
	}
	public int getRequiredPermissionLevel() {return 0;}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		String FinalMessage = "";
		for (String arg : args) {FinalMessage += arg + " ";}
		if (RefStrings.MessageList.contains(FinalMessage)){
			RefStrings.MessageList.remove(FinalMessage);
			try {
				FileHandler.saveResponses(RefStrings.MessageList, "Message.txt" );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("Message has been deleted"));
		}
		else{
			Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(FinalMessage+"is not in your messages, make sure it is spelt exactly the same"));
		}
	}

}
