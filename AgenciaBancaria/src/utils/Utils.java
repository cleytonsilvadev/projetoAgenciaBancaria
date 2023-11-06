package utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Utils {
    static NumberFormat formatandoNumeros = new DecimalFormat("R$ #,##0.00"); // Formata números como moeda (com símbolo "R$" e separadores de milhares e decimais)
    static SimpleDateFormat formatandoData = new SimpleDateFormat("dd/MM/yyyy"); // Formata datas no formato "dd/MM/yyyy"

    public static String dateToString(LocalDate localDate) {
        return Utils.formatandoData.format(localDate); // Converte um objeto LocalDate em uma string formatada
    }

    public static String doubleToString(Double valor) {
        return Utils.formatandoNumeros.format(valor); // Converte um número decimal em uma string formatada como moeda
    }
}
