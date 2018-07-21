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
import net.minecraftforge.client.IClientCommand;

public class RemoveNotify implements IClientCommand {
	
	private final List aliases;

	public RemoveNotify()
    { 
		aliases = new ArrayList();
		aliases.add("removenick");
		aliases.add("removenotify");
		aliases.add("delnick");
		aliases.add("nicklist");
		
    } 

	@Override
	public boolean checkPermission(MinecraftServer arg0, ICommandSender arg1) {
		return true;
	}

	@Override
	public void execute(MinecraftServer arg0, ICommandSender arg1, String[] arg2) throws CommandException {
		if (arg2.length > 1) {
			Minecraft.getMinecraft().player.sendMessage(new TextComponentString("No spaces allowed!"));
		}
		else if (arg2.length == 0) {
			Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Current nicks:"));
			for (int i = 1; i < PrefixMod.nicks.size(); i ++) {
			Minecraft.getMinecraft().player.sendMessage(new TextComponentString(PrefixMod.nicks.get(i)));
			}
		}
		else {
			try {
				int index = PrefixMod.nicks.indexOf(arg2[0]);
				PrefixMod.nicks.remove(index);
				Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Nickname successfully removed"));
			} catch (Exception e) {
				Minecraft.getMinecraft().player.sendMessage(new TextComponentString("That nick does not exist. do /delnick for list of nicks"));
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
		return "removenotify";
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer arg0, ICommandSender arg1, String[] arg2, BlockPos arg3) {
		List<String> empty = new ArrayList<>();
		return empty;
	}

	@Override
	public String getUsage(ICommandSender arg0) {
		// TODO Auto-generated method stub
		return "removenotify";
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
