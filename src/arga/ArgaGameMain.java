/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arga;

import com.golden.gamedev.GameLoader;
import java.awt.Dimension;

/**
 *
 * @author godric
 */
public class ArgaGameMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // code application logic here
        // loading game
        GameLoader game = new GameLoader();
        game.setup(new ArgaGame(), new Dimension(640, 480), false);
        game.start();
        
    }
}
