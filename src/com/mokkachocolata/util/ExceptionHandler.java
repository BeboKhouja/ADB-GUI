// Copyright (C) 2023 Bebo Khouja

package com.mokkachocolata.util;
public class ExceptionHandler {
    
    /**
     */
    static void ignoreExc(Runnable r) {
        try {
            r.run();
        } catch (Exception ignored) { }
    }
    
    @FunctionalInterface
    interface Runnable {
        void run() throws Exception;
    }
}
