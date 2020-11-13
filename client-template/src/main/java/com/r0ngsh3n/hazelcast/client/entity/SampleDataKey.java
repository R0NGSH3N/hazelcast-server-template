package com.r0ngsh3n.hazelcast.client.entity;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;

import java.io.IOException;

public class SampleDataKey implements IdentifiedDataSerializable {
    @Override
    public int getFactoryId() {
        return SampleDataSerializableFactory.SAMPLE_DATA_KEY_FACTORY_ID;
    }

    @Override
    public int getClassId() {
        return SampleDataSerializableFactory.SAMPLE_DATA_KEY_ID;
    }

    @Override
    public void writeData(ObjectDataOutput out) throws IOException {

    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {

    }
}
