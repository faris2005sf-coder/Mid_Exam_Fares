package com.example.mid_exam_fares;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvCounter, tvTitle, tvArtist;
    private ImageView ivArtwork;
    private Button btnPrevious, btnNext;

    private int currentArtwork = 0;

    private static final String STATE_ARTWORK = "current_artwork";

    public static final String EXTRA_TITLE = "artworkTitle";
    public static final String EXTRA_ARTIST = "artworkArtist";
    public static final String EXTRA_YEAR = "artworkYear";

    private final String[] titles = {
            "Starry Night",
            "Mona Lisa",
            "The Scream",
            "The Persistence of Memory",
            "Girl with a Pearl Earring",
            "The Great Wave"
    };

    private final String[] artists = {
            "Vincent van Gogh",
            "Leonardo da Vinci",
            "Edvard Munch",
            "Salvador Dali",
            "Johannes Vermeer",
            "Katsushika Hokusai"
    };

    private final int[] years = {
            1889, 1503, 1893, 1931, 1665, 1831
    };

    private final int[] images = {
            R.drawable.starry_night,
            R.drawable.mona_lisa,
            R.drawable.the_scream,
            R.drawable.persistence_of_memory,
            R.drawable.girl_with_pearl_earring,
            R.drawable.the_great_wave
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Connect XML views
        tvCounter = findViewById(R.id.tvCounter);
        tvTitle = findViewById(R.id.tvTitle);
        tvArtist = findViewById(R.id.tvArtist);

        ivArtwork = findViewById(R.id.ivArtwork);

        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);

        // Restore the current artwork after screen rotation
        if (savedInstanceState != null) {
            currentArtwork = savedInstanceState.getInt(
                    STATE_ARTWORK, 0
            );
        }

        // Display the current artwork
        updateArtwork();

        // Previous button
        btnPrevious.setOnClickListener(v -> {
            if (currentArtwork > 0) {
                currentArtwork--;
                updateArtwork();
            }
        });

        // Next button
        btnNext.setOnClickListener(v -> {
            if (currentArtwork < titles.length - 1) {
                currentArtwork++;
                updateArtwork();
            }
        });

        // Open artwork details when image is clicked
        ivArtwork.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    ArtworkDetailsActivity.class
            );

            intent.putExtra(EXTRA_TITLE,
                    titles[currentArtwork]);

            intent.putExtra(EXTRA_ARTIST,
                    artists[currentArtwork]);

            intent.putExtra(EXTRA_YEAR,
                    years[currentArtwork]);

            startActivity(intent);
        });
    }

    // Update all artwork information
    private void updateArtwork() {

        tvCounter.setText(
                (currentArtwork + 1) + " / " + titles.length
        );

        ivArtwork.setImageResource(
                images[currentArtwork]
        );

        tvTitle.setText(
                titles[currentArtwork]
        );

        tvArtist.setText(
                artists[currentArtwork] + " - " +
                        years[currentArtwork]
        );

        // Enable or disable navigation buttons
        btnPrevious.setEnabled(currentArtwork > 0);

        btnNext.setEnabled(
                currentArtwork < titles.length - 1
        );
        // Update button colors
        updateButtonColors();
    }

    private void updateButtonColors() {

        int blue = Color.parseColor("#4169C9");
        int gray = Color.parseColor("#DCE5FA");

        int white = Color.WHITE;
        int disabledText = Color.parseColor("#777777");

        btnPrevious.setBackgroundTintList(
                ColorStateList.valueOf(
                        btnPrevious.isEnabled() ? blue : gray
                )
        );

        btnNext.setBackgroundTintList(
                ColorStateList.valueOf(
                        btnNext.isEnabled() ? blue : gray
                )
        );

        btnPrevious.setTextColor(
                btnPrevious.isEnabled() ? white : disabledText
        );

        btnNext.setTextColor(
                btnNext.isEnabled() ? white : disabledText
        );
    }

    // Save current artwork when screen configuration changes
    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putInt(STATE_ARTWORK, currentArtwork);

        super.onSaveInstanceState(outState);
    }
}