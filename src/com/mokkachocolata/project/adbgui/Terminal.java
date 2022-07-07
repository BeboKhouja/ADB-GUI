package com.mokkachocolata.project.adbgui;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

public class Terminal {
   /**
	   * Checks if the app is started from the terminal.
  	 *
	   * @return {@code true} If the app is started from the terminal, {@code false} otherwise.
     * @since 1.1
	 */
    public static boolean isOpenedInConsole(String[] args) {
    Console console = System.console();
      if (console != null || args.length > 0) {
    // The app was started from a terminal
    return true;
      }
    return false;
    }
    /**
	   * Checks if the app can execute that particular command.
  	 *
     * @param command
     *        The command.
	   * @return {@code true} If the app can execute command, {@code false} otherwise.
     * @throws IOException
     *         If the app cannot excecute the command
     * @throws IllegalArgumentException
     *         If the <b>command</b> parameter is empty.
     * @since 1.1
	 */
    @SuppressWarnings("all")
    public static boolean canExecuteCommand(String command) throws IOException {
      Process child = Runtime.getRuntime().exec(command);
      BufferedReader input = new BufferedReader(new InputStreamReader(
                child.getInputStream()), 13107200);
      String line = null;

      while ((line = input.readLine()) != null) {
        System.out.println(line);
    }
if (command == "") {
  throw new IllegalArgumentException("Command is empty");
}
      try {
        Runtime.getRuntime().exec(command);
        return true;
      } catch (Exception e) {
        e.printStackTrace();
        return false;
      }
      }
    }
