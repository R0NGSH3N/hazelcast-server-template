package com.r0ngsh3n.hazelcast.server.entity;

import com.hazelcast.nio.serialization.DataSerializableFactory;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;

public class SampleDataSerializableFactory implements DataSerializableFactory {
    public static final int SAMPLE_DATA_FACTORY_ID = 1;
    public static final int SAMPLE_DATA_KEY_FACTORY_ID = 2;
    public static final int SAMPLE_DATA_ID = 1;
    public static final int SAMPLE_DATA_KEY_ID = 2;

    public IdentifiedDataSerializable create(int typeId){
        switch (typeId){
            case SAMPLE_DATA_ID:
                return new SampleData();
            case SAMPLE_DATA_KEY_ID:
                return new SampleDataKey();
        }

        throw new IllegalArgumentException("No Type id found for " + typeId);
    }

}
