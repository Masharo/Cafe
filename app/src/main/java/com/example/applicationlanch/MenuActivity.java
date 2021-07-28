package com.example.applicationlanch;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class MenuActivity extends AppCompatActivity {

    public static final String ORDER = "ORDER";

    private RadioGroup typeDrink;
    private CheckBox checkboxLemon,
                     checkboxSugar,
                     checkboxMilk;
    private Spinner nameDrink;
    private TextView dop;
    private String name,
                   password,
                   drinkTypeStr;

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Objects.nonNull(getIntent());
        name = getIntent().getStringExtra(MainActivity.LOGIN);
        password = getIntent().getStringExtra(MainActivity.PASSWORD);

        TextView halloText = findViewById(R.id.textview_menu_hallo);
        halloText.setText(
                getString(R.string.text_main_hello_one) + " " +
                name + ", " +
                getString(R.string.text_main_hello_two));

        dop = findViewById(R.id.radiobutton_menu_dop);
        typeDrink = findViewById(R.id.group_tea_coffee);

        checkboxLemon = findViewById(R.id.checkbox_lemon);
        checkboxSugar = findViewById(R.id.checkbox_sugar);
        checkboxMilk = findViewById(R.id.checkbox_milk);

        nameDrink = findViewById(R.id.spinner_menu_type_drink);

        View tea = findViewById(R.id.radiobutton_menu_tea);
        onClickGroupTeaAndCoffee(tea);
    }

    @SuppressLint("ResourceType")
    public void onClickGroupTeaAndCoffee(View view) {
        String[] arrayString;

        if (view.getId() == R.id.radiobutton_menu_coffee) {
            drinkTypeStr = getString(R.string.text_radiobutton_menu_coffee).toLowerCase();
            checkboxLemon.setVisibility(View.INVISIBLE);
            checkboxLemon.setChecked(false);
            arrayString = getResources().getStringArray(R.array.type_drink_coffee);
        } else {
            drinkTypeStr = getString(R.string.text_radiobutton_menu_tea).toLowerCase();
            checkboxLemon.setVisibility(View.VISIBLE);
            arrayString = getResources().getStringArray(R.array.type_drink_tea);
        }

        dop.setText(String.format(getString(R.string.text_main_what_dop), drinkTypeStr));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item_adapter, arrayString);
        nameDrink.setAdapter(adapter);
    }

    public void onClickSendOrder(View view) {

        StringBuilder builder = new StringBuilder();

        builder.append(
                checkboxMilk.isChecked() ? getString(R.string.text_checkbox_menu_milk) + " " : ""
        ).append(
                checkboxLemon.isChecked() ? getString(R.string.text_checkbox_menu_lemon) + " " : ""
        ).append(
                checkboxSugar.isChecked() ? getString(R.string.text_checkbox_menu_sugar) + " " : ""
        );

        String order = String.format(getString(R.string.text_menu_order_drink),
                name, password, drinkTypeStr, nameDrink.getSelectedItem());
        String dop = builder.length() > 0 ?
                String.format(getString(R.string.text_menu_dop), builder.toString()) : "";

        String fullOrder = order + dop;

        Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra(ORDER, fullOrder);

        startActivity(intent);
    }
}