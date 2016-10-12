/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arga;

import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author godric
 */
public class ArgaGame extends com.golden.gamedev.Game {
    
    ImageBackground bgimg;
    Arga _hero;
    SpriteGroup spritesgr;
    
   
    
    @Override
    public void initResources() {
        
        
        // create main hero
        _hero = new Arga(getImage("resources/pix/initial_bacterium.png"),
                        this.getWidth()/2, this.getHeight()/2);
        
        // create group sprites
        spritesgr = new SpriteGroup("Sprites");
        spritesgr.add(_hero);
        
        // loading background
        bgimg = new ImageBackground(getImage("pix/background.jpg"));
        bgimg.setClip(0, 0, 640, 480);
        
        spritesgr.setBackground(bgimg);
    }
    
    private double getAngleDir(double hx, double hy, double mx, double my) {
        double res = Math.toDegrees(Math.atan((my-hy)/(mx-hx)));
        if (mx-hx < 0) {
            res += 180;
        }
        System.out.printf("%f %f|%f %f|%f\n", hx, hy, mx, my, res);
        return res;
    }
    
    @Override
    public void update(long l) {
        double hx = _hero.getX();
        double hy = _hero.getY();
        double mx = this.getMouseX() + this.bgimg.getX();
        double my = this.getMouseY() + this.bgimg.getY();
        // update hero 
        _hero.update(l);
        _hero.setMovement(1, 90+this.getAngleDir(hx, hy, mx, my));
        if (hx < 0 || bgimg.getWidth() - _hero.getWidth() < hx) {
            _hero.setHorizontalSpeed(0);
        }
        if (hy < 0 || bgimg.getHeight() - _hero.getHeight() < hy) {
            _hero.setVerticalSpeed(0);
        }
        
        bgimg.update(l);
        bgimg.setToCenter(_hero);
    }

    @Override
    public void render(Graphics2D gd) {
        bgimg.render(gd);
        _hero.render(gd);
    }
    
}
