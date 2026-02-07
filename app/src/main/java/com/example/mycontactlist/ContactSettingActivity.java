package com.example.mycontactlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ContactSettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact_setting);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initListButton();

        initMapButton();

        initSettingButton();

        initSettings();

        initSortByClick();

        initSortOrderClick();
    }


    private void initListButton() {
        ImageButton IbList = findViewById(R.id.imageButtonList);
        IbList.setOnClickListener(view -> {
            Intent intent = new Intent(ContactSettingActivity.this, ContactListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    private void initMapButton() {
        ImageButton IbMap = findViewById(R.id.imageButtonMap);
        IbMap.setOnClickListener(view -> {
            Intent intent = new Intent(ContactSettingActivity.this, ContactMapActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    private void initSettingButton() {
        ImageButton IbSet = findViewById(R.id.imageButtonSetting);
        IbSet.setOnClickListener(view -> {
            Intent intent = new Intent(ContactSettingActivity.this, ContactSettingActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    private void initSettings() {
        String sortBy = getSharedPreferences("MyContactListPreferences",
                Context.MODE_PRIVATE).getString("sortfield", "contactname");
        String sortOrder = getSharedPreferences("MyContactListPreferences",
                Context.MODE_PRIVATE).getString("sortorder", "ASC");

        RadioButton rbName = findViewById(R.id.radioButtonName);
        RadioButton rbCity = findViewById(R.id.radioButtonCity);
        RadioButton rbBday = findViewById(R.id.radioButtonBday);

        if (sortBy.equalsIgnoreCase("contactname")) {
            rbName.setChecked(true);
        } else if (sortBy.equalsIgnoreCase("city")) {
            rbCity.setChecked(true);
        } else {
            rbBday.setChecked(true);
        }

        RadioButton rbAscending = findViewById(R.id.radioButtonAscending);
        RadioButton rbDescending = findViewById(R.id.radioButtonDescending);

        if (sortBy.equalsIgnoreCase("ASC")) {
            rbAscending.setChecked(true);
        } else {
            rbDescending.setChecked(true);
        }

    }

    private void initSortByClick() {
        RadioGroup rgSort = findViewById(R.id.radioGroupSortBy);
        rgSort.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull RadioGroup group, int checkedId) {
                RadioButton rbName = findViewById(R.id.radioButtonName);
                RadioButton rbCity = findViewById(R.id.radioButtonCity);

                if (rbName.isChecked()) {
                    getSharedPreferences("MyContactListPreferences",
                            Context.MODE_PRIVATE).edit()
                            .putString("sortfield", "name").apply();
                } else if (rbCity.isChecked()) {
                    getSharedPreferences("MyContactListPreferences",
                            Context.MODE_PRIVATE).edit()
                            .putString("sortfield", "city").apply();
                } else {
                    getSharedPreferences("MyContactListPreferences",
                            Context.MODE_PRIVATE).edit()
                            .putString("sortfield", "birthday").apply();
                }
            }
        });
    }
    private void initSortOrderClick(){
        RadioGroup rgSortBy = findViewById(R.id.radioGroupSortOrder);
        rgSortBy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull RadioGroup group, int checkedId) {
             RadioButton rbAscending = findViewById(R.id.radioButtonAscending);
             if (rbAscending.isChecked()){
                 getSharedPreferences("MyContactListPreferences",
                         Context.MODE_PRIVATE).edit()
                         .putString("sortorder", "ASC").apply();
             }
             else {
                 getSharedPreferences("MyContactListPreferences",
                         Context.MODE_PRIVATE).edit()
                         .putString("sortorder", "DESC").apply();
             }
            }
        });
    }
}

