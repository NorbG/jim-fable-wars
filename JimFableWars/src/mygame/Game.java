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
        app.setDisplayFps(false);
        app.setDisplayStatView(false);
        app.start();
    }

    @Override
    public void simpleInitApp() {
        flyCam.setMoveSpeed(10);
        flyCam.setEnabled(false);
        bulletAppState = new BulletAppState();
        bulletAppState.setThreadingType(BulletAppState.ThreadingType.PARALLEL);
        stateManager.attach(bulletAppState);
        player = CharacterFactory.createPlayer("Dragon", assetManager);
        loadMenue();
                bulletAppState.getPhysicsSpace().enableDebug(assetManager);
    }

    public void loadHeaven() {
        //remove Hell or Menue first
        heaven = new Heaven();
      //  heaven.initialize(stateManager, this);
        if (stateManager.hasState(menue)) {
            detachState(menue);
        }
        attachState(heaven);

        camera = new mygame.Camera();
        stateManager.attach(camera);
    }

    public Heaven getHeaven()
    {
        return heaven;
    }
    
    public void attachState(AppState level) {
        stateManager.attach(level);
    }

    public void detachState(AppState level) {
        stateManager.detach(level);
    }

    public void loadMenue() {
        menue = new MenueState(this);
        if (stateManager.hasState(heaven)) {
            detachState(heaven);
        }
        stateManager.attach(menue);
    }

    public BulletAppState getBulletAppState() {
        return bulletAppState;
    }
}
