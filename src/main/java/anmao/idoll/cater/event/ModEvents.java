package anmao.idoll.cater.event;

import anmao.idoll.cater.Cater;
import anmao.idoll.cater.San.PlayerSan;
import anmao.idoll.cater.San.PlayerSanProvider;
import anmao.idoll.cater.entity.ModEntityTypes;
import anmao.idoll.cater.entity.custom.EntityEQ;
import anmao.idoll.cater.networking.ModMessages;
import anmao.idoll.cater.networking.packet.SanDataSyncS2CPacket;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

public class ModEvents {
    @Mod.EventBusSubscriber(modid = Cater.MOD_ID)
    public static class ForgeEvents {

        @SubscribeEvent
        public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event)
        {
            if (event.getObject() instanceof Player)
            {
                if (!event.getObject().getCapability(PlayerSanProvider.PLAYER_SAN).isPresent())
                {
                    event.addCapability(new ResourceLocation(Cater.MOD_ID,"properties"),new PlayerSanProvider());
                }
            }
        }
        @SubscribeEvent
        public static void onPlayerCloned(PlayerEvent.Clone event)
        {
            if (event.isWasDeath())
            {
                event.getOriginal().getCapability(PlayerSanProvider.PLAYER_SAN).ifPresent(oldStore -> {
                    event.getOriginal().getCapability(PlayerSanProvider.PLAYER_SAN).ifPresent(newStore ->{
                        newStore.copyFrom(oldStore);
                    });
                });
            }
        }
        @SubscribeEvent
        public static void onRegisterCapabilities(RegisterCapabilitiesEvent event)
        {
            event.register(PlayerSan.class);
        }
        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event)
        {
            if (event.side == LogicalSide.SERVER)
            {
                event.player.getCapability(PlayerSanProvider.PLAYER_SAN).ifPresent(playerSan -> {
                    if (playerSan.getSan()>0 && event.player.getRandom().nextFloat()<0.005f)
                    {
                        playerSan.subSan(1);
                        event.player.sendSystemMessage(Component.literal("SAN Down"));
                        ModMessages.sendToPlayer(new SanDataSyncS2CPacket(playerSan.getSan()),(ServerPlayer) event.player);
                    }
                });
            }
        }
        @SubscribeEvent
        public static void onPlayerJoinWorld(EntityJoinLevelEvent event)
        {
            if (!event.getLevel().isClientSide())
            {
                if (event.getEntity() instanceof ServerPlayer player)
                {
                    player.getCapability(PlayerSanProvider.PLAYER_SAN).ifPresent(playerSan -> {
                        ModMessages.sendToPlayer(new SanDataSyncS2CPacket(playerSan.getSan()),player);
                    });
                }
            }
        }
        @SubscribeEvent
        public static void onJoinLevel(EntityJoinLevelEvent event){

            event.getEntity().removeTag("DamageSafe");
            /*
            tmpi++;
            System.out.println( "["+tmpi +"]Mob spawn " + event.getEntity().getName());
             */
            if (event.getEntity() instanceof Mob mob){
                if(!mob.getTags().contains("MiaoooDropChance")) {
                    mob.addTag("MiaoooDropChance");
                    mob.setDropChance(EquipmentSlot.CHEST, 0.0f);
                    mob.setDropChance(EquipmentSlot.FEET, 0.0f);
                    mob.setDropChance(EquipmentSlot.HEAD, 0.0f);
                    mob.setDropChance(EquipmentSlot.LEGS, 0.0f);
                    mob.setDropChance(EquipmentSlot.MAINHAND, 0.0f);
                    mob.setDropChance(EquipmentSlot.OFFHAND, 0.0f);
                }
            }
        }

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
        public static void onDamage(LivingDamageEvent event){
            //System.out.println("NowTime:"+System.currentTimeMillis());
            //System.out.println("MobTime:"+event.getEntity().getLastHurtMobTimestamp());
            System.out.println("ByMobTime:"+event.getEntity().getLastHurtByMobTimestamp());
            System.out.println("Tick:"+event.getEntity().tickCount);
        }
         */
        @SubscribeEvent
        public static void onHurt(LivingHurtEvent event){
            // 限制伤害值
            float damage =  0.2f;
            if (event.getSource() == DamageSource.OUT_OF_WORLD){
                damage =  0.5f;
            }
            damage = event.getEntity().getMaxHealth() * damage;
            if (event.getAmount() > damage){
                event.setAmount(damage);
                event.getEntity().addTag("DamageSafe");
            }
        }
        @SubscribeEvent
        public static void onAttack(LivingAttackEvent event){
            // 限制伤害频率
            if (event.getEntity().getTags().contains("DamageSafe")){
                if (event.getEntity().tickCount - event.getEntity().getLastHurtByMobTimestamp() < 20){
                    event.setCanceled(true);
                    return;
                }
                event.getEntity().removeTag("DamageSafe");
            }
            if (event.getEntity() instanceof Mob mob){
                Entity source = event.getSource().getEntity();
                if (source != null){
                    float AAR = 9.0F;
                    if (source != event.getSource().getDirectEntity()){
                        AAR = 256.0F;
                    }
                    double x = source.getX() - mob.getX();
                    double y = source.getY() - mob.getY();
                    double z = source.getZ() - mob.getZ();
                    //x = x * x + y * y + z * z;
                    //System.out.println(x);
                    if (x * x + y * y + z * z  > AAR){
                        event.setCanceled(true);
                    }
                }
                /*
                if (event.getSource().getEntity() instanceof LivingEntity livingEntity){
                    float AAR = 3.0F;
                    Multimap<Attribute, AttributeModifier> att = livingEntity.getMainHandItem().getAttributeModifiers(EquipmentSlot.MAINHAND);
                    if (att.get(Attributes.ATTACK_SPEED).isEmpty()){
                        if (att.get(Attributes.ATTACK_DAMAGE).isEmpty()){
                            AAR = 16.0F;
                        }
                    }
                    if (Math.abs(livingEntity.getX() - mob.getX()) >AAR
                            || Math.abs( livingEntity.getY() -mob.getY()) >AAR
                            || Math.abs( livingEntity.getZ() -mob.getZ()) >AAR){
                        event.setCanceled(true);
                    }


                }

                 */
                /*
                //Entity source = event.getSource().getDirectEntity();
                Entity source = event.getSource().getEntity();
                if (source != null){
                    float AAR = 3.0F;
                    Multimap<Attribute, AttributeModifier> att = source.getHandSlots().iterator().next().getAttributeModifiers(EquipmentSlot.MAINHAND);
                    /*
                    if (source instanceof LivingEntity livingEntity){
                        if(livingEntity.getMainHandItem().getAttributeModifiers(EquipmentSlot.MAINHAND).get(Attributes.ATTACK_SPEED).isEmpty()){
                            System.out.println("Ture");
                            AAR = 16.0F;
                        }
                    }

                    if (att.get(Attributes.ATTACK_SPEED).isEmpty()){
                        if (att.get(Attributes.ATTACK_DAMAGE).isEmpty()){
                            AAR = 16.0F;
                        }
                    }
                    if (Math.abs(source.getX() - mob.getX()) >AAR || Math.abs( source.getY() -mob.getY()) >AAR || Math.abs( source.getZ() -mob.getZ()) >AAR){
                        event.setCanceled(true);
                    }


                }

                 */
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
