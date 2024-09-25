package com.noodlegamer76.denim.item;

import com.noodlegamer76.denim.DenimMod;
import com.noodlegamer76.denim.block.InitBlocks;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class InitItems {

    public static final DeferredRegister<Item> ITEMS = DenimMod.ITEMS;

    //dev stuff
    public static final RegistryObject<Item> TEST_ITEM = ITEMS.register("test_item",
            () -> new TestItem(new Item.Properties()));

    public static final RegistryObject<Item> RENDER_TESTER_BLOCK_ITEM = ITEMS.register("render_tester_block",
            () -> new BlockItem(InitBlocks.RENDER_TESTER_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<Item> FRYING_PAN = ITEMS.register("frying_pan",
            () -> new FryingPan(InitBlocks.FRYING_PAN_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<Item> JELMET = ITEMS.register("jelmet",
            () -> new ArmorItem(ModArmorMaterials.DENIM, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> JACKET = ITEMS.register("jacket",
            () -> new ArmorItem(ModArmorMaterials.DENIM, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> JEANS = ITEMS.register("jeans",
            () -> new ArmorItem(ModArmorMaterials.DENIM, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> JOOTS = ITEMS.register("joots",
            () -> new ArmorItem(ModArmorMaterials.DENIM, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> CONFETTI_TNT = ITEMS.register("confetti_tnt",
            () -> new BlockItem(InitBlocks.CONFETTI_TNT.get(), new Item.Properties()));
}
