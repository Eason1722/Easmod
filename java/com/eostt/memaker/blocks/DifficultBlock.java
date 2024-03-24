package com.eostt.memaker.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class DifficultBlock extends BlockBase{
    public DifficultBlock(String name, Material material, SoundType soundType, float hardness, float resistance, String toolClass, int level) {
        super(name, material);
        setSoundType(soundType);
        //Hardness、Resistance这两个值越大，越不好挖
        setHardness(hardness);
        setResistance(resistance);

        //设置挖掘工具、挖掘等级
        setHarvestLevel(toolClass,level);
    }
}
