package com.eostt.memaker;


import com.eostt.memaker.proxy.CommonProxy;
import com.eostt.memaker.util.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.LogWrapper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.slf4j.Logger;

@Mod(modid = Reference.Mod_ID, name = Reference.NAME, version= Reference.VERSION)
public class Main {


    @Instance
    public static Main instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
    public static CommonProxy proxy;

    @EventHandler
    public static void PreInit(FMLPreInitializationEvent event)
    {
       event.getModLog().info("preInit event from easmod");
    }

    @EventHandler
    public static void Init(FMLInitializationEvent event)
    {

    }

    @EventHandler
    public static void PostInit(FMLPostInitializationEvent event)
    {

    }

}

