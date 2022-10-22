int MoisturePin = A0;
float moistureValue = 0;

void setup() {
  Serial.begin(9600);
}

void loop() {
  for (int i = 0; i <= 100; i++)
  {
    moistureValue = moistureValue + analogRead(MoisturePin);
    delay(1);
  }
  moistureValue = moistureValue/100.0;
  Serial.println(moistureValue);
  delay(1000);
}
