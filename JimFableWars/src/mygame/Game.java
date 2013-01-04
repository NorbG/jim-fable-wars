package mygame;
import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.LoopMode;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh.Type;
import com.jme3.effect.shapes.EmitterSphereShape;
import com.jme3.light.DirectionalLight;
import com.jme3.math.Vector2f;
import com.jme3.post.filters.BloomFilter;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Sphere;
import com.jme3.scene.shape.Sphere.TextureMode;
import com.jme3.terrain.heightmap.AbstractHeightMap;
import com.jme3.terrain.heightmap.ImageBasedHeightMap;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;
import com.jme3.util.SkyFactory;
import character.CharacterFactory;
import character.Player;
import com.jme3.animation.AnimEventListener;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.input.ChaseCamera;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.post.FilterPostProcessor;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeContext;
import com.jme3.terrain.geomipmap.TerrainLodControl;
import com.jme3.terrain.geomipmap.TerrainQuad;
import java.util.ArrayList;
import java.util.List;
import jme3test.app.state.RootNodeState;
import jme3test.bullet.BombControl;
import level.Heaven;
import level.Hell;

/**
 * test
 * @author normenhansen
 */
public class Game extends SimpleApplication {
 public BulletAppState bulletAppState;
 private Heaven heaven;
 private Hell hell;
 private MenueState menue;
 
    //character
   // CharacterControl character;
   public Player player;
 //   Node model;


    public static void main(String[] args) {
        Game app = new Game();
        app.start();
    }
   


    @Override
    public void simpleInitApp() {
        bulletAppState = new BulletAppState();
        bulletAppState.setThreadingType(BulletAppState.ThreadingType.PARALLEL);
        stateManager.attach(bulletAppState);
   // createHeaven();
    }


    
    /* @Override
    public void start(JmeContext.Type contextType){
        AppSettings settings = new AppSettings(true);
        settings.setResolution(1024, 768);
        setSettings(settings);
  
        super.start(contextType);
    }*/

 @Override
    public void initialize(){
        super.initialize();
        heaven = new Heaven();
        heaven.initialize(stateManager, this);
        stateManager.attach(heaven);
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

