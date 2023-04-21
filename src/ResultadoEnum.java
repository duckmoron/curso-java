import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ResultadoEnum {

    private List<String[]> resultados = DatosSQL.leerResultados();

    private int puntos;
    private int partidoNum;

    private Ronda rondas = new Ronda();
    private Fase fases = new Fase();


    private int puntosPartido = 1;
    private int puntosExtraRonda = 5;
    private int puntosExtraFase = 10;


    List<Integer> resultado;
    List<Integer> pronostico;


    public ResultadoEnum(List<Integer> resultado, List<Integer> pronostico) {
        this.resultado = resultado;
        this.pronostico = pronostico;

        puntosParticipante(resultado, pronostico);
    }


    private Integer puntosParticipante(List<Integer> resultado, List<Integer> pronostico) {

        puntos = 0;

        partidoNum = 0;

        List<String> rc = rondas.getRondas();
        List<String> fc = fases.getFases();

        Integer[] rondasCompletas = new Integer[rc.size()];
        Integer[] fasesCompletas = new Integer[fc.size()];



        for (String[] cantidadPartidos : this.resultados) {

            // chequeo los puntos por partido
            if (!Objects.equals(resultado.get(partidoNum), pronostico.get(partidoNum))) {

                // defino 0 posicion de rondas y fases
                fasesCompletas[fc.indexOf(cantidadPartidos[0])] = 0;
                rondasCompletas[rc.indexOf(cantidadPartidos[1])] = 0;

            }else{
                // defino 1 posicion de rondas y fases
                if(!Objects.nonNull(fasesCompletas[fc.indexOf(cantidadPartidos[0])]) || fasesCompletas[fc.indexOf(cantidadPartidos[0])] == 1) {
                    fasesCompletas[fc.indexOf(cantidadPartidos[0])] = 1;
                }
                if(!Objects.nonNull(rondasCompletas[rc.indexOf(cantidadPartidos[1])]) || rondasCompletas[rc.indexOf(cantidadPartidos[1])] == 1) {
                    rondasCompletas[rc.indexOf(cantidadPartidos[1])] = 1;
                }
                // sumo un punto de partido acertado.
                puntos += puntosPartido;
            }

            partidoNum++;

        }

        System.out.printf("%35s %s %n","Fases completas (+ "+puntosExtraFase+" puntos): ",sumaDatos(fasesCompletas));
        System.out.printf("%35s %s %n","Rondas completas (+ "+puntosExtraRonda+" puntos): ",sumaDatos(rondasCompletas));

        // sumo puntos totales en caso de completar fases y/o rondas
        puntos += (sumaDatos(fasesCompletas) * puntosExtraFase) + (sumaDatos(rondasCompletas) * puntosExtraRonda);

        return puntos;
    }

    private Integer sumaDatos(Integer[] dato){

        int resultadoSuma = 0;

        for (int i: dato) {
            resultadoSuma += i;
        }

        return resultadoSuma;
    }

    public int getPuntos() {
        return puntos;
    }
}
