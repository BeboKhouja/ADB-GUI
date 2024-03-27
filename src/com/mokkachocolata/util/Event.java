package com.mokkachocolata.util;

import java.util.concurrent.Callable;

/**
 * The {@code Event} class is a class used to create events in Java.
 * @author Bebo Khouja
 * @since 1.5.0
 */
public class Event {
    private Callable<Void>[] func;
    private ArrayUtillity array;

    /**
     * Executes the provided function when called.
     * This function is called when the {@code fireEvent()} is called.
     * @param functions
     *        The function to execute.
     * @since 1.5.0
     */
    @SafeVarargs
    public final void onFire(Callable<Void>... functions) {
        array.addToArrayList(functions);
        func = (Callable<Void>[]) array.toArray();
    }
    /**
     * Calls the {@code onFire()} function when this function is called.
     * @since 1.5.0
     */
    public void fireEvent() throws Exception {
        for(Callable<Void> functions : func) {
            functions.call();
        }
    }
}
