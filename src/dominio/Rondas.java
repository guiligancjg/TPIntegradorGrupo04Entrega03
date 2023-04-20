package dominio;

public class Rondas {
    private String ronda;
    private int cantidad;


    public Rondas(String ronda, int cantidad) {
        this.ronda = ronda;
        this.cantidad = cantidad;
    }

    public String getRonda() {
        return ronda;
    }

    public void setRonda(String ronda) {
        this.ronda = ronda;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
