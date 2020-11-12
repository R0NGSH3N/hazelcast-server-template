package com.r0ngsh3n.hazelcast.client.listener;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.map.MapEvent;
import com.hazelcast.map.listener.*;

public class ClientMapEntryListener implements
        EntryAddedListener<String, String>,
        EntryRemovedListener<String, String>,
        EntryUpdatedListener<String, String>,
        EntryEvictedListener<String, String>,
        EntryLoadedListener<String, String>,
        MapEvictedListener,
        MapClearedListener {


    @Override
    public void entryAdded(EntryEvent<String, String> event) {

    }

    @Override
    public void entryEvicted(EntryEvent<String, String> event) {

    }

    @Override
    public void entryLoaded(EntryEvent<String, String> event) {

    }

    @Override
    public void entryRemoved(EntryEvent<String, String> event) {

    }

    @Override
    public void entryUpdated(EntryEvent<String, String> event) {

    }

    @Override
    public void mapCleared(MapEvent event) {

    }

    @Override
    public void mapEvicted(MapEvent event) {

    }
}
