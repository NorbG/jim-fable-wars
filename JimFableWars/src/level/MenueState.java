/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package level;

import com.jme3.app.state.AbstractAppState;
import com.jme3.audio.AudioNode;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.niftygui.NiftyJmeDisplay;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.PanelRenderer;
import de.lessvoid.nifty.tools.Color;
import mygame.Game;


/**
 *
 * @author Lena
 */
public class MenueState extends AbstractAppState {

    private AudioNode background = null;
    
    private Game game;
    private Nifty nifty;
    private MainMenuSelection currentSelection = MainMenuSelection.START_GAME;
    // these two must be exactly the number of hearts that are drawn in the xml file
    private final int MAX_PLAYER_HEALTH = 4;
    private final int MAX_BOSS_HEALTH = 20;

    // the screens in the XML file
    public static enum Screen {

        MAIN_MENU, HUD
    };

    public static enum MainMenuSelection {

        START_GAME, EXIT_GAME
    };

    public MenueState(Game app) {
        this.game = app;
        background =  new AudioNode(game.getAssetManager(), "Sounds/Menue/Menue.wav");
        background.setLooping(true);
        background.play();
        // create nifty
        NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(
                app.getAssetManager(), app.getInputManager(), app.getAudioRenderer(), app.getGuiViewPort());
        nifty = niftyDisplay.getNifty();
        app.getGuiViewPort().addProcessor(niftyDisplay);

        // load hud screen
        setScreen(Screen.MAIN_MENU);        

        initKeys();
    }

    public void setScreen(Screen screen) {
        if (screen == Screen.MAIN_MENU) {
            nifty.fromXml("Interface/screen.xml", "start");
        } else if (screen == Screen.HUD) {
            nifty.fromXml("Interface/screen.xml", "hud");
            
            // set default health
            updatePlayerHealth(MAX_PLAYER_HEALTH);
            updateBossHealth(0);
        }
    }

    private Element findHUDElementByName(String name) {
        return nifty.getCurrentScreen().findElementByName(name);
    }

    public void updatePlayerHealth(int health) {
        // prevent invalid health
        if (health < 0) {
            health = 0;
        }

        if (health > MAX_PLAYER_HEALTH) {
            health = MAX_PLAYER_HEALTH;
        }

        // show all hearts first 
        for (int i = 1; i <= MAX_PLAYER_HEALTH; i++) {
            Element curHeart = findHUDElementByName("ph_0" + i);

            if (curHeart != null) {
                curHeart.show();
            }
        }

        // now hide those that are not available
        for (int i = health + 1; i <= MAX_PLAYER_HEALTH; i++) {
            Element curHeart = findHUDElementByName("ph_0" + i);

            if (curHeart != null) {
                curHeart.hide();
            }
        }
    }

    public void updateBossHealth(int health) {
        // prevent invalid health
        if (health < 0) {
            health = 0;
        }

        if (health > MAX_BOSS_HEALTH) {
            health = MAX_BOSS_HEALTH;
        }

        // show all hearts first 
        for (int i = 1; i <= MAX_BOSS_HEALTH; i++) {
            String prefix = "bh_0";

            if (i >= 10) {
                prefix = "bh_";
            }
            
            String name = prefix + i;

            Element curHeart = findHUDElementByName(prefix + i);

            if (curHeart != null) {
                curHeart.show();
            }
        }

        // now hide those that are not available
        for (int i = health + 1; i <= MAX_BOSS_HEALTH; i++) {
            String prefix = "bh_0";

            if (i >= 10) {
                prefix = "bh_";
            }

            Element curHeart = findHUDElementByName(prefix + i);

            if (curHeart != null) {
                curHeart.hide();
            }
        }
    }

    private void initKeys() {
        game.getInputManager().addMapping("Up", new KeyTrigger(KeyInput.KEY_UP));
        game.getInputManager().addMapping("Down", new KeyTrigger(KeyInput.KEY_DOWN));
        game.getInputManager().addMapping("Enter", new KeyTrigger(KeyInput.KEY_RETURN));
        game.getInputManager().addListener(actionListener, new String[]{"Up", "Down", "Enter"});
    }
    
    private ActionListener actionListener = new ActionListener() {
        public void onAction(String name, boolean keyPressed, float tpf) {
            // navigate through the menu
            if ((name.equals("Up") || name.equals("Down")) && keyPressed) {
                if (currentSelection == MainMenuSelection.START_GAME) {
                    currentSelection = MainMenuSelection.EXIT_GAME;

                    findHUDElementByName("button_start").getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#0002"));
                    findHUDElementByName("button_end").getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#0006"));
                } else {
                    currentSelection = MainMenuSelection.START_GAME;

                    findHUDElementByName("button_start").getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#0006"));
                    findHUDElementByName("button_end").getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#0002"));
                }
            }

            // define action on enter press
            if (name.equals("Enter") && keyPressed) {
                if (currentSelection == MainMenuSelection.START_GAME) {
                    // TODO: START LEVEL
                    background.stop();
                    game.loadHeaven();
                    setScreen(Screen.HUD);
                    game.getInputManager().removeListener(actionListener);
                } else {
                    game.stop();
                }
            }
        }
    };
}
