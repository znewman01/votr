import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.dynamodb.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodb.model.AttributeValue;
import com.amazonaws.services.dynamodb.model.PutItemRequest;

public class LoadDynamoDb {

    static AmazonDynamoDBClient client;
    static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    static String productCatalogTableName = "ProductCatalog";
    static String forumTableName = "Forum";
    static String threadTableName = "Thread";
    static String replyTableName = "Reply";
    static String votesTableName = "Votes"; 
    
    public static void main(String[] args) throws Exception {
        createClient();

        try {

            uploadSampleVotes(votesTableName);
//            uploadSampleForums(forumTableName);
//            uploadSampleThreads(threadTableName);
//            uploadSampleReplies(replyTableName);

        } catch (AmazonServiceException ase) {
            System.err.println("Data load script failed.");
        }
    }
    
    private static void createClient() throws Exception {
        AWSCredentials credentials = new PropertiesCredentials(
                LoadDynamoDb.class.getResourceAsStream("AwsCredentials.properties"));

        client = new AmazonDynamoDBClient(credentials);
        client.setEndpoint("https://dynamodb.us-west-1.amazonaws.com");
    }

    public static void addVote(Vote vote)
    {
    
    }
    private static void uploadSampleVotes(String tableName) {
        
        try {
        	Collection<String> tags1 = new HashSet<String>(); 
        	tags1.add("gaymarriage");
        	tags1.add("healthcare"); 
        	Vote vote1 = new Vote("3052343234", "2012_prez_us", "1", tags1 ); 

        	
        	Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
        	item.put("voter_id", new AttributeValue().withS("4087688027")); 
        	item.put("poll_id", new AttributeValue().withS("2012_prez_us")); 
        	item.put("choice", new AttributeValue().withN("2")); 
        	item.put("tags", new AttributeValue().withSS(Arrays.asList("guncontrol", "healthcare")));
            
            PutItemRequest itemRequest = new PutItemRequest().withTableName(tableName).withItem(item);
            client.putItem(itemRequest);
            item.clear();
           
            
            
            
            ////
        	item.put("voter_id", new AttributeValue().withS(vote1.voter_id)); 
        	item.put("poll_id", new AttributeValue().withS(vote1.poll_id)); 
        	item.put("choice", new AttributeValue().withN(vote1.choice)); 
        	item.put("tags", new AttributeValue().withSS(vote1.tags));
            
            itemRequest = new PutItemRequest().withTableName(tableName).withItem(item);
            client.putItem(itemRequest);
            item.clear();
            
          
          
       

                
        }   catch (AmazonServiceException ase) {
            System.err.println("Failed to create item in " + tableName);
            System.out.println(ase); 
        } 

    }

    private static void uploadSampleForums(String tableName) {
        try {
            // Add forums.
            Map<String, AttributeValue> forum = new HashMap<String, AttributeValue>();
            forum.put("Name", new AttributeValue().withS("Amazon DynamoDB"));
            forum.put("Category", new AttributeValue().withS("Amazon Web Services"));
            forum.put("Threads", new AttributeValue().withN("2"));
            forum.put("Messages", new AttributeValue().withN("4"));
            forum.put("Views", new AttributeValue().withN("1000"));

            PutItemRequest forumRequest = new PutItemRequest().withTableName(tableName).withItem(forum);
            client.putItem(forumRequest);
            forum.clear();
            
            forum.put("Name", new AttributeValue().withS("Amazon S3"));
            forum.put("Category", new AttributeValue().withS("Amazon Web Services"));
            forum.put("Threads", new AttributeValue().withN("0"));
            
            forumRequest = new PutItemRequest().withTableName(tableName).withItem(forum);
            client.putItem(forumRequest);
                
        }   catch (AmazonServiceException ase) {
            System.err.println("Failed to create item in " + tableName);
        }         
    }

    private static void uploadSampleThreads(String tableName) {
        try {
            long time1 = (new Date()).getTime() - (7L*24L*60L*60L*1000L); // 7 days ago
            long time2 = (new Date()).getTime() - (14L*24L*60L*60L*1000L); // 14 days ago
            long time3 = (new Date()).getTime() - (21L*24L*60L*60L*1000L); // 21 days ago

            Date date1 = new Date();
            date1.setTime(time1);

            Date date2 = new Date();
            date2.setTime(time2);

            Date date3 = new Date();
            date3.setTime(time3);

            // Add threads.
            Map<String, AttributeValue> forum = new HashMap<String, AttributeValue>();
            forum.put("ForumName", new AttributeValue().withS("Amazon DynamoDB"));
            forum.put("Subject", new AttributeValue().withS("DynamoDB Thread 1"));
            forum.put("Message", new AttributeValue().withS("DynamoDB thread 1 message"));
            forum.put("LastPostedBy", new AttributeValue().withS("User A"));
            forum.put("LastPostedDateTime", new AttributeValue().withS(dateFormatter.format(date2)));
            forum.put("Views", new AttributeValue().withN("0"));
            forum.put("Replies", new AttributeValue().withN("0"));
            forum.put("Answered", new AttributeValue().withN("0"));
            forum.put("Tags", new AttributeValue().withSS(Arrays.asList("index", "primarykey", "table")));
            
            PutItemRequest forumRequest = new PutItemRequest().withTableName(tableName).withItem(forum);
            client.putItem(forumRequest);

            forum.clear();
            
            forum.put("ForumName", new AttributeValue().withS("Amazon DynamoDB"));
            forum.put("Subject", new AttributeValue().withS("DynamoDB Thread 2"));
            forum.put("Message", new AttributeValue().withS("DynamoDB thread 2 message"));
            forum.put("LastPostedBy", new AttributeValue().withS("User A"));
            forum.put("LastPostedDateTime", new AttributeValue().withS(dateFormatter.format(date3)));
            forum.put("Views", new AttributeValue().withN("0"));
            forum.put("Replies", new AttributeValue().withN("0"));
            forum.put("Answered", new AttributeValue().withN("0"));
            forum.put("Tags", new AttributeValue().withSS(Arrays.asList("index", "primarykey", "rangekey")));

            forumRequest = new PutItemRequest().withTableName(tableName).withItem(forum);
            client.putItem(forumRequest);
            
            forum.clear();
            
            forum.put("ForumName", new AttributeValue().withS("Amazon S3"));
            forum.put("Subject", new AttributeValue().withS("S3 Thread 1"));
            forum.put("Message", new AttributeValue().withS("S3 Thread 3 message"));
            forum.put("LastPostedBy", new AttributeValue().withS("User A"));
            forum.put("LastPostedDateTime", new AttributeValue().withS(dateFormatter.format(date1)));
            forum.put("Views", new AttributeValue().withN("0"));
            forum.put("Replies", new AttributeValue().withN("0"));
            forum.put("Answered", new AttributeValue().withN("0"));
            forum.put("Tags", new AttributeValue().withSS(Arrays.asList("largeobjects", "multipart upload")));

            forumRequest = new PutItemRequest().withTableName(tableName).withItem(forum);
            
            client.putItem(forumRequest);

                
        }   catch (AmazonServiceException ase) {
            System.err.println("Failed to create item in " + tableName);
        }         
        
    }

    private static void uploadSampleReplies(String tableName) {
        try {
            long time0 = (new Date()).getTime() - (1L*24L*60L*60L*1000L); // 1 day ago
            long time1 = (new Date()).getTime() - (7L*24L*60L*60L*1000L); // 7 days ago
            long time2 = (new Date()).getTime() - (14L*24L*60L*60L*1000L); // 14 days ago
            long time3 = (new Date()).getTime() - (21L*24L*60L*60L*1000L); // 21 days ago

            Date date0 = new Date();
            date0.setTime(time0);

            Date date1 = new Date();
            date1.setTime(time1);

            Date date2 = new Date();
            date2.setTime(time2);

            Date date3 = new Date();
            date3.setTime(time3);

            // Add threads.
            Map<String, AttributeValue> reply = new HashMap<String, AttributeValue>();
            reply.put("Id", new AttributeValue().withS("Amazon DynamoDB#DynamoDB Thread 1"));
            reply.put("ReplyDateTime", new AttributeValue().withS(dateFormatter.format(date3)));
            reply.put("Message", new AttributeValue().withS("DynamoDB Thread 1 Reply 1 text"));
            reply.put("PostedBy", new AttributeValue().withS("User A"));

            PutItemRequest replyRequest = new PutItemRequest().withTableName(tableName).withItem(reply);
            client.putItem(replyRequest);
            
            reply.clear();
            
            reply = new HashMap<String, AttributeValue>();
            reply.put("Id", new AttributeValue().withS("Amazon DynamoDB#DynamoDB Thread 1"));
            reply.put("ReplyDateTime", new AttributeValue().withS(dateFormatter.format(date2)));
            reply.put("Message", new AttributeValue().withS("DynamoDB Thread 1 Reply 2 text"));
            reply.put("PostedBy", new AttributeValue().withS("User B"));

            replyRequest = new PutItemRequest().withTableName(tableName).withItem(reply);
            client.putItem(replyRequest);

            reply.clear();

            reply = new HashMap<String, AttributeValue>();
            reply.put("Id", new AttributeValue().withS("Amazon DynamoDB#DynamoDB Thread 2"));
            reply.put("ReplyDateTime", new AttributeValue().withS(dateFormatter.format(date1)));
            reply.put("Message", new AttributeValue().withS("DynamoDB Thread 2 Reply 1 text"));
            reply.put("PostedBy", new AttributeValue().withS("User A"));

            replyRequest = new PutItemRequest().withTableName(tableName).withItem(reply);
            client.putItem(replyRequest);
            
            reply.clear();

            reply = new HashMap<String, AttributeValue>();
            reply.put("Id", new AttributeValue().withS("Amazon DynamoDB#DynamoDB Thread 2"));
            reply.put("ReplyDateTime", new AttributeValue().withS(dateFormatter.format(date0)));
            reply.put("Message", new AttributeValue().withS("DynamoDB Thread 2 Reply 2 text"));
            reply.put("PostedBy", new AttributeValue().withS("User A"));

            replyRequest = new PutItemRequest().withTableName(tableName).withItem(reply);

            client.putItem(replyRequest);
 
        }   catch (AmazonServiceException ase) {
            System.err.println("Failed to create item in " + tableName);
        }        
    }
}