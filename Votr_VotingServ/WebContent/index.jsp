<?xml version="1.0" encoding="UTF-8"?>
<Response>
<%@ page import="com.votr.Vote,com.votr.LoadDynamoDb" %>
<%
java.util.logging.Logger logger = java.util.logging.Logger.getLogger("");
java.util.Enumeration paramNames = request.getParameterNames();

String pollId = "2012 General Election"; //Should be in some configuration file
String voterId = request.getParameterValues("From")[0];
String voterCity = request.getParameterValues("FromCity")[0];
String voterState = request.getParameterValues("FromState")[0];
String voterZip = request.getParameterValues("FromZip")[0];
String voterCountry = request.getParameterValues("FromCountry")[0];

String body = request.getParameterValues("Body")[0];
java.util.List<String> bodyValues = java.util.Arrays.asList(body.split(" "));
java.util.Iterator<String> iterator = bodyValues.iterator();
String choice = iterator.next().toString();

java.util.Collection<String> tags = new java.util.ArrayList<String>();
tags.clear();

while (iterator.hasNext()) {
	tags.add(iterator.next());
}

if (!voterCountry.equals("US")) {
	logger.setLevel(java.util.logging.Level.INFO);
	logger.warning("Voter " + voterId + " is not from the US.");
	out.println("<Sms>Error: Voter not from the U.S.</Sms>");
} else {
	Vote vote = new Vote(voterId, pollId, choice, tags);
	LoadDynamoDb.createClient();
	LoadDynamoDb.addVote(vote);
	out.println("<Sms>Voted successfully for " + choice + " </Sms>");
}
%>

</Response>