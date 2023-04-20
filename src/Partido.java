public class Partido {

    private String golesEquipo1;
    private String golesEquipo2;

    private String equipo1;
    private String equipo2;

    private int resultado;


    public Partido(String equipo1, String golesEquipo1, String golesEquipo2, String equipo2) {

        this.golesEquipo1 = golesEquipo1;
        this.golesEquipo2 = golesEquipo2;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;

        // compruebo en base al resultado quien ganó o si empataron.
        setResultado(resultadoPartido(Integer.parseInt(golesEquipo1), Integer.parseInt(golesEquipo2)));
    }


    // resultadoPartido = 0: EMPATE.
    // resultadoPartido = 1: Equipo1.
    // resultadoPartido = 2: Equipo2.
    private int resultadoPartido(int golesEquipo1, int golesEquipo2) {

        resultado = 0;

        if (golesEquipo1 > golesEquipo2) {
            resultado = 1;
        } else if (golesEquipo2 > golesEquipo1) {
            resultado = 2;
        }

        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public int getResultado() {
        return resultado;
    }
}
