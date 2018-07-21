package com.Traumatophobia.prefixmod;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.IClientCommand;

public class AutoAdvertTime implements IClientCommand {

	private final List aliases;
	public static String message = "";
	public static boolean sendmessage = false;
	
	public static int time = 4800;


	public AutoAdvertTime()
    { 
		aliases = new ArrayList();
		aliases.add("resettimer");
		aliases.add("adverttimer");
		aliases.add("adverttime");
		aliases.add("autoadverttime");
    } 

	@Override
	public boolean checkPermission(MinecraftServer arg0, ICommandSender arg1) {
		return true;
	}

	@Override
	public void execute(MinecraftServer arg0, ICommandSender arg1, String[] arg2) throws CommandException {
		if (arg2.length == 0) {
			Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Timer reset to default"));
			time = 2000;
		}
		else if (arg2.length > 1) {
			Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Please do /autoadverttime [time]"));
		}
		else {
			try {
			if (Integer.parseInt(arg2[0]) < 2) {
				Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Minimum time of 2 minuites!"));
			}
			else {
				time = 2400 * Integer.parseInt(arg2[0]);
				Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Auto advert set to repeat every "+String.valueOf(Integer.parseInt(arg2[0]))+" mins"));
			}
			} catch (Exception e) {
				Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Please enter a whole number"));
			}
		}
	}

	@Override
	public List<String> getAliases() {
		// TODO Auto-generated method stub
		return this.aliases;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "autoadverttimer";
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer arg0, ICommandSender arg1, String[] arg2, BlockPos arg3) {
		List<String> empty = new ArrayList<>();
		return empty;
	}

	@Override
	public String getUsage(ICommandSender arg0) {
		// TODO Auto-generated method stub
		return "autoadverttimer <text>";
	}

	@Override
	public boolean isUsernameIndex(String[] arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int compareTo(ICommand arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean allowUsageWithoutPrefix(ICommandSender arg0, String arg1) {
		// TODO Auto-generated method stub
		return false;
	}

}
