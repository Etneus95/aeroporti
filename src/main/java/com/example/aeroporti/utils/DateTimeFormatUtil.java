package com.example.aeroporti.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeFormatUtil {

    // Formatta la data partendo da una stringa e restituisce una nuova stringa leggibile
    public static String formatVoloDate(String dataOriginale) {
        DateTimeFormatter formatterInput = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm:ss,SSSSSSSSS");
        
        // Locale italiano per nomi dei mesi
        DateTimeFormatter formatterOutput = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm", Locale.ITALIAN);
        
        LocalDateTime ldt = LocalDateTime.parse(dataOriginale, formatterInput);
        return ldt.format(formatterOutput);
    }

}