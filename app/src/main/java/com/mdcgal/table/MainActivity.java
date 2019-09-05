package com.mdcgal.table;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TableLayout tableLayout;
    private EditText etName,etLastname;
    private Button btnAdd;
    private String[]header = {"Id","Nombre","Apellido"};
    private  ArrayList<String[]>rows = new ArrayList<>();
    private TableDynamic tableDynamic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tableLayout = (TableLayout) findViewById(R.id.TableLayout);
        etName = (EditText) findViewById(R.id.etName);
        etLastname = (EditText) findViewById(R.id.etLastname);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        tableDynamic = new TableDynamic(tableLayout,getApplicationContext());
        tableDynamic.addHeader(header);
        tableDynamic.addData(getClients());
        tableDynamic.backgroundHeader(Color.BLUE);
        tableDynamic.backgroundData(Color.GREEN,Color.GRAY);
        tableDynamic.lineColor(Color.BLACK);
        tableDynamic.textColorData(Color.WHITE);
        tableDynamic.textColorHeader(Color.MAGENTA);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[]item = new String[]{"N",etName.getText().toString(),etLastname.getText().toString()};
                tableDynamic.addItems(item);
            }
        });
    }

    private ArrayList<String[]> getClients(){
        rows.add(new String[]{"1","Pedro","Lopez"});
        rows.add(new String[]{"2","juan","Nuñez"});
        rows.add(new String[]{"3","Marcos","Guitierrez"});
        rows.add(new String[]{"4","Lucas","Lima"});
        rows.add(new String[]{"5","Diego","abc"});
        rows.add(new String[]{"6","fra","gty"});
        return rows;
    }
}
