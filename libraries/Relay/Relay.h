/*
	Relay.h - Library for control relay.
	Created by Primael Bruant, February 21, 2015.
*/
#ifndef Relay_h
#define Relay_h

#include <Arduino.h>

class Relay
{
public:
  Relay(int pin);
  ~Relay();
  
  void upRelay();
  void downRelay();
  void toggleRelay();
  int status();

private:
  int _pin;
};

#endif