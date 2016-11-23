/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import arga.Agar;
import arga.AgarGame;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import java.awt.Point;

/**
 *
 * @author godric
 */
public class BotMovement extends MovingAI {

    private static int VISION = 500;
    
    public BotMovement(GameObject game, Sprite obj) {
        super(game, obj);
    }

    @Override
    protected double getSpeed() {
        return 0.2;
    }

    private double distance(Sprite a, Sprite b) {
        double res = 0;
        return res;
    }
    
    @Override
    protected double getAngle() {
        Point p1 = ((arga.Character)_object).getPosition();
        double res = GameMath.angle(p1, 
                _gameobj.getHero().getPosition());
        double mindis = VISION*2;
        double dis;
        PlayField field = ((AgarGame)_gameobj).getPlayField();
        SpriteGroup agars = field.getGroup("Agars");
        for (Sprite s : agars.getSprites()) {
            if (s != null) {
                dis = _object.getDistance(s);
                if (s.isActive() && dis < VISION && dis < mindis) {
                    mindis = dis;
                    res = GameMath.angle(p1, ((Agar)s).getPosition());
                }
            }
        }
        return res;
    }
    
}
