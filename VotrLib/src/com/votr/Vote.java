import java.util.Collection;

public class Vote
{
	public Vote(String voter_id, String poll_id, String choice, String city, String state, String zipcode, Collection<String> tags) {
		this.voter_id = voter_id;
		this.poll_id = poll_id;
		this.tags = tags;
		this.choice = choice;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
	}
	final String voter_id; 
	final String poll_id; 
	final Collection<String> tags; 
	final String choice; 
	final String city;
	final String state;
	final String zipcode;


}