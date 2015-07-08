#include "Screen.h"

Screen::Screen(UTFT* glcd){
	this->glcd = glcd;
	this->myTimeZone = new Timezone(CEST, CET);
	this->isMenu = false;
};


void Screen::drawHeader(){

	this->utc = now();
	TimeChangeRule *tcr; 
	this->local = this->myTimeZone->toLocal(utc, &tcr);

	//icône -- Date
	showDate();
	showTime(tcr);
};

void Screen::showDate(){
	this->glcd->setColor(150,150,150);
	this->glcd->setFont(SmallFont);
	if(isMenu){	
		this->glcd->print(shortWeekDays[weekday()], 0, 10);
		this->glcd->printNumI(day(local), 30, 10, 2);
		this->glcd->print(shortMonthNames[month(local)], 55, 10, 2);
		this->glcd->printNumI(year(local), 90, 10, 2);
	} else {
		this->glcd->printNumI(day(local), 0, 10, 2, '0');
		this->glcd->print("/", 15, 10);
		this->glcd->printNumI(month(local), 22, 10, 2, '0');		
		this->glcd->print("/", 35, 10);
		this->glcd->printNumI(year(local), 41, 10);
	}
};

void Screen::showTime(TimeChangeRule *tcr){
	char *timezone = tcr -> abbrev;

	this->glcd->setColor(100, 100, 200);
	this->glcd->setFont(SmallFont);
	this->glcd->print(timezone, 5, 50);

	this->glcd->setFont(SmallFont);
	this->glcd->printNumI(hour(local), 190, 10, 2 ,'0');
	this->glcd->print(":", 204, 10);
	this->glcd->printNumI(minute(local), 210, 10, 2 ,'0');

	this->glcd->setFont(BigFont);
	this->glcd->printNumI(second(), 50, 50, 2 ,'0');
};

void  Screen::drawContent(){
	
};

void  Screen::drawFooter(){
	
};

void  Screen::computeTouch(){
	
};

void  Screen::setDateTime(){
	int second = 30;
	int minute = 2;
	int hour = 14;
	int weekDay = 5;
	int monthDay = 26;
	int month = 2;
	int year = 2015;

	setTime(hour, minute, second, monthDay, month, year);
	RTC.set(now());
};

const char* Screen::shortWeekDays[] = {
	"   \0",
	"Dim\0",
	"Lun\0",
	"Mar\0",
	"Mer\0",
	"Jeu\0",
	"Ven\0",
	"Sam\0"
};

const char* Screen::shortMonthNames[] = {
	"    \0",
	"Janv.\0",
	"Févr.\0",
	"Mars\0",
	"Avr.\0",
	"Mai\0",
	"Juin\0",
	"Juill.\0",
	"Août\0",
	"Sept.\0",
	"Oct.\0",
	"Nov.\0",
	"Déc.\0"
};

TimeChangeRule Screen::CEST = {"CEST", Last, Sun, Mar, 2, 120};
TimeChangeRule Screen::CET = {"CET", Last, Sun, Oct, 3, 60};