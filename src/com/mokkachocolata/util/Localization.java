package com.mokkachocolata.util;

import com.mokkachocolata.enums.Languages;
import org.jetbrains.annotations.NotNull;
import org.json.*;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Localization {
    public int getCurrentLanguage() {
        return currentLanguage;
    }

    public void setCurrentLanguage(int language) {
        this.currentLanguage = currentLanguage;
    }

    public int currentLanguage = Languages.EN_US; // this is the default
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
        String stringLanguage = null;
        switch(language){
            case Languages.EN_US:
                stringLanguage = "en_us";
                break;
            case Languages.ID_ID:
                stringLanguage = "id_id";
                break;
        }
        return stringLanguage;
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
        for(int i = 0; i < indexes.size(); i++) {
            if(localizedTexts.getJSONObject(indexes.get(i)).getString("language").equals(languageToString(currentLanguage))) {
                // Finally, we got the array we needed!
                index = indexes.get(i);
                break;
            }
        }
        // Third and final stage: Getting the string
        return localizedTexts.getJSONObject(index).getString("string");
        // And, that's it!
    }
}
