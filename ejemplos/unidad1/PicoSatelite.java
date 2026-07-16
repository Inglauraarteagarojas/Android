public class PicoSatelite {
    private String id;
    private double temperatura;
    private double humedad;
    private double latitud;
    private double longitud;

    public PicoSatelite(String id) {
        this.id = id;
    }

    public void actualizarTelemetria(double temperatura, double humedad,
                                     double latitud, double longitud) {
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String evaluarEstado() {
        if (temperatura > 45) return "ALERTA: temperatura alta";
        if (humedad > 90) return "ALERTA: humedad alta";
        return "Sistema estable";
    }

    public String resumen() {
        return id + " | T=" + temperatura + " °C | HR=" + humedad
                + "% | GPS=" + latitud + "," + longitud;
    }
}
