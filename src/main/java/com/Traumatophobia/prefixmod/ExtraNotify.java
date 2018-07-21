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

public class ExtraNotify implements IClientCommand {
	
	private final List aliases;

	public ExtraNotify()
    { 
		aliases = new ArrayList();
		aliases.add("addnick");
		aliases.add("addnotify");
		aliases.add("extranick");
		
    } 

	@Override
	public boolean checkPermission(MinecraftServer arg0, ICommandSender arg1) {
		return true;
	}
	
	@Override
	public void execute(MinecraftServer arg0, ICommandSender arg1, String[] arg2) throws CommandException {
		boolean alreadythere = false;
		if (arg2.length > 1) {
			Minecraft.getMinecraft().player.sendMessage(new TextComponentString("No spaces allowed!"));
		}
		else if (arg2.length == 0) {
			Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Please enter an extra nickname"));
		}
		else {
			String newnick = arg2[0].toLowerCase();
			for (int i = 0; i < PrefixMod.nicks.size(); i++) {
				String currentnicks = PrefixMod.nicks.get(i).toLowerCase();
				if (currentnicks.equals(newnick)) {
					alreadythere = true;
					
				}
			}
			if (alreadythere == false) {
				PrefixMod.nicks.add(arg2[0]);
				Minecraft.getMinecraft().player.sendMessage(new TextComponentString("You will be notified when someone says "+arg2[0]));
			}
			else {
				Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Nickname (or part of the nickname) already exists"));
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
		return "extranotify";
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer arg0, ICommandSender arg1, String[] arg2, BlockPos arg3) {
		List<String> empty = new ArrayList<>();
		return empty;
	}

	@Override
	public String getUsage(ICommandSender arg0) {
		// TODO Auto-generated method stub
		return "extranotify <text>";
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
