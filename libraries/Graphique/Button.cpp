#include "Button.h"


Button::Button(int x, int y){
	_x = x;
	_y = y;
	_on = false;
}

Button::Button(){
	_on = false;
}

Button::~Button(){

}

void Button::setX(int x){
	_x = x;
}

int Button::getX(){
	return this->_x;
}

void Button::setY(int y){
	_y = y;
}

int Button::getY(){
	return this->_y;
}

void Button::setScale(int scale){
	_scale = scale;
}

int Button::getScale(){
	return this->_scale;
}

bool Button::isOn(){
	return _on;
}

void Button::draw(UTFT* myGLCD){
	if(_on){
		drawOn(myGLCD);
	} else {
		drawOff(myGLCD);
	}
}

void Button::drawOn(UTFT* myGLCD){
	int width = 40 * _scale;
	int height = 20 * _scale;
	myGLCD->setColor(0, 255, 0);
	myGLCD->fillRoundRect(_x, _y, _x + width , _y + height);
}

void Button::drawOff(UTFT* myGLCD){
	int width = 40 * _scale;
	int height = 20 * _scale;
	myGLCD->setColor(255, 0, 0);
	myGLCD->fillRoundRect(_x, _y, _x + width , _y + height);
}

void DoubleButton::draw(UTFT* myGLCD){
	int width = 40 * getScale();
	int height = 20 * getScale();
	myGLCD->setColor(0, 255, 0);
	myGLCD->fillRoundRect(getX(), getY(), getX() + width , getY() + height);
	myGLCD->setColor(255, 0, 0);
	myGLCD->fillRoundRect(getX() + width, getY(), getX() + (width * 2), getY() + height);
}

DoubleButton::~DoubleButton(){

}