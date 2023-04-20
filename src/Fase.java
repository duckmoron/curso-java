import java.util.ArrayList;
import java.util.List;


public class Fase {

    private List<String[]> resultados = DatosSQL.leerResultados();

    private List<String> fases;

    public Fase() {

        setFases(cantidadFases());
    }

    public List<String> cantidadFases() {

        List<String> fases = new ArrayList<>();

        for (String[] cantidadFases : this.resultados) {

            // guardo los datos sin repetir obteniendo cada fase.
            if (!fases.contains(cantidadFases[0])) {
                fases.add(cantidadFases[0]);
            }

        }

        return fases;
    }

    public List<String> getFases() {
        return fases;
    }

    public void setFases(List<String> fases) {
        this.fases = fases;
    }


}
