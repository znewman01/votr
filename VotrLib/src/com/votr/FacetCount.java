package com.votr;

public class FacetCount {
	public final String value;
	public final int count;
	
	public FacetCount(String value, int count) {
		super();
		this.value = value;
		this.count = count;
	}

	@Override
	public String toString() {
		return "FacetCount [value=" + value + ", count=" + count + "]";
	}
	
	
}
