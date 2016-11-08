/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Sprite;

/**
 *
 * @author godric
 */
public class BotMovement extends MovingAI {

    public BotMovement(GameObject game, Sprite obj) {
        super(game, obj);
    }

    @Override
    protected double getSpeed() {
        return 0.3;
    }

    @Override
    protected double getAngle() {
        double res = 0;
        if (Math.random() < 0.4) {
            res = 360 * Math.random();
        }
        return res;
    }
    
}
