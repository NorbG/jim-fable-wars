package mygame;
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
 public Player player;
 private Heaven heaven;
 private Hell hell;
 private MenueState menue;



    public static void main(String[] args) {
        Game app = new Game();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        bulletAppState = new BulletAppState();
        bulletAppState.setThreadingType(BulletAppState.ThreadingType.PARALLEL);
        stateManager.attach(bulletAppState);
         player = CharacterFactory.createPlayer("", assetManager, this.bulletAppState);  
        rootNode.attachChild(player.model);

        
   // createHeaven();
    }

 @Override
    public void initialize(){
        super.initialize();
        loadHeaven();
    }

    private void loadHeaven() {
        //remove Hell or Menue first
        heaven = new Heaven();
        heaven.initialize(stateManager, this);
        stateManager.attach(heaven);
    }
    
    
    private void loadHell() {
    }
    
    
    private void loadMenue() {
    }


    @Override
    public void update(){
        super.update();
        // do some animation
        float tpf = timer.getTimePerFrame();
        stateManager.update(tpf);
        stateManager.render(renderManager);
        // render the viewports
        renderManager.render(tpf, context.isRenderable());
    }    
    
}

