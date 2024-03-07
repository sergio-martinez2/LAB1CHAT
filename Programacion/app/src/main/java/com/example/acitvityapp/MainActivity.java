package com.example.acitvityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextBt;
    Button btnEnviar;
    TextView mensajeRecibidoTextView;
    StringBuilder historialMensajes = new StringBuilder(); // Para almacenar el historial de mensajes


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextBt = findViewById(R.id.mensajeEditText);
        btnEnviar = findViewById(R.id.boton);
        mensajeRecibidoTextView = findViewById(R.id.mensajeRecibidoTextView); //  TextView




        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarMensaje();
            }
        });

        // Verificar si hay un mensaje devuelto del MainActivity_2 y actualizar el historial de mensajes
        if (getIntent().getStringExtra("KeyDatos") != null) {
            String mensajeRecibido = getIntent().getStringExtra("KeyDatos");
            actualizarHistorialMensajes("Otro: " + mensajeRecibido); // Agregamos el mensaje recibido al historial
        }

        // Mostrar el historial de mensajes al iniciar la actividad
        mensajeRecibidoTextView.setText(historialMensajes.toString());


        // Botón para limpiar el historial de mensajes
        Button btnLimpiarHistorial = findViewById(R.id.btnLimpiarHistorial);
        btnLimpiarHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarHistorial();
            }
        });
    }

    private void enviarMensaje() {
        String mensaje = editTextBt.getText().toString().trim();
        if (!mensaje.isEmpty()) {
            Intent abriractivity2 = new Intent(MainActivity.this, MainActivity_2.class);
            abriractivity2.putExtra("KeyDatos", mensaje);
            abriractivity2.putExtra("historial", historialMensajes.toString()); // Envía el historial como una cadena de texto
            startActivityForResult(abriractivity2, 1);
            editTextBt.setText("");
            actualizarHistorialMensajes("Admin: " + mensaje);
            mensajeRecibidoTextView.setText(historialMensajes.toString());
        } else {
            Toast.makeText(MainActivity.this, "Por favor ingresa un mensaje", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String mensajeDevuelto = data.getStringExtra("KeyDatos");
            actualizarHistorialMensajes("Conductor: " + mensajeDevuelto);
            mensajeRecibidoTextView.setText(historialMensajes.toString());
        }
    }

    // Método para actualizar el historial de mensajes con un nuevo mensaje
    private void actualizarHistorialMensajes(String mensaje) {
        historialMensajes.append(mensaje).append("\n");
    }

    // Método para limpiar el historial de mensajes
    private void limpiarHistorial() {
        historialMensajes.setLength(0);
        mensajeRecibidoTextView.setText("");
    }






}