package com.mobops.one;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    protected static final String EXTRA_NAMES = "com.mobops.EXTRA_NAMES";
    private EditText editTextEnterName;
    private ArrayList<String> listNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextEnterName = findViewById(R.id.enterName);
        listNames = new ArrayList<>();
    }

    public void onAddButtonPressed(View v) {
        v.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP);
        String name = String.valueOf(editTextEnterName.getText());
        if (name.equals("")) {
            Toast.makeText(this, R.string.nothing_to_add, Toast.LENGTH_SHORT).show();
        } else {
            listNames.add(name);
            Toast.makeText(this, R.string.name_added, Toast.LENGTH_SHORT).show();
        }
        editTextEnterName.setText(null);
    }

    public void onShowAllButtonPressed(View v) {
        v.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP);
        final Intent intent = new Intent(this, DisplayNamesActivity.class);
        intent.putExtra(EXTRA_NAMES, listNames);
        startActivity(intent);
    }
}
