package com.Traumatophobia.prefixmod;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.IClientCommand;

public class EnchantAll implements IClientCommand {


	
	private final List aliases;

	public EnchantAll()
    { 
		aliases = new ArrayList();
		aliases.add("eaa");
		aliases.add("godall");
		
    } 
	

	@Override
	public boolean checkPermission(MinecraftServer arg0, ICommandSender arg1) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void execute(MinecraftServer arg0, ICommandSender arg1, String[] arg2) {
		Item item = Minecraft.getMinecraft().player.getHeldItemMainhand().getItem();
		if (PrefixMod.enchant == false) {
		if (item == Items.DIAMOND_PICKAXE || item == Items.DIAMOND_SHOVEL || item == Items.IRON_PICKAXE || item == Items.IRON_SHOVEL || item == Items.STONE_PICKAXE || item == Items.STONE_SHOVEL || item == Items.GOLDEN_PICKAXE || item == Items.GOLDEN_SHOVEL || item == Items.WOODEN_PICKAXE || item == Items.WOODEN_SHOVEL|| item == Items.SHEARS) {
			PrefixMod.pressTime = 0;
			PrefixMod.enchanttool = true;
			PrefixMod.enchant = true;
		}
		else if (item == Items.DIAMOND_AXE || item == Items.IRON_AXE || item == Items.STONE_AXE || item == Items.GOLDEN_AXE || item == Items.WOODEN_AXE) {
			PrefixMod.pressTime = 0;
			PrefixMod.enchantaxe = true;
			PrefixMod.enchant = true;
		}
		else if (item == Items.DIAMOND_SWORD || item == Items.IRON_SWORD || item == Items.STONE_SWORD || item == Items.GOLDEN_SWORD || item == Items.WOODEN_SWORD) {
			PrefixMod.pressTime = 0;
			PrefixMod.enchantsword = true;
			PrefixMod.enchant = true;
		}
		else if (item == Items.BOW) {
			PrefixMod.pressTime = 0;
			PrefixMod.enchantbow = true;
			PrefixMod.enchant = true;
		}
		else if (item == Items.DIAMOND_CHESTPLATE || item == Items.DIAMOND_LEGGINGS || item == Items.IRON_CHESTPLATE || item == Items.IRON_LEGGINGS || item == Items.GOLDEN_CHESTPLATE || item == Items.GOLDEN_LEGGINGS || item == Items.LEATHER_CHESTPLATE || item == Items.LEATHER_LEGGINGS || item == Items.CHAINMAIL_CHESTPLATE || item == Items.CHAINMAIL_LEGGINGS ) {
			PrefixMod.enchanttorso = true;
			PrefixMod.pressTime = 0;
			PrefixMod.enchant = true;
		}
		else if (item == Items.DIAMOND_HELMET || item == Items.IRON_HELMET || item == Items.GOLDEN_HELMET || item == Items.CHAINMAIL_HELMET || item == Items.LEATHER_HELMET) {
			PrefixMod.enchanthelmet = true;
			PrefixMod.pressTime = 0;
			PrefixMod.enchant = true;
		}
		else if (item == Items.DIAMOND_BOOTS || item == Items.IRON_BOOTS || item == Items.GOLDEN_BOOTS || item == Items.CHAINMAIL_BOOTS || item == Items.LEATHER_BOOTS) {
			PrefixMod.enchantboots = true;
			PrefixMod.pressTime = 0;
			PrefixMod.enchant = true;
		}
		else if (item == Items.FISHING_ROD) {
			PrefixMod.enchantrod = true;
			PrefixMod.pressTime = 0;
			PrefixMod.enchant = true;
		}
		else if (item == Items.FLINT_AND_STEEL || item == Items.ELYTRA || item == Items.WOODEN_HOE || item == Items.STONE_HOE || item == Items.IRON_HOE || item == Items.GOLDEN_HOE || item == Items.DIAMOND_HOE || item == Items.SHIELD) {
			PrefixMod.enchantother = true;
			PrefixMod.pressTime = 0;
			PrefixMod.enchant = true;
		}
		}
		else {
			Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Wait until current item has finished enchanting!"));
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
		return "enchantall";
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
		return "enchantall";
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