package com.noodlegamer76.denim.creativetabs;

import com.noodlegamer76.denim.DenimMod;
import com.noodlegamer76.denim.item.InitItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class InitCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DenimMod.MODID);

    public static RegistryObject<CreativeModeTab> denimTab = CREATIVE_TABS.register("denim_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("denim.creative_tab"))
            .icon(() -> new ItemStack(InitItems.FRYING_PAN.get()))
            .build());
}
