package com.joojvitor.firstmod.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
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

public class ElectrifierTile extends TileEntity {
    private final ItemStackHandler itemStackHandler = createHandler();

    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemStackHandler);

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(1) {
            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return true;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    public ElectrifierTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public ElectrifierTile() {
        this(ModTileEntities.ELECTRIFIER_TILE_ENTITY.get());
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
}
