package com.example.dadu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class MyAccount extends AppCompatActivity {
    private static final int REQUEST_IMAGE = 1;
    private String imageName;
    LinearLayout Imagen;
    TextInputEditText txtName, txtAge, txtMail, txtPass, txtCPass;
    ImageView profileImage;
    int resourceId = R.drawable.avatar;
    private Bitmap imagenSeleccionada;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        txtName = findViewById(R.id.tlName).findViewById(R.id.tiName);
        txtAge = findViewById(R.id.tlAge).findViewById(R.id.tiAge);
        txtMail = findViewById(R.id.tlMail).findViewById(R.id.tiMail);
        txtPass = findViewById(R.id.tlPass).findViewById(R.id.tiPass);
        txtCPass = findViewById(R.id.tlCPass).findViewById(R.id.tiCPass);
        profileImage = findViewById(R.id.profileImage);
        Imagen = findViewById(R.id.LayuImagen);

        Button btnCancel = findViewById(R.id.btnCancel);
        Button btnUnlock = findViewById(R.id.btnRegisterB);
        Button btnlock = findViewById(R.id.btnRegister);

        // Bloquear EditText
        Imagen.setEnabled(false);
        txtName.setEnabled(false);
        txtName.setTextAppearance(R.style.LockedTextInputEditTextStyle);
        txtAge.setEnabled(false);
        txtAge.setTextAppearance(R.style.LockedTextInputEditTextStyle);
        txtMail.setEnabled(false);
        txtMail.setTextAppearance(R.style.LockedTextInputEditTextStyle);
        txtPass.setEnabled(false);
        txtPass.setTextAppearance(R.style.LockedTextInputEditTextStyle);
        txtCPass.setEnabled(false);
        txtCPass.setTextAppearance(R.style.LockedTextInputEditTextStyle);



        SharedPreferences sharedPreferences1 = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String imageUriString = sharedPreferences1.getString("imageUri", "");


        // Mostrar la imagen en el ImageView si existe una ruta válida
       /* if (!imageUriString.isEmpty()) {
            Uri imageUri = Uri.parse(imageUriString);
            profileImage.setImageURI(imageUri);
        } else {
            int resourceId = R.drawable.avatar; // Reemplaza "default_image" con el identificador de tu imagen predeterminada
            Drawable drawable = getDrawable(resourceId);
            profileImage.setImageDrawable(drawable);
        }*/



        SharedPreferences sharedPreferences = getSharedPreferences("MiSharedPreferences", Context.MODE_PRIVATE);
        String textoGuardado = sharedPreferences.getString("texto_guardado", "");

        txtMail.setText(textoGuardado);

        TopBarManager topBar = new TopBarManager();
        topBar.setupTopBar(this);
        topBar.setNavigationIcon(
                ContextCompat.getDrawable(this, R.drawable.arrow_small_left),
                view -> onBackPressed()
        );
        Imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_IMAGE);
            }
        });
        btnCancel.setOnClickListener(v -> onBackPressed());

        btnUnlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtName.setEnabled(true);
                txtAge.setEnabled(true);
                txtMail.setEnabled(true);
                txtPass.setEnabled(true);
                txtCPass.setEnabled(true);
                Imagen.setEnabled(true);
                btnUnlock.setVisibility(View.GONE);
                btnlock.setVisibility(View.VISIBLE);
            }
        });
    }

    private void registrarUsuario(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.isEmpty()) {
                    Intent intent = new Intent(MyAccount.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MyAccount.this, "Usuario Actualizado correctamente", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MyAccount.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nombre", txtName.getText().toString());
                params.put("edad", txtAge.getText().toString());
                params.put("usuario", txtMail.getText().toString());
                params.put("password", txtPass.getText().toString());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void UpdateUser(View view) {
        if (txtName.getText().toString().isEmpty()) {
            mostrarError("No se ha ingresado el usuario");
            return;
        }
        if (txtAge.getText().toString().isEmpty()) {
            mostrarError("No se ha ingresado la edad");
            return;
        }
        if (txtMail.getText().toString().isEmpty()) {
            mostrarError("No se ha ingresado el correo");
            return;
        }
        if (txtPass.getText().toString().isEmpty()) {
            mostrarError("No se ha ingresado la contraseña");
            return;
        }
        if (txtCPass.getText().toString().isEmpty()) {
            mostrarError("No se ha ingresado la confirmación de la contraseña");
            return;
        }
        if (!txtPass.getText().toString().equals(txtCPass.getText().toString())) {
            mostrarError("Las contraseñas no coinciden");
            return;
        }

        registrarUsuario("https://daduappmovil.000webhostapp.com/actu_usuario.php");
    }

    private void mostrarError(String msg) {
        View view = findViewById(android.R.id.content);
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    public void selectImage(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();

            try {
                Bitmap imagenSeleccionada = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                profileImage.setImageBitmap(imagenSeleccionada);

                // Guardar la ruta de la imagen en SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("imageUri", selectedImageUri.toString());
                editor.commit();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


