package com.eostt.memaker.items;

import com.eostt.memaker.Main;
import com.eostt.memaker.capability.ThirstyValue;
import com.eostt.memaker.init.ModItems;
import com.eostt.memaker.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.IFluidTank;

//import javax.annotation.Nullable;

import static net.minecraftforge.common.EnumPlantType.Water;

public class BottleBase extends ItemBow implements IHasModel {
   private int meta;
    public BottleBase(String name, CreativeTabs tabs ,int meta){
        super();
        this.meta=meta;
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
    }
}}
