<%-- reset list jsp for processing adding something to set --%>

<%@page import="redistools.SetOperations"%>

<%
SetOperations so = (SetOperations)session.getAttribute("setoper");
so.deleteSet();

System.out.println("ResetList.jsp: Delete success!");
response.sendRedirect("index.jsp");
%>
