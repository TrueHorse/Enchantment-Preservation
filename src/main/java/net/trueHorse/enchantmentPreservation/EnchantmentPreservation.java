package net.trueHorse.enchantmentPreservation;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.trueHorse.enchantmentPreservation.config.EnchantmentPreservationConfig;
import net.trueHorse.enchantmentPreservation.items.EnchantmentPreservationItems;
import net.trueHorse.enchantmentPreservation.recipes.EnchantmentPreservationRecipeSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EnchantmentPreservation implements ModInitializer {

	public static final Logger LOGGER = LogManager.getLogger("enchantmentpreservation");
	public static final ItemGroup ENCHANTMENT_PRESERVATION_GROUP = FabricItemGroupBuilder.create(new Identifier("enchantmentpreservation","main_group")).icon(()->new ItemStack(EnchantmentPreservationItems.BLUE_ENCHANTMENT_STONE)).build();
	public static final Tag<Item> ENCHANTMENT_STONES = TagRegistry.item(new Identifier("enchantmentpreservation","enchantment_stones"));

	@Override
	public void onInitialize() {
		EnchantmentPreservationConfig.loadConfigs();
		EnchantmentPreservationItems.registerItems();
		EnchantmentPreservationRecipeSerializer.registerSpecialRecipe();
	}
}
