package com.chinmay.udacityad1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtilities {

    private static final String NAME = "name";
    private static final String KNOWNAS = "alsoKnownAs";
    private static final String MAIN_Name = "mainName";
    private static final String ORIGIN = "placeOfOrigin";
    private static final String DESC = "description";
    private static final String IMG = "image";
    private static final String INGREDIENTS = "ingredients";

    public static SandwichData parseSandwichJson(String json) {
        try {
            JSONObject sandwitch_object = new JSONObject(json);
            JSONObject Nome = sandwitch_object.getJSONObject(NAME);
            String mainName = Nome.getString(MAIN_Name);
            JSONArray alsoKnownAsJsonArray = Nome.getJSONArray(KNOWNAS);
            List<String> alsoKnownAs = new ArrayList<>();
            for (int i = 0; i < alsoKnownAsJsonArray.length(); i++) {
                alsoKnownAs.add(alsoKnownAsJsonArray.getString(i));
            }
            String placeOfOrigin = sandwitch_object.getString(ORIGIN);
            String description = sandwitch_object.getString(DESC);
            String image = sandwitch_object.getString(IMG);
            JSONArray jsonIngredientsArray = sandwitch_object.getJSONArray(INGREDIENTS);
            List<String> ingredients = new ArrayList<>();
            for (int j = 0; j < jsonIngredientsArray.length(); j++) {
                ingredients.add(jsonIngredientsArray.getString(j));
            }

            return new SandwichData(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);


        } catch (JSONException except) {
            except.printStackTrace();
            return null;

        }
    }
}
