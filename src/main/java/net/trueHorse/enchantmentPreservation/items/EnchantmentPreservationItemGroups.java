package net.trueHorse.enchantmentPreservation.items;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class EnchantmentPreservationItemGroups {

    public static final RegistryKey<ItemGroup> ENCHANTMENT_PRESERVATION_GROUP_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier("enchantmentpreservation","main_group"));
    public static final ItemGroup ENCHANTMENT_PRESERVATION_GROUP = FabricItemGroup.builder()
                                                                                    .icon(()->new ItemStack(EnchantmentPreservationItems.BLUE_ENCHANTMENT_STONE))
                                                                                    .displayName(Text.translatable("itemGroup.enchantmentpreservation.main_group"))
                                                                                    .build();

    public static void registerItemGroups(){
        Registry.register(Registries.ITEM_GROUP,ENCHANTMENT_PRESERVATION_GROUP_KEY,ENCHANTMENT_PRESERVATION_GROUP);
    }
}
