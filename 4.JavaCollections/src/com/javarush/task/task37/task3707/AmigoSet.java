package com.javarush.task.task37.task3707;

import java.io.*;
import java.util.*;

/**
 * Created by Vaisiliy Es'kin on 04/22/17.
 */
public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E> {
    private static final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    public AmigoSet() {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        int capacity = Math.max(16, (int) (collection.size() / 0.75f + 1));
        map = new HashMap<E, Object>(capacity);
        addAll(collection);
    }

    @Override
    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }


    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public boolean contains(Object o) {
        return map.keySet().contains(o);
    }

    public void clear() {
        map.clear();
    }

    public boolean remove(Object o) {
        return map.remove(o)==null;
    }

    public Object clone()
    {
        try {
            AmigoSet copy = (AmigoSet)super.clone();
            copy.map = (HashMap) map.clone();
            return copy;
        }
        catch (Exception e) {
            throw new InternalError();
        }
    }

    private void writeObject(ObjectOutputStream oos) throws IOException
    {
        oos.defaultWriteObject();

        oos.writeInt(map.size());
        for(E e:map.keySet())
        {
            oos.writeObject(e);
        }
        oos.writeFloat(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
        oos.writeInt(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException
    {
        ois.defaultReadObject();
        int size = (int)ois.readObject();
        Set<E> set = new HashSet<>();
        for (int i=0;i<size;i++)
        {
            set.add((E) ois.readObject());
        }
        float loadFactor = ois.readFloat();
        int capacity = ois.readInt();
        map = new HashMap<>(capacity,loadFactor);

        for(E e:set)
        {
            map.put(e,PRESENT);
        }
    }
}
