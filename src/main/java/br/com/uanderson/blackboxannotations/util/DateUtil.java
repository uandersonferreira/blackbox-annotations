package br.com.uanderson.blackboxannotations.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateUtil {
    public String formatLocalDateTimeToDatabaseStyle(LocalDateTime localDateTime) {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(localDateTime);
    }

    public String formatLocalTime(LocalDateTime localDateTime) {
        return DateTimeFormatter.ofPattern("HH:mm:ss").format(localDateTime);
    }
    public String formatLocalDate(LocalDateTime localDateTime) {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(localDateTime);
    }



    public static void main(String[] args) {
        // data/hora atual
        LocalDateTime agora = LocalDateTime.now();

        // formatar a data
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String dataFormatada = formatterData.format(agora);
        System.out.println(dataFormatada);

        // formatar a hora
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaFormatada = formatterHora.format(agora);
        System.out.println(horaFormatada);

    }

}
