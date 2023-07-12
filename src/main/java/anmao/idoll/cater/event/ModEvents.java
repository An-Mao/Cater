package anmao.idoll.cater.event;

import anmao.idoll.cater.Cater;
import anmao.idoll.cater.entity.ModEntityTypes;
import anmao.idoll.cater.entity.custom.EntityEQ;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ModEvents {
    @Mod.EventBusSubscriber(modid = Cater.MOD_ID)
    public static class ForgeEvents {

        //
        /*
        @SubscribeEvent
        public static void onSpawned(LivingSpawnEvent.AllowDespawn event){
            ToApi.echo(event.getEntity().getType()+"  is spawn");
            event.getEntity().setDropChance(EquipmentSlot.CHEST,0.0f);
            event.getEntity().setDropChance(EquipmentSlot.FEET,0.0f);
            event.getEntity().setDropChance(EquipmentSlot.HEAD,0.0f);
            event.getEntity().setDropChance(EquipmentSlot.LEGS,0.0f);
            event.getEntity().setDropChance(EquipmentSlot.MAINHAND,0.0f);
            event.getEntity().setDropChance(EquipmentSlot.OFFHAND,0.0f);
        }

         */
        /*
        @SubscribeEvent
        public static void onHurt(LivingHurtEvent event){
            //
        }

         */
        @SubscribeEvent
        public static void onAttack(LivingAttackEvent event){
            if (event.getEntity() instanceof Mob mob){
                //Entity source = event.getSource().getDirectEntity();
                Entity source = event.getSource().getEntity();
                if (source != null){
                    float AAR = 3.0F;
                    ItemStack item = source.getHandSlots().iterator().next();
                    /*
                    if (source instanceof LivingEntity livingEntity){
                        if(livingEntity.getMainHandItem().getAttributeModifiers(EquipmentSlot.MAINHAND).get(Attributes.ATTACK_SPEED).isEmpty()){
                            System.out.println("Ture");
                            AAR = 16.0F;
                        }
                    }
                     */
                    if (item.getAttributeModifiers(EquipmentSlot.MAINHAND).get(Attributes.ATTACK_SPEED).isEmpty()){
                        if (item.getAttributeModifiers(EquipmentSlot.MAINHAND).get(Attributes.ATTACK_DAMAGE).isEmpty()){
                            AAR = 16.0F;
                        }
                    }
                    if (Math.abs(source.getX() - mob.getX()) >AAR || Math.abs( source.getY() -mob.getY()) >AAR || Math.abs( source.getZ() -mob.getZ()) >AAR){
                        //----------------------------------
                        event.setCanceled(true);
                    }
                }
            }
        }
    }
    @Mod.EventBusSubscriber(modid = Cater.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents{
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event){
            event.put(ModEntityTypes.EQ.get(), EntityEQ.setAttributes());
        }

    }
}
