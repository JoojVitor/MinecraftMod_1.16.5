package com.joojvitor.firstmod.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
public class LevitationWand extends Item {
    public LevitationWand(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        boolean isPlayerLevitating = playerIn.getActivePotionEffect(Effects.LEVITATION) != null;

        if (!worldIn.isRemote && playerIn.isCrouching() && !isPlayerLevitating) {
            playerIn.addPotionEffect(new EffectInstance(Effects.LEVITATION, 300));

            ItemStack stack = playerIn.getHeldItemMainhand().getStack();
            stack.damageItem(1, playerIn, player -> player.sendBreakAnimation(handIn));
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean isTargetLevitating = target.getActivePotionEffect(Effects.LEVITATION) != null;

        if (target.isAlive() && !isTargetLevitating) {
            target.addPotionEffect(new EffectInstance(Effects.LEVITATION, 300));

            stack.damageItem(1, attacker, player -> player.sendBreakAnimation(attacker.getActiveHand()));
        }

        return super.hitEntity(stack, target, attacker);
    }
}
