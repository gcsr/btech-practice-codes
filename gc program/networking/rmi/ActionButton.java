

import javax.swing.*;
import java.awt.event.*;


/*
 Only here because JButton doesn't set the label correctly
 when passed an Action in its constructor
 */

public class ActionButton extends JButton {
    public ActionButton(Action clickAction) {
        //	Next line is correct in 1.3; in 1.2, we would need to explictly
        //	add clickAction	as an action listener
        super (clickAction);
        String label = (String) clickAction.getValue(Action.NAME);

        if (null != label) {
            setText(label);
        }
    }
} 

