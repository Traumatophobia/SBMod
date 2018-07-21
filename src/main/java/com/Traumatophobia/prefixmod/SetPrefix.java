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
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.IClientCommand;

public class SetPrefix implements IClientCommand {
	
	public static String ChatPrefix = "";
	
	private final List aliases;

	public SetPrefix() 
    { 
		aliases = new ArrayList();
		aliases.add("sprefix");
		aliases.add("scp");
		aliases.add("resetchatprefix");
		aliases.add("rcp");
		
    } 
	
	@Override
	public boolean checkPermission(MinecraftServer arg0, ICommandSender arg1) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void execute(MinecraftServer arg0, ICommandSender arg1, String[] arg2) throws CommandException {
		boolean nospace = false;
		if (arg2.length >= 2) {

			Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Spaces are not allowed!"));
			
		}
		if (arg2.length == 0) {
			Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Prefix Reset"));
			ChatPrefix = "";
		}


		else {
			if (arg2[0].length() == 2 && arg2[0].startsWith("&")) {
				ChatPrefix = arg2[0];
				nospace = true;
			}
	    	String rawstring = arg2[0];
	    	String[] splitformat = rawstring.split("&");
	    	String newstring = "";
	    	
	    	if (rawstring.contains("&")) {
	    	for (int i = 0; i < splitformat.length; i++) {
	    		if (splitformat[i].startsWith("a")) {
	    			splitformat[i] = splitformat[i].substring(1);
	    			newstring += TextFormatting.GREEN + splitformat[i];
	    		}
	    		else if (splitformat[i].startsWith("b")) {
	    			splitformat[i] = splitformat[i].substring(1);
	    			newstring += TextFormatting.AQUA + splitformat[i];
	    		}
	    		else if (splitformat[i].startsWith("c")) {
	    			splitformat[i] = splitformat[i].substring(1);
	    			newstring += TextFormatting.RED + splitformat[i];
	    		}
	    		else if (splitformat[i].startsWith("d")) {
	    			splitformat[i] = splitformat[i].substring(1);
	    			newstring += TextFormatting.LIGHT_PURPLE + splitformat[i];
	    		}
	    		else if (splitformat[i].startsWith("e")) {
	    			splitformat[i] = splitformat[i].substring(1);
	    			newstring += TextFormatting.YELLOW + splitformat[i];
	    		}
	    		else if (splitformat[i].startsWith("f")) {
	    			splitformat[i] = splitformat[i].substring(1);
	    			newstring += TextFormatting.WHITE + splitformat[i];
	    		}
	    		else if (splitformat[i].startsWith("0")) {
	    			splitformat[i] = splitformat[i].substring(1);
	    			newstring += TextFormatting.BLACK + splitformat[i];
	    		}
	    		else if (splitformat[i].startsWith("1")) {
	    			splitformat[i] = splitformat[i].substring(1);
	    			newstring += TextFormatting.DARK_BLUE + splitformat[i];
	    		}
	    		else if (splitformat[i].startsWith("2")) {
	    			splitformat[i] = splitformat[i].substring(1);
	    			newstring += TextFormatting.DARK_GREEN + splitformat[i];
	    		}
	    		else if (splitformat[i].startsWith("3")) {
	    			splitformat[i] = splitformat[i].substring(1);
	    			newstring += TextFormatting.DARK_AQUA + splitformat[i];
	    		}
	    		else if (splitformat[i].startsWith("4")) {
	    			splitformat[i] = splitformat[i].substring(1);
	    			newstring += TextFormatting.DARK_RED + splitformat[i];
	    		}
	    		else if (splitformat[i].startsWith("5")) {
	    			splitformat[i] = splitformat[i].substring(1);
	    			newstring += TextFormatting.DARK_PURPLE + splitformat[i];
	    		}
	    		else if (splitformat[i].startsWith("6")) {
	    			splitformat[i] = splitformat[i].substring(1);
	    			newstring += TextFormatting.GOLD + splitformat[i];
	    		}
	    		else if (splitformat[i].startsWith("7")) {
	    			splitformat[i] = splitformat[i].substring(1);
	    			newstring += TextFormatting.GRAY + splitformat[i];
	    		}
	    		else if (splitformat[i].startsWith("8")) {
	    			splitformat[i] = splitformat[i].substring(1);
	    			newstring += TextFormatting.DARK_GRAY + splitformat[i];
	    		}
	    		else if (splitformat[i].startsWith("9")) {
	    			splitformat[i] = splitformat[i].substring(1);
	    			newstring += TextFormatting.BLUE + splitformat[i];
	    		}
	    		else if (splitformat[i].startsWith("k")) {
	    			splitformat[i] = splitformat[i].substring(1);
	    			newstring += TextFormatting.OBFUSCATED + splitformat[i];
	    		}
	    		else if (splitformat[i].startsWith("l")) {
	    			splitformat[i] = splitformat[i].substring(1);
	    			newstring += TextFormatting.BOLD + splitformat[i];
	    		}
	    		else if (splitformat[i].startsWith("m")) {
	    			splitformat[i] = splitformat[i].substring(1);
	    			newstring += TextFormatting.STRIKETHROUGH + splitformat[i];
	    		}
	    		else if (splitformat[i].startsWith("n")) {
	    			splitformat[i] = splitformat[i].substring(1);
	    			newstring += TextFormatting.UNDERLINE + splitformat[i];
	    		}
	    		else if (splitformat[i].startsWith("o")) {
	    			splitformat[i] = splitformat[i].substring(1);
	    			newstring += TextFormatting.ITALIC + splitformat[i];
	    		}
	    		else if (splitformat[i].startsWith("r")) {
	    			splitformat[i] = splitformat[i].substring(1);
	    			newstring += TextFormatting.RESET + splitformat[i];
	    		}
	    		else {
	    			newstring += splitformat[i];
	    		}
	    	}
	    	}
	    	else {
	    		newstring = rawstring;
	    	}
	    	
	    	if (nospace == false) {
	    	Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Your prefix: "+newstring+" [Chat]"));
	    	ChatPrefix = rawstring+" ";
	    	}
	    	if (nospace == true) {
	    		Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Your prefix: "+newstring+"[Chat]"));
	    		ChatPrefix = rawstring+"";
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
		return "setchatprefix";
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
		return "setchatprefix <text>";
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
