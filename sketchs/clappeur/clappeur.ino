#include <dht.h>
#define dht_dpin A0 

dht DHT;

const int DO_pin = 2;
const int AO_pin = 1;

const int redPin = 9;
const int greenPin = 8;

const int inter1 = 4;
const int inter2 = 5;
const int inter3 = 6;
const int inter4 = 7;

int sound;

void setup(){
  Serial.begin(9600);
  delay(300);//Let system settle
  Serial.println("Humidity and temperature\n\n");
  delay(700);//Wait rest of 1000ms recommended delay before
  //accessing sensor
  pinMode(DO_pin, INPUT);
  pinMode(redPin, OUTPUT);
  pinMode(greenPin, OUTPUT); 
  
  pinMode(inter1, OUTPUT); 
  pinMode(inter2, OUTPUT); 
  pinMode(inter3, OUTPUT); 
  pinMode(inter4, OUTPUT); 
  
  digitalWrite(inter4, HIGH);
}//end "setup()"

void loop() {
  
  if(sound > 50){
    Serial.print(digitalRead(DO_pin));
    Serial.print("-");
    Serial.println(analogRead(AO_pin));
    DHT.read11(dht_dpin);
    Serial.print("Current humidity = ");
    Serial.print(DHT.humidity);
    Serial.print("%  ");
    Serial.print("temperature = ");
    Serial.print(DHT.temperature); 
    Serial.println("C  ");
    delay(800);
    digitalWrite(redPin, !digitalRead(redPin));
    digitalWrite(greenPin, !digitalRead(redPin));
    
    digitalWrite(inter1, !digitalRead(redPin));
    digitalWrite(inter3, !digitalRead(redPin));
    
    digitalWrite(inter2, digitalRead(redPin));
  } 
  sound = analogRead(AO_pin);
  
}
