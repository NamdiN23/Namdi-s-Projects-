package com.example.mycontactlist;

import android.content.Context;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import org.jetbrains.annotations.Contract;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.SaveDateListener {
    private Contact currentContact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initListButton();
        initMapButton();
        initSettingButton();
        initToggleButton();
        initChangeDateButton();
        initTextChangedEvents();
        initSaveButton();
        setForEditing(false);
        currentContact = new Contact();
    }

    private void initListButton() {
        ImageButton IbList = findViewById(R.id.imageButtonList);
        IbList.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ContactListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    private void initMapButton() {
        ImageButton IbMap = findViewById(R.id.imageButtonMap);
        IbMap.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ContactMapActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }
    private void initSettingButton(){
        ImageButton IbSet = findViewById(R.id.imageButtonSetting);
        IbSet.setOnClickListener(view->{
            Intent intent = new Intent(MainActivity.this, ContactSettingActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }
    private void initToggleButton() {
        final ToggleButton editToggle = findViewById(R.id.toggleButtonEdit);
        editToggle.setOnClickListener(view -> setForEditing(editToggle.isChecked()));

    }

    private void initChangeDateButton(){
        Button changeDate = findViewById(R.id.buttonBdayButton);
        changeDate.setOnClickListener(view -> {
            FragmentManager fm = getSupportFragmentManager();
            DatePickerDialog datePickerDialog = new DatePickerDialog();
            datePickerDialog.show(fm, "DatePick");
        });
    }
    private void initSaveButton(){
        Button saveButton = findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(view -> {
            boolean wasSuccessful;
            ContactDataSource ds = new ContactDataSource(MainActivity.this);
            try {
                ds.open();
                if (currentContact.getContactID() == -1) {
                    wasSuccessful = ds.insertContact(currentContact);
                    if (wasSuccessful) {
                        int newId = ds.getLastContactID();
                        currentContact.setContactID(newId);
                    }
                }
                else {
                    wasSuccessful = ds.updateContact(currentContact);
                }
                ds.close();
            }
            catch (Exception e){
                wasSuccessful = false;
            }
            if (wasSuccessful) {
                ToggleButton editToggle = findViewById(R.id.toggleButtonEdit);
                editToggle.toggle();
                setForEditing(false);
                hideKeyboard();
            }

        });
    }
    private void initTextChangedEvents(){
        final EditText etContactName = findViewById(R.id.editTextName);
        etContactName.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                currentContact.setContactName(etContactName.getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });
        final EditText etStreetAddress = findViewById(R.id.editTextAddress);
        etStreetAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                currentContact.setStreetAddress(etStreetAddress.getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });
        final EditText etCity = findViewById(R.id.editTextCity);
        etCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                currentContact.setCity(etCity.getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });
        final EditText etState = findViewById(R.id.editTextState);
        etState.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                currentContact.setState(etState.getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });
        final EditText etZipcode = findViewById(R.id.editTextZipcode);
        etZipcode.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                currentContact.setZipcode(etZipcode.getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });
        final EditText etHomephone = findViewById(R.id.editTextHome);
        etHomephone.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                currentContact.setPhoneNumber(etHomephone.getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });
        final EditText etCellphone = findViewById(R.id.editTextCell);
        etCellphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                currentContact.setCellNumber(etCellphone.getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });
        final EditText etEmail = findViewById(R.id.editTextEmail);
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                currentContact.setEMail(etEmail.getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });
        etHomephone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        etCellphone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
    }
    private void setForEditing(boolean enabled){
        EditText editName = findViewById(R.id.editTextName);
        EditText editAddress = findViewById(R.id.editTextAddress);
        EditText editCity = findViewById(R.id.editTextCity);
        EditText editState = findViewById(R.id.editTextState);
        EditText editZip = findViewById(R.id.editTextZipcode);
        EditText editHomeP = findViewById(R.id.editTextHome);
        EditText editCell = findViewById(R.id.editTextCell);
        EditText editEmail = findViewById(R.id.editTextEmail);
        Button buttonChange = findViewById(R.id.buttonBdayButton);
        Button buttonSave = findViewById(R.id.buttonSave);

        editName.setEnabled(enabled);
        editAddress.setEnabled(enabled);
        editCity.setEnabled(enabled);
        editState.setEnabled(enabled);
        editZip.setEnabled(enabled);
        editHomeP.setEnabled(enabled);
        editCell.setEnabled(enabled);
        editEmail.setEnabled(enabled);
        buttonChange.setEnabled(enabled);
        buttonSave.setEnabled(enabled);

        if (enabled) {
            editName.requestFocus();
        }
        else {
            ScrollView s = findViewById(R.id.scrollView);
            s.fullScroll(ScrollView.FOCUS_UP);
        }

        }

    @Override
    public void didFinishDatePickerDialog(Calendar selectedTime) {
        TextView birthday = findViewById(R.id.textViewDate);
        birthday.setText(DateFormat.format("MM/dd/yyyy", selectedTime));
        currentContact.setBirthday(selectedTime);

    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        EditText editName = findViewById(R.id.editTextName);
        imm.hideSoftInputFromWindow(editName.getWindowToken(), 0);
        EditText editAddress = findViewById(R.id.editTextAddress);
        imm.hideSoftInputFromWindow(editAddress.getWindowToken(), 0);
        EditText editCity = findViewById(R.id.editTextCity);
        imm.hideSoftInputFromWindow(editCity.getWindowToken(), 0);
        EditText editState = findViewById(R.id.editTextState);
        imm.hideSoftInputFromWindow(editState.getWindowToken(), 0);
        EditText getZipcode = findViewById(R.id.editTextZipcode);
        imm.hideSoftInputFromWindow(getZipcode.getWindowToken(), 0);
        EditText getPhoneNumber = findViewById(R.id.editTextHome);
        imm.hideSoftInputFromWindow(getPhoneNumber.getWindowToken(), 0);
        EditText getCellNumber = findViewById(R.id.editTextCell);
        imm.hideSoftInputFromWindow(getCellNumber.getWindowToken(), 0);
        EditText getEmail = findViewById(R.id.editTextEmail);
        imm.hideSoftInputFromWindow(getEmail.getWindowToken(), 0);
    }



}




