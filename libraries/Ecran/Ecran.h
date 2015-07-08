#ifndef ECRAN_H
#define ECRAN_H

#if ARDUINO >= 100
#include <Arduino.h> 
#else
#include <WProgram.h> 
#endif
#include <Time.h>

#include "Screen.h"

class Ecran{

public:
	Ecran(UTFT* glcd);
	void drawScreen();
private:

	UTFT* glcd;
};

#endif