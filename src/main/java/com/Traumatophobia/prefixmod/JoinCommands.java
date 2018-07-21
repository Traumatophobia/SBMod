package com.Traumatophobia.prefixmod;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.IClientCommand;

public class JoinCommands implements IClientCommand {
	private final List aliases;
	
	public JoinCommands() {
		aliases = new ArrayList();
		aliases.add("joincommand");
		aliases.add("jc");
	}
		
	@Override
	public boolean checkPermission(MinecraftServer arg0, ICommandSender arg1) {
		// TODO Auto-generated method stub
		return true;
	}

	public static List<String> commands = new ArrayList<String>();
	@Override
	public void execute(MinecraftServer arg0, ICommandSender arg1, String[] arg2) throws CommandException {
			boolean success = false;
			commands = new ArrayList<String>();
			try {
				BufferedReader in = new BufferedReader(new FileReader("tsbmodsettings.txt"));
				String line;
				while((line = in.readLine()) != null)
				{
					commands.add(line);
				}
				in.close();
				
			} catch (IOException e) {

				e.printStackTrace();

			} 
			if (arg2.length == 0) {
				Minecraft.getMinecraft().player.sendMessage(new TextComponentString("/joincommand are commands that are done whenever you log on"));
				Minecraft.getMinecraft().player.sendMessage(new TextComponentString("do /joincommand [list]"));
				Minecraft.getMinecraft().player.sendMessage(new TextComponentString("or /joincommand [add] [command]"));
				Minecraft.getMinecraft().player.sendMessage(new TextComponentString("or /joincommand [delete] [line number]"));
			}
		
			else if (arg2[0].toLowerCase().equals("list")) {
				Minecraft.getMinecraft().player.sendMessage(new TextComponentString("***********************************************"));
				for (int i = 0; i < commands.size(); i++) {
					Minecraft.getMinecraft().player.sendMessage(new TextComponentString(String.valueOf(i)+": "+commands.get(i)));
				}
				Minecraft.getMinecraft().player.sendMessage(new TextComponentString("***********************************************"));
			}
			
			else if (arg2[0].toLowerCase().equals("add")) {
				if (arg2.length == 1) {
					Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Please enter a command"));
				}
				else {
				String addtolist = "";
				for (int i = 1; i < arg2.length; i++) {
					addtolist += arg2[i]+" ";
				}
				commands.add(addtolist);
				success = true;
				Minecraft.getMinecraft().player.sendMessage(new TextComponentString(addtolist+"will be executed whenever you join"));
				
				}
			}
			else if (arg2[0].toLowerCase().equals("delete")) {
				if (arg2.length == 1) {
					Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Please enter a index"));
				}
				else {
					try {
						commands.remove(Integer.parseInt(arg2[1]));
						success = true;
					} catch (Exception e) {
						Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Invalid, please do /jc list for list of commands"));
					}
				}
			}
			
			if (success == true) {
		PrintWriter writer;
		try {
			writer = new PrintWriter("tsbmodsettings.txt", "UTF-8");
			for (int i = 0; i < commands.size(); i++) {
			writer.println(commands.get(i));
			}
			Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Successfully updated"));
			writer.close();
			success = false;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			success = false;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			success = false;
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
		return "joincommands";
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
		return "joincommands <text>";
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
