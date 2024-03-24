package com.eostt.memaker.items;

import com.eostt.memaker.Main;
import com.eostt.memaker.init.ModItems;
import com.eostt.memaker.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * @see com.eostt.memaker.util.IHasModel
 */
public class ItemBase extends Item implements IHasModel {
    public  ItemBase(String name, CreativeTabs tab){
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tab);
        ModItems.ITEMS.add(this);
    }
    @Override
    public void registerModels() {
        Main.proxy.registerRenderer(this,0,"inventory");
    }
}
