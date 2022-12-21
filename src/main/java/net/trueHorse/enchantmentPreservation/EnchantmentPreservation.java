package net.trueHorse.enchantmentPreservation;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.trueHorse.enchantmentPreservation.config.EnchantmentPreservationConfig;
import net.trueHorse.enchantmentPreservation.items.EnchantmentPreservationItems;
import net.trueHorse.enchantmentPreservation.recipes.EnchantmentPreservationRecipeSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnchantmentPreservation implements ModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("enchantmentpreservation");
	public static final ItemGroup ENCHANTMENT_PRESERVATION_GROUP = FabricItemGroup.builder(new Identifier("enchantmentpreservation","main_group")).icon(()->new ItemStack(EnchantmentPreservationItems.BLUE_ENCHANTMENT_STONE)).build();
	public static final TagKey<Item> ENCHANTMENT_STONES = TagKey.of(RegistryKeys.ITEM,new Identifier("enchantmentpreservation","enchantment_stones"));

	@Override
	public void onInitialize() {
		EnchantmentPreservationConfig.loadConfigs();
		EnchantmentPreservationItems.registerItems();
		EnchantmentPreservationItems.groupItems();
		EnchantmentPreservationRecipeSerializer.registerSpecialRecipe();
	}
}
