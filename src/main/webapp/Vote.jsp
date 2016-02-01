
<%@page import="redistools.SetOperations"%>
<%


String entryName = request.getParameter("entryName");

SetOperations so = (SetOperations)session.getAttribute("setoper");
so.incrementScore(entryName);

System.out.println("increment.jsp: Add success!");
response.sendRedirect("index.jsp");

%>
