package com.mdcgal.table;

import android.content.Context;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class TableDynamic {
    private TableLayout tableLayout;
    private Context context;
    private String[]header;
    private ArrayList<String[]>data;
    private TableRow tableRow;
    private TextView txtCell;
    private int indexC,indexR;
    private boolean multicolor = false;
    int firstColor, secondColor;

    public TableDynamic(TableLayout tableLayout, Context context){
        this.tableLayout = tableLayout;
        this.context = context;
    }

    public void addHeader(String[]header){
        this.header = header;
        createHeader();
    }

    public void addData(ArrayList<String[]>data){
        this.data = data;
        System.out.println("TAMANIO: " + data.size());
        CreateDataTable();
    }

    private void newRow(){
        tableRow = new TableRow(context);
    }

    private void newCell(){
        txtCell = new TextView(context);
        txtCell.setGravity(Gravity.CENTER);
        txtCell.setTextSize(25);
    }

    private void createHeader(){
        indexC = 0;
        newRow();
        while (indexC < header.length){
            newCell();
            System.out.println(header[indexC]);
            txtCell.setText(header[indexC++]);
            tableRow.addView(txtCell,newTableRowParams());
        }
        tableLayout.addView(tableRow);
    }

    private void CreateDataTable(){
        String info;
        for (indexR = 1; indexR <= data.size(); indexR++){
            newRow();
            for (indexC = 0; indexC < header.length; indexC++){
                newCell();
                String[] row = data.get(indexR-1);
                info =  (indexC < row.length) ? row[indexC]:"";
                txtCell.setText(info);
                tableRow.addView(txtCell,newTableRowParams());
            }
            tableLayout.addView(tableRow);
        }
    }

    public void addItems(String[]item){
        String info;
        data.add(item);
        indexC = 0;
        newRow();
        while (indexC < header.length){
            newCell();
            if (item[indexC] != "N")
                info = (indexC < item.length)? item[indexC]:"";
            else
                info = String.valueOf(data.size());
            indexC++;
            txtCell.setText(info);
            tableRow.addView(txtCell,newTableRowParams());
        }
        tableLayout.addView(tableRow,data.size());
        reColoring();
    }

    public void backgroundHeader(int color){
        indexC = 0;
        while (indexC < header.length){
            txtCell = getCell(0,indexC++);
            txtCell.setBackgroundColor(color);
        }
//        tableLayout.addView(tableRow);
    }

    public void backgroundData (int firstColor, int secondColor){
        for (indexR = 1; indexR <= data.size(); indexR++){
            multicolor =!multicolor;
            for (indexC = 0; indexC < header.length; indexC++){
                txtCell = getCell(indexR,indexC);
                txtCell.setBackgroundColor((multicolor) ? firstColor : secondColor);
            }
//            tableLayout.addView(tableRow);
        }
        this.firstColor = firstColor;
        this.secondColor = secondColor;
    }

    public void lineColor(int color){
        indexR = 0;
        while (indexR < data.size()+1)
            getRow(indexR++).setBackgroundColor(color);
    }

    public void textColorData(int color){
        for (indexR = 1; indexR <= header.length; indexR++)
            for (indexC = 0; indexC < header.length; indexC++)
                getCell(indexR,indexC).setTextColor(color);
    }

    public void textColorHeader(int color){
        indexC = 0;
        while (indexC < header.length)
            getCell(0,indexC++).setTextColor(color);
    }

    public void reColoring(){
        indexC = 0;
        multicolor =!multicolor;
        while (indexC < header.length){
            txtCell = getCell(data.size(),indexC++);
            txtCell.setBackgroundColor((multicolor) ? firstColor : secondColor);
        }
    }

    private  TableRow getRow(int index){
        return (TableRow) tableLayout.getChildAt(index);
    }

    private  TextView getCell(int rowIndex, int columnIndex){
        tableRow = getRow(rowIndex);
        return (TextView) tableRow.getChildAt(columnIndex);
    }

    private TableRow.LayoutParams newTableRowParams(){
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.setMargins(1,1,1,1);
        params.weight = 1;
        return params;
    }
}
