#include <WiFi.h>
#include <HTTPClient.h>

const char* WIFI_SSID = "TU_RED";
const char* WIFI_PASS = "TU_CLAVE";
// La URL debe terminar en .json para usar la API REST de Realtime Database.
const char* FIREBASE_URL =
  "https://TU-PROYECTO-default-rtdb.firebaseio.com/picosat/telemetria.json";

void setup() {
  Serial.begin(115200);
  WiFi.begin(WIFI_SSID, WIFI_PASS);
  while (WiFi.status() != WL_CONNECTED) delay(500);
}

void loop() {
  float temperatura = 24.6;  // Reemplazar por lectura real del sensor
  float humedad = 61.3;
  double latitud = 4.7109;   // Reemplazar por módulo GPS
  double longitud = -74.0721;

  String json = "{"temperatura":" + String(temperatura, 1)
      + ","humedad":" + String(humedad, 1)
      + ","latitud":" + String(latitud, 6)
      + ","longitud":" + String(longitud, 6) + "}";

  if (WiFi.status() == WL_CONNECTED) {
    HTTPClient http;
    http.begin(FIREBASE_URL);
    http.addHeader("Content-Type", "application/json");
    int codigo = http.PUT(json);
    Serial.println(codigo);
    http.end();
  }

  delay(5000);
}
