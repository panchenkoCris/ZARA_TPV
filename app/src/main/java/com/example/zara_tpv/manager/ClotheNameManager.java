package com.example.zara_tpv.manager;

import android.content.Context;

import com.example.zara_tpv.R;
import com.example.zara_tpv.pojo.Producto;
import com.example.zara_tpv.pojo.Type;
import com.example.zara_tpv.windows.FirstWindow;

import java.text.BreakIterator;

public class ClotheNameManager {
    public static String setName(Producto clothe, Context context) {
        String name = "";
        Type type = TypesManager.getOneType(clothe.getId_tipo());
        if(FirstWindow.getLanguage().equals("español")) {
            name = searchNameType(type.getNombre_tipo(), context) + " " + searchLengthType(type.getLongitud_tipo(), context, clothe);
        } else {
            name = searchLengthType(type.getLongitud_tipo(), context, clothe) + " " + searchNameType(type.getNombre_tipo(), context);
        }
        return name;
    }

    private static String searchLengthType(String longitud_tipo, Context context, Producto producto) {
        String lenght = "";
        if (longitud_tipo != null) {
            switch (longitud_tipo) {
                case "Largo":
                    lenght = context.getString(R.string.large);
                    break;
                case "Corto":
                    lenght = context.getString(R.string.curt);
                    break;
                case "Altos":
                    lenght = context.getString(R.string.high);
                    break;
                case "Bajos":
                    lenght = context.getString(R.string.lower);
                    break;
                case "Planos":
                    lenght = context.getString(R.string.plain);
                    break;
                case "Midi":
                    lenght = context.getString(R.string.midi);
                    break;
                case "Grande":
                    lenght = context.getString(R.string.big);
                    break;
                case "Pequeño":
                    lenght = context.getString(R.string.little);
                    break;
            }
        } else {
            lenght = ColorManager.searchColor(producto.getColor(), context);
        }

        return lenght;
    }

    private static String searchNameType(String nombre_tipo, Context context) {
        String type = "";
        switch (nombre_tipo) {
            case "Camiseta":
                type = context.getString(R.string.t_shirt);
                break;
            case "Cazadora":
            case "Abrigo":
                type = context.getString(R.string.chaquet);
                break;
            case "Zapatos":
                type = context.getString(R.string.shoes);
                break;
            case "Vestido":
                type = context.getString(R.string.dress);
                break;
            case "Chaleco":
                type = context.getString(R.string.waistcoat);
                break;
            case "Mono":
                type = context.getString(R.string.mono);
                break;
            case "Bolso":
                type = context.getString(R.string.bag);
                break;
            case "Camisa":
                type = context.getString(R.string.shirt);
                break;
            case "Shorts":
                type = context.getString(R.string.shorts);
                break;
            case "Bañador":
                type = context.getString(R.string.swimsuit);
                break;
            case "Pantalón":
                type = context.getString(R.string.trousers);
                break;
        }

        return type;
    }
}
