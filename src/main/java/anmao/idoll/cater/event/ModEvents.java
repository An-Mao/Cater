package anmao.idoll.cater.event;

import anmao.idoll.cater.Cater;
import anmao.idoll.cater.entity.ModEntityTypes;
import anmao.idoll.cater.entity.custom.EntityEQ;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ModEvents {
    @Mod.EventBusSubscriber(modid = Cater.MOD_ID)
    public static class ForgeEvents {
        //
    }
    @Mod.EventBusSubscriber(modid = Cater.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents{
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event){
            event.put(ModEntityTypes.EQ.get(), EntityEQ.setAttributes());
        }
    }
}
