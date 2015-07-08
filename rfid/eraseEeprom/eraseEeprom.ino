#include <EEPROM.h>

#define COMMON_ANODE

#ifdef COMMON_ANODE
  #define LED_ON LOW
  #define LED_OFF HIGH
#else
  #define LED_ON HIGH
  #define LED_OFF LOW
#endif

#define redLed 8
#define greenLed 7
#define blueLed 6

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  while(!Serial);
  
  Serial.println("Starting Wiping EEPROM");
  for(int x=0; x<1024; x++){
    if(EEPROM.read(x) != 0){
      EEPROM.write(x, 0);
    }
  }
  
  Serial.println("Wiped");
  digitalWrite(redLed, LED_OFF);
  delay(200);
  digitalWrite(redLed, LED_ON);
  delay(200);
  digitalWrite(redLed, LED_OFF);
  delay(200);
  digitalWrite(redLed, LED_ON);
  delay(200);
  digitalWrite(redLed, LED_OFF);
      
}

void loop() {
  // put your main code here, to run repeatedly:

}
