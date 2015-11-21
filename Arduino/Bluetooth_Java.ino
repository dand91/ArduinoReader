  
  String command = "";
  String received = "";
  
  String truePassword = "David";
  String PWDstring = "PWD";
  String testPassword = "noPassword";
  String comPassword = "noPassword";
  
  String CMDstring = "CMD";
  String restartCommand = "stop";
  String iterationCommand = "rate";

  boolean run = false;
  
  //Initial iteration speed
  int iteration = 10000;
  
  void setup() {
    
    pinMode(13, OUTPUT);
    pinMode(12, OUTPUT);
  
    Serial.begin(9600);      
    
    toSerial( "Arduino_started,_awaiting_command", "C" );

   }
  
  //Outer loop
  void loop() {
    
    //Try reading incoming trafic for password
    if( Serial.available() ){
      
        received = Serial.readString();  
        
        //Command, should be "PWD"
        command = received.substring(0,3);
        
        //Password, should be "David"
        testPassword = received.substring(4,received.length());

    if( command.equals(PWDstring) && testPassword.equals(truePassword) ){
      
           //If logic is true, command and password is correc
           
           toSerial( "Password_accepted", "C" );
            
           //run is set to true as a mean to start/stup inner loop
           run = true;
           
           blinkLED(0,500,12);
      
      //Inner loop
      while(run){
        
        //Read from analog pin 5
        int val = analogRead(5);  
        
        //Send value to client, port S
        toSerial( String(val) , "S" );
        
        blinkLED(iteration-100,100,13);
  
           //Try reading incoming trafic for commands
          if( Serial.available() ){
                  
                received = Serial.readString();  
                
                //Command should be "CMD"
                command = received.substring(0,3);

              if(command.equals(CMDstring)){
                
                //Command should be "stop" or "rate"
                String subcommand = received.substring(4,8);

              if(subcommand.equals(restartCommand)){
                
                //Exit inner loop
                run = false;
                //reset password
                testPassword = comPassword;
                
                toSerial( "Process_stopped" , "C" );
                
                blinkLED(0,500,12);
                
             }else if(subcommand.equals(iterationCommand)){
  
               //Extract and update iteration speed
               iteration = received.substring(9,received.length()).toInt();
              
               toSerial( "Iteration_speed_" +  String(iteration), "C" );

               blinkLED(0,500,12);

            }else{
              
              toSerial( "Incorrect_subcommand", "C" );
              
            }
  
          }else{
          
          toSerial( "Incorrect_command", "C" );

          }
        }
      }
      
    }else if(!testPassword.equals(comPassword) ){
      
           toSerial( "Password_not_accepted", "C" );
            
           testPassword = comPassword;
           
           blinkLED(0,500,12); 
    }
  }
    
    delay(100);
}
  
   //Led initiator and iteration delay
   void blinkLED(int pre , int post, int pin){
       
        delay(pre);
        digitalWrite(pin, HIGH);  
        delay(post);
        digitalWrite(pin, LOW);
   }
   
   //Send data and comments to client
   void toSerial(String val, String prefix){
     
     Serial.print( " " + prefix + " " + val + " " + prefix + "* " ) ;
  }
