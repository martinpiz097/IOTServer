/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iotServer.net.interfaces;

/**
 *
 * @author martin
 */
public interface Transmissible {
    public void send(String msg);
    public void send(char data);
    public void send(int data);
}
