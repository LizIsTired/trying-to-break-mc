package net.LizIsTired.SinCosSwap.mixin;

import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MathHelper.class)
public class MathHelperMixin {
    @Shadow
    private static final float[] SINE_TABLE = Util.make(new float[65536], (sineTable) -> {
        for (int i = 0; i < sineTable.length; ++i) {
            sineTable[i] = (float) Math.sin((double) i * 3.141592653589793D * 2.0D / 65536.0D);
        }

    });

    /**
     * @author
     */
    @Overwrite
    public static float sin(float value) {
        return SINE_TABLE[(int) (value * 10430.378F + 16384.0F) & '\uffff'];
    }

    /**
     * @author
     */
    @Overwrite
    public static float cos(float value) {
        return SINE_TABLE[(int) (value * 10430.378F) & '\uffff'];
    }

}
