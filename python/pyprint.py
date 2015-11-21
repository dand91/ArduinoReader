import mysql.connector
import matplotlib.pyplot as plt
import time
import sys

class ArduinoPrint:
    
    __password = "";
    
    def __init__(self):
       
       print("Arduino plot started")
        
    def setPassword(self):
        
        text_file = open("/Users/Andersson/Google Drive/Java_saved_files/Bluetooth_workspace/Bluetooth_Connector/password/password.txt", "r")
        self.__password = text_file.readline()       
        
    def handle_close(evt):
        
        sys.exit(0)    
        
    def run(self):
        plt.ylabel('Light')
        
        while True: 

            cnx = mysql.connector.connect(user='DAnd91', password=self.__password,
                                      host='127.0.0.1',
                                      database='bluetooth')
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
