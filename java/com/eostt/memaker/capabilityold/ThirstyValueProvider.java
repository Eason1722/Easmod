package com.eostt.memaker.capabilityold;

import com.eostt.memaker.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

public class ThirstyValueProvider implements ICapabilitySerializable<NBTTagCompound> {


    private  final ThirstyValue instance;
    private final Capability<ThirstyValue> capability;
    public ThirstyValueProvider() {
        this.instance =new ThirstyValue();
        this.capability =ThirstyValue.THIRSTY_VALUE;
    }
    @Override
    public boolean hasCapability(@NotNull Capability<?> capability, EnumFacing facing) {
        return this.capability.equals(capability);
    }

    @Override
    public <T> T getCapability(@NotNull Capability<T> capability, EnumFacing facing) {
        return this.capability.equals(capability)?this.capability.cast(instance):null;
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
    public static void onAttachCapability(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof EntityPlayer) {
            ThirstyValueProvider thirstyValue = new ThirstyValueProvider();
            event.addCapability(new ResourceLocation(Reference.Mod_ID + ":thirsty_value"), thirstyValue);
        }
    }
    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event){
        ThirstyValue instance=event.getEntityPlayer().getCapability(ThirstyValue.THIRSTY_VALUE,null);
        ThirstyValue orginal=event.getOriginal().getCapability(ThirstyValue.THIRSTY_VALUE,null);
        if (instance != null) {
            if (orginal != null) {
                instance.setThirstyValue(orginal.getThirstyValue());
            }
        }
    }
//    @SubscribeEvent
//    public static void onPLayerJoin(PlayerEvent){
//
//    }
}
