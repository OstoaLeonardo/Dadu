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
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import androidx.annotation.NonNull;
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
    private static final int REQUEST_IMAGE = 100;
    private String imageName;
    LinearLayout Imagen;
    TextInputEditText txtName, txtAge, txtMail, txtPass, txtCPass;
    ImageView profileImage;
    int resourceId = R.drawable.avatar;
    private Bitmap imagenSeleccionada;
    private static final String PREF_IMAGE_PATH = "image_path";

    private String imagePath;
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

        mostrarImagenGuardada();

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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            if (imageUri != null) {
                try {
                    // Guardar la imagen en la memoria interna
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    String filename = "profile_image.jpg";
                    File file = new File(getFilesDir(), filename);
                    FileOutputStream fos = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.close();

                    // Guardar la ruta de la imagen en las SharedPreferences
                    imagePath = file.getAbsolutePath();
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(PREF_IMAGE_PATH, imagePath);
                    editor.apply();

                    // Mostrar la imagen en el ImageView
                    profileImage.setImageURI(imageUri);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Error al guardar la imagen", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void mostrarImagenGuardada() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        imagePath = preferences.getString(PREF_IMAGE_PATH, "");
        if (!imagePath.isEmpty()) {
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                Uri imageUri = Uri.fromFile(imageFile);
                profileImage.setImageURI(imageUri);
            }
        }
    }


}


