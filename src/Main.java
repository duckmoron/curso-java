import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        List<String[]> resultados = DatosSQL.leerResultados();
        List<String[]> pronosticos = DatosSQL.leerPronosticos();

        List<String> participantes;
        List<String> rondas;
        List<String> fases;
        List<String> participantePronostico = new ArrayList<>();
        List<Integer> resultadosGanador = new ArrayList<>();

        Participante cantidadParticipantes = new Participante();
        participantes = cantidadParticipantes.getParticipantes();

        Fase cantidadFases = new Fase();
        fases = cantidadFases.getFases();

        Ronda cantidadRondas = new Ronda();
        rondas = cantidadRondas.getRondas();

        Pronostico pronostico = new Pronostico(cantidadParticipantes.getParticipantes().get(0));
        participantePronostico = pronostico.getPronostico();


        // configuro para obtener formato tipo tabla
        String formatoTablaResultados = "%-10s | %-10s | %18s | %-18s | %15s | %-15s | %10s %n";

        // Header tabla RESULTADOS:
        System.out.println("\nTABLA DE RESULTADOS:\n");
        System.out.printf(formatoTablaResultados,"Fase","Ronda","Nombre equipo 1","Nombre equipo 2","Goles equipo 1","Goles equipo 2","GANADOR");

        for (String[] esteResultado : resultados) {

            // guardo los valores en la clase partido
            Partido partido = new Partido(esteResultado[2], esteResultado[4], esteResultado[5], esteResultado[3]);

            // guardo el resultadoGanador de cada partido tomando los datos de resultados
            resultadosGanador.add(partido.getResultado());

            // muestro los datos de resultado + GANADOR
            System.out.printf(formatoTablaResultados, esteResultado[0], esteResultado[1], esteResultado[2], esteResultado[3], esteResultado[4], esteResultado[5], partido.getResultado());
        }

        System.out.printf("\n%15s %s %n","PARTICIPANTES:",participantes);
        System.out.printf("%15s %s %n","FASES:",fases);
        System.out.printf("%15s %s %n","RONDAS:",rondas);
        System.out.printf("%15s %s %n","RESULTADO:",resultadosGanador);
        System.out.printf("%15s %s %n","PRONOSTICO:",cantidadParticipantes.getParticipantes().get(0) + ": " + participantePronostico);


        // bucle comparando resultadosGanador con los pronósticos de cada participante.
        // si el resultado del partido es correcto sumar X puntos.
        // si dentro de ronda son todos correctos sumar X puntos.
        // si todas las rondas dentro de la fase son correctas sumar X puntos.
        // Ordenar por puntajes.


       /* for (String[] esteParticipantes : pronosticos){

            // deberia guardar esto en participante. (nombre y su pronostico).

            for(int i = 0;i<participantes.size();i++) {
                if (Objects.equals(esteParticipantes[0], participantes.get(i))) {
                    //System.out.println("PARTICIPANTE: " + esteParticipantes[0] + " PRONOSTICO: " + esteParticipantes[5]);
                }
            }

        }*/
    }
}