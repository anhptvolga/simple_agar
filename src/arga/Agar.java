/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arga;

import com.golden.gamedev.util.ImageUtil;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 * @author godric
 */
public class Agar extends com.golden.gamedev.object.Sprite {
    
    Agar(BufferedImage image) {
        super(ImageUtil.resize(image, 20, 20));
    }

    Agar(BufferedImage image, double x, double y) {
        super(ImageUtil.resize(image, 20, 20),
                x-image.getWidth()/2, y-image.getHeight()/2);
    }
    
    public Point getPosition() {
        Point position = new Point();
        position.setLocation(
            (int) this.getX(),
            (int) this.getY());
        return position;
    }
}
