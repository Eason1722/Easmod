package com.eostt.memaker.util.handlers;

import com.eostt.memaker.capability.ThirstyValue;
import com.eostt.memaker.init.ModBlocks;
import com.eostt.memaker.init.ModFoods;
import com.eostt.memaker.init.ModItems;
import com.eostt.memaker.network.NetworkBase;
import com.eostt.memaker.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@EventBusSubscriber
public class RegistryHandler {
    @SubscribeEvent
    public static void onFMLNetworkRegister(RegistryEvent<Potion> event){
//        NetworkBase.ThirstyValue.C
         NetworkBase thirstyValue=new NetworkBase();
         thirstyValue.registerModels();
    }


    @SubscribeEvent
    public static void onCapabilityRegister(RegistryEvent.Register<Potion> event){
        CapabilityManager.INSTANCE.register(ThirstyValue.class, new Capability.IStorage<ThirstyValue>() {
            @Override
            public NBTBase writeNBT(Capability<ThirstyValue> capability, ThirstyValue instance, EnumFacing side) {
                return instance.serializeNBT();
            }

            @Override
            public void readNBT(Capability<ThirstyValue> capability, ThirstyValue instance, EnumFacing side, NBTBase nbt) {
                if (nbt instanceof NBTTagCompound){
                    instance.deserializeNBT((NBTTagCompound) nbt);
                }
            }
        },ThirstyValue::new);
    }

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(ModFoods.FOODS.toArray(new Item[0]));
        event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
    }

    //新加入对物品的注册事件
    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
    }
    
    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        for(ItemFood itemFood:ModFoods.FOODS){
            if(itemFood instanceof IHasModel){
                ((IHasModel)itemFood).registerModels();
            }
        }
        //对于item的信息注册
        for(Item item: ModItems.ITEMS){
            if(item instanceof IHasModel) {
                ((IHasModel)item).registerModels();
            }
        }

        //新加入对于block的信息注册
        for (Block block: ModBlocks.BLOCKS) {
            if (block instanceof IHasModel) {
                ((IHasModel)block).registerModels();
            }
        }

    }
}
