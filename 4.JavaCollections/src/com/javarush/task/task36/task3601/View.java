package com.javarush.task.task36.task3601;

/**
 * Created by Vaisiliy Es'kin on 05/06/17.
 */
public class View {
    Controller controller = new Controller();

    public void fireEventShowData() {
        System.out.println(controller.onDataListShow());
    }

}
