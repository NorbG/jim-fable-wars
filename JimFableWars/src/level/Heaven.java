/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package level;

import character.Player;
import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.BloomFilter;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import mygame.Game;
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
import com.jme3.post.FilterPostProcessor;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.terrain.geomipmap.TerrainLodControl;
import com.jme3.terrain.geomipmap.TerrainQuad;
import java.util.ArrayList;
import java.util.List;
import jme3test.bullet.BombControl;

/**
 *
 * @author Lena
 */
public class Heaven extends LevelState implements ActionListener, PhysicsCollisionListener, AnimEventListener {
    
    private SimpleApplication app;
    private Node              rootNode;
    private AssetManager      assetManager;
    private AppStateManager   stateManager;
    private InputManager      inputManager;
    private ViewPort          viewPort;
    private BulletAppState    bulletAppState;
    private Player            player;
 //   Node model;
    //temp vectors
    Vector3f walkDirection = new Vector3f();
    //terrain
    TerrainQuad terrain;
    RigidBodyControl terrainPhysicsNode;
    //Materials
    Material matRock;
    Material matBullet;
    float airTime = 0;
    //camera
    boolean left = false, right = false, up = false;
    ChaseCamera chaseCam;
    //bullet
    Sphere bullet;
    SphereCollisionShape bulletCollisionShape;
    //explosion
    ParticleEmitter effect;
    //brick wall
    Box brick;
    float bLength = 0.8f;
    float bWidth = 0.4f;
    float bHeight = 0.4f;
    FilterPostProcessor fpp;
    
    public Heaven(){
        super();
    }
    
    public Node getRootNode(){
    return this.rootNode;
    }
    
  public void initialize(AppStateManager stateManager, Application app) {
    this.app = (Game) app; // can cast Application to something more specific
    this.rootNode     = this.app.getRootNode();
    this.assetManager = this.app.getAssetManager();
    this.stateManager = this.app.getStateManager();
    this.inputManager = this.app.getInputManager();
    this.viewPort     = this.app.getViewPort();
    this.bulletAppState = this.stateManager.getState(BulletAppState.class);
    this.player       = ((Game)app).player;
    
    
        setupKeys();
        prepareBullet();
        prepareEffect();
        createLight();
        createSky();
        createTerrain();
        createWall();
        createCharacter();
        setupChaseCamera();
        setupFilter();
        bulletControl();
        
      
  }
    
    public void moveOpponents(){
    }
    
    public void attackpPlayer(){
    }
    

    
    private void setupKeys() {
        inputManager.addMapping("wireframe", new KeyTrigger(KeyInput.KEY_T));
        inputManager.addListener(this, "wireframe");
        inputManager.addMapping("CharLeft", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("CharRight", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("CharUp", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("CharDirectAttack", new KeyTrigger(KeyInput.KEY_RETURN));
        inputManager.addMapping("CharDistanceAttack", new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addListener(this, "CharLeft");
        inputManager.addListener(this, "CharRight");
        inputManager.addListener(this, "CharUp");
        inputManager.addListener(this, "CharDirectAttack");
        inputManager.addListener(this, "CharDistanceAttack");
    }

    
        private void setupFilter() {
        FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
        BloomFilter bloom = new BloomFilter(BloomFilter.GlowMode.Objects);
        fpp.addFilter(bloom);
        viewPort.addProcessor(fpp);
    }
        
         private void createWall() {
        float xOff = -144;
        float zOff = -40;
        float startpt = bLength / 4 - xOff;
        float height = 6.1f;
        brick = new Box(Vector3f.ZERO, bLength, bHeight, bWidth);
        brick.scaleTextureCoordinates(new Vector2f(1f, .5f));
        for (int j = 0; j < 15; j++) {
            for (int i = 0; i < 4; i++) {
                Vector3f vt = new Vector3f(i * bLength * 2 + startpt, bHeight + height, zOff);
                addBrick(vt);
            }
            startpt = -startpt;
            height += 1.01f * bHeight;
        }
    }

         
    private void addBrick(Vector3f ori) {
        Geometry reBoxg = new Geometry("brick", brick);
        reBoxg.setMaterial(matBullet);
        reBoxg.setLocalTranslation(ori);
        reBoxg.addControl(new RigidBodyControl(1.5f));
        reBoxg.setShadowMode(ShadowMode.CastAndReceive);
        this.rootNode.attachChild(reBoxg);
        this.getPhysicsSpace().add(reBoxg);
    }


    private void prepareBullet() {
        bullet = new Sphere(32, 32, 0.4f, true, false);
        bullet.setTextureMode(TextureMode.Projected);
        bulletCollisionShape = new SphereCollisionShape(0.4f);
        matBullet = new Material(this.app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        matBullet.setColor("Color", ColorRGBA.Green);
        matBullet.setColor("GlowColor", ColorRGBA.Green);
        getPhysicsSpace().addCollisionListener(this);
    }


    private void prepareEffect() {
        int COUNT_FACTOR = 1;
        float COUNT_FACTOR_F = 1f;
        effect = new ParticleEmitter("Flame", Type.Triangle, 32 * COUNT_FACTOR);
        effect.setSelectRandomImage(true);
        effect.setStartColor(new ColorRGBA(1f, 0.4f, 0.05f, (float) (1f / COUNT_FACTOR_F)));
        effect.setEndColor(new ColorRGBA(.4f, .22f, .12f, 0f));
        effect.setStartSize(1.3f);
        effect.setEndSize(2f);
        effect.setShape(new EmitterSphereShape(Vector3f.ZERO, 1f));
        effect.setParticlesPerSec(0);
        effect.setGravity(0, -5, 0);
        effect.setLowLife(.4f);
        effect.setHighLife(.5f);
        effect.setInitialVelocity(new Vector3f(0, 7, 0));
        effect.setVelocityVariation(1f);
        effect.setImagesX(2);
        effect.setImagesY(2);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Particle.j3md");
        mat.setTexture("Texture", assetManager.loadTexture("Effects/Explosion/flame.png"));
        effect.setMaterial(mat);
//        effect.setLocalScale(100);
        rootNode.attachChild(effect);
    }


    private void createLight() {
        Vector3f direction = new Vector3f(-0.1f, -0.7f, -1).normalizeLocal();
        DirectionalLight dl = new DirectionalLight();
        dl.setDirection(direction);
        dl.setColor(new ColorRGBA(1f, 1f, 1f, 1.0f));
        rootNode.addLight(dl);
    }


    private void createSky() {
        rootNode.attachChild(SkyFactory.createSky(assetManager, "Textures/Sky/Bright/BrightSky.dds", false));
    }


    private void createTerrain() {
        matRock = new Material(assetManager, "Common/MatDefs/Terrain/TerrainLighting.j3md");
        matRock.setBoolean("useTriPlanarMapping", false);
        matRock.setBoolean("WardIso", true);
        matRock.setTexture("AlphaMap", assetManager.loadTexture("Textures/Terrain/splat/alphamap.png"));
        Texture heightMapImage = assetManager.loadTexture("Textures/Terrain/splat/mountains512.png");
        Texture grass = assetManager.loadTexture("Textures/Terrain/splat/grass.jpg");
        grass.setWrap(WrapMode.Repeat);
        matRock.setTexture("DiffuseMap", grass);
        matRock.setFloat("DiffuseMap_0_scale", 64);
        Texture dirt = assetManager.loadTexture("Textures/Terrain/splat/dirt.jpg");
        dirt.setWrap(WrapMode.Repeat);
        matRock.setTexture("DiffuseMap_1", dirt);
        matRock.setFloat("DiffuseMap_1_scale", 16);
        Texture rock = assetManager.loadTexture("Textures/Terrain/splat/road.jpg");
        rock.setWrap(WrapMode.Repeat);
        matRock.setTexture("DiffuseMap_2", rock);
        matRock.setFloat("DiffuseMap_2_scale", 128);
        Texture normalMap0 = assetManager.loadTexture("Textures/Terrain/splat/grass_normal.jpg");
        normalMap0.setWrap(WrapMode.Repeat);
        Texture normalMap1 = assetManager.loadTexture("Textures/Terrain/splat/dirt_normal.png");
        normalMap1.setWrap(WrapMode.Repeat);
        Texture normalMap2 = assetManager.loadTexture("Textures/Terrain/splat/road_normal.png");
        normalMap2.setWrap(WrapMode.Repeat);
        matRock.setTexture("NormalMap", normalMap0);
        matRock.setTexture("NormalMap_1", normalMap2);
        matRock.setTexture("NormalMap_2", normalMap2);


        AbstractHeightMap heightmap = null;
        try {
            heightmap = new ImageBasedHeightMap(heightMapImage.getImage(), 0.25f);
            heightmap.load();


        } catch (Exception e) {
            e.printStackTrace();
        }


        terrain = new TerrainQuad("terrain", 65, 513, heightmap.getHeightMap());
        List<Camera> cameras = new ArrayList<Camera>();
        cameras.add(this.app.getCamera());
        TerrainLodControl control = new TerrainLodControl(terrain, cameras);
        terrain.addControl(control);
        terrain.setMaterial(matRock);
        terrain.setLocalScale(new Vector3f(2, 2, 2));


        terrainPhysicsNode = new RigidBodyControl(CollisionShapeFactory.createMeshShape(terrain), 0);
        terrain.addControl(terrainPhysicsNode);
        rootNode.attachChild(terrain);
        getPhysicsSpace().add(terrainPhysicsNode);
    }


    private void createCharacter() {
        player = CharacterFactory.createPlayer("", assetManager, this.bulletAppState);  
        rootNode.attachChild(player.model);
        getPhysicsSpace().add(player.character);
        player.animation.addListener(this);
    }


    private void setupChaseCamera() {
        this.app.getFlyByCamera().setEnabled(false);
        chaseCam = new ChaseCamera(this.app.getCamera(), player.model, inputManager);
    }



    @Override
    public void update(float tpf) {
      //  rootNode.updateLogicalState(tpf);
        rootNode.updateGeometricState();
        Vector3f camDir = this.app.getCamera().getDirection().clone().multLocal(0.1f);
        Vector3f camLeft = this.app.getCamera().getLeft().clone().multLocal(0.1f);
        camDir.y = 0;
        camLeft.y = 0;
        walkDirection.set(0, 0, 0);
        if (left) {
            walkDirection.addLocal(camLeft);
        }
        if (right) {
            walkDirection.addLocal(camLeft.negate());
        }
        if (up) {
            walkDirection.addLocal(camDir);
        }
        
        if (!player.character.onGround()) {
            airTime = airTime + tpf;
        } else {
            airTime = 0;
        }
        if (walkDirection.length() == 0) {
            if (!"stand".equals(player.channel.getAnimationName())) {
                player.channel.setAnim("stand", 1f);
            }
        } else {
            player.character.setViewDirection(walkDirection);
            if (airTime > .3f) {
                if (!"stand".equals(player.channel.getAnimationName())) {
                    player.channel.setAnim("stand");
                }
            } else if (!"Walk".equals(player.channel.getAnimationName())) {
                player.channel.setAnim("Walk", 0.7f);
            }
        }
        player.character.setWalkDirection(walkDirection);
    }


    public void onAction(String binding, boolean value, float tpf) {
        if (binding.equals("CharLeft")) {
            if (value) {
                left = true;
            } else {
                left = false;
            }
        } 
        if (binding.equals("CharRight")) {
            if (value) {
                right = true;
            } else {
                right = false;
            }
        } 
        if (binding.equals("CharUp")) {
            player.character.jump();
        } 
        if (binding.equals("CharDistanceAttack")) {
            player.distanceAttack();
        } else if (binding.equals("CharDirectAttack")) {
            player.directAttack(true);
        }
    }


    private void bulletControl() {
        player.channel.setAnim("Dodge", 0.1f);
        player.channel.setLoopMode(LoopMode.DontLoop);
        Geometry bulletg = new Geometry("bullet", bullet);
        bulletg.setMaterial(matBullet);
        bulletg.setShadowMode(ShadowMode.CastAndReceive);
        bulletg.setLocalTranslation(player.character.getPhysicsLocation().add(this.app.getCamera().getDirection().mult(5)));
        RigidBodyControl bulletControl = new BombControl(bulletCollisionShape, 1);
        bulletControl.setCcdMotionThreshold(0.1f);
        bulletControl.setLinearVelocity(this.app.getCamera().getDirection().mult(80));
        bulletg.addControl(bulletControl);
        rootNode.attachChild(bulletg);
        getPhysicsSpace().add(bulletControl);
    }

        public void collision(PhysicsCollisionEvent event) {
        if (event.getObjectA() instanceof BombControl) {
            final Spatial node = event.getNodeA();
            effect.killAllParticles();
            effect.setLocalTranslation(node.getLocalTranslation());
            effect.emitAllParticles();
        } else if (event.getObjectB() instanceof BombControl) {
            final Spatial node = event.getNodeB();
            effect.killAllParticles();
            effect.setLocalTranslation(node.getLocalTranslation());
            effect.emitAllParticles();
        }
    }


    public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) {
        if (channel == player.channel) {
            channel.setAnim("stand");
        }
    }
    
      private PhysicsSpace getPhysicsSpace() {
        return bulletAppState.getPhysicsSpace();
    }

    public void onAnimChange(AnimControl control, AnimChannel channel, String animName) {
      
    }
}
