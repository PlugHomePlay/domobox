#ifndef SCREEN_H
#define SCREEN_H

#include <Time.h>
#include <UTFT.h>
#include <UTouch.h>
#include <Timezone.h>
#include <DS1307RTC.h> 
#include <Wire.h>


extern uint8_t SmallFont[];
extern uint8_t BigFont[];
extern uint8_t SevenSegNumFont[];

class Screen
{
public:
	Screen(UTFT* glcd);
	void drawHeader();
	void drawContent();
	void drawFooter();
	void computeTouch();

private:

	void setDateTime();
	void showDate();
	void showTime(TimeChangeRule *tcr);

	bool isMenu;

	UTFT* glcd;
	UTouch* touch;

	static const char* shortWeekDays[];
	static const char* shortMonthNames[];
	static TimeChangeRule CEST;     //Central European Summer Time +2h
	static TimeChangeRule CET;       //Central European Standard Time +1h
	Timezone* myTimeZone;
	time_t utc, local;
};

#endif