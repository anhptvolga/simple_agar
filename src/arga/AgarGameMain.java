/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arga;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.GameObject;
import java.awt.Dimension;

/**
 *
 * @author godric
 */
public class AgarGameMain extends GameEngine {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // code application logic here
        // loading game
        GameLoader game = new GameLoader();
        game.setup(new AgarGameMain(), 
                new Dimension(AgarGame.WIDTH_SCREEN, AgarGame.HEIGHT_SCREEN), 
                false);
        game.start();
    }

    @Override
    public GameObject getGame(int i) {
        switch (i) {
            case 0:
                return new AgarGame(this);
            case 1:
                return new GameOver(this);
        }
        return null;
    }
}
