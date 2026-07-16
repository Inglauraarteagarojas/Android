public class MainActivity extends AppCompatActivity {
    private EditText edtTemperatura, edtHumedad;
    private TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTemperatura = findViewById(R.id.edtTemperatura);
        edtHumedad = findViewById(R.id.edtHumedad);
        txtResultado = findViewById(R.id.txtResultado);
        Button btnEvaluar = findViewById(R.id.btnEvaluar);

        btnEvaluar.setOnClickListener(v -> evaluar());
    }

    private void evaluar() {
        String t = edtTemperatura.getText().toString().trim();
        String h = edtHumedad.getText().toString().trim();

        if (t.isEmpty() || h.isEmpty()) {
            txtResultado.setText("Completa los dos campos");
            return;
        }

        double temperatura = Double.parseDouble(t);
        double humedad = Double.parseDouble(h);
        String estado = (temperatura > 45 || humedad > 90)
                ? "ALERTA" : "NORMAL";

        txtResultado.setText("T=" + temperatura + " °C
HR="
                + humedad + "%
Estado=" + estado);
    }
}
