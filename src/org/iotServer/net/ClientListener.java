/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iotServer.net;

@FunctionalInterface
/**
 *
 * @author martin
 */
public interface ClientListener {
    public void onClientConnected();
}
