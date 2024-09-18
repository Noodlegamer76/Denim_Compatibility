package com.noodlegamer76.denim.entity;

import com.noodlegamer76.denim.DenimMod;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class InitEntity {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, DenimMod.MODID);

        public static final RegistryObject<EntityType<Reaper>> REAPER = ENTITIES.register("reaper",
            () -> EntityType.Builder.<Reaper>of(Reaper::new, MobCategory.MONSTER).sized(1F, 1F).clientTrackingRange(10).updateInterval(10).build("reaper"));

}