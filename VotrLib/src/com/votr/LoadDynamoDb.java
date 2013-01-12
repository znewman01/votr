package com.votr;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.dynamodb.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodb.model.AttributeValue;
import com.amazonaws.services.dynamodb.model.PutItemRequest;

public class LoadDynamoDb {

    static AmazonDynamoDBClient client;
    static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    static String tableName = "Votes"; 
    
    public static void main(String[] args) throws Exception {
        createClient();

        try {

            uploadSampleVotes(tableName);

        } catch (AmazonServiceException ase) {
            System.err.println("Data load script failed.");
        }
    }
    
    public static void createClient() throws Exception {
        //AWSCredentials credentials = new PropertiesCredentials(
        //        LoadDynamoDb.class.getResourceAsStream("AwsCredentials.properties"));
    	AWSCredentials credentials = new BasicAWSCredentials("",
    			""); // I AM A BAD PERSON FOR DOING THIS
    	//Key censored
    	
        client = new AmazonDynamoDBClient(credentials);
        client.setEndpoint("https://dynamodb.us-west-1.amazonaws.com");
    }

    public static void addVote(Vote vote)
    {
    	try
    	{
    		Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();

     	item.put("voter_id", new AttributeValue().withS(vote.voter_id)); 
     	item.put("poll_id", new AttributeValue().withS(vote.poll_id)); 
     	item.put("choice", new AttributeValue().withN(vote.choice)); 
     	item.put("tags", new AttributeValue().withSS(vote.tags));
     	item.put("city", new AttributeValue().withS(vote.city)); 
     	item.put("state", new AttributeValue().withS(vote.state)); 
     	item.put("zipcode", new AttributeValue().withS(vote.zipcode)); 
     	
     	PutItemRequest itemRequest = new PutItemRequest().withTableName(tableName).withItem(item);
         client.putItem(itemRequest);
         item.clear();
    	}
    	
    	catch (AmazonServiceException ase) {
            System.err.println("Failed to create item in " + tableName);
            System.out.println(ase); 
        } 
    }
    private static void uploadSampleVotes(String tableName) {
        
        try {
        	Collection<String> tags1 = new HashSet<String>(); 
        	tags1.add("cheezburgers");
        	tags1.add("catnip"); 
        	Vote vote1 = new Vote("4153423422", "2012_prez_us", "1", "New York", "NY", "10000", tags1); 

        	addVote(vote1); 
             
        }   catch (AmazonServiceException ase) {
            System.err.println("Failed to create item in " + tableName);
            System.out.println(ase); 
        } 
    }   
}
