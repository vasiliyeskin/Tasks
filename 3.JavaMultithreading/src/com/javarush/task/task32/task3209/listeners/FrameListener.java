package com.javarush.task.task32.task3209.listeners;

import com.javarush.task.task32.task3209.View;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Vaisiliy Es'kin on 06/02/17.
 */
public class FrameListener extends WindowAdapter {
    private View view;

    public FrameListener(View view) {
        this.view = view;
    }

    @Override
    public void windowClosing(WindowEvent windowEvent)
    {
        view.exit();
    }
}
