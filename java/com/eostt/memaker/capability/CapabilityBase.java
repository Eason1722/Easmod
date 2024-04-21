package com.eostt.memaker.capability;

import com.eostt.memaker.capabilityold.ThirstyValue;
import com.eostt.memaker.capabilityold.ThirstyValueProvider;
import com.eostt.memaker.init.ModCapability;
import com.eostt.memaker.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CapabilityBase implements  INBTSerializable<NBTTagCompound> {
    public double getValue() {
        return value;
    }

    @CapabilityInject(CapabilityBase.class)
    public static Capability<CapabilityBase> CAPABILITY;

    public void setValue(double value) {
        this.value = value;
    }

    public CapabilityBase(String name, double value) {
        this.name = name;
        this.value = value;
        ModCapability.capabilities.add(this);

    }

    private final String name;
    private double value;

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setDouble(name, value);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        this.value = nbt.getDouble(name);
    }

    public class CapabilityProvider implements ICapabilitySerializable<NBTTagCompound> {
        private final CapabilityBase instance;
        private final Capability<CapabilityBase> capability;

        public CapabilityProvider() {
            this.instance = new CapabilityBase(name, value);
            this.capability = CapabilityBase.CAPABILITY;
        }

        @Override
        public boolean hasCapability(@NotNull Capability<?> capability, @Nullable EnumFacing facing) {
            return this.capability.equals(capability);
        }

        @Nullable
        @Override
        public <T> T getCapability(@NotNull Capability<T> capability, @Nullable EnumFacing facing) {
            return this.capability.equals(capability) ? this.capability.cast(instance) : null;
        }

        @Override
        public NBTTagCompound serializeNBT() {
            return this.instance.serializeNBT();
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            this.instance.deserializeNBT(nbt);
        }

        @SubscribeEvent
        public void onAttachCapability(AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof EntityPlayer) {
                ThirstyValueProvider thirstyValue = new ThirstyValueProvider();
                event.addCapability(new ResourceLocation(Reference.Mod_ID + ":" + name), thirstyValue);
            }
        }

        @SubscribeEvent
        public void onPlayerClone(PlayerEvent.Clone event) {
            ThirstyValue instance = event.getEntityPlayer().getCapability(ThirstyValue.THIRSTY_VALUE, null);
            ThirstyValue original = event.getOriginal().getCapability(ThirstyValue.THIRSTY_VALUE, null);
            if (instance != null) {
                if (original != null) {
                    instance.setThirstyValue(original.getThirstyValue());
                }
            }
        }
    }
}
