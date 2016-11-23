/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collision;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import arga.Character;
import events.BotDieEvent;
import events.BotDieListener;
import java.util.ArrayList;

/**
 *
 * @author godric
 */
public class CharactersCollision extends BasicCollisionGroup{
    
    public CharactersCollision() {
	pixelPerfectCollision = true;
    }
    @Override
    public void collided(Sprite sprite1, Sprite sprite2) {
        Character a = (Character) sprite1;
        Character b = (Character) sprite2;
        if (a.getWidth() > b.getWidth()) {
            a.kill(b);
            b.setActive(false);
        } else {
            b.kill(a);
            a.setActive(false);
        }
        _listeners.stream().forEach((l) -> {
            l.BotDieAction(_event);
        });
    }
    
    private ArrayList<BotDieListener> _listeners = new ArrayList<BotDieListener>();
    private BotDieEvent _event = new BotDieEvent(this);
    
    public void addListener(BotDieListener listener) {
        _listeners.add(listener);
    }
    
}
