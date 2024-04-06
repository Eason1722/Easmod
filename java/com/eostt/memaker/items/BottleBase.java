package com.eostt.memaker.items;

import com.eostt.memaker.Main;
import com.eostt.memaker.capability.ThirstyValue;
import com.eostt.memaker.init.ModItems;
import com.eostt.memaker.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BottleBase extends ItemBow implements IHasModel {
   private int meta;
    public BottleBase(String name, CreativeTabs tabs ,int meta){
        super();
        this.meta=meta;
        setCreativeTab(tabs);
        setUnlocalizedName(name);
        setRegistryName(name);
        setMaxStackSize(1);
        ModItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        Main.proxy.registerRenderer(this,meta,"inventory");
    }


    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
//        super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
//            boolean flag = entityplayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
            int i = this.getMaxItemUseDuration(stack) - timeLeft;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, entityplayer, i, true);
           ThirstyValue thirstyValue= entityplayer.getCapability(ThirstyValue.THIRSTY_VALUE,null);
            if (i < 0) return;
            if (thirstyValue.getThirstyValue()+i>72000) {
                thirstyValue.setThirstyValue(thirstyValue.getThirstyValue() + i);
            }else{
                thirstyValue.setThirstyValue(72000);
            }

    }
}}
