package com.example.myapplication;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private View square;
    private RelativeLayout container;
    private float dX, dY;
    private int containerWidth, containerHeight, squareSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener referencia al cuadrado y al contenedor
        square = findViewById(R.id.square);
        container = findViewById(R.id.container);

        // Obtener dimensiones después de que se haya creado la vista
        container.post(new Runnable() {
            @Override
            public void run() {
                containerWidth = container.getWidth();
                containerHeight = container.getHeight();
                squareSize = square.getWidth(); // Suponemos que el cuadrado es un cuadrado (ancho = alto)
            }
        });

        // Configurar el listener de toque
        square.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Guardar la posición inicial del toque
                        dX = v.getX() - event.getRawX();
                        dY = v.getY() - event.getRawY();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        // Calcular nueva posición
                        float newX = event.getRawX() + dX;
                        float newY = event.getRawY() + dY;

                        // Restringir el movimiento dentro del contenedor
                        if (newX < 0) newX = 0; // Límite izquierdo
                        if (newY < 0) newY = 0; // Límite superior
                        if (newX + squareSize > containerWidth) newX = containerWidth - squareSize; // Límite derecho
                        if (newY + squareSize > containerHeight) newY = containerHeight - squareSize; // Límite inferior

                        // Mover el cuadrado
                        v.setX(newX);
                        v.setY(newY);
                        break;
                }
                return true;
            }
        });
    }
}

