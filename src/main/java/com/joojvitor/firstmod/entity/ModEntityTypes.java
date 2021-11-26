package com.joojvitor.firstmod.entity;

import com.joojvitor.firstmod.FirstMod;
import com.joojvitor.firstmod.entity.custom.CastorEntity;
import com.joojvitor.firstmod.entity.custom.IuroEntity;
import com.joojvitor.firstmod.entity.custom.PossessedArmorEntity;
import com.joojvitor.firstmod.util.Registration;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

public class ModEntityTypes {

    public static final RegistryObject<EntityType<PossessedArmorEntity>> POSSESSED_ARMOR =
            Registration.ENTITY_TYPES.register("possessed_armor", () ->
                    EntityType.Builder.create(PossessedArmorEntity::new, EntityClassification.MONSTER)
                            .size(1f, 3f)
                            .build(new ResourceLocation(FirstMod.MOD_ID, "possessed_armor").toString()));

    public static final RegistryObject<EntityType<IuroEntity>> IURO =
            Registration.ENTITY_TYPES.register("iuro", () ->
                    EntityType.Builder.create(IuroEntity::new, EntityClassification.CREATURE)
                            .size(1f, 3f)
                            .build(new ResourceLocation(FirstMod.MOD_ID, "iuro").toString()));

    public static final RegistryObject<EntityType<CastorEntity>> CASTOR =
            Registration.ENTITY_TYPES.register("castor", () ->
                    EntityType.Builder.create(CastorEntity::new, EntityClassification.CREATURE)
                            .size(1.1f, 0.4f)
                            .build(new ResourceLocation(FirstMod.MOD_ID, "castor").toString()));

    public static void register () { }
}
