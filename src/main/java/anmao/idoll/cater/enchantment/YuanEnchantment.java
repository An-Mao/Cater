package anmao.idoll.cater.enchantment;

import anmao.idoll.cater.ToApi;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.stringtemplate.v4.ST;

import java.util.Objects;

public class YuanEnchantment extends Enchantment {
    public YuanEnchantment(Rarity p_44676_, EnchantmentCategory p_44677_, EquipmentSlot... p_44678_) {
        super(p_44676_, p_44677_, p_44678_);
    }

    @Override
    public void doPostAttack(LivingEntity p_44686_, Entity p_44687_, int p_44688_) {
        if(!p_44686_.level.isClientSide){
            //entity.minecraft.player

            if(p_44687_.getType() == EntityType.PLAYER ){
                if (p_44687_ instanceof LivingEntity livingentity){
                    if(livingentity.getHealth() > 1.0f ){
                        float x = p_44686_.getMainHandItem().getMaxDamage();
                        float a = livingentity.getMaxHealth();
                        float b = (a-x) * 0.3f;
                        if (livingentity.getHealth() > b && a < x) {
                            livingentity.setHealth(b);
                        } else {
                            livingentity.hurt(DamageSource.OUT_OF_WORLD,b);
                        }
                        //
                        //
                        if(ToApi.getRNwithLuck(1.0f,0.03f)){
                            livingentity.setHealth(1.0f);
                        } else if (ToApi.getRNwithLuck(1.0f,0.001f)) {
                            livingentity.setHealth(0.0f);
                        }
                    }
                }
            } else if (p_44686_.getType() == EntityType.PLAYER) {
                p_44686_.setHealth(0.0f);
            }
        }
        super.doPostAttack(p_44686_, p_44687_, p_44688_);
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }
}
