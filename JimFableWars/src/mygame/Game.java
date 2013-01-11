package mygame;

import level.MenueState;
import character.CharacterFactory;
import character.Player;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppState;
import com.jme3.bullet.BulletAppState;
import level.Heaven;
import level.Hell;

/**
 * test
 *
 * @author normenhansen
 */
public class Game extends SimpleApplication {

    public BulletAppState bulletAppState;
    public Player player = null;
    private Heaven heaven = null;
    private Hell hell = null;
    private MenueState menue = null;
    private Camera camera;
    private GUIManager hudManager;

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
        player = CharacterFactory.createPlayer(this, "Dragon", assetManager);
        //rootNode.attachChild(player.model);
        hudManager = new GUIManager(this);

        // createHeaven();
    }

    @Override
    public void initialize() {
        super.initialize();
        loadMenue();
    }

    private void loadHeaven() {
        //remove Hell or Menue first
        heaven = new Heaven();
        heaven.initialize(stateManager, this);
        if (stateManager.hasState(menue)) {
            detachState(menue);
        }
        attachState(heaven);

        camera = new Camera();
        stateManager.attach(camera);
    }

    private void loadHell() {
    }

    public void attachState(AppState level) {
        stateManager.attach(level);
    }

    public void detachState(AppState level) {
        stateManager.detach(level);
    }

    private void loadMenue() {
        menue = new MenueState("MainMenue");
        menue.initialize(stateManager, this);
        attachState(menue);
    }

    @Override
    public void update() {
        super.update();
        if (heaven == null) {// && !menue.getCharacterName().isEmpty()){
            loadHeaven();
        }


        // }
        //load hell if...

        // do some animation
    /*    float tpf = timer.getTimePerFrame();
         stateManager.update(tpf);
         stateManager.render(renderManager);
         // render the viewports
         renderManager.render(tpf, context.isRenderable());*/
    }

    @Override
    public void simpleUpdate(float tpf) {
        player.update(tpf);
    }

    public BulletAppState getBulletAppState() {
        return bulletAppState;
    }
}
