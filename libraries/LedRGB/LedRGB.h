/*
	LeRGB.h - Library for control led.
	Created by Primael Bruant, February 21, 2015.
*/
#ifndef LedRGB_h
#define LedRGB_h

#include <Arduino.h>
#include <inttypes.h>

class LedRGB
{
public:
  LedRGB(uint8_t redPin, uint8_t greenPin, uint8_t bluePin);
  ~LedRGB();
  
  void colorize(uint8_t redValue, uint8_t greenValue, uint8_t blueValue);

  void progressiveColorize(uint8_t redValue, uint8_t greenValue, uint8_t blueValue);

private:
  uint8_t redPin;
  uint8_t greenPin;
  uint8_t bluePin;
  uint8_t previousRed;
  uint8_t previousGreen;
  uint8_t previousBlue;
};

#endif