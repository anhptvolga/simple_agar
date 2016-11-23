/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collision;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionBounds;

/**
 *
 * @author godric
 */
public class BoundHitCollision extends CollisionBounds {

    public BoundHitCollision(int i, int i1, int i2, int i3) {
        super(i, i1, i2, i3);
    }
    public BoundHitCollision(Background backgr) {
        super(backgr);
    }

    @Override
    public void collided(Sprite sprite) {
        sprite.setVerticalSpeed(0);
        sprite.setHorizontalSpeed(0);
    }
    
}
