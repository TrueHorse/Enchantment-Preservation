package net.trueHorse.enchantmentStones.mixin;

import net.fabricmc.fabric.impl.client.indigo.renderer.helper.ColorHelper;
import net.fabricmc.fabric.impl.client.rendering.ColorProviderRegistryImpl;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.ClickType;
import net.minecraft.util.registry.Registry;
import net.trueHorse.enchantmentStones.EnchantmentStones;
import net.trueHorse.enchantmentStones.ItemStackAccess;
import net.trueHorse.enchantmentStones.Utils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements ItemStackAccess {

    @Shadow
    private NbtCompound nbt;
    @Shadow
    public abstract NbtCompound getOrCreateNbt();

    /*
    @Inject(method = "onClicked",at=@At(value = "HEAD"))
    private void testStones(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference, CallbackInfoReturnable<Boolean> info){
        EnchantmentStones.LOGGER.error(String.valueOf(stack.getOrCreateNbt().contains("Enchantment Stones")));
    }
     */

    /*
    @Inject(method = "damage(ILnet/minecraft/entity/LivingEntity;Ljava/util/function/Consumer;)V",at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;decrement(I)V",shift = At.Shift.BEFORE))
    private <T> void pickUpEnchantmentStonesOnBreak(int amount, LivingEntity entity, Consumer<T> breakCallback, CallbackInfo info){
        if (entity instanceof PlayerEntity) {
            EnchantmentStones.LOGGER.error("player");
            for (ItemStack stone:addedEnchantmentStones) {
                boolean test =((PlayerEntity)entity).giveItemStack(stone);
                EnchantmentStones.LOGGER.error(String.valueOf(test));
            }
            addedEnchantmentStones.clear();
        }
    }
     */

    @Override
    public void addEnchantmentStone(ItemStack stoneStack){
        this.getOrCreateNbt();
        if (!this.nbt.contains("Enchantment Stones", 9)) {
            this.nbt.put("Enchantment Stones", new NbtList());
        }

        NbtList nbtList = this.nbt.getList("Enchantment Stones", 10);
        nbtList.add(Utils.createEnchantmentstoneNbt(stoneStack));
    }
}
