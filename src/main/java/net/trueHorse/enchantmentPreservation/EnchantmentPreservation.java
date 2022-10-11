package net.trueHorse.enchantmentPreservation;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.trueHorse.enchantmentPreservation.config.EnchantmentPreservationConfig;
import net.trueHorse.enchantmentPreservation.items.EnchantmentPreservationItems;
import net.trueHorse.enchantmentPreservation.recipes.EnchantmentPreservationRecipeSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EnchantmentPreservation implements ModInitializer {

	public static final Logger LOGGER = LogManager.getLogger("enchantmentpreservation");
	public static final ItemGroup ENCHANTMENT_STONES_GROUP = FabricItemGroupBuilder.create(new Identifier("enchantmentpreservation","main_group")).icon(()->new ItemStack(EnchantmentPreservationItems.BLUE_ENCHANTMENT_STONE)).build();
	public static final Tag<Item> ENCHANTMENT_STONES = TagRegistry.item(new Identifier("enchantmentpreservation","enchantment_stones"));

	@Override
	public void onInitialize() {
		EnchantmentPreservationConfig.loadConfigs();
		EnchantmentPreservationItems.registerItems();
		EnchantmentPreservationRecipeSerializer.registerSpecialRecipe();
		if(Boolean.parseBoolean(EnchantmentPreservationConfig.getVal("brotherEdition"))){
			LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, tableBuilder, source) -> {
				if (new Identifier("minecraft", "chests/end_city_treasure") .equals(id)) {
					LootPool.Builder poolBuilder = LootPool.builder()
							.with(ItemEntry.builder(EnchantmentPreservationItems.BIGS_BLACK_HALF).weight(1))
							.with(ItemEntry.builder(EnchantmentPreservationItems.BIGS_RED_HALF).weight(1));

					tableBuilder.pool(poolBuilder);
				}
			});
		}
	}
}
