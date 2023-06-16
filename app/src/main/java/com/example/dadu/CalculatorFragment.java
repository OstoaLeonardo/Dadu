package com.example.dadu;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.mozilla.javascript.Scriptable;

public class CalculatorFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView tvNumeros, tvResultado;
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnPunto, btnIgual, btnSuma, btnResta, btnProducto, btnDivision, btnPorcentaje, btnPar1, btnPar2, btnLimpiar, btnBack;
    private String resultado;
    boolean puntoColocado = false;

    public CalculatorFragment() {
        // Required empty public constructor
    }

    public static CalculatorFragment newInstance(String param1, String param2) {
        CalculatorFragment fragment = new CalculatorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);

        tvNumeros = view.findViewById(R.id.tvNumeros);
        tvResultado = view.findViewById(R.id.tvResultado);
        btn0 = view.findViewById(R.id.btn0);
        btn1 = view.findViewById(R.id.btn1);
        btn2 = view.findViewById(R.id.btn2);
        btn3 = view.findViewById(R.id.btn3);
        btn4 = view.findViewById(R.id.btn4);
        btn5 = view.findViewById(R.id.btn5);
        btn6 = view.findViewById(R.id.btn6);
        btn7 = view.findViewById(R.id.btn7);
        btn8 = view.findViewById(R.id.btn8);
        btn9 = view.findViewById(R.id.btn9);
        btnPunto = view.findViewById(R.id.btnPunto);
        btnIgual = view.findViewById(R.id.btnIgual);
        btnSuma = view.findViewById(R.id.btnSuma);
        btnResta = view.findViewById(R.id.btnResta);
        btnProducto = view.findViewById(R.id.btnProducto);
        btnDivision = view.findViewById(R.id.btnDivision);
        btnPorcentaje = view.findViewById(R.id.btnPorcentaje);
        btnPar1 = view.findViewById(R.id.btnPar1);
        btnPar2 = view.findViewById(R.id.btnPar2);
        btnLimpiar = view.findViewById(R.id.btnLimpiar);
        btnBack = view.findViewById(R.id.btnBack);

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressButton(v);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressButton(v);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressButton(v);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressButton(v);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressButton(v);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressButton(v);
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressButton(v);
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressButton(v);
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressButton(v);
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressButton(v);
            }
        });

        btnPunto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressButton(v);
            }
        });

        btnIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressIgual(v);
            }
        });

        btnSuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressButton(v);
            }
        });

        btnResta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressButton(v);
            }
        });

        btnProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressButton(v);
            }
        });

        btnDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressButton(v);
            }
        });

        btnPorcentaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressButton(v);
            }
        });

        btnPar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressButton(v);
            }
        });

        btnPar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressButton(v);
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressDelete(v);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressBack(v);
            }
        });

        return view;
    }

    public void pressButton(View view) {
        switch (view.getId()) {
            case R.id.btn0:
                tvNumeros.setText(tvNumeros.getText() + "0");
                calcular(view);
                break;
            case R.id.btn1:
                tvNumeros.setText(tvNumeros.getText() + "1");
                calcular(view);
                break;
            case R.id.btn2:
                tvNumeros.setText(tvNumeros.getText() + "2");
                calcular(view);
                break;
            case R.id.btn3:
                tvNumeros.setText(tvNumeros.getText() + "3");
                calcular(view);
                break;
            case R.id.btn4:
                tvNumeros.setText(tvNumeros.getText() + "4");
                calcular(view);
                break;
            case R.id.btn5:
                tvNumeros.setText(tvNumeros.getText() + "5");
                calcular(view);
                break;
            case R.id.btn6:
                tvNumeros.setText(tvNumeros.getText() + "6");
                calcular(view);
                break;
            case R.id.btn7:
                tvNumeros.setText(tvNumeros.getText() + "7");
                calcular(view);
                break;
            case R.id.btn8:
                tvNumeros.setText(tvNumeros.getText() + "8");
                calcular(view);
                break;
            case R.id.btn9:
                tvNumeros.setText(tvNumeros.getText() + "9");
                calcular(view);
                break;
            case R.id.btnSuma:
                if(tvNumeros.getText().toString().matches(".*\\d$") || tvNumeros.getText().toString().endsWith(")") || tvNumeros.getText().toString().endsWith("-")) {
                    tvNumeros.setText(tvNumeros.getText() + "+");
                    puntoColocado = false;
                    calcular(view);
                }
                break;
            case R.id.btnResta:
                if(tvNumeros.getText().toString().matches(".*\\d$") || tvNumeros.getText().toString().isEmpty() || tvNumeros.getText().toString().endsWith("+") || tvNumeros.getText().toString().endsWith("×") || tvNumeros.getText().toString().endsWith("÷") || tvNumeros.getText().toString().endsWith("(") || tvNumeros.getText().toString().endsWith(")")) {
                    tvNumeros.setText(tvNumeros.getText() + "-");
                    puntoColocado = false;
                    calcular(view);
                }
                break;
            case R.id.btnProducto:
                if(tvNumeros.getText().toString().matches(".*\\d$") || tvNumeros.getText().toString().endsWith(")")) {
                    tvNumeros.setText(tvNumeros.getText() + "×");
                    puntoColocado = false;
                    calcular(view);
                }
                break;
            case R.id.btnDivision:
                if(tvNumeros.getText().toString().matches(".*\\d$") || tvNumeros.getText().toString().endsWith(")")) {
                    tvNumeros.setText(tvNumeros.getText() + "÷");
                    puntoColocado = false;
                    calcular(view);
                }
                break;
            case R.id.btnPunto:
                if (tvNumeros.getText().toString().matches(".*\\d$") && (!puntoColocado || tvNumeros.getText().toString().endsWith("."))) {
                    String texto = tvNumeros.getText().toString();
                    String[] numeros = texto.split("\\+|-|\\*|/");
                    String ultimoNumero = numeros[numeros.length - 1];

                    if (!ultimoNumero.contains(".")) {
                        tvNumeros.setText(texto + ".");
                        puntoColocado = true;
                        calcular(view);
                    }
                }
                break;
            case R.id.btnPorcentaje:
                if(tvNumeros.getText().toString().matches(".*\\d$")) {
                    tvNumeros.setText(tvNumeros.getText() + "%");
                    calcular(view);
                }
                break;
            case R.id.btnPar1:
                if(!tvNumeros.getText().toString().endsWith(".")) {
                    tvNumeros.setText(tvNumeros.getText() + "(");
                    calcular(view);
                }
                break;
            case R.id.btnPar2:
                if(tvNumeros.getText().toString().matches(".*\\d$") || tvNumeros.getText().toString().endsWith(")")) {
                    tvNumeros.setText(tvNumeros.getText() + ")");
                    calcular(view);
                }
                break;
        }
    }

    public void calcular(View view) {
        if(!tvNumeros.getText().toString().isEmpty()) {
            String expresion = tvNumeros.getText().toString();
            resultado = calcularExpresion(expresion);
            tvResultado.setText(resultado);

            if (tvResultado.getText().toString().endsWith(".0")) {
                tvResultado.setText(tvResultado.getText().toString().replace(".0", ""));
            }

            int currentTheme = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
            if(tvResultado.getText().toString() == "Infinity") {
                tvResultado.setText("No se puede dividir por 0");
                if(currentTheme == Configuration.UI_MODE_NIGHT_YES) {
                    tvResultado.setTextColor(getResources().getColor(R.color.md_theme_dark_error));
                    tvNumeros.setTextColor(getResources().getColor(R.color.md_theme_dark_error));
                } else {
                    tvResultado.setTextColor(getResources().getColor(R.color.md_theme_light_error));
                    tvNumeros.setTextColor(getResources().getColor(R.color.md_theme_light_error));
                }
            } else {
                if(currentTheme == Configuration.UI_MODE_NIGHT_YES) {
                    tvResultado.setTextColor(getResources().getColor(R.color.md_theme_dark_onBackground));
                    tvNumeros.setTextColor(getResources().getColor(R.color.md_theme_dark_onBackground));
                } else {
                    tvResultado.setTextColor(getResources().getColor(R.color.md_theme_light_onBackground));
                    tvNumeros.setTextColor(getResources().getColor(R.color.md_theme_light_onBackground));
                }
            }
        }
    }

    public String calcularExpresion(String expresion) {
        resultado = "";

        try {
            expresion = expresion.replaceAll("×", "*");
            expresion = expresion.replaceAll("%", "/100");
            expresion = expresion.replaceAll("÷", "/");
            expresion = expresion.replaceAll("\\)\\(", ")*(");
            expresion = expresion.replaceAll("(\\d+)\\(", "$1*(");
            expresion = expresion.replaceAll("(\\))(\\d+)", "$1*$2");

            org.mozilla.javascript.Context rhino = org.mozilla.javascript.Context.enter();
            rhino.setOptimizationLevel(-1);

            Scriptable scriptable = rhino.initStandardObjects();
            resultado = rhino.evaluateString(scriptable, expresion, "Javascript", 1, null).toString();

            if(resultado.length() > 16) {
                resultado = resultado.substring(0, 16);
            }
        }catch (Exception e){
            tvResultado.setText("Error");
        }
        return resultado;
    }

    public void pressIgual(View view) {
        if(!tvResultado.getText().toString().isEmpty()) {
            tvNumeros.setText(tvResultado.getText().toString());
            tvResultado.setText("");
        }
    }

    public void pressDelete(View view) {
        tvNumeros.setText("");
        tvResultado.setText("");
    }

    public void pressBack(View view) {
        if(!tvNumeros.getText().toString().isEmpty()) {
            tvNumeros.setText(tvNumeros.getText().toString().substring(0, tvNumeros.length() - 1));
            calcular(view);
        }

        if (tvNumeros.getText().toString().trim().isEmpty()) {
            tvResultado.setText("");
        }
    }
}