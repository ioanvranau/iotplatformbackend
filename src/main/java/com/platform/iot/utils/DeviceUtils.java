package com.platform.iot.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by ioan.vranau on 10/3/2016.
 */
public class DeviceUtils {

    public static long randLong(int min, int max) {

        return ThreadLocalRandom.current().nextLong((max - min) + 1) + min;
    }
}
