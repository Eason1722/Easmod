package com.eostt.memaker.blocks;

import com.eostt.memaker.Main;
import com.eostt.memaker.init.ModItems;
import com.eostt.memaker.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import com.eostt.memaker.init.ModBlocks;
import static com.eostt.memaker.tabs.EosttTab.EOSTT_TABS;

public class BlockBase extends Block implements IHasModel {

    public BlockBase(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(EOSTT_TABS);//将你的方块放在哪个物品栏，这里我们选择的是草方块(建筑方块)那一类

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void registerModels() {
        Main.proxy.registerRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

}
//————————————————
//        版权声明：本文为CSDN博主「Jay_fearless」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//        原文链接：https://blog.csdn.net/Jay_fearless/article/details/116407081
