import mysql.connector
import matplotlib.pyplot as plt
import time
import sys

class ArduinoPrint:
    
    __password = "";
    __username = "";
    __database = "";
    
    def __init__(self):
       
       print("Arduino plot started")
        
    def setPassword(self):
        
        text_file = open("/Users/Andersson/Google Drive/Java_saved_files/Bluetooth_workspace/Bluetooth_Connector/info/info.txt", "r")
        self.__password = text_file.readline().rstrip('\n')   
        self.__username = text_file.readline().rstrip('\n')      
        self.__database = text_file.readline().rstrip('\n')      

    def handle_close(evt):
        
        sys.exit(0)    
        
    def run(self):
        
        
        plt.ylabel('Light')
        
        while True: 

            cnx = mysql.connector.connect(user=self.__username, password=self.__password,
                                      host='127.0.0.1',
                                      database=self.__database)
            cursor = cnx.cursor()
            query = ("SELECT value FROM data ORDER BY date DESC LIMIT 1000;")
            cursor.execute(query)
            plotList = list();
            
            for (value) in cursor:
                plotList.append(value);
            plt.plot(plotList)
            plt.draw
            plt.pause(0.01)
            time.sleep(1) 
            cursor.close()
            cnx.close()
            plt.cla()
        

if __name__ == "__main__":
    app = ArduinoPrint()
    app.setPassword()
    app.run()
