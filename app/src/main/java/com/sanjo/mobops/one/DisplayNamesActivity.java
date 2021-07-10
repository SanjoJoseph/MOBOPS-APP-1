package com.mobops.one;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class DisplayNamesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_names);
        getSupportActionBar().setTitle(R.string.names);
        final ArrayList<String> listNames = getIntent().getStringArrayListExtra(MainActivity.EXTRA_NAMES);
        if (listNames == null || listNames.isEmpty()) {
            findViewById(R.id.view_nothing_to_show).setVisibility(View.VISIBLE);
        } else {
            final LinearLayout layout = (LinearLayout) findViewById(R.id.display_names_layout);
            final LayoutParams layoutParams = new LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
            final Handler handler = new Handler(Looper.getMainLooper());
            final ExecutorService executorService = Executors.newSingleThreadExecutor();
            final float textSize = getResources().getDimension(R.dimen.show_name_text_size);
            layoutParams.setMargins(0, getResources().getDimensionPixelSize(
                    R.dimen.margin_show_name), 0, 0);
            executorService.submit(() -> {
                for (String name: listNames) {
                    TextView view = new TextView(this);
                    view.setGravity(Gravity.CENTER);
                    view.setText(name);
                    view.setTextSize(textSize);
                    handler.post(() -> layout.addView(view, layoutParams));
                }
            });
            executorService.shutdown();
        }
    }
}
