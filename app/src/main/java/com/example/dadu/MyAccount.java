package com.example.dadu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class MyAccount extends AppCompatActivity {
    private static final int REQUEST_IMAGE = 100;
    private String imageName;
    TextInputEditText txtName, txtAge, txtMail, txtPass, txtCPass;
    ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        txtName = findViewById(R.id.tlName).findViewById(R.id.tiName);
        txtAge = findViewById(R.id.tlAge).findViewById(R.id.tiAge);
        txtMail = findViewById(R.id.tlMail).findViewById(R.id.tiMail);
        txtPass = findViewById(R.id.tlPass).findViewById(R.id.tiPass);
        txtCPass = findViewById(R.id.tlCPass).findViewById(R.id.tiCPass);
        Button btnCancel = findViewById(R.id.btnCancel);
        Button btnUnlock = findViewById(R.id.btnRegisterB);
        Button btnlock = findViewById(R.id.btnRegister);
        profileImage = findViewById(R.id.profileImage);

        // Bloquear EditText
        txtName.setEnabled(false);
        txtName.setTextAppearance(this, R.style.LockedTextInputEditTextStyle);
        txtAge.setEnabled(false);
        txtAge.setTextAppearance(this, R.style.LockedTextInputEditTextStyle);
        txtMail.setEnabled(false);
        txtMail.setTextAppearance(this, R.style.LockedTextInputEditTextStyle);
        txtPass.setEnabled(false);
        txtPass.setTextAppearance(this, R.style.LockedTextInputEditTextStyle);
        txtCPass.setEnabled(false);
        txtCPass.setTextAppearance(this, R.style.LockedTextInputEditTextStyle);

        // Obtén la imagen de la preferencia compartida
     //   loadImageFromSharedPreference();

        // Resto del código...

        btnUnlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtName.setEnabled(true);
                txtAge.setEnabled(true);
                txtMail.setEnabled(true);
                txtPass.setEnabled(true);
                txtCPass.setEnabled(true);
                btnUnlock.setVisibility(View.GONE);
                btnlock.setVisibility(View.VISIBLE);
            }
        });
    }



    public void selectImage(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_IMAGE);
    }



    private String resizeAndSaveImage(Uri imageUri, String imageName) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(imageUri);

            // Decodificar la imagen y obtener sus dimensiones originales
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream, null, options);
            inputStream.close();

            int originalWidth = options.outWidth;
            int originalHeight = options.outHeight;

            // Calcular la escala de reducción necesaria
            int maxSize = 1024; // Tamaño máximo deseado para la imagen
            int scaleFactor = Math.min(originalWidth / maxSize, originalHeight / maxSize);

            // Decodificar la imagen con la escala de reducción calculada
            inputStream = getContentResolver().openInputStream(imageUri);
            options.inJustDecodeBounds = false;
            options.inSampleSize = scaleFactor;
            Bitmap resizedBitmap = BitmapFactory.decodeStream(inputStream, null, options);
            inputStream.close();

            // Guardar la imagen redimensionada en el almacenamiento interno
            File file = new File(getFilesDir(), imageName);
            FileOutputStream outputStream = new FileOutputStream(file);
            resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
            outputStream.close();

            String imagePath = file.getAbsolutePath();
            Log.d("Image Path", imagePath); // Mostrar la ruta de la imagen en el Logcat

            return imagePath;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String imagePath = resizeAndSaveImage(selectedImage, "profile_image_" + System.currentTimeMillis());

           // saveImagePathToSharedPreference(imagePath);

            ImageView profileImage = findViewById(R.id.profileImage);
            Glide.with(this)
                    .load(new File(imagePath))
                    .apply(RequestOptions.circleCropTransform())
                    .into(profileImage);
        }
    }

}

