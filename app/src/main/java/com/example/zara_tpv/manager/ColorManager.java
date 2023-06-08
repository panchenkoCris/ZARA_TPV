package com.example.zara_tpv.manager;

import android.content.Context;

import com.example.zara_tpv.R;

public class ColorManager {
    public static String searchColor(String color, Context context) {
        String translatedColor = "";
        switch (color) {
            case "Negro":
                translatedColor = context.getString(R.string.black);
                break;
            case "Rosa":
                translatedColor = context.getString(R.string.pink);
                break;
            case "Amarillo":
                translatedColor = context.getString(R.string.yellow);
                break;
            case "Verde":
                translatedColor = context.getString(R.string.green);
                break;
            case "Azul":
                translatedColor = context.getString(R.string.blue);
                break;
            case "Blanco":
                translatedColor = context.getString(R.string.white);
                break;
            case "Metalizado":
                translatedColor = context.getString(R.string.metallized);
                break;
            case "Variados":
                translatedColor = context.getString(R.string.variosColours);
                break;
            case "Beige":
                translatedColor = context.getString(R.string.beige);
                break;
            case "Kaki":
                translatedColor = context.getString(R.string.kaki);
                break;
            case "Naranja":
                translatedColor = context.getString(R.string.orange);
                break;
            case "Marrón":
                translatedColor = context.getString(R.string.brown);
                break;
        }

        return translatedColor;
    }
}
