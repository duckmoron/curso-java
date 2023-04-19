import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pronostico extends DatosSQL{

    private String participante;
    private List<String> pronostico;

    public Pronostico(String participante) {
        super(leerResultados(),leerPronosticos());
        this.participante = participante;
        pronostico = new ArrayList<>();

        setPronostico(participantePronostico(participante));
    }

    public List<String> participantePronostico(String participante){

        List<String> pronostico = new ArrayList<>();

        for (String[] participantePronostico : this.pronosticos) {

            // guardo los datos del pronostico segun participante.
            if (Objects.equals(participantePronostico[0], participante)) {
                pronostico.add(participantePronostico[5]);
            }

        }

        return pronostico;
    }

    public List<String> getPronostico() {
        return pronostico;
    }

    public void setPronostico(List<String> pronostico) {
        this.pronostico = pronostico;
    }
}
