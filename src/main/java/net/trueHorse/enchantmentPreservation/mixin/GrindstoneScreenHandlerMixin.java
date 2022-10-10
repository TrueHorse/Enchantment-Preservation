package net.trueHorse.enchantmentPreservation.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.screen.GrindstoneScreenHandler;
import net.trueHorse.enchantmentPreservation.ItemStackAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;
import java.util.stream.Collectors;

@Mixin(GrindstoneScreenHandler.class)
public class GrindstoneScreenHandlerMixin {

    @Redirect(method = "updateResult",at=@At(value = "INVOKE",target = "Lnet/minecraft/item/ItemStack;hasEnchantments()Z"))
    private boolean hasStoneEnchantments(ItemStack stack){
        for (NbtElement stone : ((ItemStackAccess)(Object)stack).getEnchantmentStones()) {
            if (!((NbtCompound) stone).getList("StoredEnchantments", 10).isEmpty()) {
                return true;
            }
        }
        return stack.hasEnchantments();
    }

    @Inject(method = "grind",at=@At(value = "INVOKE",target = "Lnet/minecraft/item/ItemStack;removeSubNbt(Ljava/lang/String;)V",ordinal = 1),locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void grindEnchantmentStones(ItemStack item, int damage, int amount, CallbackInfoReturnable<ItemStack> info, ItemStack itemStack){
        for(NbtElement stone:((ItemStackAccess)(Object)itemStack).getEnchantmentStones()){
            Map<Enchantment, Integer> map = (Map) EnchantmentHelper.fromNbt(((NbtCompound)stone).getList("StoredEnchantments",10)).entrySet().stream().filter((entry) -> {
                return ((Enchantment)entry.getKey()).isCursed();
            }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            ((NbtCompound)stone).remove("StoredEnchantments");

            ItemStack tmpStack = ItemStack.EMPTY;
            EnchantmentHelper.set(map,tmpStack);
            ((NbtCompound) stone).put("StoredEnchantments",tmpStack.getEnchantments());
        }
    }
}
