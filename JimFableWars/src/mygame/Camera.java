/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.Vector3f;

/**
 *
 * @author Aty
 */
public class Camera extends AbstractAppState
{
    private Game app;
    private final float CAMERA_SPEED = 3.f;
    private final float CAMERA_DISTANCE = 20.f;
    
    public Camera() {
    }
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (Game) app;

        app.getCamera().setLocation(new Vector3f(0,0,20.f));
    }

    @Override
    public void update(float tpf) {

        Vector3f playerPosition = app.player.getPlayerLocation();
        Vector3f cameraPosition = app.getCamera().getLocation();
        
        float xMovement = (playerPosition.x - cameraPosition.x) * tpf * CAMERA_SPEED;
        float yMovement = (playerPosition.y - cameraPosition.y) * tpf * CAMERA_SPEED;
        
        app.getCamera().setLocation(new Vector3f(cameraPosition.x + xMovement,
                cameraPosition.y + yMovement, CAMERA_DISTANCE));
    }
}
