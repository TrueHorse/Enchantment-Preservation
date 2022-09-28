package net.trueHorse.enchantmentStones.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.EnchantmentScreenHandler;
import net.trueHorse.enchantmentStones.EnchantmentStones;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(EnchantmentScreenHandler.class)
public abstract class EnchantmentScreenHandlerMixin {

    @ModifyArg(method = "method_17410",at=@At(value = "INVOKE",target = "Lnet/minecraft/screen/EnchantmentScreenHandler;generateEnchantments(Lnet/minecraft/item/ItemStack;II)Ljava/util/List;"),index=0)
    private ItemStack bookEnchantmentsIfEnchantmentStone(ItemStack stack){
        return stack.isIn(EnchantmentStones.ENCHANTMENT_STONES) ? Items.BOOK.getDefaultStack() : stack;
    }

    @ModifyArg(method = "method_17411",at=@At(value = "INVOKE",target = "Lnet/minecraft/screen/EnchantmentScreenHandler;generateEnchantments(Lnet/minecraft/item/ItemStack;II)Ljava/util/List;"),index=0)
    private ItemStack bookEnchantmentsIfEnchantmentStone2(ItemStack stack){
        return stack.isIn(EnchantmentStones.ENCHANTMENT_STONES) ? Items.BOOK.getDefaultStack() : stack;
    }
}
