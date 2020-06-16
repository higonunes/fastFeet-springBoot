package com.fastfeet.Services.util;

import java.util.Random;

public class GenerateID {

    public static String generateValue() {
        return Integer.toHexString(new Random().nextInt());
    }
}
