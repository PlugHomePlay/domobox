/*
	Commande.h - Library for command control.
	Created by Primael Bruant, February 21, 2015.
*/
#ifndef Commande_h
#define Commande_h

#include <Arduino.h>

#define DIGITAL 0
#define ANALOGIC 1

class Commande
{
public:

  Commande(String commande);
  ~Commande();

  boolean isAnalogique();
  int getValue();
  int* getPins();

  void compute();

private:
  int stringToInt(String chaine);
  int _value;
  int _pins[16];
  String _commande;
  bool _reader;
  bool _analogic;
  int _nbrElement;
};

#endif