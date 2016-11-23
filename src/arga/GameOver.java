/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arga;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.background.ImageBackground;
import java.awt.Graphics2D;

/**
 *
 * @author godric
 */
public class GameOver extends GameObject {

    private String bgname = "resources/pix/gameover.png";
        
    private ImageBackground _imgbg;

    Timer timer;
    
    public GameOver(GameEngine ge) {
        super(ge);
        timer = new Timer(2000);
    }

    @Override
    public void initResources() {
        _imgbg = new ImageBackground(getImage(bgname));
        setFPS(10);
    }

    @Override
    public void update(long l) {
        if (timer.action(l)) {
            parent.nextGameID = 0;
            setFPS(60);
            finish();
        }
    }

    @Override
    public void render(Graphics2D gd) {
        _imgbg.render(gd);
    }
    
}

