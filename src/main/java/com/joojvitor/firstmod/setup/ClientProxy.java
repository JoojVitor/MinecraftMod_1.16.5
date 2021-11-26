package com.joojvitor.firstmod.setup;

import com.joojvitor.firstmod.FirstMod;
import com.joojvitor.firstmod.block.ModBlocks;
import com.joojvitor.firstmod.container.ModContainers;
import com.joojvitor.firstmod.entity.ModEntityTypes;
import com.joojvitor.firstmod.entity.render.CastorRenderer;
import com.joojvitor.firstmod.entity.render.IuroRenderer;
import com.joojvitor.firstmod.screens.ElectrifierScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FirstMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientProxy implements IProxy{
    @Override
    public void init() {
        RenderTypeLookup.setRenderLayer(ModBlocks.ZUCCINI_CROP.get(), RenderType.getCutout());

        RenderTypeLookup.setRenderLayer(ModBlocks.REDWOOD_LEAVES.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.REDWOOD_SAPLING.get(), RenderType.getCutout());

        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.CASTOR.get(), CastorRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.IURO.get(), IuroRenderer::new);

        ScreenManager.registerFactory(ModContainers.ELECTRIFIER_CONTAINER.get(), ElectrifierScreen::new);
    }

    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().world;
    }
}
