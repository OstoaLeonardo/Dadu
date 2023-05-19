package com.example.dadu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    EditText txtName, txtMail, txtPass, txtCPass, txtEdad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtMail = findViewById(R.id.txtMail);
        txtPass = findViewById(R.id.txtPass);
        txtCPass = findViewById(R.id.txtCPass);
        txtName = findViewById(R.id.txtName);
        txtEdad = findViewById(R.id.txtAge);
    }

    private void registrarUsuario(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()){
                    Intent intent = new Intent(Register.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(Register.this, "Usuario creado correctamente", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Register.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nombre", txtName.getText().toString());
                params.put("edad", txtEdad.getText().toString());
                params.put("usuario", txtMail.getText().toString());
                params.put("password", txtPass.getText().toString());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void RegisterUser(View view){
        if(txtName.getText().toString().isEmpty()) {
            mostrarError("No se ha ingresado el usuario");
            return;
        }
        if(txtEdad.getText().toString().isEmpty()) {
            mostrarError("No se ha ingresado la edad");
            return;
        }
        if(txtMail.getText().toString().isEmpty()) {
            mostrarError("No se ha ingresado el correo");
            return;
        }
        if(txtPass.getText().toString().isEmpty()) {
            mostrarError("No se ha ingresado la contraseña");
            return;
        }
        if(txtCPass.getText().toString().isEmpty()) {
            mostrarError("No se ha ingresado la confirmación de la contraseña");
            return;
        }
        if(!txtPass.getText().toString().equals(txtCPass.getText().toString())) {
            mostrarError("Las contraseñas no coinsiden");
            return;
        }

        registrarUsuario("https://daduappmovil.000webhostapp.com/registrar_usuario.php");
    }

    private void mostrarError(String msg){
        View view = findViewById(android.R.id.content);
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    public void back(View view){
        finish();
    }
}