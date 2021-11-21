package com.joojvitor.firstmod.item.custom;

import com.joojvitor.firstmod.util.InputHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.List;

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

    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        if (InputHelper.isHoldingShift()) {
            tooltip.add(new StringTextComponent("Levitate the target when attack;\n" +
                    "CROUCH and RIGHT-CLICK to self levitate."));
        } else {
            tooltip.add(new StringTextComponent("Hold \u00A7eSHIFT\u00A7r for more information"));
        }

        super.addInformation(stack, world, tooltip, flag);
    }
}
