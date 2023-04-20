import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        // guardo los datos de resultados y pronosticos
        List<String[]> resultados = DatosSQL.leerResultados();
        List<String[]> pronosticos = DatosSQL.leerPronosticos();

        // guardo los datos limpios, sin repetir
        List<String> participantes;
        List<String> rondas;
        List<String> fases;

        Participante cantidadParticipantes = new Participante();
        participantes = cantidadParticipantes.getParticipantes();

        Fase cantidadFases = new Fase();
        fases = cantidadFases.getFases();

        Ronda cantidadRondas = new Ronda();
        rondas = cantidadRondas.getRondas();

        // guardo los resultados finales y pronostico
        List<Integer> participantePronostico = new ArrayList<>();
        List<Integer> resultadosGanador = new ArrayList<>();


        // configuro para obtener formato tipo tabla
        String formatoTablaResultados = "%-10s | %-10s | %18s | %-18s | %15s | %-15s | %10s %n";

        // Header tabla RESULTADOS:
        System.out.println("\nTABLA DE RESULTADOS:\n");
        System.out.printf(formatoTablaResultados,"Fase","Ronda","Nombre equipo 1","Nombre equipo 2","Goles equipo 1","Goles equipo 2","GANADOR");

        // Contenido de la tabla RESULTADOS:
        for (String[] esteResultado : resultados) {

            // guardo los valores en partido
            Partido partido = new Partido(esteResultado[2], esteResultado[4], esteResultado[5], esteResultado[3]);

            // guardo el resultadoGanador de cada partido tomando los datos de resultados
            resultadosGanador.add(partido.getResultado());

            // muestro los datos de resultado + GANADOR
            System.out.printf(formatoTablaResultados, esteResultado[0], esteResultado[1], esteResultado[2], esteResultado[3], esteResultado[4], esteResultado[5], partido.getResultado());
        }

        // Muestro los datos por separado
        System.out.printf("\n%15s %s %n","PARTICIPANTES:",participantes);
        System.out.printf("%15s %s %n","FASES:",fases);
        System.out.printf("%15s %s %n","RONDAS:",rondas);
        //System.out.printf("%15s %s %n","RESULTADO:",resultadosGanador);
        System.out.println(" ");
        System.out.printf("%15s %s %n","RESULTADO:","");



        // BUCLE comparando resultadosGanador con los pronósticos de cada participante.
        for(int i = 0;i < participantes.size();i++) {
            Pronostico pronostico = new Pronostico(cantidadParticipantes.getParticipantes().get(i));
            participantePronostico = pronostico.getPronostico();

            ResultadoEnum resultadoEnum = new ResultadoEnum(resultadosGanador, participantePronostico);
            System.out.printf("%15s %s %n",cantidadParticipantes.getParticipantes().get(i)+": ",resultadoEnum.getPuntos());

            // Pronostico del participante:
            // System.out.printf("%15s %s %n","PRONOSTICO:",cantidadParticipantes.getParticipantes().get(i) + ": " + participantePronostico);
        }

    }
}