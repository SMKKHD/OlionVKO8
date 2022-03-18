package com.example.homework8;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TimeUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {
    private Spinner spinner1, spinner2;
    private Dispenser dispenser = new Dispenser();
    private SeekBar seekBar;
    private ArrayList<String> bList = new ArrayList<>();
    private ArrayList<String> sList = new ArrayList<>();
    private ArrayList <Bottle> bottleList = new ArrayList<Bottle>();
    Context context;
    TextView text, bottles, sizes, money;
    Button addMoney, cashOut;
    String s;
    View v;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner1 = findViewById(R.id.spinner3);
        text = findViewById(R.id.textView2);
        text.setText("Please add money first!");
        spinner2 = findViewById(R.id.spinner5);

        bottles = findViewById(R.id.id_bottles);
        sizes = findViewById(R.id.id_sizes);
        money = findViewById(R.id.id_money);

        addMoney = findViewById(R.id.button);
        cashOut = findViewById(R.id.button2);

        seekBar = findViewById(R.id.seekBar);
        context = MainActivity.this;



        bList.add("Coke");
        bList.add("Pepsi");
        bList.add("Fanta");
        bList.add("Jaffa");
        bList.add("Muumi");

        sList.add("1.0");
        sList.add("0.75");
        sList.add("0.5");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                money.setText("" + i + "€");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        spinner1.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, bList));
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                bottles.setText(adapterView.getItemAtPosition(i).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, sList));
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sizes.setText(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void onClickAddMoney(View v){
       int dep = seekBar.getProgress();
       dispenser.setBalance(dep);
       text.setText("Balance: " + dispenser.getBalance());
       seekBar.setProgress(0);

    }
    public void onClickBuyBottle(View v){
        Bottle botl = new Bottle();
        botl.setPrice(Double.parseDouble(sList.get(spinner2.getSelectedItemPosition())));
        double size = Double.parseDouble(sizes.getText().toString());
        bottleList = dispenser.getBottleList();

        if(bottleList.get(spinner1.getSelectedItemPosition()).getPrice() > dispenser.getBalance()){
            text.setText("Add more balance!");
        }
        else {
            // Jos valitun pullon nimi ja koko EI löytyy pulloslistasta, pulloa ei voida ostaa
            if(bottleList.get(spinner1.getSelectedItemPosition()).getSize() != Double.parseDouble(sList.get(spinner2.getSelectedItemPosition())) || bottleList.get(spinner1.getSelectedItemPosition()).getPrice() == 0.0){
                text.setText("Valitsemasi pullo on loppunut\nValitse toinen tuote!");

            }
            else{
                dispenser.setBalance(- bottleList.get(spinner1.getSelectedItemPosition()).getPrice());
                String s = "Bottle: " + bottleList.get(spinner1.getSelectedItemPosition()).getName() + "\nSize: " + bottleList.get(spinner1.getSelectedItemPosition()).getSize() + "\nPrice: "+bottleList.get(spinner1.getSelectedItemPosition()).getPrice() + "€\n" + "Remaining balance: "+ dispenser.getBalance() + "€".toString();
                text.setText(s);
                receiptToFile(s);
                dispenser.buyBottle(spinner1.getSelectedItemPosition());

                //dispenser.setBalance();
                }
            }
        }


    public void cashOut(View v){
        if(dispenser.getBalance() == 0.0){
            text.setText("No balance to cashout!");

        }
        else{
            text.setText("Cash out: " +  dispenser.getBalance());
            dispenser.cashOut();
        }

    }

    public void receiptToFile(String s){
        try{
            String file = "kuitti.txt"; // Tiedosto, johon laitetaan kuitti
            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput(file, Context.MODE_PRIVATE));
            ows.write(s);
            ows.close();
            System.out.println(s);
            System.out.println("Kuitin löytää tiedostosta: "+"'" +file +"'\nSijainnista: "+ context.getFilesDir());
        } catch (IOException e) {
            Log.e("IOExecption", "Virhe Syötteessä");
        }
    }
}




