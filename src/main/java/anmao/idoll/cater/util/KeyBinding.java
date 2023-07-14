package anmao.idoll.cater.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATER_CATEGORY = "key.category.cater.idoll";
    public static final String KEY_CATER_ADD_SAN = "key.cater.add_san";
    public static final KeyMapping ADDSAN_KEY = new KeyMapping(KEY_CATER_ADD_SAN, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_0,KEY_CATER_CATEGORY);
}
