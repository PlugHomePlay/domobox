#include <LedRGB.h>
#include <Commande.h>

#include <string.h>

#define DEBUG

LedRGB* ledRGB;

String inputString = "";         // a string to hold incoming data
boolean stringComplete = false;  // whether the string is complete

void setup() {
  ledRGB = new LedRGB(2,3,4);
  Serial.begin(9600);
  while(!Serial){
    ;
  }
  inputString.reserve(200);
}

void loop() {
  //ledRGB->colorize(255,255,255);
  //delay(1000);
  ledRGB->progressiveColorize(49,140,231);
  delay(1000);
  
  if (stringComplete) {
    //Serial.println(inputString);
    //Serial.println(getValue(inputString, ' ', 0));
    inputString = "";
    stringComplete = false;
  }
  
}

void serialEventRun(void) {
  if (Serial.available()) serialEvent();
} 

void serialEvent(){
   while(Serial.available()){
     char inChar =  (char)Serial.read();
     inputString += inChar;
     Serial.println(inputString);
     if(inChar == '\n' || inChar == '\r\n') {
       stringComplete = true;
       Commande commande(inputString);
       commande.compute();
     }
   } 
}

