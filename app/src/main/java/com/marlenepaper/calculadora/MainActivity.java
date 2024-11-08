package com.marlenepaper.calculadora;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.math.BigDecimal;
import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    private StringBuilder inputExpresionMatematica = new StringBuilder(); //Aqui voy a almacenar/construir la secuencia de números y operadores
    private TextView impresionDeExpresionMatematica,impresionResultado; //Aqui voy a mostrar la expresion y el resultado (son tipo TextView)
    private double resultado=0;
    private boolean esElUltimoInputOperador=false; //no se ha ingresado un operador
    private boolean esElUltimoInputPunto=false; //no se ha ingresado un punto
    private int exponente = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Configuracion de inserts para la pantalla completa
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Esto hace que esten declarados y tengan relacion con mi codigo
        impresionDeExpresionMatematica=findViewById(R.id.textViewExpresionMatematica);
        impresionResultado=findViewById(R.id.textViewResultado);
        Button button0=findViewById(R.id.button0);
        Button button1=findViewById(R.id.button1);
        Button button2=findViewById(R.id.button2);
        Button button3=findViewById(R.id.button3);
        Button button4=findViewById(R.id.button4);
        Button button5=findViewById(R.id.button5);
        Button button6=findViewById(R.id.button6);
        Button button7=findViewById(R.id.button7);
        Button button8=findViewById(R.id.button8);
        Button button9=findViewById(R.id.button9);
        Button buttonPunto=findViewById(R.id.buttonPunto);
        Button buttonDividir=findViewById(R.id.buttonDividir);
        Button buttonMultiplicar=findViewById(R.id.buttonMultiplicar);
        Button buttonRestar=findViewById(R.id.buttonRestar);
        Button buttonSumar=findViewById(R.id.buttonSumar);
        Button buttonAC=findViewById(R.id.buttonAC);
        Button buttonIgual=findViewById(R.id.buttonIgual);

        //Esto hace que al dar click se asigne el valor o haga la operacion
        button0.setOnClickListener(v-> crearExpresion("0"));
        button1.setOnClickListener(v-> crearExpresion("1"));
        button2.setOnClickListener(v-> crearExpresion("2"));
        button3.setOnClickListener(v-> crearExpresion("3"));
        button4.setOnClickListener(v-> crearExpresion("4"));
        button5.setOnClickListener(v-> crearExpresion("5"));
        button6.setOnClickListener(v-> crearExpresion("6"));
        button7.setOnClickListener(v-> crearExpresion("7"));
        button8.setOnClickListener(v-> crearExpresion("8"));
        button9.setOnClickListener(v-> crearExpresion("9"));
        buttonPunto.setOnClickListener(v-> crearExpresion("."));
        buttonSumar.setOnClickListener(v-> crearExpresion("+"));
        buttonRestar.setOnClickListener(v-> crearExpresion("-"));
        buttonMultiplicar.setOnClickListener(v-> crearExpresion("*"));
        buttonDividir.setOnClickListener(v-> crearExpresion("/"));
        buttonAC.setOnClickListener(v-> limpiarExpresionMatematica());
        buttonIgual.setOnClickListener(v -> calcularResultado());

    }


    private void limpiarExpresionMatematica(){
        inputExpresionMatematica.setLength(0);
        impresionDeExpresionMatematica.setText("");
        impresionResultado.setText("0");
    }

    private void crearExpresion(String input){

        if (input.matches("[0-9]")){
            esElUltimoInputOperador = false;
            esElUltimoInputPunto=false;
            inputExpresionMatematica.append(input);

            String[] cifra = inputExpresionMatematica.toString().split("[+\\-*/]");
            String ultimaCifra = cifra[cifra.length - 1];

            if (ultimaCifra.length() > 9) {
                BigDecimal numero = new BigDecimal(ultimaCifra);

                DecimalFormat formato = new DecimalFormat("0.###E0");
                String numeroFormateado = formato.format(numero);

                inputExpresionMatematica.setLength(inputExpresionMatematica.length() - ultimaCifra.length());
                inputExpresionMatematica.append(numeroFormateado);
            }
            impresionDeExpresionMatematica.setText(inputExpresionMatematica.toString());

        } else if (input.matches("[+\\-*/]")){

            if (!esElUltimoInputOperador) { //se ha ingresado un operador
                esElUltimoInputOperador = true;
                esElUltimoInputPunto=true;
                inputExpresionMatematica.append(input);
            } else {
                inputExpresionMatematica.delete(inputExpresionMatematica.length() - 1, inputExpresionMatematica.length());
                inputExpresionMatematica.append(input);
            }

            impresionDeExpresionMatematica.setText(inputExpresionMatematica.toString());
        } else if (input.equals(".")) {

            String[] partes = inputExpresionMatematica.toString().split("[+\\-*/]");
            String ultimoNumero = partes[partes.length - 1];

            if (!esElUltimoInputPunto && !ultimoNumero.contains(".")) { //se ha ingresado un operador y un punto
                esElUltimoInputOperador = true;
                esElUltimoInputPunto = true;
                inputExpresionMatematica.append(input);
            }
            impresionDeExpresionMatematica.setText(inputExpresionMatematica.toString());
        }
    }


    private void calcularResultado() {
        if (!esElUltimoInputOperador) {
            try {
                String exp = inputExpresionMatematica.toString();
                exp = exp.replace(",", ".");

                double resultado = evaluarExpresion(exp);
                String resultadoConExponente;

                BigDecimal resultadoBD = new BigDecimal(resultado);

                if (resultadoBD.toString().length() > 9) {
                    DecimalFormat formato = new DecimalFormat("0.###E0");
                    resultadoConExponente = formato.format(resultadoBD);
                } else {
                    resultadoConExponente = String.format("%.2f", resultadoBD.doubleValue());
                }

                impresionResultado.setText(resultadoConExponente);
                inputExpresionMatematica.setLength(0);
                inputExpresionMatematica.append(resultadoConExponente);
                impresionDeExpresionMatematica.setText(resultadoConExponente);

            } catch (Exception e) {
                impresionResultado.setText("Error");
            }
        } else {
            impresionResultado.setText("Error");
        }
    }


    private double evaluarExpresion(String exp) {
        Expression expression = new ExpressionBuilder(exp).build();
        return expression.evaluate();
    }


}