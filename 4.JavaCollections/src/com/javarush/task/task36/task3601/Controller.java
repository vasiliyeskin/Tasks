package com.javarush.task.task36.task3601;

import java.util.List;

/**
 * Created by Vaisiliy Es'kin on 05/06/17.
 */
public class Controller {
    Model model = new Model();

    public List<String> onDataListShow() {
        return model.getStringDataList();
    }
}
