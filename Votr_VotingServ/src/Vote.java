import java.util.Collection;

public class Vote
{
	public Vote(String voter_id, String poll_id, String choice, Collection<String> tags) {
		this.voter_id = voter_id;
		this.poll_id = poll_id;
		this.tags = tags;
		this.choice = choice;
	}
	final String voter_id; 
	final String poll_id; 
	final Collection<String> tags; 
	final String choice; 
	
	
}

