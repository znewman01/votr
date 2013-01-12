package com.votr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

public class Result {
	final Map<String,List<FacetCount>> facets;
	
	public Result(Map<String,List<FacetCount>> facets) {
		this.facets = facets;
	}
	
	public Result(JsonNode root) {
		this.facets = parse(root);
	}
	
	private Map<String,List<FacetCount>> parse(JsonNode root) {
		Map<String, List<FacetCount>> fac = new HashMap<String, List<FacetCount>>();
		List<String> fac_val = Arrays.asList("zipcode_count","city_count","choice_count","state_count","tags_count");
		
		
		//System.out.println("choices: " + root.findValue("choice_count"));
		//debugging: System.out.println("zpc: " + root.findValue("zipcode_count"));
		for (String s : fac_val) {
			List<FacetCount> fcl = new ArrayList<FacetCount>();
			JsonNode n = root.findValue(s).getElements().next();

			Iterator<JsonNode> i = n.getElements();
			while(i.hasNext()) {
				JsonNode n2 = i.next();
				FacetCount fc = new FacetCount(n2.get("value").getTextValue(), n2.get("count").getValueAsInt());
				fcl.add(fc);
			}
			
			fac.put(s, fcl);
	
		}
		System.out.println(fac);
		return fac;
	}

}
