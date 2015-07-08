#include "Temperature.h"
#include "../OneWire/OneWire.cpp"

#define DS18B20 0x28

Temperature::Temperature(uint8_t pin)
{
  ds = new OneWire(pin);
}

Temperature::~Temperature()
{
  delete ds;
}

boolean Temperature::getTemperature(float *temp)
{
  byte data[9], addr[8];
  // data : Donn�es lues depuis le scratchpad
  // addr : adresse du module 1-Wire d�tect�
 
  if (!ds->search(addr)) { // Recherche un module 1-Wire
    ds->reset_search();    // R�initialise la recherche de module
    return false;         // Retourne une erreur
  }
   
  if (OneWire::crc8(addr, 7) != addr[7]) // V�rifie que l'adresse a �t� correctement re�ue
    return false;                        // Si le message est corrompu on retourne une erreur
 
  if (addr[0] != DS18B20) // V�rifie qu'il s'agit bien d'un DS18B20
    return false;         // Si ce n'est pas le cas on retourne une erreur
 
  ds->reset();             // On reset le bus 1-Wire
  ds->select(addr);        // On s�lectionne le DS18B20
   
  ds->write(0x44, 1);      // On lance une prise de mesure de temp�rature
  delay(800);             // Et on attend la fin de la mesure
   
  ds->reset();             // On reset le bus 1-Wire
  ds->select(addr);        // On s�lectionne le DS18B20
  ds->write(0xBE);         // On envoie une demande de lecture du scratchpad
 
  for (byte i = 0; i < 9; i++) // On lit le scratchpad
    data[i] = ds->read();       // Et on stock les octets re�us
   
  // Calcul de la temp�rature en degr� Celsius
  *temp = ((data[1] << 8) | data[0]) * 0.0625; 
   
  // Pas d'erreur
  return true;
}