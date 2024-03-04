
// Firebase Arduino Client Library for ESP8266 and ESP32 - Version: 4.2.0
#include <FirebaseESP8266.h>

// ESP8266WiFi for esp8266 - Version: Latest 
#include <ESP8266WiFi.h>

#include<Servo.h>
Servo servo;

// Wifi Details
#define wifiUsername "Nova12"
#define wifiPassword "012012012"

// Firebase Details
#define firebaseHostURL "smartdoor-e4489-default-rtdb.firebaseio.com"
#define firebaseAuth "Au5GTw0y49boUPiZrVBj9cHDJpZ1uc9wQtjASFJW"

#define buzzer D1
#define trigger D5


FirebaseData firebaseData;
FirebaseData doorUpdate;  
FirebaseJson firebaseJson;


void setup() {
  Serial.begin(115200);
  WiFi.begin(wifiUsername, wifiPassword);
  
  Serial.print("Waiting for WiFi to be successfully connected with NodeMCU-ESP8266");
  while(WiFi.status() == WL_CONNECTED){
    Serial.print(".");
    delay(1000);
  }
  Serial.println();
  Serial.print("Ip Address of ESP8266: ");
  Serial.println(WiFi.localIP());

  pinMode(trigger, INPUT);
  pinMode(buzzer, OUTPUT);
  
  servo.attach(2);
  servo.write(2);
  
  Firebase.begin(firebaseHostURL, firebaseAuth);
  Firebase.reconnectWiFi(true);
}

void loop() {

  //Reading value from database.
  if(Firebase.getString(doorUpdate, "/MainDoor/Mode")){
    String doorModeFirebase = doorUpdate.stringData();
    Firebase.getString(doorUpdate, "/MainDoor/Status");
    String currentDoorFirebase = doorUpdate.stringData();    
    Firebase.getString(doorUpdate, "/MainDoor/Buzzer");    
    String buzzerStat = doorUpdate.stringData();
    int signal = digitalRead(trigger);    
    Serial.println(signal);    
        
    //(doorModeFirebase == "lock" || doorModeFirebase == "unlock") && currentDoorFirebase == "open" &&
    if(signal == 0){
      Serial.println("Door is open/unlock.");
      Firebase.setString(firebaseData, "/MainDoor/Status", "open");
      //Ring the Buzzer.
      if(buzzerStat == "On"){
        digitalWrite(buzzer, HIGH);
        delay(2000);
        digitalWrite(buzzer, LOW);    
      }else{
        digitalWrite(buzzer, LOW);        
      }
      
      //Serial.println("Buzzer is ringing");
      //digitalWrite(buzzer, HIGH);
      //delay(2000);
      //digitalWrite(buzzer, LOW);
      //Firebase.setString(firebaseData, "/MainDoor/Mode", "unlock");      
    }else if(currentDoorFirebase == "close" && doorModeFirebase == "lock"){
      Serial.println("Locking the door.");
      servo.write(180);
      delay(2000);
      //Perform servo motor to lock and close the door.
    }else if(currentDoorFirebase == "close" && doorModeFirebase == "unlock"){
      Serial.println("Unlocking the door.");
      servo.write(0);
      delay(2000);
      //Perform servo motor to lock and0 close the door.
    }else{
      Firebase.setString(firebaseData, "/MainDoor/Status", "close");      
    }
    delay(100);
  }
      
}
