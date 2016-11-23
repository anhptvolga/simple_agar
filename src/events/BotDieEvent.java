/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import java.util.EventObject;

/**
 *
 * @author godric
 */
public class BotDieEvent extends EventObject {
    
    public BotDieEvent(Object source) {
        super(source);
    }
    
}
