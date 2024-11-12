package com.noodlegamer76.denim.entity;

import com.noodlegamer76.denim.DenimMod;
import com.noodlegamer76.denim.client.renderer.entity.ConfettiTntRenderer;
import com.noodlegamer76.denim.entity.block.PrimedConfettiTnt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class InitEntity {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, DenimMod.MODID);

    public static final RegistryObject<EntityType<PrimedConfettiTnt>> CONFETTI_TNT = ENTITIES.register("reaper",
            () -> EntityType.Builder.<PrimedConfettiTnt>of(PrimedConfettiTnt::new, MobCategory.MISC).sized(1F, 1F).build("reaper"));
}
