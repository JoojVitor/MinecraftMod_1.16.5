package com.joojvitor.firstmod.entity.render;

import com.joojvitor.firstmod.FirstMod;
import com.joojvitor.firstmod.entity.custom.CastorEntity;
import com.joojvitor.firstmod.entity.model.CastorModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class CastorRenderer extends MobRenderer<CastorEntity, CastorModel<CastorEntity>> {

    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(FirstMod.MOD_ID, "textures/entity/castor.png");

    public CastorRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CastorModel<>(), 0.2f);
    }

    @Override
    public ResourceLocation getEntityTexture(CastorEntity entity) {
        return TEXTURE;
    }


}
