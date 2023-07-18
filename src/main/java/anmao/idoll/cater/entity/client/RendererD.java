package anmao.idoll.cater.entity.client;

import anmao.idoll.cater.Cater;
import anmao.idoll.cater.entity.custom.EntityD;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RendererD extends GeoEntityRenderer<EntityD> {
    public RendererD(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ModelD());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(EntityD animatable) {
        return new ResourceLocation(Cater.MOD_ID,"textures/entity/d_texture.png");
    }

    @Override
    public RenderType getRenderType(EntityD animatable, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        poseStack.scale(1.2f,1.2f,1.2f);
        return super.getRenderType(animatable, partialTick, poseStack, bufferSource, buffer, packedLight, texture);
    }
}
