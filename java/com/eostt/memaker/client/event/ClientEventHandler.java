package com.eostt.memaker.client.event;

import com.eostt.memaker.capability.ThirstyValue;
import com.eostt.memaker.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class ClientEventHandler {
    private static final ResourceLocation TEXTURE_THV=new ResourceLocation(Reference.Mod_ID+":txtures/gui/overlay_thv");
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onRenderGameOverlayPost(RenderGameOverlayEvent.Post event){
        if(RenderGameOverlayEvent.ElementType.ALL.equals(event.getType())) {
            Minecraft mc = Minecraft.getMinecraft();
            Entity entity = mc.getRenderViewEntity();
            ScaledResolution resolution = event.getResolution();
            int width = resolution.getScaledWidth(), height = resolution.getScaledHeight();
            GlStateManager.enableBlend();
            if(entity instanceof EntityPlayer) {
                {
                    mc.ingameGUI.drawTexturedModalRect(width / 2 - 175, height - 40, 0, 4, 40, 80);
                    mc.ingameGUI.drawString(mc.fontRenderer, "" + entity.getCapability(ThirstyValue.THIRSTY_VALUE, null).getThirstyValue(), width / 2 - 125, height - 36, 0xffffff);
                }
            }
        }
    }

}
