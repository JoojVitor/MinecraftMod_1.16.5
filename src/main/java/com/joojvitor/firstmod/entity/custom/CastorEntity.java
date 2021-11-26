package com.joojvitor.firstmod.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CastorEntity extends PolarBearEntity {
    public CastorEntity(EntityType<? extends PolarBearEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 5.00)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 3)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 20.00)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.20);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(4, new SwimGoal(this));
        this.goalSelector.addGoal(4, new PanicGoal(this, 0.4));
        //this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, IuroEntity.class, false));
        //this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.20, true));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 0.20));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 6.8f));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_PANDA_PRE_SNEEZE;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_BAT_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_PANDA_HURT;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.BLOCK_GRASS_STEP, 0.20f, 0.5f);
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (entityIn instanceof CastorEntity) {
            playSound(SoundEvents.BLOCK_ANVIL_PLACE, 0.20f, 0.5f);
            entityIn.performHurtAnimation();
            this.damageEntity(DamageSource.causeMobDamage((LivingEntity)entityIn), 2f);
            return true;
        }
        return false;
    }
}
