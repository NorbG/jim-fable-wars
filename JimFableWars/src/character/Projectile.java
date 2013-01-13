/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package character;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.bullet.collision.PhysicsRayTestResult;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import java.util.List;
import mygame.Game;

/**
 *
 * @author Aty
 */
public class Projectile extends AbstractAppState {

    private Vector3f direction;
    private Vector3f position;
    private Game game;
    private ParticleEmitter emitter;
    private final float PROJECTILE_SPEED = 10.f;
    private boolean enabled = true;

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
        emitter.setImagesX(2);
        emitter.setImagesY(2); // 2x2 texture animation
        emitter.setEndColor(new ColorRGBA(1f, 0f, 0f, 1f));   // red
        emitter.setStartColor(new ColorRGBA(1f, 1f, 0f, 0.5f)); // yellow
        emitter.getParticleInfluencer().setInitialVelocity(new Vector3f(0, 2, 0));
        emitter.setStartSize(0.6f);
        emitter.setEndSize(0.1f);
        emitter.setGravity(0f, 0f, 0f);
        emitter.setLowLife(0.5f);
        emitter.setHighLife(3f);
        emitter.getParticleInfluencer().setVelocityVariation(0.3f);
        emitter.setLocalTranslation(this.position);

        game.getRootNode().attachChild(emitter);
    }

    @Override
    public void update(float tpf) {
        super.update(tpf);
        
        if (!enabled)
            return;

        position = position.add(direction.mult(tpf * PROJECTILE_SPEED));
        emitter.setLocalTranslation(position);

        Vector3f start = position;
        Vector3f end = position.add(direction).mult(1.f);
        List<PhysicsRayTestResult> physRayResults = game.getBulletAppState().getPhysicsSpace().rayTest(start, end);

        // draw helper line
        /*Geometry g = new Geometry("line2", new Line(start, end));
        Material m = new Material(game.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        g.setMaterial(m);
        game.getRootNode().detachChildNamed("line2");
        game.getRootNode().attachChild(g);*/

        // if test failed -> player is falling because he is not above ground
        if (physRayResults.isEmpty()) {
        } else {
            Object hitObject = physRayResults.get(0).getCollisionObject().getUserObject();

            if (hitObject instanceof Node) {
                Node hitNode = ((Node) hitObject);
                
                if (hitNode.getName().equals("Enemy"))
                {
                    // this makes no sense but without it, the target can't be removed
                    hitNode.getControl(RigidBodyControl.class)
                            .setPhysicsLocation(position.add(new Vector3f(0, 5, 0)));
                    hitNode.getParent().getChildren().remove(hitNode);
                    
                    game.getHeaven().killOpponent(hitNode.getName());
                }
                else if (hitNode.getName().equals("Player"))
                {
                    game.player.setHealth(0);
                }
                
                // remove fireball
                emitter.getParent().getChildren().remove(emitter);
                enabled = false;
            }
        }
    }
}
