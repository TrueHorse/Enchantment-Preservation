package net.trueHorse.enchantmentPreservation.recipes;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.trueHorse.enchantmentPreservation.EnchantmentPreservation;
import net.trueHorse.enchantmentPreservation.ItemAccess;
import net.trueHorse.enchantmentPreservation.ItemStackAccess;
import net.trueHorse.enchantmentPreservation.config.EnchantmentPreservationConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddEnchantmentStoneRecipe extends SpecialCraftingRecipe {

    public AddEnchantmentStoneRecipe(Identifier id, CraftingRecipeCategory category) {
        super(id,category);
    }

    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        boolean hasEquipment = false;
        int stoneCount = 0;

        for(int i=0;i<inventory.size();i++){
            ItemStack stack = inventory.getStack(i);
            if(!stack.isEmpty()){
                int maxStones = Integer.parseInt(EnchantmentPreservationConfig.getVal("stonesPerEquip"));
                if(stack.isIn(EnchantmentPreservation.ENCHANTMENT_STONES)&&stoneCount< maxStones){
                    stoneCount+=1;
                    continue;
                }
                if(((ItemAccess)stack.getItem()).isEquipment(stack)&&!hasEquipment){
                    hasEquipment = true;
                    stoneCount+= ((ItemStackAccess)(Object)stack).getEnchantmentStones().size();
                    if(stoneCount<= maxStones) continue;
                    else return false;
                }
                return false;
            }
        }

        return stoneCount>0&&hasEquipment;
    }

    @Override
    public ItemStack craft(CraftingInventory inventory, DynamicRegistryManager dynamicRegistryManager) {
        ItemStack equipmentStack = ItemStack.EMPTY;
        ArrayList<ItemStack> stoneStacks = new ArrayList<>();

        for(int i=0;i<inventory.size();i++){
            ItemStack stack = inventory.getStack(i);

            if(!stack.isEmpty()&&((ItemAccess)stack.getItem()).isEquipment(stack)){
                equipmentStack = stack.copy();
                continue;
            }

            if(!stack.isEmpty()&&stack.isIn(EnchantmentPreservation.ENCHANTMENT_STONES)){
                stoneStacks.add(stack.copy());
            }
        }

        for(ItemStack stack:stoneStacks){
            Map<Enchantment,Integer> stoneEnchantments = EnchantmentHelper.fromNbt(stack.getOrCreateNbt().getList("StoredEnchantments",10));
            List<Enchantment> notApplyEnchants = new ArrayList<>();

            ItemStack finalEquipmentStack = equipmentStack;
            Map<Enchantment,Integer> equipEnchants = EnchantmentHelper.get(equipmentStack);
            stoneEnchantments.forEach((e, i)->{
                equipEnchants.forEach((ee,ei)->{
                    if(e != ee && !e.canCombine(ee)){
                        notApplyEnchants.add(e);
                    }
                });

                if(equipEnchants.containsKey(e)&&equipEnchants.get(e)<=i){
                    NbtCompound weakEnchantNbt = EnchantmentHelper.createNbt(EnchantmentHelper.getEnchantmentId(e),equipEnchants.get(e));

                    finalEquipmentStack.getEnchantments().remove(weakEnchantNbt);

                    if (!finalEquipmentStack.getOrCreateNbt().contains("weaker enchantments", 9)) {
                        finalEquipmentStack.getOrCreateNbt().put("weaker enchantments", new NbtList());
                    }

                    NbtList nbtList = finalEquipmentStack.getOrCreateNbt().getList("weaker enchantments", 10);
                    nbtList.add(weakEnchantNbt);
                }

                if(!e.isAcceptableItem(finalEquipmentStack)||(equipEnchants.containsKey(e)&&equipEnchants.get(e)>i))notApplyEnchants.add(e);
            });
            for(Enchantment enchantment:notApplyEnchants){
                stoneEnchantments.remove(enchantment);
            }

            stoneEnchantments.forEach((e,i)->((ItemStackAccess)(Object)finalEquipmentStack).addEnchantmentFromStone(e,i));
            ((ItemStackAccess)(Object)equipmentStack).addEnchantmentStone(stack);
        }

        return equipmentStack;
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<AddEnchantmentStoneRecipe> getSerializer() {
        return EnchantmentPreservationRecipeSerializer.ADD_ENCHANTMENT_STONE;
    }
}
