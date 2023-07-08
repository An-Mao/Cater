package anmao.idoll.cater.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.living.AnimalTameEvent;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.core.manager.InstancedAnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class EntityEQ extends Monster implements IAnimatable {
    protected static final AnimationBuilder ANIM_WALK = new AnimationBuilder().addAnimation("move.walk", ILoopType.EDefaultLoopTypes.LOOP);
    protected static final AnimationBuilder ANIM_IDLE = new AnimationBuilder().addAnimation("idle.idle", ILoopType.EDefaultLoopTypes.LOOP);
    protected static final AnimationBuilder ANIM_ATK_ATTACK = new AnimationBuilder().addAnimation("atk.attack", ILoopType.EDefaultLoopTypes.PLAY_ONCE);
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);//new AnimationFactory(this);
    public EntityEQ(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
    }

    public static AttributeSupplier setAttributes(){
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH,35.00)
                .add(Attributes.ATTACK_DAMAGE,7.0f)
                .add(Attributes.ATTACK_SPEED,0.5f)
                .add(Attributes.MOVEMENT_SPEED,0.7f).build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1,new FloatGoal(this));
        this.goalSelector.addGoal(2,new MeleeAttackGoal(this,1.2d,false));
        this.goalSelector.addGoal(4,new WaterAvoidingRandomStrollGoal(this,1.0d));
        this.goalSelector.addGoal(5,new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(2,new NearestAttackableTargetGoal<>(this, Player.class,true));
        this.targetSelector.addGoal(3,new NearestAttackableTargetGoal<>(this, AbstractVillager.class,false));
        this.targetSelector.addGoal(3,new NearestAttackableTargetGoal<>(this, IronGolem.class,true));
        this.targetSelector.addGoal(4,new NearestAttackableTargetGoal<>(this, Creeper.class,true));
    }

    private <E extends EntityEQ> PlayState predicate(final AnimationEvent<E> event){
        if (event.isMoving()){
            event.getController().setAnimation(ANIM_WALK);
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(ANIM_IDLE);
        return PlayState.CONTINUE;
    }
    @Override
    public void registerControllers(final AnimationData data) {
        data.addAnimationController(new AnimationController<>(this,"Controller",0,this::predicate));
        data.addAnimationController(new AnimationController<>(this,"AttackController",0,this::attackPredicate));

    }

    private <T extends EntityEQ> PlayState attackPredicate(final AnimationEvent<T> event) {
        if (this.swinging && event.getController().getAnimationState().equals(AnimationState.Stopped)){
            event.getController().markNeedsReload();
            event.getController().setAnimation(ANIM_ATK_ATTACK);
            this.swinging = false;
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
    protected void playStepSound(BlockPos pos, BlockState blockIn){
        this.playSound(SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES,0.15f,1.0f);
    }
    protected SoundEvent getAmbientSound(){return SoundEvents.CAT_STRAY_AMBIENT;}
    protected SoundEvent getHurtSound(DamageSource damageSourceIn){return SoundEvents.DOLPHIN_HURT;}
    protected SoundEvent getDeathSound(){return SoundEvents.DOLPHIN_DEATH;}
    protected float getSoundVolume(){return 0.2f;}
}
