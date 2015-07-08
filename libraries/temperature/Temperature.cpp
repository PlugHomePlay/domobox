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
  // data : Données lues depuis le scratchpad
  // addr : adresse du module 1-Wire détecté
 
  if (!ds->search(addr)) { // Recherche un module 1-Wire
    ds->reset_search();    // Réinitialise la recherche de module
    return false;         // Retourne une erreur
  }
   
  if (OneWire::crc8(addr, 7) != addr[7]) // Vérifie que l'adresse a été correctement reçue
    return false;                        // Si le message est corrompu on retourne une erreur
 
  if (addr[0] != DS18B20) // Vérifie qu'il s'agit bien d'un DS18B20
    return false;         // Si ce n'est pas le cas on retourne une erreur
 
  ds->reset();             // On reset le bus 1-Wire
  ds->select(addr);        // On sélectionne le DS18B20
   
  ds->write(0x44, 1);      // On lance une prise de mesure de température
  delay(800);             // Et on attend la fin de la mesure
   
  ds->reset();             // On reset le bus 1-Wire
  ds->select(addr);        // On sélectionne le DS18B20
  ds->write(0xBE);         // On envoie une demande de lecture du scratchpad
 
  for (byte i = 0; i < 9; i++) // On lit le scratchpad
    data[i] = ds->read();       // Et on stock les octets reçus
   
  // Calcul de la température en degré Celsius
  *temp = ((data[1] << 8) | data[0]) * 0.0625; 
   
  // Pas d'erreur
  return true;
}