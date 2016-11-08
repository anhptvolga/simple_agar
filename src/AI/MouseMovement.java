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
public class MouseMovement extends MovingAI {
    
    public MouseMovement(GameObject game, Sprite obj) {
        super(game, obj);
    }

    @Override
    protected double getSpeed() {
        return 0.2;
    }

    @Override
    protected double getAngle() {
        return getAngleDir(_object.getX(), _object.getY(), 
                _gameobj.getMouseX(), _gameobj.getMouseY());
    }
    
    protected double getAngleDir(double hx, double hy, double mx, double my) {
        double res = Math.toDegrees(Math.atan((my-hy)/(mx-hx)));
        if (mx-hx < 0) {
            res += 180;
        }
        return res;
    }
    
}
