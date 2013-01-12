import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;


public class DataSimulator {
	private int totalEntry;
	private int entryPerSec;
	private int timeSpan; // in seconds
	private String poll_ID;
	private int numChoice;
	private ArrayList<String> topics;
	private ArrayList<String> areacode;
	private Random randomGen;
	
	private void initTopics()
	{
		topics = new ArrayList<String>();
		topics.add("guncontrol");
		topics.add("medicare");
		topics.add("babyeating");
		topics.add("cheezeburger");
		topics.add("litterboxcleanup");
		topics.add("catniplegalization");
		topics.add("ceilingcat");		
	}	
	
	private String getRandomVoterID()
	{
		
		String id = areacode.get(this.randomGen.nextInt(this.areacode.size()));
		String str = Integer.toString(this.randomGen.nextInt(9999999));
		while (str.length()<7) str = "0" + str;
		id = id + str;
		System.out.println(id);
		return id;
	}
	
	private Collection<String> getRandomTags()
	{
		HashSet<String> tags = new HashSet<String>();
		int num = this.randomGen.nextInt(4)+1;
		while(num>0)
		{
			tags.add(this.topics.get(randomGen.nextInt(this.topics.size())));
			num--;
		}
		
		return tags;
	}
	
	private Vote getRandomVote()
	{
		String choice = Integer.toString(randomGen.nextInt(this.numChoice)+1);
		Vote vote;
		int i = randomGen.nextInt(10);
		String city = "West Lafayette";
		String state = "IN";
		String zipcode = "47906";
		switch (i)
		{
			case 0:
				city = "New York"; state = "NY"; zipcode = "10001";
				break;
			case 1:
				city = "New York"; state = "NY"; zipcode = "10001";
				break;
			case 2:
				city = "New York"; state = "NY"; zipcode = "10001";
				break;
			case 3:
				city = "Chicago"; state = "IL"; zipcode = "60601";
				break;
			case 4:
				city = "Chicago"; state = "IL"; zipcode = "60601";
				break;
			case 5:
				city = "Chicago"; state = "IL"; zipcode = "60601";
				break;
			case 6:
				city = "Los Angeles"; state = "CA"; zipcode = "90001";
				break;
			case 7:
				city = "Los Angeles"; state = "CA"; zipcode = "90001";
				break;
			case 8:
				city = "Houston"; state = "TX"; zipcode = "77001";
				break;
			case 9:
				city = "Philadelphia"; state = "PA"; zipcode = "19019";
				break;
			default :
				
				break;
		}
		
		if (randomGen.nextInt(2)%2==0)
		{
			vote = new Vote(this.getRandomVoterID(), this.poll_ID, choice, city, state, zipcode, this.getRandomTags());
		}
		else
		{
			vote = new Vote(this.getRandomVoterID(), this.poll_ID, "2", city, state, zipcode,  this.getRandomTags());				
		}
		
		return vote;
	}
	
	public DataSimulator(int totalEntry, int timeSpan, String poll_ID, int numChoice)
	{
		this.totalEntry = totalEntry;
		this.timeSpan = timeSpan;
		this.entryPerSec = totalEntry/timeSpan;
		this.poll_ID = poll_ID;
		this.numChoice = numChoice;
		this.randomGen = new Random(19580427);
		initTopics();
		
		this.areacode = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("/home/formatjam/votr/DataSimulator/src/areacode.dat"));
			String str;
			while ((str = br.readLine())!=null)
			{
				areacode.add(str);			
			}
			
			br.close();
		} catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void generateRecords()
	{
		long currTime = System.currentTimeMillis();
		long nextTime = currTime + 1000;
		while (totalEntry>0)
		{
			int limit = this.entryPerSec;
			//System.out.println(currTime);
			while(currTime<nextTime)
			{
				if (limit>0)
				{
					totalEntry--;
					limit--;
					//TODO call the static function 
					this.getRandomVote();
					//create a vote and add it into the database
					
				}
				currTime = System.currentTimeMillis();
			}	
			currTime = System.currentTimeMillis();
			nextTime = currTime + 1000;
		}
		
		//System.out.println(currTime);
	}
	
	public static void main(String[] args) 
	{
		DataSimulator ds = new DataSimulator(1000000, 30, "2012_prez_us", 2);
		ds.generateRecords();
	}	
}
