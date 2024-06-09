package com.mokkachocolata.util;

import com.mokkachocolata.enums.Languages;
import com.mokkachocolata.exception.JarNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Localization {
    Util util = new Util();
    /**
     * Gets the {@code currentLanguage}. <br>
     * @return The current language
     */
    public int getCurrentLanguage() {
        return currentLanguage;
    }

    /**
     * Sets the current language. <br>
     * <b>Warning: </b> Not recommended to be used.
     * @param language
     */
    public void setCurrentLanguage(int language) {
        this.currentLanguage = currentLanguage;
    }

    /**
     * Sets the current language, but this time also saves it to the preferences file.
     * @param language
     */
    public void setCurrentLanguageToPreferences(int language) throws JarNotFoundException, URISyntaxException, IOException {
        File preferences = new File(new File(util.getJarLocation()).getParentFile().getPath() + "\\preferences.json");
        if (!preferences.exists()) {
            preferences.createNewFile();
        }
        JSONObject preferencesJson = new JSONObject(Files.readAllLines(preferences.toPath()));
        preferencesJson.put("language", languageToString(language));
        FileWriter preferencesWrite = new FileWriter(preferences);
        preferencesWrite.write(preferencesJson.toString());
        preferencesWrite.close();
    }

    private int currentLanguage = Languages.EN_US; // this is the default
    private String getLocalizedJson() throws IOException {

        InputStream iStream = getClass().getClassLoader().getResourceAsStream("localizedtexts.json");

        if (iStream == null) {
            throw new IOException("Resource not found");
        }

        byte[] bytes = iStream.readAllBytes();
        return new String(bytes);
    }
    private @NotNull List<String> getKeys() throws IOException, URISyntaxException {
        JSONArray keys = new JSONArray(getLocalizedJson());
        List<String> list = new ArrayList<String>();
        for(int i = 0; i < keys.length(); i++){
            list.add(keys.getJSONObject(i).getString("key"));
        }
        return list;
    }
    public String languageToString(int language) {
        return switch (language) {
            case Languages.EN_US -> "en_us";
            case Languages.ID_ID -> "id_id";
            default -> null;
        };
    }
    public String getLocalizedText(String key) throws IOException {
        // First stage: Finding the keys
        JSONArray localizedTexts = new JSONArray(getLocalizedJson());
        ArrayList<Integer> indexes = new ArrayList<>();
        for(int i = 0; i < localizedTexts.length(); i++) {
            if (localizedTexts.getJSONObject(i).getString("key").equals(key)) {
                // then this is the index(es)
                indexes.add(i);
            }
        }
        // Second stage: Filtering by language
        int index = 0;
        for (Integer integer : indexes) {
            if (localizedTexts.getJSONObject(integer).getString("language").equals(languageToString(currentLanguage))) {
                // Finally, we got the array we needed!
                index = integer;
                break;
            }
        }
        // Third and final stage: Getting the string
        return localizedTexts.getJSONObject(index).getString("string");
        // And, that's it!
    }
}
