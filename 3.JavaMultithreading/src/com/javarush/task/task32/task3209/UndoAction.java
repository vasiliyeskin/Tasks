package com.javarush.task.task32.task3209;

import java.awt.event.ActionEvent;

/**
 * Created by Vaisiliy Es'kin on 06/03/17.
 */
public class UndoAction {
    private View view;

    public UndoAction(View view) {
        this.view = view;
    }

    public void actionPerformed(ActionEvent actionEvent)
    {
        view.undo();
    }
}
