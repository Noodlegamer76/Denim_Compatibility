package com.noodlegamer76.denim.item;

import com.noodlegamer76.denim.DenimMod;
import com.noodlegamer76.denim.block.InitBlocks;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class InitItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DenimMod.MODID);

    //dev stuff
    public static final RegistryObject<Item> TEST_ITEM = ITEMS.register("test_item",
            () -> new TestItem(new Item.Properties()));
    public static final RegistryObject<Item> RENDER_TESTER_BLOCK_ITEM = ITEMS.register("render_tester_block",
            () -> new BlockItem(InitBlocks.RENDER_TESTER_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> FRYING_PAN = ITEMS.register("frying_pan",
            () -> new FryingPan(InitBlocks.FRYING_PAN_BLOCK.get(), new Item.Properties()));
}
