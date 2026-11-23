package com.example.mid_exam_fares;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ArtworkDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_artwork_details);

        TextView tvDetails = findViewById(R.id.tvDetails);

        String title = getIntent().getStringExtra(
                MainActivity.EXTRA_TITLE
        );

        String artist = getIntent().getStringExtra(
                MainActivity.EXTRA_ARTIST
        );

        int year = getIntent().getIntExtra(
                MainActivity.EXTRA_YEAR, 0
        );

        tvDetails.setText(
                "Artwork Title: " + title +
                        "\nArtist: " + artist +
                        "\nYear: " + year
        );
    }
}