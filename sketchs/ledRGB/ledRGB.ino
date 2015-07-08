#include <string.h>

int const red = 2;
int const green = 3;
int const blue = 4;

int ledDigitalOne[] = {2, 3, 4};

String inputString = "";         // a string to hold incoming data
boolean stringComplete = false; 

void setup()
{
  Serial.begin(9600);
  inputString.reserve(200);
}

void loop(){
  
  int redValue = random(0,255);
  int greenValue = random(0,255);
  int blueValue = random(0,255);
  
  int color[] = {redValue, greenValue, blueValue};
  
  setColor(ledDigitalOne, color);
  Serial.print("valeur de rouge : ");
  Serial.println(redValue);
  
  Serial.print("valeur de vert : ");
  Serial.println(greenValue);
  
  Serial.print("valeur de bleue : ");
  Serial.println(blueValue);
  
  if (stringComplete) {
    Serial.println(inputString);
    //Serial.println(getValue(inputString, ' ', 0));
    inputString = "";
    stringComplete = false;
  }
  
  delay(5000);
}

void serialEvent(){
 while(Serial.available()){
  Serial.println("reception de commande");
   char inChar =  (char)Serial.read();
     inputString += inChar;
     
     if(inChar == '\n' || inChar == '\r\n') {
       stringComplete = true;
       
     }
 } 
}

void setColor(int* led, int* color) {
 for(int i = 0 ; i < 3 ; i++){
   Serial.print("couleur ");
   Serial.print(i);
   Serial.print(": ");
   Serial.print(color[i]);
   
   Serial.print(" sur la pin  ");
   Serial.print(": ");
   Serial.println(led[i]);
   
  digitalWrite(led[i], HIGH);
 } 
}
