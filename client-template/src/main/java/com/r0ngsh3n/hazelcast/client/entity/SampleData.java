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

    private Long id;
    private String stringSample;
    private Date dateSample;
    private BigDecimal bigDecimalSample;

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
        out.writeLong(id);
        out.writeUTF(stringSample);
        out.writeObject(dateSample);
        out.writeObject(bigDecimalSample);
    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {
        this.id = in.readLong();
        this.stringSample = in.readUTF();
        this.dateSample = (Date)in.readObject();
        this.bigDecimalSample = (BigDecimal)in.readObject();
    }
}
