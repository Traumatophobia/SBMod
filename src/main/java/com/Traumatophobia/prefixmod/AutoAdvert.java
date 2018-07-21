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

public class AutoAdvert implements IClientCommand {

	private final List aliases;
	public static String message = "";
	public static boolean sendmessage = false;


	public AutoAdvert()
    { 
		aliases = new ArrayList();
		aliases.add("stopautoadvert");
    } 

	@Override
	public boolean checkPermission(MinecraftServer arg0, ICommandSender arg1) {
		return true;
	}

	@Override
	public void execute(MinecraftServer arg0, ICommandSender arg1, String[] arg2) throws CommandException {
		if (arg2.length == 0) {
			Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Stopped sending messages"));
			PrefixMod.advertTimer = 0;
			message = "";
			sendmessage = false;
		}
		else {
			message = "";
			for (int i = 0; i < arg2.length; i++) {
				message += arg2[i]+" ";
				PrefixMod.advertTimer = AutoAdvertTime.time;
				sendmessage = true;
			}
			Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Auto sending advert"));
			Minecraft.getMinecraft().player.sendMessage(new TextComponentString(TextFormatting.RED+""+TextFormatting.BOLD+"WARNING"+TextFormatting.RESET+" malicious or spammy use of this command may result in a ban!"));
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
		return "autoadvert";
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer arg0, ICommandSender arg1, String[] arg2, BlockPos arg3) {
		List<String> empty = new ArrayList<>();
		return empty;
	}

	@Override
	public String getUsage(ICommandSender arg0) {
		// TODO Auto-generated method stub
		return "autoadvert <text>";
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
