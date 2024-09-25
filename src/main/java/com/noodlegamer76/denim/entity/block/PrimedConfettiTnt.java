package com.noodlegamer76.denim.entity.block;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class PrimedConfettiTnt extends PrimedTnt {
    private static final EntityDataAccessor<Integer> DATA_FUSE_ID = SynchedEntityData.defineId(PrimedConfettiTnt.class, EntityDataSerializers.INT);

    public PrimedConfettiTnt(EntityType<? extends PrimedConfettiTnt> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public PrimedConfettiTnt(Level pLevel, double pX, double pY, double pZ, @Nullable LivingEntity pOwner) {
        super(pLevel, pX, pY, pZ, pOwner);
    }

    @Override
    protected void explode() {
        float f = 20.0F;
        this.level().explode(this, this.getX(), this.getY(0.0625D), this.getZ(), f, Level.ExplosionInteraction.TNT);
    }
}
