#include <EEPROM.h>
#include <SPI.h>
#include <MFRC522.h>


#define COMMON_ANODE

#ifdef COMMON_ANODE
  #define LED_ON LOW
  #define LED_OFF HIGH
#else
  #define LED_ON HIGH
  #define LED_OFF LOW
#endif

#define redLed 8
#define greenLed 7
#define blueLed 6
#define wipeB 3

boolean match = false; //initialize card match to false
boolean programMode = false; //initialize programming mode to false

int successRead;

byte storedCard[4]; //Stores an ID read from EEPROM
byte readCard[4]; //Stores scanned ID read from RFID Module
byte masterCard[4]; //Stores master card's ID read form EEPROM

#define SS_PIN 10
#define RST_PIN 5
MFRC522 mfrc522(SS_PIN, RST_PIN); //Create MRFC522 Instance

void setup() {
  pinMode(redLed, OUTPUT);
  pinMode(greenLed, OUTPUT);
  pinMode(blueLed, OUTPUT);

  digitalWrite(redLed, LED_OFF);
  digitalWrite(greenLed, LED_OFF);
  digitalWrite(blueLed, LED_OFF);
  
  Serial.begin(9600);
  while(!Serial);
  SPI.begin();
  mfrc522.PCD_Init();
  mfrc522.PCD_SetAntennaGain(mfrc522.RxGain_max);
  
  pinMode(wipeB, INPUT_PULLUP);
  if(digitalRead(wipeB) == LOW) {
    digitalWrite(redLed, LED_ON);
    Serial.println("Wipe Button Pressed");
    Serial.println("You have 5 seconds to Cancel");
    Serial.println("This will be remove all records and cannot be undone");
    delay(5000);
    if(digitalRead(wipeB) == LOW) {
      Serial.println("Starting Wiping EEPROM");
      for(int x=0; x<1024; x++){
        if(EEPROM.read(x) != 0){
          EEPROM.write(x, 0);
        }
      }
      
      Serial.println("Wiped");
      digitalWrite(redLed, LED_OFF);
      delay(200);
      digitalWrite(redLed, LED_ON);
      delay(200);
      digitalWrite(redLed, LED_OFF);
      delay(200);
      digitalWrite(redLed, LED_ON);
      delay(200);
      digitalWrite(redLed, LED_OFF);
   } else {
     Serial.println("!!!Wipping Cancelled !!!");
     digitalWrite(redLed, LED_OFF);
   }
  }
  
  if(EEPROM.read(1) != 143) {
    Serial.println("No Master Card Defined");
    Serial.println("Scan a PIC to define as Master Card");
    
    do {
      successRead = getID();
      digitalWrite(blueLed, LED_ON);
      delay(200);
      digitalWrite(blueLed, LED_OFF);
      delay(200);
    } while(!successRead);
    
    for(int x = 0; x < 4; x++){
      EEPROM.write(2 + x, readCard[x]);
    }
    EEPROM.write(1, 143);
    Serial.println("Master card defined");
  }
  
  Serial.println("#### RFID reader Access Control v1.0.0 ####");
  Serial.println("Master Card's UID");
  
  for(int x = 0; x < 4; x++){
    masterCard[x] = EEPROM.read(2+x);
    Serial.print(masterCard[x], HEX);
  }
  
  Serial.println("");
  Serial.println("Waiting PICs to be scanned");
  cycleLeds();
}

void loop() {
  do {
    successRead = getID(); // sets successRead to 1 when we get read from reader otherwise 0
    if (programMode) {
      cycleLeds(); // Program Mode cycles through RGB waiting to read a new card
    }
    else {
      normalModeOn(); // Normal mode, blue Power LED is on, all others are off
    }
  }
  while (!successRead); //the program will not go further while you not get a successful read
  if (programMode) {
    if ( isMaster(readCard) ) {  //If master card scanned again exit program mode
      Serial.println("This is Master Card"); 
      Serial.println("Exiting Program Mode");
      Serial.println("-----------------------------");
      programMode = false;
      return;
    }
    else {	
      if ( findID(readCard) ) { //If scanned card is known delete it
        Serial.println("I know this PICC, so removing");
        deleteID(readCard);
        Serial.println("-----------------------------");
      }
      else {                    // If scanned card is not known add it
        Serial.println("I do not know this PICC, adding...");
        writeID(readCard);
        Serial.println("-----------------------------");
      }
    }
  }
  else {
    if ( isMaster(readCard) ) {  // If scanned card's ID matches Master Card's ID enter program mode
      programMode = true;
      Serial.println("Hello Master - Entered Program Mode");
      int count = EEPROM.read(0); // Read the first Byte of EEPROM that
      Serial.print("I have ");    // stores the number of ID's in EEPROM
      Serial.print(count);
      Serial.print(" record(s) on EEPROM");
      Serial.println("");
      Serial.println("Scan a PICC to ADD or REMOVE");
      Serial.println("-----------------------------");
    }
    else {
      if ( findID(readCard) ) {        // If not, see if the card is in the EEPROM 
        Serial.println("Welcome, You shall pass");
        //openDoor(300);                // Open the door lock for 300 ms
      }
      else {				// If not, show that the ID was not valid
        Serial.println("You shall not pass");
        failed(); 
      }
    }
  }
}

int getID() {
  // Getting ready for Reading PICCs
  
  if ( ! mfrc522.PICC_IsNewCardPresent()) { //If a new PICC placed to RFID reader continue
    return 0;
  }
  if ( ! mfrc522.PICC_ReadCardSerial()) { //Since a PICC placed get Serial and continue
    return 0;
  }
  // There are Mifare PICCs which have 4 byte or 7 byte UID care if you use 7 byte PICC
  // I think we should assume every PICC as they have 4 byte UID
  // Until we support 7 byte PICCs
  Serial.println("Scanned PICC's UID:");
  for (int i = 0; i < 4; i++) {  // 
    readCard[i] = mfrc522.uid.uidByte[i];
    Serial.print(readCard[i], HEX);
  }
  Serial.println("");
  mfrc522.PICC_HaltA(); // Stop reading
  return 1;
}

void cycleLeds() {
  digitalWrite(redLed, LED_OFF); 
  digitalWrite(greenLed, LED_ON); 
  digitalWrite(blueLed, LED_OFF); 
  delay(200);
  digitalWrite(redLed, LED_OFF); 
  digitalWrite(greenLed, LED_OFF); 
  digitalWrite(blueLed, LED_ON); 
  delay(200);
  digitalWrite(redLed, LED_ON); 
  digitalWrite(greenLed, LED_OFF); 
  digitalWrite(blueLed, LED_OFF); 
  delay(200);
}

void normalModeOn () {
  digitalWrite(blueLed, LED_ON); // Blue LED ON and ready to read card
  digitalWrite(redLed, LED_OFF); // Make sure Red LED is off
  digitalWrite(greenLed, LED_OFF); // Make sure Green LED is off
  //digitalWrite(relay, HIGH); // Make sure Door is Locked
}

void readID( int number ) {
  int start = (number * 4 ) + 2; // Figure out starting position
  for ( int i = 0; i < 4; i++ ) { // Loop 4 times to get the 4 Bytes
    storedCard[i] = EEPROM.read(start+i); // Assign values read from EEPROM to array
  }
}

void writeID( byte a[] ) {
  if ( !findID( a ) ) { // Before we write to the EEPROM, check to see if we have seen this card before!
    int num = EEPROM.read(0); // Get the numer of used spaces, position 0 stores the number of ID cards
    int start = ( num * 4 ) + 6; // Figure out where the next slot starts
    num++; // Increment the counter by one
    EEPROM.write( 0, num ); // Write the new count to the counter
    for ( int j = 0; j < 4; j++ ) { // Loop 4 times
      EEPROM.write( start+j, a[j] ); // Write the array values to EEPROM in the right position
    }
    successWrite();
  }
  else {
    failedWrite();
  }
}

void deleteID( byte a[] ) {
  if ( !findID( a ) ) { // Before we delete from the EEPROM, check to see if we have this card!
    failedWrite(); // If not
  }
  else {
    int num = EEPROM.read(0); // Get the numer of used spaces, position 0 stores the number of ID cards
    int slot; // Figure out the slot number of the card
    int start;// = ( num * 4 ) + 6; // Figure out where the next slot starts
    int looping; // The number of times the loop repeats
    int j;
    int count = EEPROM.read(0); // Read the first Byte of EEPROM that stores number of cards
    slot = findIDSLOT( a ); //Figure out the slot number of the card to delete
    start = (slot * 4) + 2;
    looping = ((num - slot) * 4);
    num--; // Decrement the counter by one
    EEPROM.write( 0, num ); // Write the new count to the counter
    for ( j = 0; j < looping; j++ ) { // Loop the card shift times
      EEPROM.write( start+j, EEPROM.read(start+4+j)); // Shift the array values to 4 places earlier in the EEPROM
    }
    for ( int k = 0; k < 4; k++ ) { //Shifting loop
      EEPROM.write( start+j+k, 0);
    }
    successDelete();
  }
}

boolean checkTwo ( byte a[], byte b[] ) {
  if ( a[0] != NULL ) // Make sure there is something in the array first
    match = true; // Assume they match at first
  for ( int k = 0; k < 4; k++ ) { // Loop 4 times
    if ( a[k] != b[k] ) // IF a != b then set match = false, one fails, all fail
      match = false;
  }
  if ( match ) { // Check to see if if match is still true
    return true; // Return true
  }
  else  {
    return false; // Return false
  }
}

int findIDSLOT( byte find[] ) {
  int count = EEPROM.read(0); // Read the first Byte of EEPROM that
  for ( int i = 1; i <= count; i++ ) { // Loop once for each EEPROM entry
    readID(i); // Read an ID from EEPROM, it is stored in storedCard[4]
    if( checkTwo( find, storedCard ) ) { // Check to see if the storedCard read from EEPROM
      // is the same as the find[] ID card passed
      return i; // The slot number of the card
      break; // Stop looking we found it
    }
  }
}

boolean findID( byte find[] ) {
  int count = EEPROM.read(0); // Read the first Byte of EEPROM that
  for ( int i = 1; i <= count; i++ ) {  // Loop once for each EEPROM entry
    readID(i); // Read an ID from EEPROM, it is stored in storedCard[4]
    if( checkTwo( find, storedCard ) ) {  // Check to see if the storedCard read from EEPROM
      return true;
      break; // Stop looking we found it
    }
    else {  // If not, return false   
    }
  }
  return false;
}

void successWrite() {
  digitalWrite(blueLed, LED_OFF); // Make sure blue LED is off
  digitalWrite(redLed, LED_OFF); // Make sure red LED is off
  digitalWrite(greenLed, LED_OFF); // Make sure green LED is on
  delay(200);
  digitalWrite(greenLed, LED_ON); // Make sure green LED is on
  delay(200);
  digitalWrite(greenLed, LED_OFF); // Make sure green LED is off
  delay(200);
  digitalWrite(greenLed, LED_ON); // Make sure green LED is on
  delay(200);
  digitalWrite(greenLed, LED_OFF); // Make sure green LED is off
  delay(200);
  digitalWrite(greenLed, LED_ON); // Make sure green LED is on
  delay(200);
  Serial.println("Succesfully added ID record to EEPROM");
}

void failedWrite() {
  digitalWrite(blueLed, LED_OFF); // Make sure blue LED is off
  digitalWrite(redLed, LED_OFF); // Make sure red LED is off
  digitalWrite(greenLed, LED_OFF); // Make sure green LED is off
  delay(200);
  digitalWrite(redLed, LED_ON); // Make sure red LED is on
  delay(200);
  digitalWrite(redLed, LED_OFF); // Make sure red LED is off
  delay(200);
  digitalWrite(redLed, LED_ON); // Make sure red LED is on
  delay(200);
  digitalWrite(redLed, LED_OFF); // Make sure red LED is off
  delay(200);
  digitalWrite(redLed, LED_ON); // Make sure red LED is on
  delay(200);
  Serial.println("Failed! There is something wrong with ID or bad EEPROM");
}

void successDelete() {
  digitalWrite(blueLed, LED_OFF); // Make sure blue LED is off
  digitalWrite(redLed, LED_OFF); // Make sure red LED is off
  digitalWrite(greenLed, LED_OFF); // Make sure green LED is off
  delay(200);
  digitalWrite(blueLed, LED_ON); // Make sure blue LED is on
  delay(200);
  digitalWrite(blueLed, LED_OFF); // Make sure blue LED is off
  delay(200);
  digitalWrite(blueLed, LED_ON); // Make sure blue LED is on
  delay(200);
  digitalWrite(blueLed, LED_OFF); // Make sure blue LED is off
  delay(200);
  digitalWrite(blueLed, LED_ON); // Make sure blue LED is on
  delay(200);
  Serial.println("Succesfully removed ID record from EEPROM");
}

boolean isMaster( byte test[] ) {
  if ( checkTwo( test, masterCard ) )
    return true;
  else
    return false;
}

void failed() {
  digitalWrite(greenLed, LED_OFF); // Make sure green LED is off
  digitalWrite(blueLed, LED_OFF); // Make sure blue LED is off
  digitalWrite(redLed, LED_ON); // Turn on red LED
  delay(1200);
}



