package com.Zxl.Main;

import com.Zxl.Commands.deleateMessage;
import com.Zxl.Commands.Fly;
import com.Zxl.Commands.ListMessages;
import com.Zxl.Commands.OpenChat;
import com.Zxl.Commands.NewMessage;
import com.Zxl.FileHandler.FileHandler;
import com.Zxl.lib.RefStrings;
import com.Zxl.FileHandler.MessageHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.lwjgl.input.Keyboard;

import com.google.common.eventbus.Subscribe;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.command.CommandTrigger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.IInteractionObject;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.reflect.internal.Trees.New;


@Mod (modid = RefStrings.MODID , name = RefStrings.NAME , version = RefStrings.VERSION)
public class MainRegistry {
	public static KeyBinding MScrollL;
	public static KeyBinding MScrollR;
	public static KeyBinding MSend;
	public static KeyBinding AutoSend;
	public static KeyBinding deleteMessage;
	public static KeyBinding ChatTriggers;
	private int CanAutoSend = 0;
	private int MessageNumber = 0;
	
	@EventHandler
	public void init(FMLInitializationEvent event) throws ClassNotFoundException, IOException
	{
		MinecraftForge.EVENT_BUS.register(this);
        FMLCommonHandler.instance().bus().register(this);
		MScrollR = new KeyBinding("Increase message number", Keyboard.KEY_RIGHT, "HelperHelp");
		MSend = new KeyBinding("Send Message", Keyboard.KEY_UP, "HelperHelp");
		MScrollL = new KeyBinding("Decrease Message Number", Keyboard.KEY_LEFT, "HelperHelp");
		AutoSend = new KeyBinding("Send selected message", Keyboard.KEY_RETURN, "HelperHelp");
		deleteMessage = new KeyBinding("delete selected message",Keyboard.KEY_DELETE, "HelperHelp");
		ChatTriggers = new KeyBinding("KeyBind for chat triggers",Keyboard.CHAR_NONE,"ChatTriggers");
		ClientRegistry.registerKeyBinding(AutoSend);
		ClientRegistry.registerKeyBinding(MScrollL);
		ClientRegistry.registerKeyBinding(MScrollR);
		ClientRegistry.registerKeyBinding(MSend);
		ClientRegistry.registerKeyBinding(ChatTriggers);
        ClientCommandHandler.instance.registerCommand(new OpenChat());
        ClientCommandHandler.instance.registerCommand(new Fly());
        ClientCommandHandler.instance.registerCommand(new NewMessage());
        ClientCommandHandler.instance.registerCommand(new ListMessages());
        ClientCommandHandler.instance.registerCommand(new deleateMessage());
        FileHandler.FileCreate("Message.txt","Please Add Messages (Also Zxl is Really Cool)");
		FileHandler.LoadResponses("Message.txt");
	}
	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event) throws IOException {
		if (this.MSend.isPressed()) {
			MessageHandler.OpenChat(RefStrings.MessageList.get(MessageNumber));
		}
		else if (this.deleteMessage.isPressed()){
			if (CanAutoSend == 1){
				if(RefStrings.MessageList.size() > 1){
					RefStrings.MessageList.remove(MessageNumber);
					MessageHandler.PrivateMessage("Message has been deleted");
					if(MessageNumber == 0){
						MessageNumber = RefStrings.MessageList.size() - 1;
					}
					else{
						MessageNumber = MessageNumber - 1;
					}
					this.CanAutoSend = 1;
					MessageHandler.PrivateCycleMessage(RefStrings.MessageList.get(MessageNumber), 2000);
					try {
						FileHandler.saveResponses(RefStrings.MessageList, "Message.txt" );
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{MessageHandler.PrivateMessage("There has to be at least 1 message add another message then you can delete this one");}
			}
			else{MessageHandler.PrivateMessage("Please select a message");}
		}
		else if (this.AutoSend.isPressed()) {
			if (this.CanAutoSend == 1){
				MessageHandler.PublicMessage(RefStrings.MessageList.get(MessageNumber));
				this.CanAutoSend = 0;
			}
			else {
				MessageHandler.PrivateMessage("Please select a message");
			}
		}
		else if (this.MScrollR.isPressed()) {
			if (MessageNumber == RefStrings.MessageList.size() - 1){
				MessageNumber = 0;
			}
			else{
				MessageNumber = MessageNumber + 1;
			}
			this.CanAutoSend = 1;
			MessageHandler.PrivateCycleMessage(RefStrings.MessageList.get(MessageNumber), 2000);
		}
		else if (this.ChatTriggers.isPressed()){
            String send = "";
            MessageHandler.PrivateMessage("Trigger Command");
		}
		else if (this.MScrollL.isPressed()) {
			if(MessageNumber == 0){
				MessageNumber = RefStrings.MessageList.size() - 1;
			}
			else{
			MessageNumber = MessageNumber - 1;
			}
			this.CanAutoSend = 1;
			MessageHandler.PrivateCycleMessage(RefStrings.MessageList.get(MessageNumber), 2000);
		}
	}
}