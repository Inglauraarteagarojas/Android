RadioGroup grupo = findViewById(R.id.grupoComunicacion);
CheckBox chkGps = findViewById(R.id.chkGps);
CheckBox chkTemperatura = findViewById(R.id.chkTemperatura);
CheckBox chkHumedad = findViewById(R.id.chkHumedad);
TextView salida = findViewById(R.id.txtConfiguracion);
Button guardar = findViewById(R.id.btnConfigurar);

guardar.setOnClickListener(v -> {
    int idSeleccionado = grupo.getCheckedRadioButtonId();
    if (idSeleccionado == -1) {
        salida.setText("Selecciona un canal de comunicación");
        return;
    }

    RadioButton canal = findViewById(idSeleccionado);
    StringBuilder sensores = new StringBuilder();
    if (chkGps.isChecked()) sensores.append("GPS ");
    if (chkTemperatura.isChecked()) sensores.append("Temperatura ");
    if (chkHumedad.isChecked()) sensores.append("Humedad ");

    if (sensores.length() == 0) {
        salida.setText("Selecciona al menos un sensor");
        return;
    }

    salida.setText("Canal: " + canal.getText()
            + "
Sensores: " + sensores);
});
