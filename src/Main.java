import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // clase de estilo de colores.
        ColoresTxt color = new ColoresTxt();

        // guardo los datos de resultados
        List<String[]> resultados = DatosSQL.leerResultados();

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
        String formatoTablaResultados = "%-10s | %-10s | %18s | %-18s | %15s | %-15s | %15s %n";

        // Header tabla RESULTADOS:
        System.out.println("");
        System.out.println(color.fAzul + "TABLA DE RESULTADOS:" + color.b);
        System.out.println("");
        System.out.printf(formatoTablaResultados, "Fase", "Ronda", "Nombre equipo 1", "Nombre equipo 2", "Goles equipo 1", "Goles equipo 2", "GANADOR");

        // Contenido de la tabla RESULTADOS:
        for (String[] esteResultado : resultados) {

            String ganadorPartido = "-";

            // guardo los valores en partido
            Partido partido = new Partido(esteResultado[2], esteResultado[4], esteResultado[5], esteResultado[3]);

            // guardo el resultadoGanador de cada partido tomando los datos de resultados
            resultadosGanador.add(partido.getResultado());

            // Reemplazo el valor por defecto "-" (EMPATE), por el nombre del equipo GANADOR
            if (partido.getResultado() == 1) {
                ganadorPartido = esteResultado[2];
            } else if (partido.getResultado() == 2) {
                ganadorPartido = esteResultado[3];
            }

            // muestro los datos de resultado + GANADOR
            System.out.printf(formatoTablaResultados, esteResultado[0], esteResultado[1], esteResultado[2], esteResultado[3], esteResultado[4], esteResultado[5], ganadorPartido);
        }

        // Muestro los datos por separado
        System.out.println(color.amarillo);
                System.out.printf("%15s %s %n", "PARTICIPANTES:", participantes);
        System.out.printf("%15s %s %n", "FASES:", fases);
        System.out.printf("%15s %s %n", "RONDAS:", rondas);
        // System.out.printf("%15s %s %n","RESULTADO:",resultadosGanador);


        // BUCLE comparando resultadosGanador con los pronósticos de cada participante.
        System.out.println(color.celeste);
        System.out.printf("%15s %n", "RESULTADOS:");

        for (int i = 0; i < participantes.size(); i++) {

            // Creo un objeto por participante
            Pronostico pronostico = new Pronostico(cantidadParticipantes.getParticipantes().get(i));
            participantePronostico = pronostico.getPronostico();

            // Comparo resultadoGanador con cada participante
            System.out.println("---------------------------------------");
            System.out.printf("%35s %n", cantidadParticipantes.getParticipantes().get(i) + ": ");

            ResultadoEnum resultadoEnum = new ResultadoEnum(resultadosGanador, participantePronostico);
            System.out.printf("%35s %s %n", "TOTAL DE PUNTOS OBTENIDOS: ", resultadoEnum.getPuntos());

            // List Pronostico del participante:
            // System.out.printf("%15s %s %n","PRONOSTICO:",cantidadParticipantes.getParticipantes().get(i) + ": " + participantePronostico);

            // Resultados:
            // Mariana: 20, Pedro:24, Juan: 20, Sofia: 21.
            // Sofia completa solo la ronda final.
            // Nadie completa una fase.
        }
        System.out.println(color.b);

    }
}