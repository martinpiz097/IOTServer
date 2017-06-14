/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iotServer.net;

import com.klaw.easyarduinorxtx.SerialArduino;
import com.klaw.easyarduinorxtx.event.ArduinoEvent;

/**
 *
 * @author martin
 */
public class ArduinoConnector {
    private final SerialArduino arduino;

    public ArduinoConnector(String port) {
        this.arduino = new SerialArduino(port);
    }
    
    public ArduinoConnector(){
        this("/dev/ttyACM0");
    }
    
    public void send(String msg){
        arduino.send(msg);
    }
    
    public void send(char data){
        arduino.send(data);
    }
    
    public void send(int data){
        arduino.send(data);
    }
    
    public void setArduinoListener(ArduinoEvent event){
        arduino.addArduinoEvent(event);
    }
    
    public void init(){
        arduino.initialize();
    }
    
}
