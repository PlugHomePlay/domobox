#include "Ecran.h"

Ecran::Ecran(UTFT* glcd){
	this->glcd = glcd;
}

void Ecran::drawScreen(){
	Screen screen(this->glcd);
	screen.drawHeader();
}