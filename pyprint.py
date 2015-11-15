import mysql.connector
import matplotlib.pyplot as plt
import time
import sys

def handle_close(evt):
    sys.exit(0)    
    
plt.ylabel('Light')

while True: 
    
    cnx = mysql.connector.connect(user='DAnd91', password='%%',
                              host='127.0.0.1',
                              database='bluetooth')
    cursor = cnx.cursor()
    query = ("SELECT value FROM data ORDER BY date DESC LIMIT 100;")
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
    
    


