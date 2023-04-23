package dominio;

public class Fases {
    private String fase;
    private String ronda;
    private int cantidad;

    public Fases(String fase, String ronda, int cantidad) {
        this.fase = fase;
        this.ronda = ronda;
        this.cantidad = cantidad;
    }

    public String getRonda() {
        return ronda;
    }

    public void setRonda(String ronda) {
        this.ronda = ronda;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
