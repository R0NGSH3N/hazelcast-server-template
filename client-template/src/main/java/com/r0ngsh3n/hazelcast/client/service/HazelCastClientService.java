package com.r0ngsh3n.hazelcast.client.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.r0ngsh3n.hazelcast.client.entity.SampleData;
import com.r0ngsh3n.hazelcast.client.entity.SampleDataKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HazelCastClientService {

    private HazelcastInstance clientInstance;

    @Autowired
    public void setClientInstance(HazelcastInstance clientInstance) {
        this.clientInstance = clientInstance;
    }

    public void storeData(SampleDataKey key,  SampleData sampleData){
        IMap<SampleDataKey, SampleData> map = clientInstance.getMap("samel");
        map.put(key, sampleData) ;
    }

    public SampleData getData(SampleDataKey key){
        IMap<SampleDataKey, SampleData> map = clientInstance.getMap("samel");
        return map.get(key);
    }
}
