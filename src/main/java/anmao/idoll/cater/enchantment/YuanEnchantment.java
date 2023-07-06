package anmao.idoll.cater.enchantment;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class YuanEnchantment extends Enchantment {
    public YuanEnchantment(Rarity p_44676_, EnchantmentCategory p_44677_, EquipmentSlot... p_44678_) {
        super(p_44676_, p_44677_, p_44678_);
    }

    @Override
    public void doPostAttack(LivingEntity p_44686_, Entity p_44687_, int p_44688_) {
        if(!p_44686_.level.isClientSide){
            if(p_44687_ instanceof LivingEntity livingentity){
                livingentity.setHealth(0.0f);
            }
        }
        super.doPostAttack(p_44686_, p_44687_, p_44688_);
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }
}
