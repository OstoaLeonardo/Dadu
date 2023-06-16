package com.example.dadu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ConverterFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Spinner spMedida1, spMedida2;
    private EditText etValor1, etValor2;
    private String[] medidas = {"Celsius", "Fahrenheit", "Kelvin"};

    public ConverterFragment() {
        // Required empty public constructor
    }

    public static ConverterFragment newInstance(String param1, String param2) {
        ConverterFragment fragment = new ConverterFragment();
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
        View view = inflater.inflate(R.layout.fragment_converter, container, false);

        spMedida1 = view.findViewById(R.id.spMedida1);
        spMedida2 = view.findViewById(R.id.spMedida2);
        etValor1 = view.findViewById(R.id.etValor1);
        etValor2 = view.findViewById(R.id.etValor2);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, medidas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMedida1.setAdapter(adapter);
        spMedida2.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                convertirUnidades();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        };

        spMedida1.setOnItemSelectedListener(itemSelectedListener);
        spMedida2.setOnItemSelectedListener(itemSelectedListener);

        etValor1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                convertirUnidades();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        return view;
    }

    private void convertirUnidades() {
        String medida1 = spMedida1.getSelectedItem().toString();
        String medida2 = spMedida2.getSelectedItem().toString();

        if(etValor1.getText().toString().isEmpty()) {
            etValor2.setText("");
            etValor2.setHint("0");
        } else {
            double valor1 = Double.parseDouble(etValor1.getText().toString());
            double valor2 = 0;

            switch (medida1) {
                case "Celsius":
                    switch (medida2) {
                        case "Celsius":
                            valor2 = valor1;
                            break;
                        case "Fahrenheit":
                            valor2 = (valor1 * 1.8) + 32;
                            break;
                        case "Kelvin":
                            valor2 = valor1 + 273.15;
                            break;
                    }
                    break;
                case "Fahrenheit":
                    switch (medida2) {
                        case "Celsius":
                            valor2 = (valor1 - 32) / 1.8;
                            break;
                        case "Fahrenheit":
                            valor2 = valor1;
                            break;
                        case "Kelvin":
                            valor2 = (valor1 + 459.67) * 5/9;
                            break;
                    }
                    break;
                case "Kelvin":
                    switch (medida2) {
                        case "Celsius":
                            valor2 = valor1 - 273.15;
                            break;
                        case "Fahrenheit":
                            valor2 = (valor1 * 9/5) - 459.67;
                            break;
                        case "Kelvin":
                            valor2 = valor1;
                            break;
                    }
                    break;
            }

            etValor2.setText(String.format("%.7f", valor2).replaceAll("\\.?0*$", ""));

            if (etValor2.getText().toString().endsWith(".0")) {
                etValor2.setText(etValor2.getText().toString().replace(".0", ""));
            }
        }
    }
}