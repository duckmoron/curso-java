import java.util.ArrayList;
import java.util.List;

public class Ronda {

    private List<String[]> resultados = DatosSQL.leerResultados();

    private List<String> rondas;

    public Ronda() {

        setRondas(cantidadRondas());
    }

    public List<String> cantidadRondas() {

        List<String> rondas = new ArrayList<>();

        for (String[] cantidadRondas : this.resultados) {

            // guardo los datos sin repetir obteniendo cada ronda.
            if (!rondas.contains(cantidadRondas[1])) {
                rondas.add(cantidadRondas[1]);
            }

        }

        return rondas;
    }

    public List<String> getRondas() {
        return rondas;
    }

    public void setRondas(List<String> rondas) {
        this.rondas = rondas;
    }
}
