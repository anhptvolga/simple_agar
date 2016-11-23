/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collision;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

/**
 *
 * @author godric
 */
public class CharacterHitRock extends BasicCollisionGroup {

    public CharacterHitRock() {
	pixelPerfectCollision = true;
    }
    @Override
    public void collided(Sprite sprite, Sprite sprite1) {
//        sprite.setActive(false);
        sprite.setSpeed(0, 0);
        sprite.setX(sprite.getOldX());
        sprite.setY(sprite.getOldY());
    }
    
}
