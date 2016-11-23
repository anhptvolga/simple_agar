/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collision;

import arga.Character;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import events.ArgaDieEvent;
import events.ArgaDieListener;
import java.util.ArrayList;

/**
 *
 * @author godric
 */
public class CharacterEatArga extends BasicCollisionGroup {

    public CharacterEatArga() {
        pixelPerfectCollision = true;
    }
    
    @Override
    public void collided(Sprite sprite, Sprite sprite1) {
        ((Character) sprite).eatAgar();
        sprite1.setActive(false);
        _listeners.stream().forEach((l) -> {
            l.ArgaDieAction(_event);
        });
    }
    
    private ArrayList<ArgaDieListener> _listeners = new ArrayList<ArgaDieListener>();
    private ArgaDieEvent _event = new ArgaDieEvent(this);
    
    public void addListener(ArgaDieListener listener) {
        _listeners.add(listener);
    }

}
