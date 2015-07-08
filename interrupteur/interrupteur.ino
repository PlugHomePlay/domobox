#include <UTFT.h>
#include <UTouch.h>
#include <avr/pgmspace.h>

// Declare wich font we will be using
extern uint8_t BigFont[];

// Declare wich image we will be using
extern unsigned int moon[0x400];
extern unsigned int sun[0x400];

// Set the pins to the correct ones
UTFT myGLCD(ITDB32S,38,39,40,41);
UTouch myTouch(6,5,4,3,2);

//Coordonate touch
int x,y;

void setup(){
  myGLCD.InitLCD(PORTRAIT);
  myGLCD.clrScr();  
  myGLCD.setFont(BigFont);
  
  myTouch.InitTouch();
  myTouch.setPrecision(PREC_MEDIUM);
  
  // initialize serial communication at 9600 bits per second:
  Serial.begin(9600);
  attachInterrupt(0, onTouch, CHANGE);
}

void waitForIt(int x1, int y1, int x2, int y2)
{
  myGLCD.setColor(255,0,0);
  myGLCD.drawRect (x1, y1, x2, y2);
  while(myTouch.dataAvailable()){
     myTouch.read(); 
  }
  myGLCD.setColor(0,0,0);
  myGLCD.drawRect (x1, y1, x2, y2);
}

void drawVariateur(){
   // Lune
   myGLCD.drawBitmap(10,10,32,32, moon);
   //Soleil
   myGLCD.drawBitmap(200,10,32,32, sun);
}

void drawWeather(){
  
}

void drawInterrupteur(){
  drawVariateur();
}
void loop(){

   drawInterrupteur();
   
   // Nom du proprietaire de la chambre
   myGLCD.print("MAME", CENTER, 80);
   
   // Temperature de la chambre
   myGLCD.print("17.0 C", CENTER, 120);
   serialEvent1();
}

void serialEvent1(){
  while(Serial.available()){
   Serial.println("reception de donnees serialEvent1");
  }   
}

void serialEvent2(){
  while(Serial.available()){
   Serial.println("reception de donnees serialEvent2");
  }   
}

void serialEvent3(){
  while(Serial.available()){
   Serial.println("reception de donnees serialEvent3");
  }   
}

void onTouch(){
  while (true) {
     if(myTouch.dataAvailable())
     {
       myTouch.read();
       x = myTouch.getX();
       y = myTouch.getY();
       
       Serial.print("touched at (");
       Serial.print(x);
       Serial.print(", ");
       Serial.print(y);
       Serial.println(")");
       
       //bandeau haut
       if((x>=275) && (x<=315))
       {
         //lune
         if((y>=10) && (y<=50))
         {
           //waitForIt(315,40,290,10);
           Serial.println("moon of requested.");
           myGLCD.lcdOff();
         }
         
         if((y>=190) && (y<=240))
         {
           Serial.println("sun of requested.");
         }
         
       }
     }
  }
}
