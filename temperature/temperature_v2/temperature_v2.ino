
#include <Temperature.h>
#include <Commande.h>

#include <string.h>

String inputString = "";         // a string to hold incoming data
boolean stringComplete = false;  // whether the string is complete

Temperature temperature(7);

void setup(){
  Serial.begin(9600);
  while(!Serial){
    ;
  }
  inputString.reserve(200);
  pinMode(2, OUTPUT);
  pinMode(3, OUTPUT);
  pinMode(4, OUTPUT);  
}

void loop(){
  float temp;
  
  if( temperature.getTemperature(&temp)){
    Serial.println(temp); 
  }
  
  if (stringComplete) {
    //Serial.println(inputString);
    //Serial.println(getValue(inputString, ' ', 0));
    inputString = "";
    stringComplete = false;
  }
}

void serialEvent(){
   while(Serial.available()){
     char inChar =  (char)Serial.read();
     inputString += inChar;
     
     if(inChar == '\n' || inChar == '\r\n') {
       stringComplete = true;
       Commande commande(inputString);
       Serial.println(commande.isAnalogique());
       commande.compute();
     }
   } 
}
