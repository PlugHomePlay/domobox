#ifndef BUTTON_H
#define BUTTON_H

#include <Arduino.h>
#include <../UTFT/UTFT.h>

class Button
{

	public:
		Button();
		Button(int x, int y);
		~Button();

		bool isOn();
		void setScale(int scale);
		void setX(int x);
		void setY(int y);

		int getScale();
		int getX();
		int getY();

		void draw(UTFT* myGLCD);


		//void onPress();

	private:

		void drawOn(UTFT* myGLCD);
		void drawOff(UTFT* myGLCD);
		
		int _x;
		int _y;
		int _scale;

		bool _on;
};

class DoubleButton : public Button
{

	public:
		DoubleButton():Button(){};
		DoubleButton(int x, int y):Button(x, y){};
		~DoubleButton();

		void draw(UTFT* myGLCD);
		//void onPress();
};

#endif