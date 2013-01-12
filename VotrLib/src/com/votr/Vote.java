package com.votr;

import java.util.Collection;

public class Vote
{
	public Vote(String voter_id, String poll_id, String choice, Collection<String> tags, String voter_city, String voter_state, String voter_zip) {
		this.voter_id = voter_id;
		this.poll_id = poll_id;
		this.tags = tags;
		this.choice = choice;
		this.voter_city = voter_city;
		this.voter_state = voter_state;
		this.voter_zip = voter_zip;
	}
	final String voter_id; 
	final String poll_id; 
	final Collection<String> tags; 
	final String choice; 
	final String voter_city;
	final String voter_state;
	final String voter_zip;
	
}

