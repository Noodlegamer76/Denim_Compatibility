package com.noodlegamer76.denim.creativetabs;

import com.noodlegamer76.denim.item.InitItems;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DenimTab {
    @SubscribeEvent
    public void buildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == InitCreativeTabs.denimTab.getKey()) {
            event.accept(InitItems.TEST_ITEM);
            event.accept(InitItems.RENDER_TESTER_BLOCK_ITEM);
            event.accept(InitItems.FRYING_PAN);
        }
    }
}
