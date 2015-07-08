#include "LedRGB.h"



LedRGB::LedRGB(uint8_t redPin, uint8_t greenPin, uint8_t bluePin){
	this->redPin = redPin;
	this->greenPin = greenPin;
	this->bluePin = bluePin;

	this->previousRed=0;
	this->previousGreen=0;
	this->previousBlue=0;
}

void LedRGB::colorize(uint8_t redValue, uint8_t greenValue, uint8_t blueValue){
	analogWrite(this->redPin, redValue);
	analogWrite(this->greenPin, greenValue);
	analogWrite(this->bluePin, blueValue);

	this->previousRed=redValue;
	this->previousGreen=greenValue;
	this->previousBlue=blueValue;
}

void LedRGB::progressiveColorize(uint8_t redValue, uint8_t greenValue, uint8_t blueValue){

	for(int i = 0; i <= 255; i++){
		if( i >= previousRed - redValue && previousRed < redValue){
			analogWrite(redPin, previousRed + i);
		}

		if( i >= previousGreen - greenValue && previousGreen < greenValue){
			analogWrite(greenPin, previousGreen + i);
		}

		if( i >= previousBlue - blueValue && previousBlue < blueValue){
			analogWrite(bluePin, previousBlue + i);
		}

		if( i >= redValue - previousRed && previousRed > redValue){
			analogWrite(redPin, previousRed - i);
		}

		if( i >= greenValue - previousGreen && previousGreen > greenValue){
			analogWrite(greenPin, previousGreen - i);
		}

		if( i >= blueValue - previousBlue && previousBlue > blueValue){
			analogWrite(bluePin, previousBlue - i);
		}

		delay (10);
	}

	delay(10);

	this->previousRed=redValue;
	this->previousGreen=greenValue;
	this->previousBlue=blueValue;
}