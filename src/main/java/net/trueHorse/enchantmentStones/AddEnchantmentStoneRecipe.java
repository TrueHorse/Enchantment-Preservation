package net.trueHorse.enchantmentStones;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Map;

public class AddEnchantmentStoneRecipe extends SpecialCraftingRecipe {

    private final int maxStones = 3;

    public AddEnchantmentStoneRecipe(Identifier id) {
        super(id);
    }

    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        boolean hasEquipment = false;
        int stoneCount = 0;

        for(int i=0;i<inventory.size();i++){
            ItemStack stack = inventory.getStack(i);
            if(!stack.isEmpty()){
                if(stack.isIn(EnchantmentStones.ENCHANTMENT_STONES)&&stoneCount<maxStones){
                    stoneCount+=1;
                    continue;
                }
                if(((ItemAccess)stack.getItem()).isEquipment(stack)&&!hasEquipment){
                    hasEquipment = true;
                    stoneCount+=stack.getOrCreateNbt().getList("Enchantment Stones",10).size();
                    if(stoneCount<=maxStones) continue;
                    else return false;
                }
                return false;
            }
        }

        return stoneCount>0&&hasEquipment;
    }

    @Override
    public ItemStack craft(CraftingInventory inventory) {
        ItemStack equipmentStack = ItemStack.EMPTY;
        ArrayList<ItemStack> stoneStacks = new ArrayList<>();

        for(int i=0;i<inventory.size();i++){
            ItemStack stack = inventory.getStack(i);

            if(!stack.isEmpty()&&((ItemAccess)stack.getItem()).isEquipment(stack)){
                equipmentStack = stack.copy();
                continue;
            }

            if(!stack.isEmpty()&&stack.isIn(EnchantmentStones.ENCHANTMENT_STONES)){
                stoneStacks.add(stack.copy());
            }
        }

        for(ItemStack stack:stoneStacks){
            Map<Enchantment,Integer> stoneEnchantments = EnchantmentHelper.fromNbt(stack.getOrCreateNbt().getList("StoredEnchantments",10));
            stoneEnchantments.forEach(equipmentStack::addEnchantment);
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
        return EnchantmentStonesRecipeSerializer.ADD_ENCHANTMENT_STONE;
    }
}
