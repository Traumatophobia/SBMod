package com.Traumatophobia.prefixmod;

import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.Text;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.IClientCommand;
import scala.reflect.api.Trees.TemplateExtractor;

public class Colours implements IClientCommand {
	
	
	private final List aliases;

	public Colours() 
    { 
		aliases = new ArrayList();
		aliases.add("colour");
		
    } 
	
	@Override
	public boolean checkPermission(MinecraftServer arg0, ICommandSender arg1) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void execute(MinecraftServer arg0, ICommandSender arg1, String[] arg2) throws CommandException {
		Minecraft.getMinecraft().player.sendMessage(new TextComponentString(TextFormatting.GREEN+"a"+TextFormatting.AQUA+"b"+TextFormatting.RED+"c"+TextFormatting.LIGHT_PURPLE+"d"+TextFormatting.YELLOW+"e"+TextFormatting.WHITE+"f"+TextFormatting.DARK_BLUE+"1"+TextFormatting.DARK_GREEN+"2"+TextFormatting.DARK_AQUA+"3"+TextFormatting.DARK_RED+"4"+TextFormatting.DARK_PURPLE+"5"+TextFormatting.GOLD+"6"+TextFormatting.GRAY+"7"+TextFormatting.DARK_GRAY+"8"+TextFormatting.BLUE+"9"+TextFormatting.BLACK+"0"));
	}

	@Override
	public List<String> getAliases() {
		// TODO Auto-generated method stub
		return this.aliases;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "colours";
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
		return "colours";
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

