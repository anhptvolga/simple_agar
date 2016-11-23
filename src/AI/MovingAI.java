/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import arga.AgarGame;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Sprite;

/**
 *
 * @author godric
 */
public abstract class MovingAI implements StrategyAI {
    protected AgarGame _gameobj;
    protected Sprite _object;

    public MovingAI(GameObject game, Sprite obj) {
        _gameobj = (AgarGame) game;
        _object = obj;
    }
    
    @Override
    public void update(long l) {
        _object.setMovement(getSpeed(), getAngle());
    }
    
    protected abstract double getSpeed();
    protected abstract double getAngle();
    
}
