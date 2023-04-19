import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ronda extends DatosSQL {

    ArrayList<Partido> partidos;
    private List<String[]> resultados = leerResultados();

    private List<String> rondas;

    public Ronda() {
        super(leerResultados(),leerPronosticos());

        partidos = new ArrayList<>();

        setRondas(cantidadRondas());
    }

    public List<String> cantidadRondas(){

        List<String> rondas = new ArrayList<>();

        for (String[] cantidadRondas : this.resultados) {

            // guardo los datos sin repetir obteniendo cada ronda.
            if(!rondas.contains(cantidadRondas[1])){
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
