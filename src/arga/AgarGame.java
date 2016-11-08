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
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author godric
 */
public class AgarGame extends com.golden.gamedev.Game {
    
    public final static int WIDTH_SCREEN = 800;
    public final static int HEIGHT_SCREEN = 600;
    
    private final int ROCK_COUNT = 40;
    
    private PlayField _plfield;
    
    ImageBackground bgimg;
    Agar _hero;
    
    SpriteGroup argagr;
    SpriteGroup rocksgr;
    SpriteGroup botsgr;
    
    int _botcount = 20;
    
    CollisionManager argahitrockColl;
    CollisionManager argaeatColl;
    
    @Override
    public void initResources() {
        bgimg = new ImageBackground(getImage("pix/background.jpg"));
        bgimg.setClip(0, 0, WIDTH_SCREEN, HEIGHT_SCREEN);
        _plfield = new PlayField(bgimg);
        
        // create main hero
        _hero = new Agar(getImage("resources/pix/PRIMITIVE_PLANT.png"),
                        this.getWidth()/2, this.getHeight()/2);
        
        // create group sprites
        argagr = new SpriteGroup("Sprites");
        botsgr = new SpriteGroup("Bots");
        argagr.add(_hero);
        
        addRandomRock();
        addRandomAgars();
        
        // loading background
        rocksgr.setBackground(bgimg);
        argagr.setBackground(bgimg);
        // add all to playfield
        _plfield.addGroup(argagr);
        _plfield.addGroup(rocksgr);
        _plfield.addCollisionGroup(argagr, botsgr, new AgarEatArga());
        _plfield.addCollisionGroup(argagr, rocksgr, new AgarHitRock());
    }
    
    private void addRandomRock() {
        // add rocks
        BufferedImage img = getImage("resources/pix/rock.jpg");
        int bgw = bgimg.getWidth();
        int bgh = bgimg.getHeight();
        rocksgr = new SpriteGroup("Rocks");
        double xs[] = new double[ROCK_COUNT];
        double ys[] = new double[ROCK_COUNT];
        
        for (int i =0; i < ROCK_COUNT; ++i) {
            xs[i] = bgw*Math.random();
            ys[i] = bgh*Math.random();
        }
        
        for (int i = 0; i < ROCK_COUNT; ++i) {
            Rock tmp = new Rock(img, xs[i], ys[i]);
            this.rocksgr.add(tmp);
        }
    }
    
    private boolean isOnRock(double x, double y, double w, double h) {
        Sprite[] rocks = rocksgr.getSprites();
        for (int i = 0; i < rocksgr.getSize(); ++i) {
            if ((new Rectangle2D.Double(x, y, w, h)).intersects(
                    rocks[i].getX(), rocks[i].getY(), 
                    rocks[i].getWidth(), rocks[i].getHeight())) {
                return true;
            }
        }
        return false;
    }
    
    private void addRandomAgar() {
        BufferedImage img = getImage("resources/pix/initial_bacterium.png");
        double h = img.getHeight();
        double w = img.getWidth();
        double bgh = bgimg.getHeight();
        double bgw = bgimg.getWidth();
        double tx, ty;
        tx = Math.random();
        ty = Math.random();
        while (!isOnRock(tx*bgw, ty*bgh, w, h)) {
            tx = Math.random();
            ty = Math.random();
        }
        Agar tmp = new Agar(img, tx*bgw, ty*bgh);
        argagr.add(tmp);
        botsgr.add(tmp);
    }
    
    private void addRandomAgars() {
        // add bots
        for (int i = 0; i < this._botcount; ++i) {
            addRandomAgar();
        }
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
        
        _plfield.update(l);

        updateHero(l);
        
        bgimg.update(l);
        bgimg.setToCenter(_hero);
        
    }
    
    public void updateHero(long l) {
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
        _hero.setMovement(0.2, 90+this.getAngleDir(hx, hy, mx, my));
    }

    @Override
    public void render(Graphics2D gd) {
//        bgimg.render(gd);
//        argagr.render(gd);
//        rocksgr.render(gd);
        _plfield.render(gd);
        gd.drawString(_hero.toStringEat(), 10, 10);
    }
    
}
