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
public class Character extends com.golden.gamedev.object.Sprite {
    private final int GROWTH = 1;
    private final int KILL_GR = 2;
    private int eatCount;
    private int killed;
    
    Character(BufferedImage image) {
        super(ImageUtil.resize(image, 40, 40));
        eatCount = 0;
        killed = 0;
    }

    Character(BufferedImage image, double x, double y) {
        super(ImageUtil.resize(image, 40, 40),
                x-image.getWidth()/2, y-image.getHeight()/2);
        eatCount = 0;
    }
    
    public int getKilled() {
        return this.killed;
    }
    
    public void kill(Character dead) {
        this.killed += dead.killed;
        this.setImage(ImageUtil.resize(this.getImage(), 
                this.getWidth() + (dead.killed+1)*KILL_GR, 
                this.getHeight() + (dead.killed+1)*KILL_GR));
    }
    
    public void setEated(int a) {
        this.eatCount = a;
        this.setImage(ImageUtil.resize(this.getImage(), 
                this.getWidth() + a*GROWTH, this.getHeight() + a*GROWTH));
    }
    
    public void eatAgar() {
        this.eatCount ++;
        this.setImage(ImageUtil.resize(this.getImage(), 
                this.getWidth() + GROWTH, this.getHeight() + GROWTH));
    }

    public String toStringEat() {
        return String.format("Eat %d", this.eatCount);
    }
    
    public void update(long l) {
        super.update(l);
    }
    
    public Point getPosition() {
        Point position = new Point();
        position.setLocation(
            (int) this.getX(),
            (int) this.getY());
        return position;
    }
}
