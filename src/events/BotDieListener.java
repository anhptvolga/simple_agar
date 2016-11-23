/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import java.util.EventListener;

/**
 *
 * @author godric
 */
public interface BotDieListener extends EventListener {
    public void BotDieAction(BotDieEvent e);
}
