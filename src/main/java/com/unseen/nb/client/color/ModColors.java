package com.unseen.nb.client.color;

import com.unseen.nb.util.ModRand;
import net.minecraft.util.math.Vec3d;

public class ModColors {

    public static final Vec3d PURPLE = new Vec3d(0.882, 0, 1);



    public static Vec3d variateColor(Vec3d baseColor, float variance) {
        float f = ModRand.getFloat(variance);

        return new Vec3d((float) Math.min(Math.max(0, baseColor.x + f), 1),
                (float) Math.min(Math.max(0, baseColor.y + f), 1),
                (float) Math.min(Math.max(0, baseColor.z + f), 1));
    }
}
