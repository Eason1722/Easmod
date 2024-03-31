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
    private static final ResourceLocation TEXTURE_THV=new ResourceLocation(Reference.Mod_ID+":textures/gui/overlay_thv.png");
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
                    mc.getTextureManager().bindTexture(TEXTURE_THV);
                    ThirstyValue thv=entity.getCapability(ThirstyValue.THIRSTY_VALUE,null);
                    float thirstyValue = 0;
                    if (thv != null) {
                        thirstyValue = thv.getThirstyValue();
                    }//aminoac
                    mc.ingameGUI.drawTexturedModalRect(0, height - 40, 0, 4, 20,36);
                    mc.ingameGUI.drawString(mc.fontRenderer, ("thirsty value    "+thirstyValue ), 50, height - 24, 0xffffff);
                }
            }
        }
    }

}
