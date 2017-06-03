package com.javarush.task.task32.task3209;

import java.awt.event.ActionEvent;

/**
 * Created by Vaisiliy Es'kin on 06/03/17.
 */
public class RedoAction {
    private View view;

    public RedoAction(View view) {
        this.view = view;
    }

    public void actionPerformed(ActionEvent actionEvent)
    {
        view.redo();
    }
}
