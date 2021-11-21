package com.joojvitor.firstmod.item;

import com.joojvitor.firstmod.FirstMod;
import com.joojvitor.firstmod.item.custom.CopperedApple;
import com.joojvitor.firstmod.item.custom.LevitationWand;
import com.joojvitor.firstmod.util.Registration;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

public class ModItems {
    public static final RegistryObject<Item> COPPER_INGOT =
            Registration.ITEMS.register("copper_ingot",() ->
                    new Item(new Item.Properties().group(FirstMod.FIRSTMOD_TAB)));

    public static final RegistryObject<Item> COPPER_WIRE =
            Registration.ITEMS.register("copper_wire",() ->
                    new Item(new Item.Properties().group(FirstMod.FIRSTMOD_TAB)));

    public static final RegistryObject<Item> COPPERED_APPLE =
            Registration.ITEMS.register("coppered_apple", () ->
                    new CopperedApple(new Item.Properties().group(FirstMod.FIRSTMOD_TAB)));

    public static final RegistryObject<Item> SHULKER_DUST =
            Registration.ITEMS.register("shulker_dust", () ->
                    new Item(new Item.Properties().group(FirstMod.FIRSTMOD_TAB)));

    public static final RegistryObject<Item> LEVITATION_WAND =
            Registration.ITEMS.register("levitation_wand", () ->
                    new LevitationWand(new Item.Properties().group(FirstMod.FIRSTMOD_TAB)
                            .maxDamage(20)));

    /* TOOLS */

    public static final RegistryObject<Item> COPPER_SHOVEL =
            Registration.ITEMS.register("copper_shovel",
                    () -> new ShovelItem(ModItemTier.COPPER, 0 , -1.5f,
                            new Item.Properties()
                                    .defaultMaxDamage(150)
                                    .addToolType(ToolType.SHOVEL, 2)
                                    .group(FirstMod.FIRSTMOD_TAB)));

    public static final RegistryObject<Item> COPPER_SWORD =
            Registration.ITEMS.register("copper_sword",
                    () -> new SwordItem(ModItemTier.COPPER, 2 , -1.5f,
                            new Item.Properties()
                                    .defaultMaxDamage(150)
                                    .group(FirstMod.FIRSTMOD_TAB)));

    public static final RegistryObject<Item> COPPER_PICKAXE =
            Registration.ITEMS.register("copper_pickaxe",
                    () -> new PickaxeItem(ModItemTier.COPPER, 0 , -1.5f,
                            new Item.Properties()
                                    .defaultMaxDamage(150)
                                    .addToolType(ToolType.PICKAXE, 2)
                                    .group(FirstMod.FIRSTMOD_TAB)));

    public static final RegistryObject<Item> COPPER_AXE =
            Registration.ITEMS.register("copper_axe",
                    () -> new AxeItem(ModItemTier.COPPER, 2.5f , -3f,
                            new Item.Properties()
                                    .defaultMaxDamage(150)
                                    .addToolType(ToolType.AXE, 2)
                                    .group(FirstMod.FIRSTMOD_TAB)));

    public static final RegistryObject<Item> COPPER_HOE =
            Registration.ITEMS.register("copper_hoe",
                    () -> new HoeItem(ModItemTier.COPPER, 0 , -1.5f,
                            new Item.Properties()
                                    .defaultMaxDamage(150)
                                    .addToolType(ToolType.HOE, 2)
                                    .group(FirstMod.FIRSTMOD_TAB)));

    public static void register() {

    }

    public enum ModItemTier implements IItemTier {

        COPPER(2, 150, 2.5f, 0f, 15,
                Ingredient.fromStacks(new ItemStack(ModItems.COPPER_INGOT.get())));

        private final int harvestLevel;
        private final int maxUses;
        private final float efficiency;
        private final float attackDamage;
        private final int enchantability;
        private final Ingredient repairMaterial;

        ModItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage,
                    int enchantability, Ingredient repairMaterial) {
            this.harvestLevel = harvestLevel;
            this.maxUses = maxUses;
            this.efficiency = efficiency;
            this.attackDamage = attackDamage;
            this.enchantability = enchantability;
            this.repairMaterial = repairMaterial;
        }


        @Override
        public int getMaxUses() {
            return maxUses;
        }

        @Override
        public float getEfficiency() {
            return efficiency;
        }

        @Override
        public float getAttackDamage() {
            return attackDamage;
        }

        @Override
        public int getHarvestLevel() {
            return harvestLevel;
        }

        @Override
        public int getEnchantability() {
            return enchantability;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return repairMaterial;
        }
    }
}
