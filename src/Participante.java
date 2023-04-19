import java.util.ArrayList;
import java.util.List;

public class Participante extends DatosSQL{
    ArrayList<Pronostico> pronostico;
    private List<String[]> resultados = leerResultados();

    private List<String> participantes;

    public Participante() {
        super(leerResultados(),leerPronosticos());

        pronostico = new ArrayList<>();

        setParticipantes(cantidadParticipantes());
    }

    public List<String> cantidadParticipantes(){

        List<String> participantes = new ArrayList<>();

        for (String[] cantidadParticipantes : this.pronosticos) {

            // guardo los datos sin repetir obteniendo cada participante.
            if(!participantes.contains(cantidadParticipantes[0])){
                participantes.add(cantidadParticipantes[0]);
            }

        }

        return participantes;
    }

    public List<String> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<String> participantes) {
        this.participantes = participantes;
    }
}
