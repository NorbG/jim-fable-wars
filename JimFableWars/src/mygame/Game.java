package mygame;
import level.MenueState;
import character.CharacterFactory;
import character.Player;
import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import level.Heaven;
import level.Hell;

/**
 * test
 * @author normenhansen
 */
public class Game extends SimpleApplication {
 public BulletAppState bulletAppState;
 public Player player = null;
 private Heaven heaven = null;
 private Hell hell = null;
 private MenueState menue = null;



    public static void main(String[] args) {
        Game app = new Game();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        bulletAppState = new BulletAppState();
        bulletAppState.setThreadingType(BulletAppState.ThreadingType.PARALLEL);
        stateManager.attach(bulletAppState);
        player = CharacterFactory.createPlayer("Dragon", assetManager, this.bulletAppState);  
        rootNode.attachChild(player.model);

        
   // createHeaven();
    }

 @Override
    public void initialize(){
        super.initialize();
        loadMenue();
    }

    private void loadHeaven() {
        //remove Hell or Menue first
        heaven = new Heaven();
        heaven.initialize(stateManager, this);
        stateManager.detach(menue);
        stateManager.attach(heaven);
    }
    
    
    private void loadHell() {
    }
    
    
    private void loadMenue() {
        menue = new MenueState("MainMenue");
        menue.initialize(stateManager, this);
        stateManager.attach(menue);
    }


    @Override
    public void update(){
        if(heaven == null && !menue.getCharacterName().isEmpty()){
            loadHeaven();
        }
  //load hell if...
        super.update();
        // do some animation
        float tpf = timer.getTimePerFrame();
        stateManager.update(tpf);
        stateManager.render(renderManager);
        // render the viewports
        renderManager.render(tpf, context.isRenderable());
    }    
    
}

