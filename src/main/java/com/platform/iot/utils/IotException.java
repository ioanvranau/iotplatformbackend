package com.platform.iot.utils;

import java.util.Date;

/**
 * Created by ioan.vranau on 4/28/2016.
 */
public class IotException extends RuntimeException {

    public IotException(String message) {
        super(message);
    }

    public static void main(String[] args) {
        System.out.println(new Date().getYear() + 1900);
    }
}
