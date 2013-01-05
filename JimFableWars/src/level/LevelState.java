/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package level;
import character.Opponent;
import character.Player;
import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.input.ChaseCamera;
import com.jme3.input.InputManager;
import com.jme3.input.controls.ActionListener;
import com.jme3.math.Vector3f;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import java.util.LinkedList;
import mygame.Game;

/**
 *
 * @author Lena
 */
public class LevelState extends AbstractAppState implements ActionListener, PhysicsCollisionListener, AnimEventListener {
    
    protected LinkedList<Item> alterableItems = new LinkedList<Item>();
    protected LinkedList<Opponent> opponents = new LinkedList<Opponent>();
    protected Game game;
    protected Node              rootNode;
    protected AssetManager      assetManager;
    protected AppStateManager   stateManager;
    protected InputManager      inputManager;
    protected ViewPort          viewPort;
    protected BulletAppState    bulletAppState;
    protected Player            player;
    protected ChaseCamera       chaseCam;
    protected Vector3f walkDirection = new Vector3f();
    

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
    this.game = (Game) app; // can cast Application to something more specific
    this.rootNode     = this.game.getRootNode();
    this.assetManager = this.game.getAssetManager();
    this.stateManager = this.game.getStateManager();
    this.inputManager = this.game.getInputManager();
    this.viewPort     = this.game.getViewPort();
    this.bulletAppState = this.stateManager.getState(BulletAppState.class);
    this.player       = ((Game)app).player;
    this.game.getFlyByCamera().setEnabled(false);
    chaseCam = new ChaseCamera(this.game.getCamera(), player.model, inputManager);
    bulletAppState.getPhysicsSpace().add(player.character);
    }

    public void onAction(String name, boolean isPressed, float tpf) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void collision(PhysicsCollisionEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onAnimChange(AnimControl control, AnimChannel channel, String animName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
  
    @Override
    public void stateAttached(AppStateManager stateManager) {
        super.stateAttached(stateManager);
    }


    @Override
    public void stateDetached(AppStateManager stateManager) {
        super.stateDetached(stateManager);
    }

    
    
}
