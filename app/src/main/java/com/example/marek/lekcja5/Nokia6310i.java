package com.example.marek.lekcja5;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by Marek on 03.04.2017.
 */
public class Nokia6310i extends AppCompatActivity {

    private ListView lv;
    private String[] pho;
    private void initResources() {
        Resources res = getResources();
        pho = res.getStringArray(R.array.nokia);
    }

    private void initLanguagesListView(){
        lv.setAdapter(new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1,pho));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nokia6310i);
        lv = (ListView) findViewById(R.id.nokia);
        initResources();
        initLanguagesListView();

        Button lista = (Button) findViewById(R.id.button2);

        lista.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context;
                context = getApplicationContext();
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
