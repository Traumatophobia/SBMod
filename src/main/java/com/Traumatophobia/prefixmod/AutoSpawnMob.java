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

public class AutoSpawnMob implements IClientCommand {

	public static String command = "";
	private final List aliases;
	public static boolean rainbow = false;
	public static boolean all = false;
	public static String number = "";
	public static String player = "";

	public AutoSpawnMob()
    { 
		aliases = new ArrayList();
		aliases.add("asm");
		aliases.add("stopspawnmob");
		aliases.add("ssm");
    } 

	@Override
	public boolean checkPermission(MinecraftServer arg0, ICommandSender arg1) {
		return true;
	}

	@Override
	public void execute(MinecraftServer arg0, ICommandSender arg1, String[] arg2) throws CommandException {
		if (arg2.length == 0) {
			command = "";
			PrefixMod.spawnmobs = false;
			rainbow = false;
			all = false;
			Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Stopped spawning mobs"));
		}
		else if (arg2.length != 3) {
			Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Please do /autospawnmob <Mob> <Number> <Player>"));
		}
		else {
			if (arg2[0].toLowerCase().equals("sheep:rainbow")) {
				rainbow = true;
				all = false;
				PrefixMod.spawnmobs = false;
				PrefixMod.spawnTime = 3000;
				number = arg2[1];
				player = arg2[2];
				PrefixMod.index = 0;
				Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Spawning rainbow sheep..."));
			}
			else if (arg2[0].toLowerCase().equals("all")) {
				all = true;
				rainbow = false;
				PrefixMod.spawnmobs = false;
				PrefixMod.spawnTime = 3000;
				number = arg2[1];
				player = arg2[2];
				PrefixMod.index = 0;
				Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Spawning all farm animals..."));
			}
			else {
				command = "/spawnmob "+arg2[0]+" "+arg2[1]+" "+arg2[2];
				PrefixMod.spawnmobs = true;
				PrefixMod.spawnTime = 3000;
				rainbow = false;
				all = false;
				Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Spawning mobs..."));
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
		return "autospawnmob";
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer arg0, ICommandSender arg1, String[] arg2, BlockPos arg3) {
		List<String> empty = new ArrayList<>();
		return empty;
	}

	@Override
	public String getUsage(ICommandSender arg0) {
		// TODO Auto-generated method stub
		return "autospawnmob <text>";
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
