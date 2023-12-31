package anmao.idoll.cater.event;

import anmao.idoll.cater.San.SanTags;
import anmao.idoll.cater.Cater;
import anmao.idoll.cater.San.PlayerSan;
import anmao.idoll.cater.San.PlayerSanProvider;
import anmao.idoll.cater.entity.ModEntityTypes;
import anmao.idoll.cater.entity.custom.EntityD;
import anmao.idoll.cater.entity.custom.EntityEQ;
import anmao.idoll.cater.networking.ModMessages;
import anmao.idoll.cater.networking.packet.SanDataSyncS2CPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

public class ModEvents {
    @Mod.EventBusSubscriber(modid = Cater.MOD_ID)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void onDeath(LivingDeathEvent event)
        {
            if (event.getSource().getEntity() instanceof ServerPlayer player)
            {
                player.getCapability(PlayerSanProvider.PLAYER_SAN).ifPresent(playerSan -> {
                    if (event.getEntity() instanceof Monster)
                    {
                        playerSan.addSan(1);
                        ModMessages.sendToPlayer(new SanDataSyncS2CPacket(playerSan.getSan()), player);
                    }else {
                        if (SanDeath(player, playerSan.getSan())) {
                            if (event.getEntity().getType() == EntityType.VILLAGER || event.getEntity().getType() == EntityType.PLAYER) {
                                playerSan.subSan(3);
                                ModMessages.sendToPlayer(new SanDataSyncS2CPacket(playerSan.getSan()), player);
                            } else {
                                playerSan.subSan(1);
                                ModMessages.sendToPlayer(new SanDataSyncS2CPacket(playerSan.getSan()), player);
                            }
                        }
                    }

                });
            }
            if (event.getEntity() instanceof Player dplayer)
            {
                dplayer.getCapability(PlayerSanProvider.PLAYER_SAN).ifPresent(playerSan -> {
                    playerSan.addSan(100);
                    ModMessages.sendToPlayer(new SanDataSyncS2CPacket(playerSan.getSan()),(ServerPlayer) dplayer);
                });
            }
        }
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
            if (event.side == LogicalSide.SERVER) {
                if (event.player instanceof ServerPlayer serverPlayer) {
                    serverPlayer.getCapability(PlayerSanProvider.PLAYER_SAN).ifPresent(playerSan -> {
                        if (SanDeath(serverPlayer, playerSan.getSan()) && serverPlayer.getRandom().nextFloat() < 0.005f) {
                            if (serverPlayer.getLevel().dimension() == Level.NETHER) {
                                playerSan.subSan(1);
                            }
                            ModMessages.sendToPlayer(new SanDataSyncS2CPacket(playerSan.getSan()), serverPlayer);
                        }
                    });
                }
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
        public static void onJoinLevel(EntityJoinLevelEvent event){ }
        @SubscribeEvent
        public static void onLeaveLevel(EntityLeaveLevelEvent event){ }
        @SubscribeEvent
        public static void onSpawned(LivingSpawnEvent.AllowDespawn event){ }

        @SubscribeEvent
        public static void onDamage(LivingDamageEvent event){
            if (event.getSource().getEntity() instanceof ServerPlayer player)
            {
                player.getCapability(PlayerSanProvider.PLAYER_SAN).ifPresent(playerSan -> {
                    int _san =playerSan.getSan();
                    if (_san <50 && SanDeath(player, _san))
                    {
                        float damage_self = Math.max(player.getMaxHealth()*0.04f,1.0f);
                        player.hurt(DamageSource.OUT_OF_WORLD,damage_self);
                        playerSan.subSan(1);
                        event.setAmount(event.getAmount()*(2-_san*0.01f));
                    }
                    ModMessages.sendToPlayer(new SanDataSyncS2CPacket(playerSan.getSan()), player);
                });
            }
        }
        @SubscribeEvent
        public static void onHurt(LivingHurtEvent event){ }
        @SubscribeEvent
        public static void onAttack(LivingAttackEvent event){ }
        @SubscribeEvent
        public static void onUseItemFinish(LivingEntityUseItemEvent.Finish event) {
            if (event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(PlayerSanProvider.PLAYER_SAN).ifPresent(playerSan -> {
                    if (SanDeath(player, playerSan.getSan())) {
                        SanTags sanTags = new SanTags();
                        if (sanTags.isSanTags(event.getItem())){
                            if (sanTags.getType() == SanTags.TYPE_ADD){
                                playerSan.addSan(sanTags.getNumbers());
                            }
                            if (sanTags.getType() == SanTags.TYPE_SUB){
                                playerSan.subSan(sanTags.getNumbers());
                            }
                        }
                        ModMessages.sendToPlayer(new SanDataSyncS2CPacket(playerSan.getSan()), player);
                    }
                });
            }
        }
    }
    @Mod.EventBusSubscriber(modid = Cater.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents{
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event){
            event.put(ModEntityTypes.EQ.get(), EntityEQ.setAttributes());
            event.put(ModEntityTypes.D.get(), EntityD.setAttributes());
        }

    }



    ////////////////////////////////////////////////////////////////////////
    public static boolean SanDeath(Player player,int san){
        if (san <=0 ){
            player.hurt(DamageSource.OUT_OF_WORLD,player.getMaxHealth());
            return false;
        }
        if (san < 50){
            int lvl = 1;
            if (san<25){lvl = 2;}
            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS,200,lvl));
            player.addEffect(new MobEffectInstance(MobEffects.GLOWING,200,lvl));
            player.addEffect(new MobEffectInstance(MobEffects.UNLUCK,200,lvl));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,200,lvl));
            player.addEffect(new MobEffectInstance(MobEffects.JUMP,200,lvl));
            player.addEffect(new MobEffectInstance(MobEffects.HUNGER,200,lvl));
            player.addEffect(new MobEffectInstance(MobEffects.CONFUSION,200,lvl));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,200,lvl));
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,200,lvl));
        }
        return true;
    }
}
