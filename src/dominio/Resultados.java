package dominio;

public class Resultados {
    private int idResultado;
    private String fase;
    private String ronda;
    private String idEquipo1;
    private String idEquipo2;
    private int goles1;
    private int goles2;

    public Resultados() {
    }

    public Resultados(int idResultado) {
        this.idResultado = idResultado;
    }

    public Resultados(String fase, String ronda, String idEquipo1, String idEquipo2) {
        this.fase = fase;
        this.ronda = ronda;
        this.idEquipo1 = idEquipo1;
        this.idEquipo2 = idEquipo2;
    }

    public Resultados(String fase, String ronda, String idEquipo1, String idEquipo2, int goles1, int goles2) {
        this.fase = fase;
        this.ronda = ronda;
        this.idEquipo1 = idEquipo1;
        this.idEquipo2 = idEquipo2;
        this.goles1 = goles1;
        this.goles2 = goles2;
    }

    public int getIdResultado() {
        return idResultado;
    }

    public void setIdResultado(int idResultado) {
        this.idResultado = idResultado;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public String getRonda() {
        return ronda;
    }

    public void setRonda(String ronda) {
        this.ronda = ronda;
    }

    public String getIdEquipo1() {
        return idEquipo1;
    }

    public void setIdEquipo1(String idEquipo1) {
        this.idEquipo1 = idEquipo1;
    }

    public String getIdEquipo2() {
        return idEquipo2;
    }

    public void setIdEquipo2(String idEquipo2) {
        this.idEquipo2 = idEquipo2;
    }

    public int getGoles1() {
        return goles1;
    }

    public void setGoles1(int goles1) {
        this.goles1 = goles1;
    }

    public int getGoles2() {
        return goles2;
    }

    public void setGoles2(int goles2) {
        this.goles2 = goles2;
    }

    public ResultadoEnum resultado(int equipo1, int equipo2) {
        return switch (Integer.compare(equipo1, equipo2)) {
            case 0 -> ResultadoEnum.EMPATE;// si son iguales es 0
            case 1 -> ResultadoEnum.GANADOR;// si equipo1 > equipo2 es 1
            default -> ResultadoEnum.PERDEDOR;// si equipo1 < equipo2 es -1
        };
    }
}
