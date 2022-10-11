package net.trueHorse.enchantmentPreservation.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.EnchantmentScreenHandler;
import net.trueHorse.enchantmentPreservation.EnchantmentPreservation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EnchantmentScreenHandler.class)
public abstract class EnchantmentScreenHandlerMixin {

    @Redirect(method = "generateEnchantments",at=@At(value = "INVOKE",target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"))
    private Item getBookIfStone(ItemStack instance){
        if(EnchantmentPreservation.ENCHANTMENT_STONES.contains(instance.getItem())){
            return Items.BOOK;
        }else{
            return instance.getItem();
        }
    }
}
