/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package character;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import mygame.Game;

/**
 *
 * @author Aty
 */
public class Projectile extends AbstractAppState{
    private Vector3f direction;
    private Vector3f position;
    private Game game;
    private ParticleEmitter emitter;
    private final float PROJECTILE_SPEED = 10.f;
    
    public Projectile(Vector3f direction, Vector3f position) {
        this.direction = direction;
        this.position = position;
    }

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        
        game = (Game) app;

        emitter = new ParticleEmitter("Emitter", ParticleMesh.Type.Triangle, 30);
        Material mat_red = new Material(game.getAssetManager(), "Common/MatDefs/Misc/Particle.j3md");
        mat_red.setTexture("Texture", app.getAssetManager().loadTexture("Effects/Explosion/flame.png"));
        emitter.setMaterial(mat_red);
        emitter.setImagesX(2); emitter.setImagesY(2); // 2x2 texture animation
        emitter.setEndColor(  new ColorRGBA(1f, 0f, 0f, 1f));   // red
        emitter.setStartColor(new ColorRGBA(1f, 1f, 0f, 0.5f)); // yellow
        emitter.getParticleInfluencer().setInitialVelocity(new Vector3f(0, 2, 0));
        emitter.setStartSize(0.6f);
        emitter.setEndSize(0.1f);
        emitter.setGravity(0f,0f,0f);
        emitter.setLowLife(0.5f);
        emitter.setHighLife(3f);
        emitter.getParticleInfluencer().setVelocityVariation(0.3f);        
        emitter.setLocalTranslation(this.position);
        
        game.getRootNode().attachChild(emitter);
    }

    @Override
    public void update(float tpf) {
        super.update(tpf);
        
        emitter.move(direction.mult(tpf * PROJECTILE_SPEED));
    }
}
