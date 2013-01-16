package mygame;

import level.MenueState;
import character.CharacterFactory;
import character.Player;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppState;
import com.jme3.bullet.BulletAppState;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;
import level.Heaven;
import level.MenueState.Screen;

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
    
    public Node getGUINode(){
        return this.guiNode;
    }
    
        public AppSettings getSettings(){
        return this.settings;
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
      //  heaven.initialize(stateManager, this);
        if (stateManager.hasState(menue)) {
            detachState(menue);
            menue.setScreen(Screen.HUD);
        }
        //remove Hell or Menue first
        heaven = new Heaven();
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
