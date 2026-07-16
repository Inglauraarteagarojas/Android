public class Main {
    public static void main(String[] args) {
        PicoSatelite sat = new PicoSatelite("PICO-01");
        double[] temperaturas = {22.5, 24.1, 46.2};

        for (int i = 0; i < temperaturas.length; i++) {
            sat.actualizarTelemetria(temperaturas[i], 61.0, 4.7109, -74.0721);
            System.out.println(sat.resumen());
            System.out.println(sat.evaluarEstado());
        }
    }
}
