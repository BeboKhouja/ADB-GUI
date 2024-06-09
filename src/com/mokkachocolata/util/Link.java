package com.mokkachocolata.util;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * The {@code Link} class is a utility which houses the {@code OpenLink()} function.
 * @since 1.4.0
 * @author Bebo Khouja
 */
public class Link {
    /**
     * Opens a link from the specified string.
     * @param link
     *        String containing the URL.
     * @since 1.4.0
     * @author Bebo Khouja
     */
    public void OpenLink(String link) {
        int dialog = JOptionPane.showConfirmDialog(new JPanel(), "Are you sure you want to open "+link+"?", "Open link", JOptionPane.YES_NO_OPTION);
        if (dialog == JOptionPane.YES_OPTION) {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(new URI(link));
                } catch (IOException | URISyntaxException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
