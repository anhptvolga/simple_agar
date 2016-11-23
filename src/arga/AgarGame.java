/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arga;

import AI.BotMovement;
import AI.MouseMovement;
import AI.MovingAI;
import AI.StrategyAI;
import barrier.Rock;
import collision.BoundHitCollision;
import collision.CharacterEatArga;
import collision.CharacterHitRock;
import collision.CharactersCollision;
import com.golden.gamedev.GameEngine;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;
import com.golden.gamedev.util.ImageUtil;
import events.ArgaDieEvent;
import events.ArgaDieListener;
import events.BotDieEvent;
import events.BotDieListener;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author godric
 */
public class AgarGame extends com.golden.gamedev.GameObject 
        implements BotDieListener, ArgaDieListener{
    
    public final static int WIDTH_SCREEN = 800;
    public final static int HEIGHT_SCREEN = 600;
    
    public final static int PRIM_WIDTH = 60;
    public final static int PRIM_HEIGHT = 90;
    
    private final int ROCK_COUNT = 10;
    private final int AGAR_COUNT = 60;
    
    private PlayField _plfield;
    
    ImageBackground _bgimg;
    Character _hero;
    
    private int _botCount = 30;
    ArrayList<StrategyAI> _ais;

    public AgarGame(GameEngine ge) {
        super(ge);
    }
    
    @Override
    public void initResources() {
        _bgimg = new ImageBackground(getImage("pix/background.jpg"));
        _bgimg.setClip(0, 0, WIDTH_SCREEN, HEIGHT_SCREEN);
        _plfield = new PlayField(_bgimg);
        _ais = new ArrayList<>();
        
        // create main hero
//        double bgh = _bgimg.getHeight();
//        double bgw = _bgimg.getWidth();
//        double tx, ty;
//        tx = Math.random();
//        ty = Math.random();
//        while (!isOnRock(tx*bgw, ty*bgh, PRIM_WIDTH, PRIM_HEIGHT)) {
//            tx = Math.random();
//            ty = Math.random();
//        }
        _hero = new Character(
                ImageUtil.resize(getImage("resources/pix/PRIMITIVE_PLANT.png"), PRIM_WIDTH, PRIM_HEIGHT),
                        this.getWidth()/2, this.getHeight()/2);
        _hero.setBackground(_bgimg);
        _ais.add(new MouseMovement(this, _hero));
        
        // create group sprites
        _plfield.addGroup(new SpriteGroup("Agars"));
        _plfield.addGroup(new SpriteGroup("Rocks"));
        _plfield.addGroup(new SpriteGroup("Characters"));
        _plfield.addGroup(new SpriteGroup("Bots"));
        
        
        addRandomRock();
        addRandomAgars();
        addBots();

        _plfield.getGroup("Characters").add(_hero);
        
        // add all to playfield
        CharacterEatArga ca = new CharacterEatArga();
        ca.addListener(this);
        _plfield.addCollisionGroup(_plfield.getGroup("Characters"), 
                _plfield.getGroup("Agars"), ca);
        _plfield.addCollisionGroup(_plfield.getGroup("Characters"), 
                _plfield.getGroup("Rocks"), new CharacterHitRock());
        CharactersCollision cc = new CharactersCollision();
        cc.addListener(this);
        _plfield.addCollisionGroup(_plfield.getGroup("Characters"),
                _plfield.getGroup("Characters"), cc);
        //_plfield.addCollisionGroup(_plfield.getGroup("Characters"),
        //        _plfield.getGroup("Characters"), new BoundHitCollision(_bgimg));
        
        // loading background
        _plfield.getGroup("Rocks").setBackground(_bgimg);
        _plfield.getGroup("Characters").setBackground(_bgimg);
        
    }
    
    private Character getRandomBot() {
        BufferedImage img = ImageUtil.resize(getImage("resources/pix/PRIMITIVE_ANIMAL.png"), PRIM_WIDTH, PRIM_WIDTH);
        double bgh = _bgimg.getHeight();
        double bgw = _bgimg.getWidth();
        double tx, ty;
            tx = Math.random();
            ty = Math.random();
            while (!isOnRock(tx*bgw, ty*bgh, PRIM_WIDTH, PRIM_HEIGHT)) {
                tx = Math.random();
                ty = Math.random();
            }
            Character tmp = new Character(img, tx*bgw, ty*bgh);
            tmp.setBackground(_bgimg);
            tmp.setEated((int)(Math.random()*10));
            
            this._ais.add(new BotMovement(this, tmp));
        return tmp;
    }
    
    private void addBots() {
        SpriteGroup bots = _plfield.getGroup("Bots");
        SpriteGroup chars = _plfield.getGroup("Characters");
        for (int i = 0; i < this._botCount; ++i) {
            Character tmp = getRandomBot();
            bots.add(tmp);
            chars.add(tmp);
        }
        bots.setBackground(_bgimg);
    }
    
    private void addRandomRock() {
        // add rocks
        BufferedImage img = getImage("resources/pix/rock.jpg");
        int bgw = _bgimg.getWidth();
        int bgh = _bgimg.getHeight();
        SpriteGroup rocksgr = _plfield.getGroup("Rocks");
        double xs[] = new double[ROCK_COUNT];
        double ys[] = new double[ROCK_COUNT];
        
        for (int i =0; i < ROCK_COUNT; ++i) {
            xs[i] = bgw*Math.random();
            ys[i] = bgh*Math.random();
        }
        
        for (int i = 0; i < ROCK_COUNT; ++i) {
            Rock tmp = new Rock(img, xs[i], ys[i]);
            rocksgr.add(tmp);
        }
        rocksgr.setBackground(_bgimg);
//        _plfield.addGroup(rocksgr);
    }
    
    private boolean intersects(Rectangle2D r1, Rectangle2D r2) {
        // check 1 in 2?
        double tx = r2.getX() + r2.getWidth();
        double ty = r2.getY() + r2.getHeight();
        return (r2.getX() < r1.getX() && r2.getY() < r1.getY() ||
                r1.getX() < tx && r1.getY() < ty);
    }
    
    private boolean isOnRock(double x, double y, double w, double h) {
        SpriteGroup rg = _plfield.getGroup("Rocks");
        Rectangle2D obj = new Rectangle2D.Double(x, y, w, h);
        if (rg != null) {
            Sprite[] rocks = _plfield.getGroup("Rocks").getSprites();
            for (int i = 0; i < rocks.length; ++i) {
                if (rocks[i] != null && intersects(obj, new Rectangle2D.Double(
                        rocks[i].getX(), rocks[i].getY(), 
                        rocks[i].getWidth(), rocks[i].getHeight()))) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private Agar genRandomAgar() {
        BufferedImage img = getImage("resources/pix/initial_bacterium.png");
        double h = img.getHeight();
        double w = img.getWidth();
        double bgh = _bgimg.getHeight();
        double bgw = _bgimg.getWidth();
        double tx, ty;
        tx = Math.random();
        ty = Math.random();
        while (!isOnRock(tx*bgw, ty*bgh, w, h)) {
            tx = Math.random();
            ty = Math.random();
        }
        Agar tmp = new Agar(img, tx*bgw, ty*bgh);
        tmp.setBackground(_bgimg);
        return tmp;
    }
    
    private void addRandomAgars() {
        SpriteGroup argagr = _plfield.getGroup("Agars");
        Agar tmp;
        for (int i = 0; i < AGAR_COUNT; ++i) {
            tmp = genRandomAgar();
            argagr.add(tmp);
        }
        argagr.setBackground(_bgimg);
//        _plfield.addGroup(argagr);
    }
    
    @Override
    public void update(long l) {
        if (!_hero.isActive()) {
            parent.nextGameID = 1;
            finish();
        }
        _plfield.update(l);
        _bgimg.update(l);
        _bgimg.setToCenter(_hero); 
        _ais.stream().forEach((ai) -> {
            ai.update(l);
        });
    }

    @Override
    public void render(Graphics2D gd) {
        _plfield.render(gd);
        gd.drawString(_hero.toStringEat(), 10, 10);
    }
    
    @Override
    public int getMouseY() {
        return (int) (super.getMouseY() + _bgimg.getY());
    }
    
    @Override
    public int getMouseX() {
        return (int) (super.getMouseX() + _bgimg.getX());
    }

    public PlayField getPlayField() {
        return this._plfield;
    }
    
    public Character getHero() {
        return this._hero;
    }

    @Override
    public void BotDieAction(BotDieEvent e) {
        Character tmp = getRandomBot();
        this._plfield.getGroup("Bots").add(tmp);
        this._plfield.getGroup("Characters").add(tmp);
    }

    @Override
    public void ArgaDieAction(ArgaDieEvent e) {
        this._plfield.getGroup("Agars").add(genRandomAgar());
    }
}
