package com.joojvitor.firstmod.events;

import com.joojvitor.firstmod.FirstMod;
import com.joojvitor.firstmod.entity.ModEntityTypes;
import com.joojvitor.firstmod.entity.custom.CastorEntity;
import com.joojvitor.firstmod.entity.custom.IuroEntity;
import com.joojvitor.firstmod.item.custom.ModSpawnEggItem;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FirstMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.CASTOR.get(), CastorEntity.setCustomAttributes().create());
        event.put(ModEntityTypes.IURO.get(), IuroEntity.setCustomAttributes().create());
    }

    @SubscribeEvent
    public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
        ModSpawnEggItem.initSpawnEggs();
    }
}
