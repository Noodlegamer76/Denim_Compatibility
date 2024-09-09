package com.noodlegamer76.denim.block;

import com.noodlegamer76.denim.DenimMod;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class InitBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DenimMod.MODID);

    public static final RegistryObject<Block> RENDER_TESTER_BLOCK = BLOCKS.register("render_tester",
            () -> new RenderTesterBlock(BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(DyeColor.BLACK).noCollission().noOcclusion()));

    public static final RegistryObject<Block> FRYING_PAN_BLOCK = BLOCKS.register("frying_pan",
            () -> new FryingPan(BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(DyeColor.BLACK).noOcclusion()));


    public static final RegistryObject<Block> CONFETTI_TNT = BLOCKS.register("confetti_tnt",
            () -> new ConfettiTnt(BlockBehaviour.Properties.copy(Blocks.TNT)));
}
