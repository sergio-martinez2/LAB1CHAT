package com.example.acitvityapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity_2 extends AppCompatActivity {

    TextView Mensaje;
    EditText editTextBt;
    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);

           Mensaje = findViewById(R.id.mensajeimpreso);
           editTextBt = findViewById(R.id.mensajeEditText);
           btnEnviar = findViewById(R.id.boton);

           Bundle recibeDatos = getIntent().getExtras();
           String info = recibeDatos.getString("KeyDatos");
           String historial = recibeDatos.getString("historial"); // Recibe el historial como una cadena de texto
           Mensaje.setText(historial + "\n" + info); // Muestra el historial y el nuevo mensaje en el TextView


        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarMensaje();
            }
        });
    }


    private void enviarMensaje() {
        String mensaje = editTextBt.getText().toString().trim();
        if (!mensaje.isEmpty()) {
            // Enviar mensaje a MainActivity
            Intent returnIntent = new Intent();
            returnIntent.putExtra("KeyDatos", mensaje);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        } else {
            Toast.makeText(MainActivity_2.this, "Por favor ingresa un mensaje", Toast.LENGTH_SHORT).show();
        }
    }

}