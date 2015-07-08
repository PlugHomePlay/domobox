/*
	Temperature.h - Library for get temperature.
	Created by Primael Bruant, February 21, 2015.
*/
#ifndef Temperature_h
#define Temperature_h

#include <Arduino.h>
#include <OneWire.h>
#include <inttypes.h>

class Temperature
{
public:
  Temperature(uint8_t pin);
  ~Temperature();
  
  boolean getTemperature(float *temp);
private:
  OneWire* ds;
};

#endif