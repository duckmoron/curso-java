import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResultadoEnum {

    private int puntos;

    private int puntosPartido = 1;
    private int puntosExtraRonda = 5;
    private int puntosExtraFase = 10;

    List<Integer> resultado = new ArrayList<>();
    List<Integer> pronostico = new ArrayList<>();


    public ResultadoEnum(List<Integer> resultado, List<Integer> pronostico) {
        this.resultado = resultado;
        this.pronostico = pronostico;

        puntosParticipante(resultado, pronostico);
    }


    private Integer puntosParticipante(List<Integer> resultado, List<Integer> pronostico){

        puntos = 0;

        for(int i = 0; i<resultado.size();i++){
            if(Objects.equals(resultado.get(i),pronostico.get(i))){
                puntos += puntosPartido;
            }
        }

        return puntos;
    }

    public int getPuntos() {
        return puntos;
    }
}
