/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barrier;

import com.golden.gamedev.object.Sprite;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author godric
 */
public class Rock extends Sprite{
    public Rock(BufferedImage image, double x, double y) {
        super(image, x-image.getWidth()/2, y-image.getHeight()/2);
    }
}
