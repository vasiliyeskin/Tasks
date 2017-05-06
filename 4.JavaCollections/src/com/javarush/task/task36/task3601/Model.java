package com.javarush.task.task36.task3601;

import java.util.List;

/**
 * Created by Vaisiliy Es'kin on 05/06/17.
 */
public class Model {
    Service service = new Service();

    public List<String> getStringDataList() {
        return service.getData();
    }
}
