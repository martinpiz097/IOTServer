/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iotServer.net;

import com.klaw.easyarduinorxtx.SerialArduino;
import com.klaw.easyarduinorxtx.event.ArduinoEvent;
import org.iotServer.net.interfaces.Communicable;

/**
 *
 * @author martin
 */
public class ArduinoConnector implements Communicable {
    private final SerialArduino arduino;
    
    private static final String DEFAULT_SERIAL_PORT = "/dev/ttyACM0";
    
    public ArduinoConnector(String port) {
        this.arduino = new SerialArduino(port);
    }
    
    public ArduinoConnector(){
        this(DEFAULT_SERIAL_PORT);
    }
    
    public boolean isConnected() {
        return arduino.isConnected();
    }
    
    @Override
    public void send(String msg){
        arduino.send(msg);
    }
    
    @Override
    public void send(char data){
        arduino.send(data);
    }
    
    @Override
    public void send(int data){
        arduino.send(data);
    }
    
    /**
     * It allows to add events to the controller of the arduino in order to 
     * receive data of this and to be able to process them.
     * @param event Event to add.
     */
    public void addArduinoListener(ArduinoEvent event){
        arduino.addArduinoEvent(event);
    }
    
    /**
     * Allows you to delete events that have been added to the controller.
     * @param event Event to delete.
     */
    public void removeArduinoListener(ArduinoEvent event) {
        arduino.removeArduinoEvent(event);
    }
    
    public void init(){
        arduino.initialize();
    }
    
}
