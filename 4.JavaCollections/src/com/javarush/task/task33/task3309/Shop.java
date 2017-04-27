package com.javarush.task.task33.task3309;


import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlType(name="shop")
@XmlRootElement
public class Shop
{
    @XmlElementWrapper(name="goods", nillable = true)
    public int count;
    public double profit;
    public String[] secretData = new String[5];

    public Shop()
    {
    }
}