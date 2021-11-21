package com.joojvitor.firstmod.item.custom;

import com.joojvitor.firstmod.FirstMod;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class CopperedApple extends Item {

    public CopperedApple(Item.Properties properties) {
        super(new Properties().group(FirstMod.FIRSTMOD_TAB)
                .food(new Food.Builder()
                        .setAlwaysEdible()
                        .hunger(5)
                        .saturation(1.5f)
                        .effect(() -> new EffectInstance(Effects.GLOWING, 300, 1), 1f)
                        .build()));
    }
}
