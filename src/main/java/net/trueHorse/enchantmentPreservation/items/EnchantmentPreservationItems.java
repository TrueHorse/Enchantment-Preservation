package net.trueHorse.enchantmentPreservation.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.trueHorse.enchantmentPreservation.EnchantmentPreservation;

import java.lang.reflect.Field;

public class EnchantmentPreservationItems {

    public static final EnchantmentStoneItem WHITE_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings());
    public static final EnchantmentStoneItem BLACK_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings());
    public static final EnchantmentStoneItem GRAY_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings());
    public static final EnchantmentStoneItem LIGHT_GRAY_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings());
    public static final EnchantmentStoneItem YELLOW_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings());
    public static final EnchantmentStoneItem ORANGE_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings());
    public static final EnchantmentStoneItem RED_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings());
    public static final EnchantmentStoneItem PURPLE_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings());
    public static final EnchantmentStoneItem MAGENTA_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings());
    public static final EnchantmentStoneItem PINK_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings());
    public static final EnchantmentStoneItem BLUE_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings());
    public static final EnchantmentStoneItem LIGHT_BLUE_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings());
    public static final EnchantmentStoneItem LIME_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings());
    public static final EnchantmentStoneItem GREEN_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings());
    public static final EnchantmentStoneItem CYAN_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings());
    public static final EnchantmentStoneItem BROWN_ENCHANTMENT_STONE = new EnchantmentStoneItem(new FabricItemSettings());


    public static void registerItems(){
        Registry.register(Registries.ITEM,new Identifier("enchantmentpreservation","white_enchantment_stone"),WHITE_ENCHANTMENT_STONE);
        Registry.register(Registries.ITEM,new Identifier("enchantmentpreservation","orange_enchantment_stone"),ORANGE_ENCHANTMENT_STONE);
        Registry.register(Registries.ITEM,new Identifier("enchantmentpreservation","light_blue_enchantment_stone"),LIGHT_BLUE_ENCHANTMENT_STONE);
        Registry.register(Registries.ITEM,new Identifier("enchantmentpreservation","blue_enchantment_stone"),BLUE_ENCHANTMENT_STONE);
        Registry.register(Registries.ITEM,new Identifier("enchantmentpreservation","yellow_enchantment_stone"),YELLOW_ENCHANTMENT_STONE);
        Registry.register(Registries.ITEM,new Identifier("enchantmentpreservation","red_enchantment_stone"),RED_ENCHANTMENT_STONE);
        Registry.register(Registries.ITEM,new Identifier("enchantmentpreservation","purple_enchantment_stone"),PURPLE_ENCHANTMENT_STONE);
        Registry.register(Registries.ITEM,new Identifier("enchantmentpreservation","pink_enchantment_stone"),PINK_ENCHANTMENT_STONE);
        Registry.register(Registries.ITEM,new Identifier("enchantmentpreservation","magenta_enchantment_stone"),MAGENTA_ENCHANTMENT_STONE);
        Registry.register(Registries.ITEM,new Identifier("enchantmentpreservation","cyan_enchantment_stone"),CYAN_ENCHANTMENT_STONE);
        Registry.register(Registries.ITEM,new Identifier("enchantmentpreservation","green_enchantment_stone"),GREEN_ENCHANTMENT_STONE);
        Registry.register(Registries.ITEM,new Identifier("enchantmentpreservation","lime_enchantment_stone"),LIME_ENCHANTMENT_STONE);
        Registry.register(Registries.ITEM,new Identifier("enchantmentpreservation","black_enchantment_stone"),BLACK_ENCHANTMENT_STONE);
        Registry.register(Registries.ITEM,new Identifier("enchantmentpreservation","gray_enchantment_stone"),GRAY_ENCHANTMENT_STONE);
        Registry.register(Registries.ITEM,new Identifier("enchantmentpreservation","light_gray_enchantment_stone"),LIGHT_GRAY_ENCHANTMENT_STONE);
        Registry.register(Registries.ITEM,new Identifier("enchantmentpreservation","brown_enchantment_stone"),BROWN_ENCHANTMENT_STONE);
    }

    public static void groupItems(){
        groupStone(BLACK_ENCHANTMENT_STONE);
        groupStone(GRAY_ENCHANTMENT_STONE);
        groupStone(LIGHT_GRAY_ENCHANTMENT_STONE);
        groupStone(WHITE_ENCHANTMENT_STONE);
        groupStone(YELLOW_ENCHANTMENT_STONE);
        groupStone(ORANGE_ENCHANTMENT_STONE);
        groupStone(RED_ENCHANTMENT_STONE);
        groupStone(PINK_ENCHANTMENT_STONE);
        groupStone(MAGENTA_ENCHANTMENT_STONE);
        groupStone(PURPLE_ENCHANTMENT_STONE);
        groupStone(BLUE_ENCHANTMENT_STONE);
        groupStone(LIGHT_BLUE_ENCHANTMENT_STONE);
        groupStone(CYAN_ENCHANTMENT_STONE);
        groupStone(LIME_ENCHANTMENT_STONE);
        groupStone(GREEN_ENCHANTMENT_STONE);
        groupStone(BROWN_ENCHANTMENT_STONE);
    }

    private static void groupStone(Item stone){
        groupItem(stone, EnchantmentPreservation.ENCHANTMENT_PRESERVATION_GROUP);
        groupItem(stone, ItemGroups.INGREDIENTS);
    }

    private static void groupItem(Item item, ItemGroup group){
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }
}
