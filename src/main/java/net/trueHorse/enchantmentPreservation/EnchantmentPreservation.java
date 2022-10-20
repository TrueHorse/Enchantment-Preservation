package net.trueHorse.enchantmentPreservation;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.trueHorse.enchantmentPreservation.config.EnchantmentPreservationConfig;
import net.trueHorse.enchantmentPreservation.items.EnchantmentPreservationItems;
import net.trueHorse.enchantmentPreservation.recipes.EnchantmentPreservationRecipeSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnchantmentPreservation implements ModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("enchantmentpreservation");
	public static final ItemGroup ENCHANTMENT_PRESERVATION_GROUP = FabricItemGroupBuilder.create(new Identifier("enchantmentpreservation","main_group")).icon(()->new ItemStack(EnchantmentPreservationItems.BLUE_ENCHANTMENT_STONE)).build();
	public static final TagKey<Item> ENCHANTMENT_STONES = TagKey.of(Registry.ITEM_KEY,new Identifier("enchantmentpreservation","enchantment_stones"));

	@Override
	public void onInitialize() {
		EnchantmentPreservationConfig.loadConfigs();
		EnchantmentPreservationItems.registerItems();
		EnchantmentPreservationRecipeSerializer.registerSpecialRecipe();
	}
}
