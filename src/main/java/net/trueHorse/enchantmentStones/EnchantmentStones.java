package net.trueHorse.enchantmentStones;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnchantmentStones implements ModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("enchantmentstones");

	public static final ItemGroup ENCHANTMENT_STONES_GROUP = FabricItemGroupBuilder.create(new Identifier("enchantmentstones","main_group")).icon(()->new ItemStack(Items.LAPIS_LAZULI)).build();
	public static final Item ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings().group(ENCHANTMENT_STONES_GROUP));

	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM,new Identifier("enchantmentstones","enchantment_stone"),ENCHANTMENT_STONE);
		EnchantmentStonesRecipeSerializer.registerSpecialRecipe();
	}
}
