<%-- add entry jsp for processing adding something to set --%>

<%@page import="redistools.SetOperations"%>

<%
String entryName = request.getParameter("entryName");

SetOperations so = (SetOperations)session.getAttribute("setoper");
so.add(0, entryName);

System.out.println("AddEntry.jsp: Add success!");
response.sendRedirect("index.jsp");
%>
