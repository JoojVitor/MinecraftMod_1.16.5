package com.joojvitor.firstmod.entity.model;

import com.joojvitor.firstmod.entity.custom.IuroEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelHelper;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;

public class IuroModel<T extends IuroEntity> extends EntityModel<T> {
    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer left_arm;
    private final ModelRenderer right_arm;
    private final ModelRenderer left_leg;
    private final ModelRenderer right_leg;
    public BipedModel.ArmPose leftArmPose = BipedModel.ArmPose.EMPTY;
    public BipedModel.ArmPose rightArmPose = BipedModel.ArmPose.EMPTY;
    public ModelRenderer bipedHeadwear;
    public boolean isSneak;
    public float swimAnimation;

    public IuroModel() {
        textureWidth = 64;
        textureHeight = 64;

        this.bipedHeadwear = new ModelRenderer(this, 32, 0);

        left_leg = new ModelRenderer(this);
        left_leg.setRotationPoint(1.9F, 12.0F, 0.0F);
        setRotationAngle(left_leg, -0.1745F, 0.0F, -0.0349F);
        left_leg.setTextureOffset(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
        left_leg.setTextureOffset(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);

        right_leg = new ModelRenderer(this);
        right_leg.setRotationPoint(-1.9F, 12.0F, 0.0F);
        setRotationAngle(right_leg, 0.192F, 0.0F, 0.0349F);
        right_leg.setTextureOffset(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
        right_leg.setTextureOffset(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);

        left_arm = new ModelRenderer(this);
        left_arm.setRotationPoint(5.0F, 2.0F, 0.0F);
        setRotationAngle(left_arm, 0.2094F, 0.0F, 0.0F);
        left_arm.setTextureOffset(40, 16).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
        left_arm.setTextureOffset(48, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);

        right_arm = new ModelRenderer(this);
        right_arm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        setRotationAngle(right_arm, -0.1745F, 0.0F, 0.0F);
        right_arm.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
        right_arm.setTextureOffset(40, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.setTextureOffset(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);
        body.setTextureOffset(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.25F, false);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(head, -0.1047F, 0.0873F, 0.0F);
        head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
        head.setTextureOffset(179, 167).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        boolean flag = entityIn.getTicksElytraFlying() > 4;
        boolean flag1 = entityIn.isActualySwimming();
        this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        if (flag) {
            this.head.rotateAngleX = (-(float)Math.PI / 4F);
        } else if (this.swimAnimation > 0.0F) {
            if (flag1) {
                this.head.rotateAngleX = this.rotLerpRad(this.swimAnimation, this.head.rotateAngleX, (-(float)Math.PI / 4F));
            } else {
                this.head.rotateAngleX = this.rotLerpRad(this.swimAnimation, this.head.rotateAngleX, headPitch * ((float)Math.PI / 180F));
            }
        } else {
            this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        }

        this.body.rotateAngleY = 0.0F;
        this.right_arm.rotationPointZ = 0.0F;
        this.right_arm.rotationPointX = -5.0F;
        this.left_arm.rotationPointZ = 0.0F;
        this.left_arm.rotationPointX = 5.0F;
        float f = 1.0F;
        if (flag) {
            f = (float)entityIn.getMotion().lengthSquared();
            f = f / 0.2F;
            f = f * f * f;
        }

        if (f < 1.0F) {
            f = 1.0F;
        }

        this.right_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
        this.left_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
        this.right_arm.rotateAngleZ = 0.0F;
        this.left_arm.rotateAngleZ = 0.0F;
        this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
        this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / f;
        this.right_leg.rotateAngleY = 0.0F;
        this.left_leg.rotateAngleY = 0.0F;
        this.right_leg.rotateAngleZ = 0.0F;
        this.left_leg.rotateAngleZ = 0.0F;
        if (this.isSitting) {
            this.right_arm.rotateAngleX += (-(float)Math.PI / 5F);
            this.left_arm.rotateAngleX += (-(float)Math.PI / 5F);
            this.right_leg.rotateAngleX = -1.4137167F;
            this.right_leg.rotateAngleY = ((float)Math.PI / 10F);
            this.right_leg.rotateAngleZ = 0.07853982F;
            this.left_leg.rotateAngleX = -1.4137167F;
            this.left_leg.rotateAngleY = (-(float)Math.PI / 10F);
            this.left_leg.rotateAngleZ = -0.07853982F;
        }

        this.right_arm.rotateAngleY = 0.0F;
        this.left_arm.rotateAngleY = 0.0F;
        boolean flag2 = entityIn.getPrimaryHand() == HandSide.RIGHT;
        boolean flag3 = flag2 ? this.leftArmPose.func_241657_a_() : this.rightArmPose.func_241657_a_();
        if (flag2 != flag3) {
            this.setArmPose(entityIn);
            this.func_241654_b_(entityIn);
        } else {
            this.func_241654_b_(entityIn);
            this.setArmPose(entityIn);
        }

        this.func_230486_a_(entityIn, ageInTicks);
        if (this.isSneak) {
            this.body.rotateAngleX = 0.5F;
            this.right_arm.rotateAngleX += 0.4F;
            this.left_arm.rotateAngleX += 0.4F;
            this.right_leg.rotationPointZ = 4.0F;
            this.left_leg.rotationPointZ = 4.0F;
            this.right_leg.rotationPointY = 12.2F;
            this.left_leg.rotationPointY = 12.2F;
            this.head.rotationPointY = 4.2F;
            this.body.rotationPointY = 3.2F;
            this.left_arm.rotationPointY = 5.2F;
            this.right_arm.rotationPointY = 5.2F;
        } else {
            this.body.rotateAngleX = 0.0F;
            this.right_leg.rotationPointZ = 0.1F;
            this.left_leg.rotationPointZ = 0.1F;
            this.right_leg.rotationPointY = 12.0F;
            this.left_leg.rotationPointY = 12.0F;
            this.head.rotationPointY = 0.0F;
            this.body.rotationPointY = 0.0F;
            this.left_arm.rotationPointY = 2.0F;
            this.right_arm.rotationPointY = 2.0F;
        }

        ModelHelper.func_239101_a_(this.right_arm, this.left_arm, ageInTicks);
        if (this.swimAnimation > 0.0F) {
            float f1 = limbSwing % 26.0F;
            HandSide handside = this.getMainHand(entityIn);
            float f2 = handside == HandSide.RIGHT && this.swingProgress > 0.0F ? 0.0F : this.swimAnimation;
            float f3 = handside == HandSide.LEFT && this.swingProgress > 0.0F ? 0.0F : this.swimAnimation;
            if (f1 < 14.0F) {
                this.left_arm.rotateAngleX = this.rotLerpRad(f3, this.left_arm.rotateAngleX, 0.0F);
                this.right_arm.rotateAngleX = MathHelper.lerp(f2, this.right_arm.rotateAngleX, 0.0F);
                this.left_arm.rotateAngleY = this.rotLerpRad(f3, this.left_arm.rotateAngleY, (float)Math.PI);
                this.right_arm.rotateAngleY = MathHelper.lerp(f2, this.right_arm.rotateAngleY, (float)Math.PI);
                this.left_arm.rotateAngleZ = this.rotLerpRad(f3, this.left_arm.rotateAngleZ, (float)Math.PI + 1.8707964F * this.getArmAngleSq(f1) / this.getArmAngleSq(14.0F));
                this.right_arm.rotateAngleZ = MathHelper.lerp(f2, this.right_arm.rotateAngleZ, (float)Math.PI - 1.8707964F * this.getArmAngleSq(f1) / this.getArmAngleSq(14.0F));
            } else if (f1 >= 14.0F && f1 < 22.0F) {
                float f6 = (f1 - 14.0F) / 8.0F;
                this.left_arm.rotateAngleX = this.rotLerpRad(f3, this.left_arm.rotateAngleX, ((float)Math.PI / 2F) * f6);
                this.right_arm.rotateAngleX = MathHelper.lerp(f2, this.right_arm.rotateAngleX, ((float)Math.PI / 2F) * f6);
                this.left_arm.rotateAngleY = this.rotLerpRad(f3, this.left_arm.rotateAngleY, (float)Math.PI);
                this.right_arm.rotateAngleY = MathHelper.lerp(f2, this.right_arm.rotateAngleY, (float)Math.PI);
                this.left_arm.rotateAngleZ = this.rotLerpRad(f3, this.left_arm.rotateAngleZ, 5.012389F - 1.8707964F * f6);
                this.right_arm.rotateAngleZ = MathHelper.lerp(f2, this.right_arm.rotateAngleZ, 1.2707963F + 1.8707964F * f6);
            } else if (f1 >= 22.0F && f1 < 26.0F) {
                float f4 = (f1 - 22.0F) / 4.0F;
                this.left_arm.rotateAngleX = this.rotLerpRad(f3, this.left_arm.rotateAngleX, ((float)Math.PI / 2F) - ((float)Math.PI / 2F) * f4);
                this.right_arm.rotateAngleX = MathHelper.lerp(f2, this.right_arm.rotateAngleX, ((float)Math.PI / 2F) - ((float)Math.PI / 2F) * f4);
                this.left_arm.rotateAngleY = this.rotLerpRad(f3, this.left_arm.rotateAngleY, (float)Math.PI);
                this.right_arm.rotateAngleY = MathHelper.lerp(f2, this.right_arm.rotateAngleY, (float)Math.PI);
                this.left_arm.rotateAngleZ = this.rotLerpRad(f3, this.left_arm.rotateAngleZ, (float)Math.PI);
                this.right_arm.rotateAngleZ = MathHelper.lerp(f2, this.right_arm.rotateAngleZ, (float)Math.PI);
            }

            float f7 = 0.3F;
            float f5 = 0.33333334F;
            this.left_leg.rotateAngleX = MathHelper.lerp(this.swimAnimation, this.left_leg.rotateAngleX, 0.3F * MathHelper.cos(limbSwing * 0.33333334F + (float)Math.PI));
            this.right_leg.rotateAngleX = MathHelper.lerp(this.swimAnimation, this.right_leg.rotateAngleX, 0.3F * MathHelper.cos(limbSwing * 0.33333334F));
        }

        this.bipedHeadwear.copyModelAngles(this.head);
    }

    private float rotLerpRad(float angleIn, float maxAngleIn, float mulIn) {
        float f = (mulIn - maxAngleIn) % ((float)Math.PI * 2F);
        if (f < -(float)Math.PI) {
            f += ((float)Math.PI * 2F);
        }

        if (f >= (float)Math.PI) {
            f -= ((float)Math.PI * 2F);
        }

        return maxAngleIn + angleIn * f;
    }

    private void setArmPose(T entityIn) {
        switch(this.leftArmPose) {
            case EMPTY:
                this.left_arm.rotateAngleY = 0.0F;
                break;
            case BLOCK:
                this.left_arm.rotateAngleX = this.left_arm.rotateAngleX * 0.5F - 0.9424779F;
                this.left_arm.rotateAngleY = ((float)Math.PI / 6F);
                break;
            case ITEM:
                this.left_arm.rotateAngleX = this.left_arm.rotateAngleX * 0.5F - ((float)Math.PI / 10F);
                this.left_arm.rotateAngleY = 0.0F;
                break;
            case THROW_SPEAR:
                this.left_arm.rotateAngleX = this.left_arm.rotateAngleX * 0.5F - (float)Math.PI;
                this.left_arm.rotateAngleY = 0.0F;
                break;
            case BOW_AND_ARROW:
                this.right_arm.rotateAngleY = -0.1F + this.head.rotateAngleY - 0.4F;
                this.left_arm.rotateAngleY = 0.1F + this.head.rotateAngleY;
                this.right_arm.rotateAngleX = (-(float)Math.PI / 2F) + this.head.rotateAngleX;
                this.left_arm.rotateAngleX = (-(float)Math.PI / 2F) + this.head.rotateAngleX;
                break;
            case CROSSBOW_CHARGE:
                ModelHelper.func_239102_a_(this.right_arm, this.left_arm, entityIn, false);
                break;
            case CROSSBOW_HOLD:
                ModelHelper.func_239104_a_(this.right_arm, this.left_arm, this.head, false);
        }
    }

    private void func_241654_b_(T p_241654_1_) {
        switch(this.rightArmPose) {
            case EMPTY:
                this.right_arm.rotateAngleY = 0.0F;
                break;
            case BLOCK:
                this.right_arm.rotateAngleX = this.right_arm.rotateAngleX * 0.5F - 0.9424779F;
                this.right_arm.rotateAngleY = (-(float)Math.PI / 6F);
                break;
            case ITEM:
                this.right_arm.rotateAngleX = this.right_arm.rotateAngleX * 0.5F - ((float)Math.PI / 10F);
                this.right_arm.rotateAngleY = 0.0F;
                break;
            case THROW_SPEAR:
                this.right_arm.rotateAngleX = this.right_arm.rotateAngleX * 0.5F - (float)Math.PI;
                this.right_arm.rotateAngleY = 0.0F;
                break;
            case BOW_AND_ARROW:
                this.right_arm.rotateAngleY = -0.1F + this.head.rotateAngleY;
                this.left_arm.rotateAngleY = 0.1F + this.head.rotateAngleY + 0.4F;
                this.right_arm.rotateAngleX = (-(float)Math.PI / 2F) + this.head.rotateAngleX;
                this.left_arm.rotateAngleX = (-(float)Math.PI / 2F) + this.head.rotateAngleX;
                break;
            case CROSSBOW_CHARGE:
                ModelHelper.func_239102_a_(this.right_arm, this.left_arm, p_241654_1_, true);
                break;
            case CROSSBOW_HOLD:
                ModelHelper.func_239104_a_(this.right_arm, this.left_arm, this.head, true);
        }

    }

    private void func_230486_a_(T p_230486_1_, float p_230486_2_) {
        if (!(this.swingProgress <= 0.0F)) {
            HandSide handside = this.getMainHand(p_230486_1_);
            ModelRenderer modelrenderer = this.getArmForSide(handside);
            float f = this.swingProgress;
            this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f) * ((float)Math.PI * 2F)) * 0.2F;
            if (handside == HandSide.LEFT) {
                this.body.rotateAngleY *= -1.0F;
            }

            this.right_arm.rotationPointZ = MathHelper.sin(this.body.rotateAngleY) * 5.0F;
            this.right_arm.rotationPointX = -MathHelper.cos(this.body.rotateAngleY) * 5.0F;
            this.left_arm.rotationPointZ = -MathHelper.sin(this.body.rotateAngleY) * 5.0F;
            this.left_arm.rotationPointX = MathHelper.cos(this.body.rotateAngleY) * 5.0F;
            this.right_arm.rotateAngleY += this.body.rotateAngleY;
            this.left_arm.rotateAngleY += this.body.rotateAngleY;
            this.left_arm.rotateAngleX += this.body.rotateAngleY;
            f = 1.0F - this.swingProgress;
            f = f * f;
            f = f * f;
            f = 1.0F - f;
            float f1 = MathHelper.sin(f * (float)Math.PI);
            float f2 = MathHelper.sin(this.swingProgress * (float)Math.PI) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
            modelrenderer.rotateAngleX = (float)((double)modelrenderer.rotateAngleX - ((double)f1 * 1.2D + (double)f2));
            modelrenderer.rotateAngleY += this.body.rotateAngleY * 2.0F;
            modelrenderer.rotateAngleZ += MathHelper.sin(this.swingProgress * (float)Math.PI) * -0.4F;
        }
    }

    private ModelRenderer getArmForSide(HandSide side) {
        return side == HandSide.LEFT ? this.left_arm : this.right_arm;
    }

    private HandSide getMainHand(T entityIn) {
        HandSide handside = entityIn.getPrimaryHand();
        return entityIn.swingingHand == Hand.MAIN_HAND ? handside : handside.opposite();
    }

    private float getArmAngleSq(float limbSwing) {
        return -65.0F * limbSwing + limbSwing * limbSwing;
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        left_arm.render(matrixStack, buffer, packedLight, packedOverlay);
        right_arm.render(matrixStack, buffer, packedLight, packedOverlay);
        left_leg.render(matrixStack, buffer, packedLight, packedOverlay);
        right_leg.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
