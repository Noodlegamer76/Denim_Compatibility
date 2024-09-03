package com.noodlegamer76.denim.datagen;

import com.noodlegamer76.denim.DenimMod;
import com.noodlegamer76.denim.item.InitItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> providerCompletableFuture,
                               CompletableFuture<TagLookup<Block>> tagLookupCompletableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, providerCompletableFuture, tagLookupCompletableFuture, DenimMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {


        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(InitItems.JELMET.get(),
                        InitItems.JACKET.get(),
                        InitItems.JEANS.get(),
                        InitItems.JOOTS.get()
                );
//
        //    this.tag(ItemTags.DOORS)
        //            .add(ItemInit.RAINBOW_DOOR.get());
    }
}
