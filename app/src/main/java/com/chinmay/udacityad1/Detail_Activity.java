package com.chinmay.udacityad1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Detail_Activity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra position";
    private static final int DEFAULT_POSITION = -1;
    String alsoknownas = "";
    TextView first;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        SandwichData sandwich = JsonUtilities.parseSandwichJson(json);
        if (sandwich == null) {

            closeOnError();
            return;
        }

        populateUI(sandwich);

        Picasso.get()
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(SandwichData sandwich) {

        first = findViewById(R.id.also_known_tv);
        if (sandwich.getAlsoKnownAs() != null) {
            for (String alsoKnownAs : sandwich.getAlsoKnownAs()) {
                if (sandwich.getAlsoKnownAs() != null) {
                    alsoknownas = alsoknownas.concat(alsoKnownAs.concat("\n"));
                }
            }
            if (!alsoknownas.equals("")) {
                first.setText(getResources().getString(R.string.detail_also_known_as_label) + " " + alsoknownas.substring(0, alsoknownas.lastIndexOf('\n')));

            }
        }

        TextView originTextView = findViewById(R.id.origin_tv);
        originTextView.setText(getResources().getString(R.string.detail_place_of_origin_label) + " " + sandwich.getPlaceOfOrigin());


        TextView ingredientsTextView = findViewById(R.id.ingredients_tv);
        String emptyIngredients = "";
        if (sandwich.getIngredients() != null) {
            for (String ingredient : sandwich.getIngredients()) {
                if (sandwich.getIngredients() != null) {
                    emptyIngredients = emptyIngredients.concat(ingredient.concat("\n"));
                }
            }
            if (!emptyIngredients.isEmpty()) {
                ingredientsTextView.setText(getResources().getString(R.string.detail_ingredients_label) + " " + emptyIngredients.substring(0, emptyIngredients.lastIndexOf('\n')));
            }
        }

        TextView descriptionTextView = findViewById(R.id.description_tv);
        descriptionTextView.setText(getResources().getString(R.string.detail_description_label) + " " + sandwich.getDescription());
    }
}
