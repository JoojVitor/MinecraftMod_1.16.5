package com.joojvitor.firstmod.tileentity;

import com.joojvitor.firstmod.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ElectrifierTile extends TileEntity implements ITickableTileEntity {

    private int tick =0;
    private int energyLevel = 0;

    private final ItemStackHandler itemStackHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemStackHandler);

    public ElectrifierTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public ElectrifierTile() {
        this(ModTileEntities.ELECTRIFIER_TILE_ENTITY.get());
    }

    public int getEnergyLevel() {
        return energyLevel;
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(3) {
            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                switch (slot) {
                    case 0: return stack.getItem() == Items.DIAMOND;
                    case 1: return stack.getItem() == ModItems.COPPER_WIRE.get();
                    case 2: return stack.getItem() == Items.EMERALD;
                    default: return false;
                }
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (!isItemValid(slot, stack)) {
                    return stack;
                }

                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Override
    public void read(BlockState state, CompoundNBT tag) {
        itemStackHandler.deserializeNBT(tag.getCompound("inv"));

        super.read(state, tag);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("inv", itemStackHandler.serializeNBT());

        return super.write(compound);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
            return cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY
                    ? handler.cast()
                    : super.getCapability(cap, side);
    }

    @Override
    public void tick() {
        tick++;

        // should be configurable
        if (tick > 10)  {
            if (this.itemStackHandler.getStackInSlot(0).getItem() == Items.DIAMOND
                    && this.energyLevel < 64) {
                itemStackHandler.extractItem(0, 1, false);
                energyLevel++;
            }

            if (this.itemStackHandler.getStackInSlot(1).getItem() == ModItems.COPPER_WIRE.get()
                    && this.itemStackHandler.getStackInSlot(2).getCount() < 64
                    && this.energyLevel > 0) {
                itemStackHandler.extractItem(1, 1, false);
                itemStackHandler.insertItem(2, new ItemStack(Items.EMERALD, 1), false);
                energyLevel--;
            }

            tick = 0;
        }
    }
}
