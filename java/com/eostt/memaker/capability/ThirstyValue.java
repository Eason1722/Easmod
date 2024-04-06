package com.eostt.memaker.capability;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.util.INBTSerializable;

public class ThirstyValue implements INBTSerializable<NBTTagCompound> {
    @CapabilityInject(ThirstyValue.class)
    public static Capability<ThirstyValue> THIRSTY_VALUE;
    public ThirstyValue(){
        this.thirstyValue=72000.0f;
    }
    private float thirstyValue;

    public float getThirstyValue() {
        return thirstyValue;
    }

    public void setThirstyValue(float thirstyValue) {
        this.thirstyValue = thirstyValue;
    }


    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt=new NBTTagCompound();
        nbt.setFloat("thirsty_value",thirstyValue);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        this.thirstyValue=nbt.getFloat("thirsty_value");

    }
}
