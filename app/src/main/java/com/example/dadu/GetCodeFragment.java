package com.example.dadu;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GetCodeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GetCodeFragment extends Fragment {

    TextInputEditText txtMail;
    Button btnCode, btnRecovery, btnCancel;
    View mView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GetCodeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GetCode.
     */
    // TODO: Rename and change types and number of parameters
    public static GetCodeFragment newInstance(String param1, String param2) {
        GetCodeFragment fragment = new GetCodeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_get_code, container, false);
        txtMail = mView.findViewById(R.id.tiMail);
        btnCode = mView.findViewById(R.id.btnCode);
        btnRecovery = mView.findViewById(R.id.btnRecovery);
        btnCancel = mView.findViewById(R.id.btnCancelar);

        btnCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtMail.getText().toString().isEmpty()){
                    mostrarError("Falta ingresar el correo electronico");
                    return;
                }
                nextFragment();
            }
        });

        btnRecovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtMail.getText().toString().isEmpty()){
                    mostrarError("Falta ingresar el correo electronico");
                    return;
                }
                validarUsuario("https://daduappmovil.000webhostapp.com/generateCode.php");
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return mView;
    }

    private void mostrarError(String msg){
        Snackbar snackbar = Snackbar.make(mView, msg, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }



    private void validarUsuario(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                nextFragment();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mostrarError(error.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("usuario", txtMail.getText().toString());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    private void nextFragment(){
        Bundle data = new Bundle();
        data.putString("user", txtMail.getText().toString());

        getActivity().getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerRP, ValidateCodeFragment.class, data)
                .commit();
    }
}