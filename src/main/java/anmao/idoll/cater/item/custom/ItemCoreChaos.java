package anmao.idoll.cater.item.custom;

import anmao.idoll.cater.ToApi;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ItemCoreChaos extends Item {
    public ItemCoreChaos(Properties p_41383_) {
        super(p_41383_);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        if (!p_41432_.isClientSide() && p_41434_ == InteractionHand.MAIN_HAND){
            if(ToApi.getRNwithLuck(p_41433_.getLuck(),0.6f)){
                p_41433_.sendSystemMessage(Component.literal("res is true"));
            }else {p_41433_.sendSystemMessage(Component.literal("res is false"));}
            p_41433_.getCooldowns().addCooldown(this,10);
        }
        return super.use(p_41432_, p_41433_, p_41434_);
    }
}