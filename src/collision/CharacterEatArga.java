/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collision;

import arga.Agar;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author godric
 */
public class AgarEatArga extends BasicCollisionGroup {

    public AgarEatArga() {
        pixelPerfectCollision = true;
    }
    
    @Override
    public void collided(Sprite sprite, Sprite sprite1) {
        ((Agar) sprite).eat();
        sprite1.setActive(false);
    }
    
}
