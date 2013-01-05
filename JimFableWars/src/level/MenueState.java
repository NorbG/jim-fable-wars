/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package level;

import com.jme3.app.state.AbstractAppState;


/**
 *
 * @author Lena
 */
public class MenueState extends AbstractAppState {

    private String character = "";
    
    public MenueState(String name){
        super();
    }
    
    public String getCharacterName(){
        return character;
    }
    
    //chooseCharacter()
    //quitGame()
    
    
}
