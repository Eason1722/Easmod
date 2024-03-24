package com.eostt.memaker.init;

import com.eostt.memaker.blocks.BlockBase;
import com.eostt.memaker.blocks.DifficultBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {
    public static final List<Block> BLOCKS = new ArrayList<>();
    public static final Block MAKE_BY_SUN = new DifficultBlock(
            "cfe_block", Material.IRON, SoundType.STONE,2.0f,4.0f,"pickaxe",2
    );


}
