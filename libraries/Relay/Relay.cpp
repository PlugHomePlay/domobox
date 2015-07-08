#include "Relay.h"

Relay::Relay(int pin){
	pinMode(pin, OUTPUT);
	downRelay();
	_pin = pin;
}

Relay::~Relay(){

}
  
void Relay::upRelay(){
	digitalWrite(_pin, HIGH);
}

void Relay::downRelay(){
	digitalWrite(_pin, LOW);
}

void Relay::toggleRelay(){
	digitalWrite(_pin, !digitalRead(_pin));
}

int Relay::status(){
	return digitalRead(_pin);
}
