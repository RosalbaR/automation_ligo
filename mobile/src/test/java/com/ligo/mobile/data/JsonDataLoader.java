package com.ligo.mobile.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class JsonDataLoader {
    public static List<PurchaseScenario> loadPurchases(String resourcePath) {
        try (InputStream inputStream = JsonDataLoader.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("No se encontró el recurso JSON: " + resourcePath);
            }
            Type listType = new TypeToken<List<PurchaseScenario>>() {}.getType();
            return new Gson().fromJson(new InputStreamReader(inputStream, StandardCharsets.UTF_8), listType);
        } catch (Exception e) {
            throw new RuntimeException("Error leyendo el JSON de datos: " + resourcePath, e);
        }
    }
}
