package com.joojvitor.firstmod.entity.model;

import com.joojvitor.firstmod.entity.custom.CastorEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class CastorModel<T extends CastorEntity> extends EntityModel<T> {
    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer tail_r1;
    private final ModelRenderer legFrontLeft;
    private final ModelRenderer legBackLeft;
    private final ModelRenderer legFrontRight;
    private final ModelRenderer legBackRight;

    public CastorModel() {
        textureWidth = 64;
        textureHeight = 64;

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 21.5F, 0.0F);
        head.setTextureOffset(20, 15).addBox(-2.5F, -2.5F, -9.0F, 5.0F, 4.0F, 4.0F, 0.0F, false);
        head.setTextureOffset(0, 18).addBox(-1.5F, -0.5F, -10.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(0, 15).addBox(-0.9F, 0.5F, -10.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(-0.5F, 21.5F, 0.0F);

        tail_r1 = new ModelRenderer(this);
        tail_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.addChild(tail_r1);
        setRotationAngle(tail_r1, -1.5708F, 0.0F, 0.0F);
        tail_r1.setTextureOffset(0, 15).addBox(-2.5F, 0.5F, 5.0F, 5.0F, 1.0F, 10.0F, 0.0F, false);
        tail_r1.setTextureOffset(0, 0).addBox(-3.5F, -3.5F, -5.0F, 7.0F, 5.0F, 10.0F, 0.0F, false);

        legFrontLeft = new ModelRenderer(this);
        legFrontLeft.setRotationPoint(-0.5F, 21.5F, 0.0F);
        legFrontLeft.setTextureOffset(0, 0).addBox(1.5F, 0.5F, -5.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);
        legFrontLeft.setTextureOffset(0, 8).addBox(1.5F, 2.5F, -6.0F, 2.0F, 0.0F, 2.0F, 0.0F, false);

        legBackLeft = new ModelRenderer(this);
        legBackLeft.setRotationPoint(-0.5F, 21.5F, 0.0F);
        legBackLeft.setTextureOffset(4, 4).addBox(2.5F, 2.5F, -1.0F, 2.0F, 0.0F, 2.0F, 0.0F, false);
        legBackLeft.setTextureOffset(24, 0).addBox(2.5F, -0.5F, 0.0F, 2.0F, 3.0F, 4.0F, 0.0F, true);

        legFrontRight = new ModelRenderer(this);
        legFrontRight.setRotationPoint(-0.5F, 21.5F, 0.0F);
        legFrontRight.setTextureOffset(4, 8).addBox(-3.5F, 2.5F, -6.0F, 2.0F, 0.0F, 2.0F, 0.0F, false);
        legFrontRight.setTextureOffset(0, 4).addBox(-3.5F, 0.5F, -5.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        legBackRight = new ModelRenderer(this);
        legBackRight.setRotationPoint(-0.5F, 21.5F, 0.0F);
        legBackRight.setTextureOffset(0, 26).addBox(-4.5F, -0.5F, 0.0F, 2.0F, 3.0F, 4.0F, 0.0F, false);
        legBackRight.setTextureOffset(4, 0).addBox(-4.5F, 2.5F, -1.0F, 2.0F, 0.0F, 2.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.rotateAngleX = headPitch * ((float)Math.PI / 290F);
        this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 280F);
        this.body.rotateAngleX = ((float)Math.PI / 2F);
        this.legBackRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.legBackLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.legFrontRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.legFrontLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        legFrontLeft.render(matrixStack, buffer, packedLight, packedOverlay);
        legBackLeft.render(matrixStack, buffer, packedLight, packedOverlay);
        legFrontRight.render(matrixStack, buffer, packedLight, packedOverlay);
        legBackRight.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}