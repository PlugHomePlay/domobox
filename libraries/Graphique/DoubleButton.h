#ifndef DOUBLEBUTTON_H
#define DOUBLEBUTTON_H

#include "Button.h"

class DoubleButton : public Button
{

	public:
		DoubleButton():Button(){};
		DoubleButton(int x, int y):Button(x, y){};
		~DoubleButton():;

		void draw(UTFT* myGLCD);
		//void onPress();
};

#endif