package mygame;

import level.MenueState;
import character.CharacterFactory;
import character.Player;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppState;
import com.jme3.bullet.BulletAppState;
import com.jme3.math.Vector3f;
import level.Heaven;

/**
 * test
 *
 * @author normenhansen
 */
public class Game extends SimpleApplication {

    public BulletAppState bulletAppState;
    public Player player = null;
    private Heaven heaven = null;
    private MenueState menue = null;
    private Camera camera;

    public static void main(String[] args) {
        Game app = new Game();
        app.setShowSettings(true);
        app.start();
    }

    @Override
    public void simpleInitApp() {
        flyCam.setMoveSpeed(10);
        bulletAppState = new BulletAppState();
        bulletAppState.setThreadingType(BulletAppState.ThreadingType.PARALLEL);
        stateManager.attach(bulletAppState);
        player = CharacterFactory.createPlayer("Dragon", assetManager);
        getRootNode().attachChild(player.model);
        loadMenue();
    }

    public void loadHeaven() {
        //remove Hell or Menue first
        heaven = new Heaven();
        heaven.initialize(stateManager, this);
        if (stateManager.hasState(menue)) {
            detachState(menue);
        }
        attachState(heaven);

        camera = new Camera();
        stateManager.attach(camera);
        
        rootNode.attachChild(CharacterFactory.createOpponent("ENEMY", assetManager, new Vector3f(5, 10, 0), getBulletAppState()).model);
    }

    public void attachState(AppState level) {
        stateManager.attach(level);
    }

    public void detachState(AppState level) {
        stateManager.detach(level);
    }

    private void loadMenue() {
        menue = new MenueState(this);
        stateManager.attach(menue);
    }

    public BulletAppState getBulletAppState() {
        return bulletAppState;
    }
}
