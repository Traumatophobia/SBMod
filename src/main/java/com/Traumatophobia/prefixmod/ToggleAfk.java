package com.Traumatophobia.prefixmod;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.IClientCommand;

public class ToggleAfk implements IClientCommand {
	
	public static boolean AFK = false;
	
	private final List aliases;

	public ToggleAfk()
    { 
		aliases = new ArrayList();
		aliases.add("toggleafk");
    } 
	

	@Override
	public boolean checkPermission(MinecraftServer arg0, ICommandSender arg1) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void execute(MinecraftServer arg0, ICommandSender arg1, String[] arg2) throws CommandException {
		ITextComponent telluser = new TextComponentString("");
		if (AFK) {
			AFK = false;
			PrefixMod.afknames = new ArrayList<String>();
			telluser.appendText("You are no longer afk");
		}
		else if (AFK == false) {
			AFK = true;
			PrefixMod.afknames = new ArrayList<String>();
			telluser.appendText("You are now afk");
			PrefixMod.afkcounter = 0;
			
		}
		
		if (arg2.length > 0) {
			Afk.AFKmessage = "/r ";
			for (int i = 0; i < arg2.length; i++) {
				Afk.AFKmessage += arg2[i]+" ";
			}
		}
		else {
			Afk.AFKmessage = "/r &5I'm currently AFK and may not be able to respond.";
		}
		
		Minecraft.getMinecraft().player.sendMessage(telluser);
	}



	@Override
	public List<String> getAliases() {
		// TODO Auto-generated method stub
		return this.aliases;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "tsbafk";
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer arg0, ICommandSender arg1, String[] arg2, BlockPos arg3) {
		// TODO Auto-generated method stub
		List<String> empty = new ArrayList<>();
		return empty;
	}

	@Override
	public String getUsage(ICommandSender arg0) {
		// TODO Auto-generated method stub
		return "tsbafk <text>";
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

