package anmao.idoll.cater.client;

import anmao.idoll.cater.Cater;
import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class SanHudOverlay {
    private static final ResourceLocation SAN_FILLED = new ResourceLocation(Cater.MOD_ID,"textures/san/san_filled.png");
    private static final ResourceLocation SAN_EMPTY = new ResourceLocation(Cater.MOD_ID,"textures/san/san_empty.png");
    private static final ResourceLocation SAN_VIEW = new ResourceLocation(Cater.MOD_ID,"textures/san/sanv.png");
    public static final IGuiOverlay SAN_HUD = ((gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        int x = screenWidth / 2;
        int y = screenHeight;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);
        RenderSystem.setShaderTexture(0,SAN_EMPTY);
        for (int i = 0; i<10; i++){
            GuiComponent.blit(poseStack,x - 94 + (i*9),y - 54,0,0,12,12,12,12);
        }
        RenderSystem.setShaderTexture(0,SAN_FILLED);

        int _san = ClientSanData.getSan();
        for (int i = 0; i<10; i++){
            if (_san>(i*10)){
                GuiComponent.blit(poseStack,x-94+i*9,y-54,0,0,12,12,12,12);
            }else {
                break;
            }
        }
        //RenderSystem.setShader(GameRenderer::getPositionShader);
        if (_san < 50)
        {
            int i_san = 55 - _san;
            RenderSystem.setShaderTexture(0,SAN_VIEW);
            RenderSystem.setShaderColor(255f,0f,0f,0.016f *(i_san) );
            GuiComponent.blit(poseStack,0,0,0,0,0, screenWidth, screenHeight, screenWidth, screenHeight);
            if (_san < 25) {
                RenderSystem.setShaderFogShape(FogShape.SPHERE);
                RenderSystem.setShaderFogColor(100.0f, 0, 0);
                RenderSystem.setShaderFogStart(6.0f);
                RenderSystem.setShaderFogEnd(10.0f);
            }
        }
        //GuiComponent.enableScissor(80,255,0,0);

        //RenderSystem.setShader(GameRenderer::getPositionColorShader);
        //RenderSystem.setShaderColor(255.0f,0,0,0);
        //GuiComponent.fill(poseStack,0,0,screenWidth,screenHeight, 0);
        //GuiComponent.blit(poseStack,0,0,0,0,screenWidth,screenHeight,screenWidth,screenHeight);
    });
}
