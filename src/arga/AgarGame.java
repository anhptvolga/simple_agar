/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arga;

import barrier.Rock;
import collision.AgarEatArga;
import collision.AgarHitRock;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.PlayField;
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
public class AgarGame extends com.golden.gamedev.Game {
    
    private PlayField _plfield;
    
    ImageBackground bgimg;
    Agar _hero;
    
    SpriteGroup argagr;
    SpriteGroup rocksgr;
    SpriteGroup botsgr;
    
    int _botcount = 10;
    int _rockcount = 80;
    
    CollisionManager argahitrockColl;
    CollisionManager argaeatColl;
    
    @Override
    public void initResources() {
        bgimg = new ImageBackground(getImage("pix/background.jpg"));
        bgimg.setClip(0, 0, 640, 480);
        
        
        // create main hero
        _hero = new Agar(getImage("resources/pix/PRIMITIVE_PLANT.png"),
                        this.getWidth()/2, this.getHeight()/2);
        
        // create group sprites
        argagr = new SpriteGroup("Sprites");
        botsgr = new SpriteGroup("Bots");
        argagr.add(_hero);
        // add bots
        for (int i = 0; i < this._botcount; ++i) {
            Agar tmp = new Agar(getImage("resources/pix/PRIMITIVE_ANIMAL.png"),
                        bgimg.getWidth()*Math.random(), bgimg.getHeight()*Math.random());
            argagr.add(tmp);
            botsgr.add(tmp);
        }
        
       // add rocks
       rocksgr = new SpriteGroup("Rocks");
       for (int i = 0; i < this._rockcount; ++i) {
           Rock tmp = new Rock(getImage("resources/pix/rock.jpg"),
                   bgimg.getWidth()*Math.random(), bgimg.getHeight()*Math.random());
           this.rocksgr.add(tmp);
       }
       
       // loading background
        
       rocksgr.setBackground(bgimg);
       argagr.setBackground(bgimg);
       
       // collision
       argahitrockColl = new AgarHitRock();
       argahitrockColl.setCollisionGroup(argagr, rocksgr);
       
       argaeatColl = new AgarEatArga();
       argaeatColl.setCollisionGroup(argagr, botsgr);
    }
    
    private double getAngleDir(double hx, double hy, double mx, double my) {
        double res = Math.toDegrees(Math.atan((my-hy)/(mx-hx)));
        if (mx-hx < 0) {
            res += 180;
        }
        // System.out.printf("%f %f|%f %f|%f\n", hx, hy, mx, my, res);
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
        
        if (hx < 0 || bgimg.getWidth() - _hero.getWidth() < hx) {
            _hero.setHorizontalSpeed(0);
        }
        if (hy < 0 || bgimg.getHeight() - _hero.getHeight() < hy) {
            _hero.setVerticalSpeed(0);
        }
        _hero.setMovement(0.3, 90+this.getAngleDir(hx, hy, mx, my));
        
        bgimg.update(l);
        bgimg.setToCenter(_hero);
        
        argahitrockColl.checkCollision();
        argaeatColl.checkCollision();
        
    }

    @Override
    public void render(Graphics2D gd) {
        bgimg.render(gd);
        argagr.render(gd);
        rocksgr.render(gd);
    }
    
}
