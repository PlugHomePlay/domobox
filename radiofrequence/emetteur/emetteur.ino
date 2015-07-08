#include <RCSwitch.h>

RCSwitch mySwitch = RCSwitch();

void setup() {
  mySwitch.enableTransmit(10);  // Using Pin #10
}

void loop() {
  mySwitch.send("000000000001010100010001");
  delay(1000);  
}
