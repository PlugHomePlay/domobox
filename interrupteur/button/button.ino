#include <Button.h>
#include <UTFT.h>
extern uint8_t BigFont[];

UTFT myGLCD(ITDB32S,38,39,40,41);
Button button;

void setup(){
  myGLCD.InitLCD(PORTRAIT);
  myGLCD.clrScr();  
  myGLCD.setFont(BigFont);
  button.setScale(2);
}

void loop(){
  button.draw(&myGLCD);
}



