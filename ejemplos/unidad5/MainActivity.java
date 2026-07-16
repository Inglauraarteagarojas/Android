public class MainActivity extends AppCompatActivity {
    private final ArrayList<String> historial = new ArrayList<>();
    private ArrayAdapter<String> adaptador;
    private TextView txtUltimaLectura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spn = findViewById(R.id.spnVariable);
        ListView lista = findViewById(R.id.listaTelemetria);
        txtUltimaLectura = findViewById(R.id.txtUltimaLectura);

        String[] variables = {"temperatura", "humedad", "latitud", "longitud"};
        spn.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, variables));

        adaptador = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, historial);
        lista.setAdapter(adaptador);

        escucharFirebase();
    }

    private void escucharFirebase() {
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("picosat/telemetria");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Double t = snapshot.child("temperatura").getValue(Double.class);
                Double h = snapshot.child("humedad").getValue(Double.class);
                Double lat = snapshot.child("latitud").getValue(Double.class);
                Double lon = snapshot.child("longitud").getValue(Double.class);

                String lectura = "T=" + t + " °C | HR=" + h
                        + "% | GPS=" + lat + "," + lon;
                txtUltimaLectura.setText(lectura);
                historial.add(0, lectura);
                adaptador.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                txtUltimaLectura.setText("Error: " + error.getMessage());
            }
        });
    }
}
