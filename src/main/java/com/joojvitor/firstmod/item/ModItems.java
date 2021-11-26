package com.joojvitor.firstmod.item;

import com.joojvitor.firstmod.FirstMod;
import com.joojvitor.firstmod.block.ModBlocks;
import com.joojvitor.firstmod.block.ModFluids;
import com.joojvitor.firstmod.entity.ModEntityTypes;
import com.joojvitor.firstmod.item.custom.CopperedApple;
import com.joojvitor.firstmod.item.custom.Firewood;
import com.joojvitor.firstmod.item.custom.LevitationWand;
import com.joojvitor.firstmod.item.custom.ModSpawnEggItem;
import com.joojvitor.firstmod.util.Registration;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
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

    public static final RegistryObject<Item> FIREWOOD =
            Registration.ITEMS.register("firewood", () ->
                    new Firewood(new Item.Properties().group(FirstMod.FIRSTMOD_TAB)));

    public static final RegistryObject<Item> ZUCCINI_SEED =
            Registration.ITEMS.register("zuccini_seed",
                    () -> new BlockItem(ModBlocks.ZUCCINI_CROP.get(),
                            new Item.Properties().group(FirstMod.FIRSTMOD_TAB)));

    public static final RegistryObject<Item> OIL_BUCKET =
            Registration.ITEMS.register("oil_bucket", () ->
                    new BucketItem(ModFluids.OIL_FLUID::get,
                            new Item.Properties().group(FirstMod.FIRSTMOD_TAB)));

    public static final RegistryObject<ModSpawnEggItem> CASTOR_SPAWN_EGG =
            Registration.ITEMS.register("castor_spawn_egg", () ->
                    new ModSpawnEggItem(ModEntityTypes.CASTOR, 0xA24C24, 0x5E3928,
                            new Item.Properties().group(FirstMod.FIRSTMOD_TAB)));

    public static final RegistryObject<ModSpawnEggItem> IURO_SPAWN_EGG =
            Registration.ITEMS.register("iuro_spawn_egg", () ->
                    new ModSpawnEggItem(ModEntityTypes.IURO, 0xFFFFFF, 0xFFA8A8,
                            new Item.Properties().group(FirstMod.FIRSTMOD_TAB)));


    /* TOOLS */
    public static final RegistryObject<Item> LEVITATION_WAND =
            Registration.ITEMS.register("levitation_wand", () ->
                    new LevitationWand(new Item.Properties().group(FirstMod.FIRSTMOD_TAB)
                            .maxDamage(20)));

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

    /*ARMOR*/

    public static final RegistryObject<Item> COPPER_HELMET =
            Registration.ITEMS.register("copper_helmet",
                    () -> new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlotType.HEAD,
                            new Item.Properties().group(FirstMod.FIRSTMOD_TAB)));
    public static final RegistryObject<Item> COPPER_CHESTPLATE =
            Registration.ITEMS.register("copper_chestplate",
                    () -> new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlotType.CHEST,
                            new Item.Properties().group(FirstMod.FIRSTMOD_TAB)));
    public static final RegistryObject<Item> COPPER_LEGGINGS =
            Registration.ITEMS.register("copper_leggings",
                    () -> new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlotType.LEGS,
                            new Item.Properties().group(FirstMod.FIRSTMOD_TAB)));
    public static final RegistryObject<Item> COPPER_BOOTS =
            Registration.ITEMS.register("copper_boots",
                    () -> new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlotType.FEET,
                            new Item.Properties().group(FirstMod.FIRSTMOD_TAB)));

    public static void register() { }

    public enum ModArmorMaterial implements IArmorMaterial {

        COPPER(50, new int[] { 3, 7, 5, 3 }, 10, SoundEvents.ITEM_ARMOR_EQUIP_IRON,
                Ingredient.fromStacks(new ItemStack(ModItems.COPPER_INGOT.get())), FirstMod.MOD_ID + ":copper",
                0, 0.1f);

        private final int durability;
        private final int[] damageReductionArray;
        private final int enchantability;
        private final SoundEvent soundEvent;
        private final Ingredient repairMaterial;
        private final String name;
        private final float toughness;
        private final float knockbackResistance;

        ModArmorMaterial(int durability, int[] damageReductionArray, int enchantability, SoundEvent soundEvent, Ingredient repairMaterial,
                         String name, float toughness, float knockbackResistance) {
            this.durability = durability;
            this.damageReductionArray = damageReductionArray;
            this.enchantability = enchantability;
            this.soundEvent = soundEvent;
            this.repairMaterial = repairMaterial;
            this.name = name;
            this.toughness = toughness;
            this.knockbackResistance = knockbackResistance;
        }

        @Override
        public int getDurability(EquipmentSlotType slotIn) {
            return durability;
        }

        @Override
        public int getDamageReductionAmount(EquipmentSlotType slotIn) {
            return damageReductionArray[slotIn.getIndex()];
        }

        @Override
        public int getEnchantability() {
            return enchantability;
        }

        @Override
        public SoundEvent getSoundEvent() {
            return soundEvent;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return repairMaterial;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public float getToughness() {
            return toughness;
        }

        @Override
        public float getKnockbackResistance() {
            return knockbackResistance;
        }
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
