// Copyright (C) 2022 Bebo Khouja

package com.mokkachocolata.util;
public class ExceptionHandler {
    
    /** 
     * @param r
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
