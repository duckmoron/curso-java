import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // PRUEBAS
        //Partido partido1 = new Partido("Argentina", 3,4,"Polonia");
        //System.out.println(partido1.getResultado());
        // FIN PRUEBAS

        List<String[]> resultados = leerResultados();
        List<String[]> pronosticos = leerPronosticos();

        List<Integer> resultadosGanador = new ArrayList<>();
        List<String> participantes = new ArrayList<>();
        List<String> rondas = new ArrayList<>();
        List<String> fases = new ArrayList<>();


        // configuro para obtener formato tipo tabla
        String formatoTablaResultados = "%-10s | %-10s | %18s | %-18s | %15s | %-15s | %10s %n";
        String formatoTablaPronosticos = "%-15s | %-10s | %10s | %18s | %-18s | %10s %n";

        // Header tabla RESULTADOS:
        System.out.println("");
        System.out.println("RESULTADOS:");
        System.out.printf(formatoTablaResultados,"Fase","Ronda","Nombre equipo 1","Nombre equipo 2","Goles equipo 1","Goles equipo 2","GANADOR");

        for (String[] esteResultado : resultados) {

            // guardo los valores en la clase partido
            Partido partido = new Partido(esteResultado[2], esteResultado[4], esteResultado[5], esteResultado[3]);

            // guardo el resultadoGanador de cada partido tomando los datos de resultados
            resultadosGanador.add(partido.getResultado());

            // muestro los datos de resultado + GANADOR
            System.out.printf(formatoTablaResultados, esteResultado[0], esteResultado[1], esteResultado[2], esteResultado[3], esteResultado[4], esteResultado[5], partido.getResultado());
        }


        // Header tabla PRONOSTICOS:
        System.out.println("");
        System.out.println("PRONOSTICOS:");
        System.out.printf(formatoTablaPronosticos,"Nombre persona","Fase","Ronda","Nombre equipo 1","Nombre equipo 2","Ganador");

        for (String[] estepronosticos : pronosticos) {

            System.out.printf(formatoTablaPronosticos, estepronosticos[0], estepronosticos[1], estepronosticos[2], estepronosticos[3], estepronosticos[4], estepronosticos[5]);
        }

        for (String[] esteParticipante : pronosticos) {

            // guardo los datos sin repetir obteniendo cada participante.
            if(!participantes.contains(esteParticipante[0])){
                participantes.add(esteParticipante[0]);
            }
            // guardo los datos sin repetir obteniendo cada fase.
            if(!fases.contains(esteParticipante[1])){
                fases.add(esteParticipante[1]);
            }// guardo los datos sin repetir obteniendo cada ronda.
            if(!rondas.contains(esteParticipante[2])){
                rondas.add(esteParticipante[2]);
            }

        }

        System.out.println("");
        System.out.printf("%15s %s %n","PARTICIPANTES:",participantes);
        System.out.printf("%15s %s %n","FASES:",fases);
        System.out.printf("%15s %s %n","RONDAS:",rondas);

    }



    // Va a devolver una Lista con un arreglo de String que va a contener:
    // Posicion 0: Ronda
    // Posicion 1: Fase
    // Posicion 2: Nombre equipo 1
    // Posicion 3: Nombre equipo 2
    // Posicion 4: Goles equipo 1
    // Posicion 5: Goles equipo 2
    public static List<String[]> leerResultados() {
        List<String []> resultados = new ArrayList<>();

        // Cargamos el Driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error cargando el driver");
        }

        try {
            // Creamos la conexión
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10612293",
                    "sql10612293", "ACwUKDKvbY");
            Statement stmt = con.createStatement();

            // El Query que vamos a correr
            ResultSet rs = stmt.executeQuery("SELECT FASE, RONDA, E1.EQUIPO AS EQUIPO_1, E2.EQUIPO AS EQUIPO_2, GOLES_1, GOLES_2 FROM RESULTADOS R JOIN EQUIPOS E1 on R.ID_EQUIPO_1 = E1.ID_EQUIPO JOIN EQUIPOS E2 on R.ID_EQUIPO_2 = E2.ID_EQUIPO");
            while (rs.next()) {
                String[] fila = new String[6];
                fila[0] = rs.getString("FASE");
                fila[1] = rs.getString("RONDA");
                fila[2] = rs.getString("EQUIPO_1");
                fila[3] = rs.getString("EQUIPO_2");
                fila[4] = rs.getString("GOLES_1");
                fila[5] = rs.getString("GOLES_2");
                resultados.add(fila);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error con SQL");
        }
        return resultados;
    }


    // Va a devolver una Lista con un arreglo de String que va a contener:
    // Posicion 0: Nombre de la persona
    // Posicion 1: Fase
    // Posicion 2: Ronda
    // Posicion 3: Nombre equipo 1
    // Posicion 4: Nombre equipo 2
    // Posicion 5: Ganador
    public static List<String[]> leerPronosticos() {
        List<String[]> pronosticos = new ArrayList<>();

        // Cargamos el Driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error cargando el driver");
        }

        try {
            // Creamos la conexión
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10612293",
                    "sql10612293", "ACwUKDKvbY");
            Statement stmt = con.createStatement();

            // El Query que vamos a correr
            ResultSet rs = stmt.executeQuery("SELECT NOMBRE, FASE, RONDA, E1.EQUIPO AS EQUIPO_1, E2.EQUIPO AS EQUIPO_2, GANADOR FROM PRONOSTICOS P JOIN RESULTADOS R on P.ID_RESULTADO = R.ID_RESULTADO JOIN EQUIPOS E1 on R.ID_EQUIPO_1 = E1.ID_EQUIPO JOIN EQUIPOS E2 on R.ID_EQUIPO_2 = E2.ID_EQUIPO");
            while (rs.next()) {
                String[] fila = new String[6];
                fila[0] = rs.getString("NOMBRE");
                fila[1] = rs.getString("FASE");
                fila[2] = rs.getString("RONDA");
                fila[3] = rs.getString("EQUIPO_1");
                fila[4] = rs.getString("EQUIPO_2");
                fila[5] = rs.getString("GANADOR");
                pronosticos.add(fila);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error con SQL");
        }

        return pronosticos;
    }
}