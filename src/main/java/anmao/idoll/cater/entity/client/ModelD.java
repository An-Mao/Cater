package anmao.idoll.cater.entity.client;

import anmao.idoll.cater.Cater;
import anmao.idoll.cater.entity.custom.EntityD;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelD extends AnimatedGeoModel<EntityD> {
    @Override
    public ResourceLocation getModelResource(EntityD object) {
        return new ResourceLocation(Cater.MOD_ID,"geo/d.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityD object) {
        return new ResourceLocation(Cater.MOD_ID,"textures/entity/d_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityD animatable) {
        return new ResourceLocation(Cater.MOD_ID,"animations/d.animation.json");
    }
}
