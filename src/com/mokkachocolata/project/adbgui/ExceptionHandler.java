package com.mokkachocolata.project.adbgui;

import com.mokkachocolata.exception.JarNotFoundException;
import com.mokkachocolata.util.AddonLoader;
import io.sentry.Sentry;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.net.URISyntaxException;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    private final Logger logger = LogManager.getLogger(ExceptionHandler.class);
    private static int getVersion() {
        String version = System.getProperty("java.version");
        if(version.startsWith("1.")) {
            version = version.substring(2, 3);
        } else {
            int dot = version.indexOf(".");
            if(dot != -1) { version = version.substring(0, dot); }
        } return Integer.parseInt(version);
    }

    public void uncaughtException(Thread thread, Throwable exception) {
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setThreshold(Level.FATAL);

        Sentry.captureException(exception);
        logger.error("Reported exception thrown at thread " + thread.getName() + '!');
        exception.printStackTrace();
        try {
            if (new AddonLoader().getModsFolder().exists()) {
                logger.info("The details as explained below is shown.\nJava version " + getVersion() + "\nMods path: " + new AddonLoader().getModsFolder().toPath());
            }
        } catch (JarNotFoundException | URISyntaxException ignored) {
        }
        System.exit(-1);
        // MessageBox message = new MessageBox(new Shell(), SWT.OK);
        // message.setMessage("An error has occurred. The application will now terminate.");
        // int chosen = message.open();
        // if (chosen == SWT.OK) {
        //   System.exit(0);
        // }
    }
}
