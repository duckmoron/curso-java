import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pronostico {

    private List<String[]> pronosticos = DatosSQL.leerPronosticos();
    private String participante;
    private List<Integer> pronostico;

    public Pronostico(String participante) {

        this.participante = participante;
        pronostico = new ArrayList<>();

        setPronostico(participantePronostico(participante));
    }

    public List<Integer> participantePronostico(String participante){

        List<Integer> pronostico = new ArrayList<>();

        for (String[] participantePronostico : this.pronosticos) {

            // guardo los datos del pronostico segun participante.
            if (Objects.equals(participantePronostico[0], participante)) {
                pronostico.add(Integer.valueOf(participantePronostico[5]));
            }

        }

        return pronostico;
    }

    public List<Integer> getPronostico() {
        return pronostico;
    }

    public void setPronostico(List<Integer> pronostico) {
        this.pronostico = pronostico;
    }
}
