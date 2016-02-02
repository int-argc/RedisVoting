<%-- Vote.jsp: handle votes --%>

<%@page import="redistools.SetOperations"%>
<%

String entryName = request.getParameter("entryName");

SetOperations so = (SetOperations)session.getAttribute("setoper");
so.incrementScore(entryName);

// System.out.println("Vote.jsp: Vote success!");
response.sendRedirect("index.jsp");

%>
