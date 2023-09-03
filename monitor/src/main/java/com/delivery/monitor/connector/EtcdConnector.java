package com.delivery.monitor.connector;

import com.coreos.jetcd.Client;
import com.coreos.jetcd.KV;
import com.coreos.jetcd.data.ByteSequence;
import com.coreos.jetcd.data.KeyValue;
import com.coreos.jetcd.kv.GetResponse;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Component;

@Component
public class EtcdConnector {

	private static final String ETCD_URL = "http://etcd:2379";
	private static final String MONITOR = "monitor";
	private static final String TRACKER = "tracker";
	
    public void register() throws ExecutionException, InterruptedException {

        Client client = Client.builder().endpoints(ETCD_URL).build();
        ByteSequence key_monitor = ByteSequence.fromString(MONITOR);
        ByteSequence value = ByteSequence.fromString("test_value");

        // put the key-value
        KV kvClient=client.getKVClient();

        kvClient.put(key_monitor,value).get();
        
    }


    public List<String> getTrackers() throws ExecutionException, InterruptedException {
    	List<String> result = new ArrayList<String>();
        Client client = Client.builder().endpoints(ETCD_URL).build();
        ByteSequence key_tracker = ByteSequence.fromString(TRACKER);

        // put the key-value
        KV kvClient=client.getKVClient();
        
    	// get the CompletableFuture
        CompletableFuture<GetResponse> getFuture = kvClient.get(key_tracker);

        // get the value from CompletableFuture
        GetResponse response = getFuture.get();

        for(KeyValue kv:response.getKvs())
        {
            result.add(kv.getValue().toString());
        	System.out.println(kv.getKey()+" == "+kv.getValue());
        }   
        
        return result;
    }
   
	
}
