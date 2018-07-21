package com.Traumatophobia.prefixmod;

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
import java.util.ArrayList;

public class ToggleNotify implements IClientCommand {
	private final List aliases;
	
	public ToggleNotify() {
		aliases = new ArrayList();
		aliases.add("togglenotify");
		aliases.add("alert");
	}
		
	@Override
	public boolean checkPermission(MinecraftServer arg0, ICommandSender arg1) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void execute(MinecraftServer arg0, ICommandSender arg1, String[] arg2) throws CommandException {
		ITextComponent telluser = new TextComponentString("");
		if (PrefixMod.toggled == false) {
			PrefixMod.toggled = true;
			telluser.appendText("Username notifications disabled");
		}
		else if (PrefixMod.toggled == true) {
			PrefixMod.toggled = false;
			telluser.appendText("Username notifications enabled");
			
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
		return "notify";
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
		return "notify";
	}

	@Override
	public boolean isUsernameIndex(String[] arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int compareTo(ICommand o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean allowUsageWithoutPrefix(ICommandSender arg0, String arg1) {
		// TODO Auto-generated method stub
		return false;
	}

}
