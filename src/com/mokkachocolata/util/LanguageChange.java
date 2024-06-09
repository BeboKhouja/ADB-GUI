package com.mokkachocolata.util;

import com.mokkachocolata.enums.Languages;
import com.mokkachocolata.exception.JarNotFoundException;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class LanguageChange extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    Localization localization = new Localization();
    private JComboBox<String> LanguageSelector;
    private JLabel LanguageCurrent;
    private JLabel RestartText;

    private int choiceToLanguage(int index) {
        return switch (index) {
            case 0 -> Languages.EN_US;
            case 1 -> Languages.ID_ID;
            default -> 0;
        };
    }

    public LanguageChange() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        LanguageSelector.addItem("American English");
        LanguageSelector.addItem("Indonesian");
        buttonOK.setEnabled(false);
        RestartText.setVisible(false);
        LanguageSelector.addActionListener(e -> {
            if (LanguageSelector.getSelectedIndex() != localization.getCurrentLanguage()) {
                buttonOK.setEnabled(true);
                RestartText.setVisible(true);
            } else {
                buttonOK.setEnabled(false);
                RestartText.setVisible(false);
            }
        });

        LanguageCurrent.setText(localization.languageToString(localization.getCurrentLanguage()));
        buttonOK.addActionListener(e -> {
            try {
                onOK();
            } catch (JarNotFoundException | IOException | URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
        });

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() throws JarNotFoundException, URISyntaxException, IOException {
        // add your code here
        localization.setCurrentLanguageToPreferences(choiceToLanguage(LanguageSelector.getSelectedIndex()));
        System.exit(2);
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
