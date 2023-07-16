package anmao.idoll.cater.event;

import anmao.idoll.cater.Cater;
import anmao.idoll.cater.client.SanHudOverlay;
import anmao.idoll.cater.entity.ModEntityTypes;
import anmao.idoll.cater.entity.client.RendererEQ;
import anmao.idoll.cater.networking.ModMessages;
import anmao.idoll.cater.networking.packet.SanC2SPacket;
import anmao.idoll.cater.util.KeyBinding;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = Cater.MOD_ID,value = Dist.CLIENT)
    public static class ClientForgeEvents{
        @SubscribeEvent
        public static void onKeyInput(InputEvent event){
            if (KeyBinding.ADDSAN_KEY.consumeClick()){
                ModMessages.sendToServer(new SanC2SPacket());
            }
        }
    }

    @Mod.EventBusSubscriber(modid = Cater.MOD_ID,value = Dist.CLIENT,bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvent{
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            //LOGGER.info("HELLO FROM CLIENT SETUP");
            //LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
            EntityRenderers.register(ModEntityTypes.EQ.get(), RendererEQ::new);
        }
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event){
            event.register(KeyBinding.ADDSAN_KEY);
        }
        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event){
            event.registerAboveAll("san", SanHudOverlay.SAN_HUD);
        }
        /*
        public static void onLivingRenderPre(RenderLivingEvent.Pre event){
        }

         */
    }
}
