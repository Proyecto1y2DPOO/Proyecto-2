package uniandes.dpoo.actividades;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FechaHoraActual {

    public static void main(String[] args) {
        // Obtener la fecha y hora actual
    	LocalDate fe=LocalDate.now();
    	
    	System.out.println("Fecha y hora actual: " + fe);
    	
        LocalDateTime ahora = LocalDateTime.now();
        
        System.out.println("Fecha y hora actual: " + ahora);
        
        LocalDateTime raro = LocalDateTime.parse("2024-12-03T10:15:30");
        
        System.out.println("Fecha y hora actual: " + raro);
        
        int respuesta=ahora.compareTo(raro);
        System.out.println("Fecha y hora actual: " + respuesta);

        // Formatear la fecha y hora como "yyyy-MM-dd HH:mm:ss"
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaHoraFormateada = ahora.format(formato);

        // Imprimir la fecha y hora formateada
        System.out.println("Fecha y hora actual: " + fechaHoraFormateada);
    }
}


