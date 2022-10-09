package net.trueHorse.enchantmentStones;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.trueHorse.enchantmentStones.config.EnchantmentStonesConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnchantmentStones implements ModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("enchantmentstones");

	public static final ItemGroup ENCHANTMENT_STONES_GROUP = FabricItemGroupBuilder.create(new Identifier("enchantmentstones","main_group")).icon(()->new ItemStack(EnchantmentStones.BLUE_ENCHANTMENT_STONE)).build();
	public static final EnchantmentStoneItem WHITE_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings().group(ENCHANTMENT_STONES_GROUP));
	public static final EnchantmentStoneItem BLACK_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings().group(ENCHANTMENT_STONES_GROUP));
	public static final EnchantmentStoneItem GRAY_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings().group(ENCHANTMENT_STONES_GROUP));
	public static final EnchantmentStoneItem LIGHT_GRAY_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings().group(ENCHANTMENT_STONES_GROUP));
	public static final EnchantmentStoneItem YELLOW_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings().group(ENCHANTMENT_STONES_GROUP));
	public static final EnchantmentStoneItem ORANGE_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings().group(ENCHANTMENT_STONES_GROUP));
	public static final EnchantmentStoneItem RED_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings().group(ENCHANTMENT_STONES_GROUP));
	public static final EnchantmentStoneItem PURPLE_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings().group(ENCHANTMENT_STONES_GROUP));
	public static final EnchantmentStoneItem MAGENTA_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings().group(ENCHANTMENT_STONES_GROUP));
	public static final EnchantmentStoneItem PINK_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings().group(ENCHANTMENT_STONES_GROUP));
	public static final EnchantmentStoneItem BLUE_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings().group(ENCHANTMENT_STONES_GROUP));
	public static final EnchantmentStoneItem LIGHT_BLUE_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings().group(ENCHANTMENT_STONES_GROUP));
	public static final EnchantmentStoneItem LIME_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings().group(ENCHANTMENT_STONES_GROUP));
	public static final EnchantmentStoneItem GREEN_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings().group(ENCHANTMENT_STONES_GROUP));
	public static final EnchantmentStoneItem CYAN_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings().group(ENCHANTMENT_STONES_GROUP));
	public static final EnchantmentStoneItem BROWN_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings().group(ENCHANTMENT_STONES_GROUP));
	public static final TagKey<Item> ENCHANTMENT_STONES = TagKey.of(Registry.ITEM_KEY,new Identifier("enchantmentstones","enchantment_stones"));

	@Override
	public void onInitialize() {
		EnchantmentStonesConfig.loadConfigs();

		Registry.register(Registry.ITEM,new Identifier("enchantmentstones","white_enchantment_stone"),WHITE_ENCHANTMENT_STONE);
		Registry.register(Registry.ITEM,new Identifier("enchantmentstones","orange_enchantment_stone"),ORANGE_ENCHANTMENT_STONE);
		Registry.register(Registry.ITEM,new Identifier("enchantmentstones","light_blue_enchantment_stone"),LIGHT_BLUE_ENCHANTMENT_STONE);
		Registry.register(Registry.ITEM,new Identifier("enchantmentstones","blue_enchantment_stone"),BLUE_ENCHANTMENT_STONE);
		Registry.register(Registry.ITEM,new Identifier("enchantmentstones","yellow_enchantment_stone"),YELLOW_ENCHANTMENT_STONE);
		Registry.register(Registry.ITEM,new Identifier("enchantmentstones","red_enchantment_stone"),RED_ENCHANTMENT_STONE);
		Registry.register(Registry.ITEM,new Identifier("enchantmentstones","purple_enchantment_stone"),PURPLE_ENCHANTMENT_STONE);
		Registry.register(Registry.ITEM,new Identifier("enchantmentstones","pink_enchantment_stone"),PINK_ENCHANTMENT_STONE);
		Registry.register(Registry.ITEM,new Identifier("enchantmentstones","magenta_enchantment_stone"),MAGENTA_ENCHANTMENT_STONE);
		Registry.register(Registry.ITEM,new Identifier("enchantmentstones","cyan_enchantment_stone"),CYAN_ENCHANTMENT_STONE);
		Registry.register(Registry.ITEM,new Identifier("enchantmentstones","green_enchantment_stone"),GREEN_ENCHANTMENT_STONE);
		Registry.register(Registry.ITEM,new Identifier("enchantmentstones","lime_enchantment_stone"),LIME_ENCHANTMENT_STONE);
		Registry.register(Registry.ITEM,new Identifier("enchantmentstones","black_enchantment_stone"),BLACK_ENCHANTMENT_STONE);
		Registry.register(Registry.ITEM,new Identifier("enchantmentstones","gray_enchantment_stone"),GRAY_ENCHANTMENT_STONE);
		Registry.register(Registry.ITEM,new Identifier("enchantmentstones","light_gray_enchantment_stone"),LIGHT_GRAY_ENCHANTMENT_STONE);
		Registry.register(Registry.ITEM,new Identifier("enchantmentstones","brown_enchantment_stone"),BROWN_ENCHANTMENT_STONE);
		EnchantmentStonesRecipeSerializer.registerSpecialRecipe();
	}
}
