package com.joojvitor.firstmod.item;

import com.joojvitor.firstmod.FirstMod;
import com.joojvitor.firstmod.util.Registration;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class ModItems {
    public static final RegistryObject<Item> COPPER_INGOT =
            Registration.ITEMS.register("copper_ingot",() ->
                    new Item(new Item.Properties().group(FirstMod.FIRSTMOD_TAB)));

    public static final RegistryObject<Item> COPPER_WIRE =
            Registration.ITEMS.register("copper_wire",() ->
                    new Item(new Item.Properties().group(FirstMod.FIRSTMOD_TAB)));

    public static void register() {

    }
}
