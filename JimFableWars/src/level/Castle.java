/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package level;

import character.CharacterFactory;
import character.Opponent;
import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.Vector3f;

/**
 *
 * @author Lena
 */
public class Castle extends LevelState{
    
    protected Opponent sansa;

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        Vector3f loc = new Vector3f(0, 10, 0);
        sansa = CharacterFactory.createOpponent("", assetManager, loc);
        rootNode.attachChild(sansa.model);
    }
    
    
    
}
