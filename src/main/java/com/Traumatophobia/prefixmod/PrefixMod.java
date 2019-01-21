package com.Traumatophobia.prefixmod;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.Traumatophobia.SkyblockMod.proxy.CommonProxy;


import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.NoteBlockEvent.Note;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientDisconnectionFromServerEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import scala.swing.event.KeyReleased;


@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS)
public class PrefixMod {
	boolean loggedon = false;
	public static boolean toggled = true;
	@Instance
	public static PrefixMod instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	List<String> colours = Arrays.asList("white","red","orange","pink","yellow","lime","green","light_blue","cyan","blue","magenta","purple","brown","gray","silver","black");
	List<String> mobs = Arrays.asList("pig","cow","mushroomcow","sheep","chicken","rabbit","parrot","ocelot");
	public static int index = 0;
	@EventHandler
    public void init(FMLInitializationEvent event)
    {
        FMLCommonHandler.instance().bus().register(this);
        MinecraftForge.EVENT_BUS.register(this);
    }
	@EventHandler
	public void serverLoad(FMLPostInitializationEvent event)
	{
		ClientCommandHandler.instance.registerCommand(new ToggleNotify());
		ClientCommandHandler.instance.registerCommand(new SetPrefix());
		ClientCommandHandler.instance.registerCommand(new AutoAdvert());
		ClientCommandHandler.instance.registerCommand(new AutoAdvertTime());
		ClientCommandHandler.instance.registerCommand(new AutoSpawnMob());
		ClientCommandHandler.instance.registerCommand(new AutoFix());
		ClientCommandHandler.instance.registerCommand(new AutoPrivate());
		ClientCommandHandler.instance.registerCommand(new AutoLotBuy());
		ClientCommandHandler.instance.registerCommand(new Afk());
		ClientCommandHandler.instance.registerCommand(new Colours());
		ClientCommandHandler.instance.registerCommand(new EnchantAll());
		ClientCommandHandler.instance.registerCommand(new ExtraNotify());
		ClientCommandHandler.instance.registerCommand(new RemoveNotify());
		ClientCommandHandler.instance.registerCommand(new JoinCommands());
		ClientCommandHandler.instance.registerCommand(new noticeclag());
		ClientCommandHandler.instance.registerCommand(new TestCommand());
		ClientCommandHandler.instance.registerCommand(new ToggleAdvancement());
		ClientCommandHandler.instance.registerCommand(new ToggleAfk());
		ClientCommandHandler.instance.registerCommand(new ToggleBans());
		ClientCommandHandler.instance.registerCommand(new ToggleGround());
		ClientCommandHandler.instance.registerCommand(new ToggleJoins());
		ClientCommandHandler.instance.registerCommand(new ToggleLottery());
		ClientCommandHandler.instance.registerCommand(new ToggleLucky());
		ClientCommandHandler.instance.registerCommand(new ToggleMobarena());
		ClientCommandHandler.instance.registerCommand(new ToggleRarecrate());
		ClientCommandHandler.instance.registerCommand(new ToggleShop());
		ClientCommandHandler.instance.registerCommand(new ToggleSkychat());
		ClientCommandHandler.instance.registerCommand(new ToggleSpawned());
		ClientCommandHandler.instance.registerCommand(new ToggleTips());
		ClientCommandHandler.instance.registerCommand(new ToggleVote());
		ClientCommandHandler.instance.registerCommand(new ToggleWelcome());
		ClientCommandHandler.instance.registerCommand(new ToggleAll());
		ClientCommandHandler.instance.registerCommand(new SetSuffix());
		ClientCommandHandler.instance.registerCommand(new Help());
	}
	
	public static int pressTime = 0;
	public static int spawnTime = 0;
	public static int advertTimer = 0;
	public static boolean enchantsword = false;
	public static boolean playsound = false;
	public static boolean enchanttool = false;
	public static boolean enchanttorso = false;
	public static boolean enchantbow = false;
	public static boolean enchanthelmet = false;
	public static boolean enchantboots = false;
	public static boolean enchantrod = false;
	public static boolean enchantother = false;
	public static boolean enchantaxe = false;
	public static boolean enchant = false;
	public static boolean spawnmobs = false;
	public static boolean autofix = false;
	public static boolean autobuy = false;
	public static boolean autoprivate = false;
	int welcomemessage = 0;
	int cooldowncounter = 0;
	boolean sendwelcome = false;
	boolean isgod = true;
	boolean isfly = true;
	boolean turnon = false;
	boolean printmsg = false;
	int turncount = 0;
	public static List<String> afknames = new ArrayList<String>();
	
	
	public static void converttext(String text) {
		for (int i = 0; i < text.length(); i ++) {
			String character = String.valueOf(text.charAt(i));
			
			try {
				Robot robot = new Robot();
				robot.keyRelease(KeyEvent.VK_SHIFT);
				switch(character) {
				case "a": robot.keyPress(KeyEvent.VK_A); robot.keyRelease(KeyEvent.VK_A); break;
				case "b": robot.keyPress(KeyEvent.VK_B); robot.keyRelease(KeyEvent.VK_B); break;
				case "c": robot.keyPress(KeyEvent.VK_C); robot.keyRelease(KeyEvent.VK_C); break;
				case "d": robot.keyPress(KeyEvent.VK_D); robot.keyRelease(KeyEvent.VK_D); break;
				case "e": robot.keyPress(KeyEvent.VK_E); robot.keyRelease(KeyEvent.VK_E); break;
				case "f": robot.keyPress(KeyEvent.VK_F); robot.keyRelease(KeyEvent.VK_F); break;
				case "g": robot.keyPress(KeyEvent.VK_G); robot.keyRelease(KeyEvent.VK_G); break;
				case "h": robot.keyPress(KeyEvent.VK_H); robot.keyRelease(KeyEvent.VK_H); break;
				case "i": robot.keyPress(KeyEvent.VK_I); robot.keyRelease(KeyEvent.VK_I); break;
				case "j": robot.keyPress(KeyEvent.VK_J); robot.keyRelease(KeyEvent.VK_J); break;
				case "k": robot.keyPress(KeyEvent.VK_K); robot.keyRelease(KeyEvent.VK_K); break;
				case "l": robot.keyPress(KeyEvent.VK_L); robot.keyRelease(KeyEvent.VK_L); break;
				case "m": robot.keyPress(KeyEvent.VK_M); robot.keyRelease(KeyEvent.VK_M); break;
				case "n": robot.keyPress(KeyEvent.VK_N); robot.keyRelease(KeyEvent.VK_N); break;
				case "o": robot.keyPress(KeyEvent.VK_O); robot.keyRelease(KeyEvent.VK_O); break;
				case "p": robot.keyPress(KeyEvent.VK_P); robot.keyRelease(KeyEvent.VK_P); break;
				case "q": robot.keyPress(KeyEvent.VK_Q); robot.keyRelease(KeyEvent.VK_Q); break;
				case "r": robot.keyPress(KeyEvent.VK_R); robot.keyRelease(KeyEvent.VK_R); break;
				case "s": robot.keyPress(KeyEvent.VK_S); robot.keyRelease(KeyEvent.VK_S); break;
				case "t": robot.keyPress(KeyEvent.VK_T); robot.keyRelease(KeyEvent.VK_T); break;
				case "u": robot.keyPress(KeyEvent.VK_U); robot.keyRelease(KeyEvent.VK_U); break;
				case "v": robot.keyPress(KeyEvent.VK_V); robot.keyRelease(KeyEvent.VK_V); break;
				case "w": robot.keyPress(KeyEvent.VK_W); robot.keyRelease(KeyEvent.VK_W); break;
				case "x": robot.keyPress(KeyEvent.VK_X); robot.keyRelease(KeyEvent.VK_X); break;
				case "y": robot.keyPress(KeyEvent.VK_Y); robot.keyRelease(KeyEvent.VK_Y); break;
				case "z": robot.keyPress(KeyEvent.VK_Z); robot.keyRelease(KeyEvent.VK_Z); break;
				
				case "A": robot.keyPress(KeyEvent.VK_SHIFT); robot.keyPress(KeyEvent.VK_A); robot.keyRelease(KeyEvent.VK_A); robot.keyRelease(KeyEvent.VK_SHIFT); break;
				case "B": robot.keyPress(KeyEvent.VK_SHIFT); robot.keyPress(KeyEvent.VK_B); robot.keyRelease(KeyEvent.VK_B); robot.keyRelease(KeyEvent.VK_SHIFT); break;
				case "C": robot.keyPress(KeyEvent.VK_SHIFT); robot.keyPress(KeyEvent.VK_C); robot.keyRelease(KeyEvent.VK_C); robot.keyRelease(KeyEvent.VK_SHIFT); break;
				case "D": robot.keyPress(KeyEvent.VK_SHIFT); robot.keyPress(KeyEvent.VK_D); robot.keyRelease(KeyEvent.VK_D); robot.keyRelease(KeyEvent.VK_SHIFT); break;
				case "E": robot.keyPress(KeyEvent.VK_SHIFT); robot.keyPress(KeyEvent.VK_E); robot.keyRelease(KeyEvent.VK_E); robot.keyRelease(KeyEvent.VK_SHIFT); break;
				case "F": robot.keyPress(KeyEvent.VK_SHIFT); robot.keyPress(KeyEvent.VK_F); robot.keyRelease(KeyEvent.VK_F); robot.keyRelease(KeyEvent.VK_SHIFT); break;
				case "G": robot.keyPress(KeyEvent.VK_SHIFT); robot.keyPress(KeyEvent.VK_G); robot.keyRelease(KeyEvent.VK_G); robot.keyRelease(KeyEvent.VK_SHIFT); break;
				case "H": robot.keyPress(KeyEvent.VK_SHIFT); robot.keyPress(KeyEvent.VK_H); robot.keyRelease(KeyEvent.VK_H); robot.keyRelease(KeyEvent.VK_SHIFT); break;
				case "I": robot.keyPress(KeyEvent.VK_SHIFT); robot.keyPress(KeyEvent.VK_I); robot.keyRelease(KeyEvent.VK_I); robot.keyRelease(KeyEvent.VK_SHIFT); break;
				case "J": robot.keyPress(KeyEvent.VK_SHIFT); robot.keyPress(KeyEvent.VK_J); robot.keyRelease(KeyEvent.VK_J); robot.keyRelease(KeyEvent.VK_SHIFT); break;
				case "K": robot.keyPress(KeyEvent.VK_SHIFT); robot.keyPress(KeyEvent.VK_K); robot.keyRelease(KeyEvent.VK_K); robot.keyRelease(KeyEvent.VK_SHIFT); break;
				case "L": robot.keyPress(KeyEvent.VK_SHIFT); robot.keyPress(KeyEvent.VK_L); robot.keyRelease(KeyEvent.VK_L); robot.keyRelease(KeyEvent.VK_SHIFT); break;
				case "M": robot.keyPress(KeyEvent.VK_SHIFT); robot.keyPress(KeyEvent.VK_M); robot.keyRelease(KeyEvent.VK_M); robot.keyRelease(KeyEvent.VK_SHIFT); break;
				case "N": robot.keyPress(KeyEvent.VK_SHIFT); robot.keyPress(KeyEvent.VK_N); robot.keyRelease(KeyEvent.VK_N); robot.keyRelease(KeyEvent.VK_SHIFT); break;
				case "O": robot.keyPress(KeyEvent.VK_SHIFT); robot.keyPress(KeyEvent.VK_O); robot.keyRelease(KeyEvent.VK_O); robot.keyRelease(KeyEvent.VK_SHIFT); break;
				case "P": robot.keyPress(KeyEvent.VK_SHIFT); robot.keyPress(KeyEvent.VK_P); robot.keyRelease(KeyEvent.VK_P); robot.keyRelease(KeyEvent.VK_SHIFT); break;
				case "Q": robot.keyPress(KeyEvent.VK_SHIFT); robot.keyPress(KeyEvent.VK_Q); robot.keyRelease(KeyEvent.VK_Q); robot.keyRelease(KeyEvent.VK_SHIFT); break;
				case "R": robot.keyPress(KeyEvent.VK_SHIFT); robot.keyPress(KeyEvent.VK_R); robot.keyRelease(KeyEvent.VK_R); robot.keyRelease(KeyEvent.VK_SHIFT); break;
				case "S": robot.keyPress(KeyEvent.VK_SHIFT); robot.keyPress(KeyEvent.VK_S); robot.keyRelease(KeyEvent.VK_S); robot.keyRelease(KeyEvent.VK_SHIFT); break;
				case "T": robot.keyPress(KeyEvent.VK_SHIFT); robot.keyPress(KeyEvent.VK_T); robot.keyRelease(KeyEvent.VK_T); robot.keyRelease(KeyEvent.VK_SHIFT); break;
				case "U": robot.keyPress(KeyEvent.VK_SHIFT); robot.keyPress(KeyEvent.VK_U); robot.keyRelease(KeyEvent.VK_U); robot.keyRelease(KeyEvent.VK_SHIFT); break;
				case "V": robot.keyPress(KeyEvent.VK_SHIFT); robot.keyPress(KeyEvent.VK_V); robot.keyRelease(KeyEvent.VK_V); robot.keyRelease(KeyEvent.VK_SHIFT); break;
				case "W": robot.keyPress(KeyEvent.VK_SHIFT); robot.keyPress(KeyEvent.VK_W); robot.keyRelease(KeyEvent.VK_W); robot.keyRelease(KeyEvent.VK_SHIFT); break;
				case "X": robot.keyPress(KeyEvent.VK_SHIFT); robot.keyPress(KeyEvent.VK_X); robot.keyRelease(KeyEvent.VK_X); robot.keyRelease(KeyEvent.VK_SHIFT); break;
				case "Y": robot.keyPress(KeyEvent.VK_SHIFT); robot.keyPress(KeyEvent.VK_Y); robot.keyRelease(KeyEvent.VK_Y); robot.keyRelease(KeyEvent.VK_SHIFT); break;
				case "Z": robot.keyPress(KeyEvent.VK_SHIFT); robot.keyPress(KeyEvent.VK_Z); robot.keyRelease(KeyEvent.VK_Z); robot.keyRelease(KeyEvent.VK_SHIFT); break;
				
				case "0": robot.keyPress(KeyEvent.VK_0); robot.keyRelease(KeyEvent.VK_0); break;
				case "1": robot.keyPress(KeyEvent.VK_1); robot.keyRelease(KeyEvent.VK_1); break;
				case "2": robot.keyPress(KeyEvent.VK_2); robot.keyRelease(KeyEvent.VK_2); break;
				case "3": robot.keyPress(KeyEvent.VK_3); robot.keyRelease(KeyEvent.VK_3); break;
				case "4": robot.keyPress(KeyEvent.VK_4); robot.keyRelease(KeyEvent.VK_4); break;
				case "5": robot.keyPress(KeyEvent.VK_5); robot.keyRelease(KeyEvent.VK_5); break;
				case "6": robot.keyPress(KeyEvent.VK_6); robot.keyRelease(KeyEvent.VK_6); break;
				case "7": robot.keyPress(KeyEvent.VK_7); robot.keyRelease(KeyEvent.VK_7); break;
				case "8": robot.keyPress(KeyEvent.VK_8); robot.keyRelease(KeyEvent.VK_8); break;
				case "9": robot.keyPress(KeyEvent.VK_9); robot.keyRelease(KeyEvent.VK_9); break;
				
				case "&": robot.keyPress(KeyEvent.VK_SHIFT); robot.keyPress(KeyEvent.VK_7); robot.keyRelease(KeyEvent.VK_7); robot.keyRelease(KeyEvent.VK_SHIFT); break;
				
				case "[": robot.keyPress(KeyEvent.VK_OPEN_BRACKET); robot.keyRelease(KeyEvent.VK_OPEN_BRACKET); break;
				case "]": robot.keyPress(KeyEvent.VK_CLOSE_BRACKET); robot.keyRelease(KeyEvent.VK_CLOSE_BRACKET); break;

				case "_": robot.keyPress(KeyEvent.VK_SHIFT); robot.keyPress(KeyEvent.VK_SUBTRACT); robot.keyRelease(KeyEvent.VK_SUBTRACT); robot.keyRelease(KeyEvent.VK_SHIFT); break;

				case "\n": robot.keyPress(KeyEvent.VK_ENTER); robot.keyRelease(KeyEvent.VK_ENTER); break;
				
				case "¬": robot.keyPress(KeyEvent.VK_ESCAPE); robot.keyRelease(KeyEvent.VK_ESCAPE); break;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			
			
		}
	}
	

	@SubscribeEvent
	public void onLogon(ClientDisconnectionFromServerEvent event) {
		nicks = new ArrayList<String>();
		commands = new ArrayList<String>();
		loggedon = false;
		enchantsword = false;
		playsound = false;
		enchanttool = false;
		enchanttorso = false;
		enchantbow = false;
		enchanthelmet = false;
		enchantboots = false;
		enchantrod = false;
		enchantother = false;
		enchantaxe = false;
		enchant = false;
		spawnmobs = false;
		autofix = false;
		sendwelcome = false;
		isgod = true;
		isfly = true;
		turnon = false;
		ToggleAdvancement.Toggled = false;
		ToggleBans.Toggled = false;
		ToggleGround.Toggled = false;
		ToggleJoins.Toggled = false;
		ToggleLucky.Toggled = false;
		ToggleMobarena.Toggled = false;
		ToggleRarecrate.Toggled = false;
		ToggleSkychat.Toggled = false;
		ToggleSpawned.Toggled = false;
		ToggleShop.Toggled = false;
		ToggleTips.Toggled = false;
		ToggleVote.Toggled = false;
		ToggleWelcome.Toggled = false;
		toggled = false;
		ToggleLottery.Toggled = false;
		ToggleAll.Toggled = false;
	}
	
	
	@SubscribeEvent
	public void onBlockPlace(PlayerInteractEvent event) {
		if (event.getItemStack().getItem() == Items.SIGN && !printmsg) {
			printmsg = true;
		}
	}

	
	@SubscribeEvent
	public void onTitle(RenderGameOverlayEvent event) {
		String title = "";
		
		try {
			title = (String) ReflectionHelper.findField(GuiIngame.class, "displayedTitle", "field_175201_x").get(Minecraft.getMinecraft().ingameGUI);
		} catch (IllegalArgumentException | IllegalAccessException e1) {
			e1.printStackTrace();
		}
		
		finally {
		
		if (ToggleWelcome.Toggled && !title.contains("removed in") && !title.equals("")) {

				try {
					ReflectionHelper.findField(GuiIngame.class, "displayedTitle", "field_175201_x").set(Minecraft.getMinecraft().ingameGUI, "");
					ReflectionHelper.findField(GuiIngame.class, "displayedSubTitle", "field_175200_y").set(Minecraft.getMinecraft().ingameGUI, "");
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				

		}
		}
		}
	
	boolean cooldown = false;

	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event) {
		
		if (Afk.AFK && Afk.afkcooldown == false) {
			Afk.AFK = false;
			Minecraft.getMinecraft().player.sendMessage(new TextComponentString("You are no longer AFK"));
			PrefixMod.afknames = new ArrayList<String>();
		}
	}
    
	public static int afkcounter = 0;
	int printmsgtimer = 0;
	
	@SubscribeEvent
	public void onPlayerTick(ClientTickEvent event) {
		
		if (printmsg) {
			if (printmsgtimer >= 10) {
				if (autoprivate) {
					converttext(AutoPrivate.text);
				}
				
				printmsg = false;
				printmsgtimer = 0;
			}
			else {
				printmsgtimer ++;
			}
		}
		
		if (Afk.AFK) {
			if (Afk.afkcooldown) {
				if (afkcounter >= 50) {
					Afk.afkcooldown = false;
				}
			}
			if (afkcounter >= 1000) {

				afknames = new ArrayList<String>();
				
				afkcounter = 0;
			}
			
			afkcounter ++;
		}
		if (autofix && cooldown == false) {

		ItemStack item = Minecraft.getMinecraft().player.getHeldItemMainhand();
		float percent = 100*(((float) item.getMaxDamage() - (float) item.getItemDamage())/ (float) item.getMaxDamage());
		try {
		if (
				percent
				< 60
				&& autofix && item.isItemStackDamageable()
				&& cooldown == false) {
			Minecraft.getMinecraft().player.sendChatMessage("/fix all");
			cooldown = true;
		}

		} catch (Exception e) {
			
		}
		}
		
		if (turnon) {
			if (turncount == 50) {
				turnon = false;
				turncount = 0;
				if (isfly == false) {
					Minecraft.getMinecraft().player.sendChatMessage("/fly on");
					isfly = true;
				}
				if (isgod == false) {
					Minecraft.getMinecraft().player.sendChatMessage("/god on");
					isgod = true;
				}
			}
			else {
				turncount ++;
			}
		}
		if (cooldown) {
			if (cooldowncounter >= 200) {
				cooldown = false;
				cooldowncounter = 0;
			}
			cooldowncounter ++;
		}
		if (AutoAdvert.sendmessage) {
			if (advertTimer >= AutoAdvertTime.time) {
				Minecraft.getMinecraft().player.sendChatMessage(AutoAdvert.message);
				advertTimer = 0;
			}
			advertTimer ++;
		}
		
		if (sendwelcome) {
			if (welcomemessage == 100) {
			nicks.add(Minecraft.getMinecraft().player.getName());
			String boarder = "";
			for (int i = 0; i < 20; i++) {
				boarder += TextFormatting.DARK_RED+"*";
				boarder += TextFormatting.RED+"*";
			}
			Minecraft.getMinecraft().player.sendMessage(new TextComponentString(boarder));
			Minecraft.getMinecraft().player.sendMessage(new TextComponentString(""));
			Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Skyblock Mod 1.7"));
			Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Type /thelp for a list of commands."));
			Minecraft.getMinecraft().player.sendMessage(new TextComponentString(""));
			Minecraft.getMinecraft().player.sendMessage(new TextComponentString(boarder));
			
			
			}
			for (int i = 0; i < commands.size(); i++) {
				if (welcomemessage == (i*100)+100) {
					Minecraft.getMinecraft().player.sendChatMessage(commands.get(i));	
					ClientCommandHandler.instance.executeCommand(FMLClientHandler.instance().getClientPlayerEntity(), commands.get(i));
					
					sendwelcome = false;
				}
				else {
					sendwelcome = true;
				}
			}
			if (sendwelcome) {
				welcomemessage ++;
			}
			
		}
		try {
			if (pressTime == 0 && enchantaxe) {
				Minecraft.getMinecraft().player.sendChatMessage("/enchant sharpness 5");
			}
			else if (pressTime == 40 && enchantaxe) {
				Minecraft.getMinecraft().player.sendChatMessage("/enchant smite 5");
			}
			else if (pressTime == 80 && enchantaxe) {
				Minecraft.getMinecraft().player.sendChatMessage("/enchant baneofarthropods 5");
			}
			else if (pressTime == 120 && enchantaxe) {
				Minecraft.getMinecraft().player.sendChatMessage("/enchant efficiency 5");
			}
			else if (pressTime == 160 && enchantaxe) {
				Minecraft.getMinecraft().player.sendChatMessage("/enchant unbreaking 3");
			}
			else if (pressTime == 200 && enchantaxe) {
				Minecraft.getMinecraft().player.sendChatMessage("/enchant fortune 3");
			}
			else if (pressTime == 240 && enchantaxe) {
				Minecraft.getMinecraft().player.sendChatMessage("/enchant mending 1");
				enchantaxe = false;
				enchant = false;
			}
		
		if (pressTime == 0 && enchantsword) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant sharpness 5");
		}
		else if (pressTime == 40 && enchantsword) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant smite 5");
		}
		else if (pressTime == 80 && enchantsword) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant baneofarthropods 5");
		}
		else if (pressTime == 120 && enchantsword) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant fireaspect 2");
		}
		else if (pressTime == 160 && enchantsword) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant looting 3");
		}
		else if (pressTime == 200 && enchantsword) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant knockback 2");
		}
		else if (pressTime == 240 && enchantsword) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant sweepingedge 3");
		}
		else if (pressTime == 280 && enchantsword) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant unbreaking 3");
		}
		else if (pressTime == 320 && enchantsword) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant mending 1");
			enchantsword = false;
			enchant = false;
		}
		if (pressTime == 0 && enchanttool) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant efficiency 5");
		}
		else if (pressTime == 40 && enchanttool) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant unbreaking  3");
		}
		else if (pressTime == 80 && enchanttool) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant mending 1");
		}
		else if (pressTime == 120 && enchanttool) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant fortune 3");
			enchanttool = false;
			enchant = false;
		}
		if (pressTime == 0 && enchanttorso) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant protection 4");
		}
		else if (pressTime == 40 && enchanttorso) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant fireprotection 4");
		}
		else if (pressTime == 80 && enchanttorso) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant blastprotection 4");
		}
		else if (pressTime == 120 && enchanttorso) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant projectileprotection 4");
		}
		else if (pressTime == 160 && enchanttorso) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant unbreaking 3");
		}
		else if (pressTime == 200 && enchanttorso) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant thorns 3");
		}
		else if (pressTime == 240 && enchanttorso) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant mending 1");
			enchanttorso = false;
			enchant = false;
		}

		if (pressTime == 0 && enchantbow) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant power 5");
		}
		else if (pressTime == 40 && enchantbow) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant punch 2");
		}
		else if (pressTime == 80 && enchantbow) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant unbreaking 3");
		}
		else if (pressTime == 120 && enchantbow) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant flame 1");
		}
		else if (pressTime == 160 && enchantbow) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant mending 1");
		}
		else if (pressTime == 200 && enchantbow) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant infinity 1");
			enchantbow = false;
			enchant = false;
		}
		
		if (pressTime == 0 && enchanthelmet) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant protection 4");
		}
		else if (pressTime == 40 && enchanthelmet) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant fireprotection 4");
		}
		else if (pressTime == 80 && enchanthelmet) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant blastprotection 4");
		}
		else if (pressTime == 120 && enchanthelmet) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant projectileprotection 4");
		}
		else if (pressTime == 160 && enchanthelmet) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant unbreaking 3");
		}
		else if (pressTime == 200 && enchanthelmet) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant thorns 3");
		}
		else if (pressTime == 240 && enchanthelmet) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant mending 1");
		}
		else if (pressTime == 280 && enchanthelmet) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant respiration 3");
		}
		else if (pressTime == 320 && enchanthelmet) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant aquaaffinity 1");
			enchanthelmet = false;
			enchant = false;
		}
		if (pressTime == 0 && enchantboots) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant protection 4");
		}
		else if (pressTime == 40 && enchantboots) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant fireprotection 4");
		}
		else if (pressTime == 80 && enchantboots) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant blastprotection 4");
		}
		else if (pressTime == 120 && enchantboots) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant projectileprotection 4");
		}
		else if (pressTime == 160 && enchantboots) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant unbreaking 3");
		}
		else if (pressTime == 200 && enchantboots) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant thorns 3");
		}
		else if (pressTime == 240 && enchantboots) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant mending 1");
		}
		else if (pressTime == 280 && enchantboots) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant depth_strider 3");
		}
		else if (pressTime == 320 && enchantboots) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant featherfalling 4");
			enchantboots = false;
			enchant = false;
		}
		if (pressTime == 0 && enchantrod) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant lure 3");
		}
		else if (pressTime == 40 && enchantrod) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant luck 3");
		}
		else if (pressTime == 80 && enchantrod) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant unbreaking 3");
		}
		else if (pressTime == 120 && enchantrod) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant mending 1");
			enchantrod = false;
			enchant = false;
		}
		if (pressTime == 0 && enchantother) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant unbreaking 3");
		}
		else if (pressTime == 40 && enchantother) {
			Minecraft.getMinecraft().player.sendChatMessage("/enchant mending 1");
			enchantother = false;
			enchant = false;
		}
		
		if (enchant) {
			pressTime++;
		}
		}
		catch (Exception e) {
			;
		}
		
		
		try {
			if (spawnmobs) {
				if (spawnTime >= 1500) {
					Minecraft.getMinecraft().player.sendChatMessage(AutoSpawnMob.command);
					spawnTime = 0;
				}
				spawnTime++;
			}
		} catch (Exception e) {
			;
		}
		try {
			if (AutoSpawnMob.rainbow) {
				if (spawnTime >= 1500) {
					Minecraft.getMinecraft().player.sendChatMessage("/spawnmob sheep:"+colours.get(index)+" "+AutoSpawnMob.number+" "+AutoSpawnMob.player);
					index += 1;
					spawnTime = 0;
				}
				spawnTime ++;
			}
		} catch (Exception e) {
			index = 0;

		}
		try {
			if (AutoSpawnMob.all) {
				if (spawnTime >= 1500) {
					Minecraft.getMinecraft().player.sendChatMessage("/spawnmob "+mobs.get(index)+" "+AutoSpawnMob.number+" "+AutoSpawnMob.player);
					index += 1;
					spawnTime = 0;
				}
				spawnTime ++;
			}
		} catch (Exception e) {
			index = 0;

		}
		
	}
	boolean isdonor50 = false;
	@SubscribeEvent
	public void editChat(ClientChatEvent event) throws InterruptedException {
		
		if (event.getMessage().startsWith("/")) {
			if ((event.getMessage().toLowerCase().equals("/home")
					|| event.getMessage().toLowerCase().equals("/is")
					|| event.getMessage().toLowerCase().equals("/island"))) {
				if (isdonor50 = true) {
					turnon = true;
				}
			}
		}
		else {
			event.setMessage(SetPrefix.ChatPrefix+event.getMessage()+SetSuffix.ChatPrefix);
		}

	}
	
	public static List<String> nicks = new ArrayList<String>();
	public static List<String> commands = new ArrayList<String>();
	@SubscribeEvent
	public void onChat(ClientChatReceivedEvent event) {
		
		if (!loggedon) {
			sendwelcome = true;
			loggedon = true;
			welcomemessage = 0;
			try {
				BufferedReader in = new BufferedReader(new FileReader("tsbmodsettings.txt"));
				String line;
				while((line = in.readLine()) != null)
				{
					commands.add(line);
				}
				in.close();
				
			} catch (Exception e) {
				PrintWriter writer;
				try {
					writer = new PrintWriter("tsbmodsettings.txt", "UTF-8");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();

			} 
		}

		boolean changechat = false;
	    String message = event.getMessage().getFormattedText();
	    String message2 = event.getMessage().getUnformattedText();
	    String[] splitmessage2 = message2.split(" ");
	    String[] splitmessage = message.split(" ");
	    String newstring = "";
	    String playername = Minecraft.getMinecraft().player.getName();
	    
	    if (message.startsWith(TextFormatting.GOLD+"[") && message2.contains(" -> me]") && !splitmessage2[0].endsWith(":") && !splitmessage[1].endsWith(":") && (Afk.AFK || ToggleAfk.AFK)) {
	    	String msgname = "";
	    	if (message2.startsWith("[[")) {
	    		msgname = splitmessage2[1];
	    	}
	    	else {
	    		msgname = splitmessage[0].substring(1);
	    	}
	    	
	    	if (!afknames.contains(msgname)) {
	    		Minecraft.getMinecraft().player.sendChatMessage(Afk.AFKmessage);
	    		afknames.add(msgname);
	    	}

	    }
	    
	    if (message2.startsWith("Set fly mode")) {
	    	String addnick = splitmessage2[splitmessage2.length-1];
	    	 nicks.add(addnick.replace(".",""));
	    	 isdonor50 = true;
	    }
	    if (message2.startsWith("Your nickname is now")) {
	    	String addnick = splitmessage2[splitmessage2.length-1];
	    	 nicks.add(addnick.replace(".",""));
	    }
	    
	    if (message2.startsWith("WARNING") && message2.contains("seconds") && noticeclag.Toggled) {
	    	Minecraft.getMinecraft().ingameGUI.displayTitle(TextFormatting.BOLD+""+TextFormatting.RED+"Items"+TextFormatting.RESET+" clear in "+splitmessage2[7], "", 0, 0, 0);
	    }
	    
	    if (message2.contains("[SkyblockBans]") && message2.startsWith("[S") && ToggleBans.Toggled) {
	    	event.setCanceled(true);
	    }

	    if (message2.contains("Welcome to Skyblock!") && message2.startsWith("Welcome") && ToggleJoins.Toggled) {
	    	event.setCanceled(true);
	    }

	    if (message2.contains("Has made the advancement") && ToggleAdvancement.Toggled) {
	    	event.setCanceled(true);
	    }

	    if (message2.contains("has just opened a Rare Crate!") && ToggleRarecrate.Toggled) {
	    	event.setCanceled(true);
	    }

	    if (message2.contains("[MobArena]") && message2.startsWith("[M") && ToggleMobarena.Toggled) {
	    	event.setCanceled(true);
	    }

	    if (message2.startsWith("WARNING") && message2.contains("Ground items will be removed in") && ToggleGround.Toggled) {
	    	event.setCanceled(true);
	    }
	    
	    if (message2.contains("[SBLottery] Cong") && autobuy) {
	    	Minecraft.getMinecraft().player.sendChatMessage("/lot buy 2");
	    }
	    
	    if (message2.contains("[SBLottery]") && message2.startsWith("[S") && ToggleLottery.Toggled) {
	    	event.setCanceled(true);
	    }
	    
	    if (message2.contains("Set fly mode disabled") && message2.startsWith("Set")) {
	    	isfly = false;
	    }
	    
	    if (message2.contains("God mode disabled") && message2.startsWith("God")) {
	    	isgod = false;
	    }
	    
	    if (message2.contains("Unknown command") && welcomemessage < (commands.size()*100)) {
	    	event.setCanceled(true);
	    }
	    
	    if (ToggleLucky.Toggled && 
	    		(!splitmessage2[0].endsWith("from voting!") && (message2.contains("was super lucky and received") || message2.contains("was lucky and received"))) ||
	    		message2.contains("was super duper lucky and received")
	    		) {
	    	event.setCanceled(true);
	    }

	    if (message2.contains("[Skyblock]") && message2.startsWith("[S") && ToggleTips.Toggled) {
	    	event.setCanceled(true);
	    }

	    if (message2.contains("[SkyChat]") && message2.startsWith("[S") && ToggleSkychat.Toggled) {
	    	event.setCanceled(true);
	    }

	    if (ToggleShop.Toggled && 
	    		(!splitmessage2[0].endsWith(":") && (message2.contains("bartered") || message2.contains("shop") || message2.contains("bought"))) ||
	    		message2.startsWith("[Shop]")
	    		) {
	    	event.setCanceled(true);
	    }
	    
	    if (ToggleSpawned.Toggled && 
	    		(!splitmessage2[0].endsWith("Mob quantity limited to server limit.") && (message2.endsWith("rabbits spawned") || message2.endsWith("cows spawned")) ||
	    		message2.endsWith("sheep spawned") && (message2.endsWith("mushroomcows spawned")) || message2.endsWith("ocelots spawned")) ||
					message2.endsWith("parrots spawned") && (message2.endsWith("pigs spawned") && message2.endsWith("chickens spawned"))
	    		) {
	    	event.setCanceled(true);
	    }

	    try {
	    	if (splitmessage2[3].contains("vote.skyblock.net") && ToggleVote.Toggled) {
	    		event.setCanceled(true);
	    	}
	    }
	    catch (Exception e) {
	    	;
	    }
	    
	    for (int i = 0; i < splitmessage.length; i++) {
	    	boolean include = true;
	    	if ((splitmessage[i].startsWith("<") && splitmessage[i].endsWith(">")) || splitmessage[i].endsWith(":") || splitmessage[i].endsWith("]")) {
	    		include = false;
	    	}
	    	
	    	for (int j = 0; j < nicks.size(); j++) {
	    	try {
	    	if (splitmessage2[i].toLowerCase().contains(nicks.get(j).toLowerCase()) && include) {
	    		String lowermessage = splitmessage[i].toLowerCase();
	    		splitmessage[i] = (lowermessage.replace(nicks.get(j).toLowerCase(),"§e"+nicks.get(j)+"§r"));
	    		changechat = true;
	    		playsound = true;
	    	}
	    	} catch (Exception e) {
	    		;
	    	}
	    	}
	    	

	    	newstring += splitmessage[i]+" ";
	    }

	    if (changechat && !toggled) {
	    	
	    	String[] splitformat = newstring.split("§");
	    	newstring = "";
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
	    	
	    	ITextComponent newmessage = new TextComponentString("");
	    	newmessage.appendText(newstring);
	    	event.setMessage(newmessage);
	    	World world = Minecraft.getMinecraft().world;
	    	EntityPlayer player = Minecraft.getMinecraft().player;
	    	playSound(world,player);
	    }
	}
	private void playSound(World world, EntityPlayer player) {
		if (playsound) {
			world.playSound(player, player.getPosition(), SoundEvents.BLOCK_NOTE_BELL, SoundCategory.MUSIC, 10, 5);
			playsound = false;
		}
	}
	
}
