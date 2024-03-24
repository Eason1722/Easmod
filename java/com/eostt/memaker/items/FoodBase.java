package com.eostt.memaker.items;

import com.eostt.memaker.Main;
import com.eostt.memaker.init.ModFoods;
import com.eostt.memaker.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;

public class FoodBase extends ItemFood implements IHasModel {
    public FoodBase(int amount, float saturation,String name, CreativeTabs tab) {
        super(amount, saturation, false);
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab( tab);
        ModFoods.FOODS.add(this);
    }

    @Override
    public void registerModels() {
        Main.proxy.registerRenderer(this,0,"inventory");
    }
}
