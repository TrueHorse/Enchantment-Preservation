package net.trueHorse.enchantmentPreservation.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.trueHorse.enchantmentPreservation.EnchantmentPreservation;

public class EnchantmentPreservationItems {

    public static final EnchantmentStoneItem WHITE_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings().group(EnchantmentPreservation.ENCHANTMENT_STONES_GROUP));
    public static final EnchantmentStoneItem BLACK_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings().group(EnchantmentPreservation.ENCHANTMENT_STONES_GROUP));
    public static final EnchantmentStoneItem GRAY_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings().group(EnchantmentPreservation.ENCHANTMENT_STONES_GROUP));
    public static final EnchantmentStoneItem LIGHT_GRAY_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings().group(EnchantmentPreservation.ENCHANTMENT_STONES_GROUP));
    public static final EnchantmentStoneItem YELLOW_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings().group(EnchantmentPreservation.ENCHANTMENT_STONES_GROUP));
    public static final EnchantmentStoneItem ORANGE_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings().group(EnchantmentPreservation.ENCHANTMENT_STONES_GROUP));
    public static final EnchantmentStoneItem RED_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings().group(EnchantmentPreservation.ENCHANTMENT_STONES_GROUP));
    public static final EnchantmentStoneItem PURPLE_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings().group(EnchantmentPreservation.ENCHANTMENT_STONES_GROUP));
    public static final EnchantmentStoneItem MAGENTA_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings().group(EnchantmentPreservation.ENCHANTMENT_STONES_GROUP));
    public static final EnchantmentStoneItem PINK_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings().group(EnchantmentPreservation.ENCHANTMENT_STONES_GROUP));
    public static final EnchantmentStoneItem BLUE_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings().group(EnchantmentPreservation.ENCHANTMENT_STONES_GROUP));
    public static final EnchantmentStoneItem LIGHT_BLUE_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings().group(EnchantmentPreservation.ENCHANTMENT_STONES_GROUP));
    public static final EnchantmentStoneItem LIME_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings().group(EnchantmentPreservation.ENCHANTMENT_STONES_GROUP));
    public static final EnchantmentStoneItem GREEN_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings().group(EnchantmentPreservation.ENCHANTMENT_STONES_GROUP));
    public static final EnchantmentStoneItem CYAN_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings().group(EnchantmentPreservation.ENCHANTMENT_STONES_GROUP));
    public static final EnchantmentStoneItem BROWN_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings().group(EnchantmentPreservation.ENCHANTMENT_STONES_GROUP));


    public static void registerItems(){
        Registry.register(Registry.ITEM,new Identifier("enchantmentpreservation","white_enchantment_stone"),WHITE_ENCHANTMENT_STONE);
        Registry.register(Registry.ITEM,new Identifier("enchantmentpreservation","orange_enchantment_stone"),ORANGE_ENCHANTMENT_STONE);
        Registry.register(Registry.ITEM,new Identifier("enchantmentpreservation","light_blue_enchantment_stone"),LIGHT_BLUE_ENCHANTMENT_STONE);
        Registry.register(Registry.ITEM,new Identifier("enchantmentpreservation","blue_enchantment_stone"),BLUE_ENCHANTMENT_STONE);
        Registry.register(Registry.ITEM,new Identifier("enchantmentpreservation","yellow_enchantment_stone"),YELLOW_ENCHANTMENT_STONE);
        Registry.register(Registry.ITEM,new Identifier("enchantmentpreservation","red_enchantment_stone"),RED_ENCHANTMENT_STONE);
        Registry.register(Registry.ITEM,new Identifier("enchantmentpreservation","purple_enchantment_stone"),PURPLE_ENCHANTMENT_STONE);
        Registry.register(Registry.ITEM,new Identifier("enchantmentpreservation","pink_enchantment_stone"),PINK_ENCHANTMENT_STONE);
        Registry.register(Registry.ITEM,new Identifier("enchantmentpreservation","magenta_enchantment_stone"),MAGENTA_ENCHANTMENT_STONE);
        Registry.register(Registry.ITEM,new Identifier("enchantmentpreservation","cyan_enchantment_stone"),CYAN_ENCHANTMENT_STONE);
        Registry.register(Registry.ITEM,new Identifier("enchantmentpreservation","green_enchantment_stone"),GREEN_ENCHANTMENT_STONE);
        Registry.register(Registry.ITEM,new Identifier("enchantmentpreservation","lime_enchantment_stone"),LIME_ENCHANTMENT_STONE);
        Registry.register(Registry.ITEM,new Identifier("enchantmentpreservation","black_enchantment_stone"),BLACK_ENCHANTMENT_STONE);
        Registry.register(Registry.ITEM,new Identifier("enchantmentpreservation","gray_enchantment_stone"),GRAY_ENCHANTMENT_STONE);
        Registry.register(Registry.ITEM,new Identifier("enchantmentpreservation","light_gray_enchantment_stone"),LIGHT_GRAY_ENCHANTMENT_STONE);
        Registry.register(Registry.ITEM,new Identifier("enchantmentpreservation","brown_enchantment_stone"),BROWN_ENCHANTMENT_STONE);
    }
}
