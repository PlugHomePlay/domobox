#include "Commande.h"

Commande::Commande(String commande){
	commande.trim();
	_commande = commande;
	_reader = commande.substring(0,1) == "#" ? true : false;

	#ifdef DEBUG
		Serial.print("reader : ");
		Serial.println(_reader == 1 ? "oui": "non");
	#endif
	//retrait du premier et du dernier caractere
	commande = commande.substring(1, commande.length() - 1);

	int idx = commande.indexOf(",");

	_analogic = stringToInt(commande.substring(0,idx)) == ANALOGIC ? true : false;

	#ifdef DEBUG
		Serial.print("analogique : ");
		Serial.println(_analogic == 1 ? "oui": "non");
	#endif
	commande = commande.substring(idx + 1);
	#ifdef DEBUG
		Serial.print("commande : ");
		Serial.println(commande);
	#endif
	idx = commande.indexOf(",");

	int beginIdx = 0;
	String arg;
	int iteration = 0;

	int datas = 0;

	while(idx != -1){
		if(iteration == 0 && !_reader){
			iteration++;
			beginIdx = idx + 1;
			idx = commande.indexOf(",", beginIdx);
		} else {
			beginIdx = idx + 1;
			idx = commande.indexOf(",", beginIdx);
			datas++;
			iteration++;
		}
	}

	#ifdef DEBUG
		Serial.print("nombre de port(s) : ");
		Serial.println(datas + 1);
	#endif
	idx = commande.indexOf(",");
	beginIdx = 0;
	iteration = 0;
	int indexTableau = 0;
	_nbrElement = datas;
	//_pins = new int[_nbrElement];

	while(idx != -1) 
	{
		arg = commande.substring(beginIdx, idx);
		if(iteration == 0 && !_reader){
			iteration++;
			_value = stringToInt(arg);
			#ifdef DEBUG
				Serial.print("value : ");
				Serial.println(_value);
			#endif
			beginIdx = idx + 1;
			idx = commande.indexOf(",", beginIdx);
		} else {
			//Serial.print("port : ");
			int valeur = stringToInt(arg);
			//Serial.println(valeur);

			_pins[indexTableau] = valeur;
			#ifdef DEBUG
				Serial.print("Assignation de la valeur ");
				Serial.print(valeur);
				Serial.print(" a l'index du tableau ");
				Serial.println(indexTableau);
			#endif
			beginIdx = idx + 1;
			idx = commande.indexOf(",", beginIdx);
			iteration++;
			indexTableau++;
		}
	}
	//Serial.print("port : ");
	int valeur = stringToInt(commande.substring(beginIdx));
	//Serial.println(valeur);
	_pins[indexTableau] = valeur;
	#ifdef DEBUG
		Serial.print("Assignation de la valeur ");
		Serial.print(valeur);
		Serial.print(" a l'index du tableau ");
		Serial.println(indexTableau);
	#endif
}

Commande::~Commande(){
	//delete _controller;
}
  
boolean Commande::isAnalogique(){
	return _analogic;
}

int Commande::getValue(){
	return _value;
}

int* Commande::getPins(){
	return _pins;
}

void Commande::compute(){
	#ifdef DEBUG
		Serial.print("Taille du tableau de pins : ");
		Serial.println(_nbrElement);
	#endif
	if(!_reader){
		if(_analogic){
			
		} else {
			for(int i = 0 ; i <= _nbrElement; i++){
				digitalWrite(_pins[i], _value);
				#ifdef DEBUG
					Serial.print("Assignation de la valeur ");
					Serial.print(_value);
					Serial.print(" au port ");
					Serial.println(_pins[i]);
				#endif
			}
		}
	}
}

int Commande::stringToInt(String chaine){
	char charBuffer[16];
	chaine.toCharArray(charBuffer, 16);

	return atoi(charBuffer);
}