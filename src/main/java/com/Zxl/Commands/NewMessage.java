package com.Zxl.Commands;

import java.io.IOException;

import com.Zxl.FileHandler.FileHandler;
import com.Zxl.FileHandler.MessageHandler;
import com.Zxl.lib.RefStrings;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandTrigger;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class NewMessage extends CommandBase{

	@Override
	public String getCommandName() {return "NM";}

	@Override
	public String getCommandUsage(ICommandSender sender) {return "/NewMessage \"type of message\" \"The rest of the message\"";}
	
	public int getRequiredPermissionLevel() {return 0;}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
//		if (args.length == 0){
//			try {
//				MessageHandler.PrivateMessage("/NM \"Messagetype\" \"Message\"");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}else if(args.length <= 1){
//			if(args[0] == "CanRepeat" ){
				String MessageType = "";
				String FinalMessage = "";
				for (String arg : args) {FinalMessage += arg + " ";}
				RefStrings.MessageList.add(FinalMessage);
				Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(FinalMessage+"has been added"+ ""));
				try {
					FileHandler.saveResponses(RefStrings.MessageList, "Message.txt" );
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//
//			}else if(args[0]=="CanNotRepeat"){
//				try {
//					MessageHandler.PrivateMessage("Test");
//				} catch (IOException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}else{
//				try {
//					MessageHandler.PrivateMessage("Please enter a valid type");
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
	}
}
