package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

/**
 * Created by Vaisiliy Es'kin on 05/17/17.
 */
public class CachingProxyRetriever implements Retriever {
    LRUCache<Long, Object> lruCache = new LRUCache<>(0);
    OriginalRetriever originalRetriever;

    @Override
    public Object retrieve(long id) {
        Object o = lruCache.find(id);
        if(o == null) {
            Object or = originalRetriever.retrieve(id);
            lruCache.set(id, or);
            return or;
        }
        else
        {
            return o;
        }
    }

    public CachingProxyRetriever(Storage storage) {
        this.originalRetriever = new OriginalRetriever(storage);
    }
}
