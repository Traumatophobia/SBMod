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

public class AutoLotBuy implements IClientCommand {
	
	private final List aliases;

	public AutoLotBuy()
    { 
		aliases = new ArrayList();
		aliases.add("autobuy");
		aliases.add("ab");
    } 
	

	@Override
	public boolean checkPermission(MinecraftServer arg0, ICommandSender arg1) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void execute(MinecraftServer arg0, ICommandSender arg1, String[] arg2) throws CommandException {
		ITextComponent telluser = new TextComponentString("");
		if (PrefixMod.autobuy == true) {
			PrefixMod.autobuy = false;
			telluser.appendText("Auto Lottery Disabled");
		}
		else if (PrefixMod.autobuy == false) {
			PrefixMod.autobuy = true;
			telluser.appendText("Auto Lottery Enabled");
			Minecraft.getMinecraft().player.sendChatMessage("/lot buy 2");
			
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
		return "autolotbuy";
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
		return "autolotbuy";
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

