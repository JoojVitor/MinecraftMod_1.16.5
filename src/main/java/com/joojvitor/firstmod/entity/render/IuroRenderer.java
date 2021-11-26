package com.joojvitor.firstmod.entity.render;

import com.joojvitor.firstmod.FirstMod;
import com.joojvitor.firstmod.entity.custom.IuroEntity;
import com.joojvitor.firstmod.entity.model.IuroModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class IuroRenderer extends MobRenderer<IuroEntity, IuroModel<IuroEntity>>
{
    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(FirstMod.MOD_ID, "textures/entity/iuro.png");

    public IuroRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new IuroModel<>(), 0.7F);
    }

    @Override
    public ResourceLocation getEntityTexture(IuroEntity entity) {
        return TEXTURE;
    }
}
