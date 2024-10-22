package com.example.actions;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View squareView = findViewById(R.id.squareView);

        // Inicializa el GestureDetector
        gestureDetector = new GestureDetector(this, new GestureListener());

        // Establece un listener de touch en el cuadrado
        squareView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Pasa el evento al GestureDetector
                return gestureDetector.onTouchEvent(event);
            }
        });
    }

    // Clase interna para manejar los gestos
    private class GestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Toast.makeText(MainActivity.this, "Doble tap detectado", Toast.LENGTH_SHORT).show();
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Toast.makeText(MainActivity.this, "Deslizar detectado", Toast.LENGTH_SHORT).show();
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Toast.makeText(MainActivity.this, "Tap simple detectado", Toast.LENGTH_SHORT).show();
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Toast.makeText(MainActivity.this, "Pulsación larga detectada", Toast.LENGTH_SHORT).show();
        }
    }
}
