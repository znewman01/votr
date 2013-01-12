<?xml version="1.0" encoding="UTF-8"?>
<Response>
<%
java.util.logging.Logger logger = java.util.logging.Logger.getLogger("");
java.util.Enumeration paramNames = request.getParameterNames();
while(paramNames.hasMoreElements()) {
  String paramName = (String)paramNames.nextElement();
  String buf = "";
  buf = buf + paramName + ": ";
  String[] paramValues = request.getParameterValues(paramName);
  if (paramValues.length == 1) {
    String paramValue = paramValues[0];
    if (paramValue.length() == 0)
      buf = buf + "No Value";
    else
      buf = buf + paramValue.toString();
  } else {
    for(int i=0; i<paramValues.length; i++) {
      buf = buf + paramValues[i].toString() + ",";
    }
  }
  logger.setLevel(java.util.logging.Level.INFO);
  logger.info(buf);
  //out.println("</Sms>");
}

%>
</Response>