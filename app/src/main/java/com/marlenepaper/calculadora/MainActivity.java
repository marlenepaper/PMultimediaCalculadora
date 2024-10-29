package com.marlenepaper.calculadora;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

//        Button button0=findViewById(R.id.button0);
//        Button button1=findViewById(R.id.button1);
//        Button button2=findViewById(R.id.button2);
//        Button button3=findViewById(R.id.button3);
//        Button button4=findViewById(R.id.button4);
//        Button button5=findViewById(R.id.button5);
//        Button button6=findViewById(R.id.button6);
//        Button button7=findViewById(R.id.button7);
//        Button button8=findViewById(R.id.button8);
//        Button button9=findViewById(R.id.button9);
//        Button buttonMu=findViewById(R.id.buttonMu);
//        Button buttonComma=findViewById(R.id.buttonComma);
//        Button buttonSubstract=findViewById(R.id.buttonSubstract);
//        Button buttonResult=findViewById(R.id.buttonResult);
//        Button buttonNegative=findViewById(R.id.buttonNegative);
//        Button buttonDivide=findViewById(R.id.buttonDivide);
//        Button buttonMultiply=findViewById(R.id.buttonMultiply);
    }

}