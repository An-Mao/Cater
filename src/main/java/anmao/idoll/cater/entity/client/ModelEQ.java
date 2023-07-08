package anmao.idoll.cater.entity.client;

import anmao.idoll.cater.Cater;
import anmao.idoll.cater.entity.custom.EntityEQ;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelEQ extends AnimatedGeoModel<EntityEQ> {
    @Override
    public ResourceLocation getModelResource(EntityEQ object) {
        return new ResourceLocation(Cater.MOD_ID,"geo/eq.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityEQ object) {
        return new ResourceLocation(Cater.MOD_ID,"textures/entity/eq_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityEQ animatable) {
        return new ResourceLocation(Cater.MOD_ID,"animations/eq.animation.json");
    }
}
