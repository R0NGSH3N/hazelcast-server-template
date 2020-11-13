package com.r0ngsh3n.hazelcast.client.entity;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class SampleData implements IdentifiedDataSerializable {
    @Getter
    @Setter
    public static class ObjectSample{
        private String name;
    }

    private Long id;
    private String stringSample;
    private Date dateSample;
    private BigDecimal bigDecimalSample;
    private ObjectSample objectSample;

    @Override
    public int getFactoryId() {
        return SampleDataSerializableFactory.SAMPLE_DATA_FACTORY_ID;
    }

    @Override
    public int getClassId() {
        return SampleDataSerializableFactory.SAMPLE_DATA_ID;
    }

    @Override
    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeUTF();

    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {

    }
}
