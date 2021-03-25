package com.rahuls.myarea;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NewCountryActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.countrylistsql.REPLY";

    private EditText mEditCountryView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_country);
        mEditCountryView = findViewById(R.id.edit_word);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditCountryView.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String word = mEditCountryView.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY, word);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}