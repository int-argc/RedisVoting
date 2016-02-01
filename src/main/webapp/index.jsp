
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- import redis connector and set operations --%>
<%@page import="redistools.RedisConnector"%>
<%@page import="redistools.SetOperations"%>
<%@page import="java.util.*"%>
<%@page import="javax.servlet.jsp.*"%>
<%@page import="java.io.*"%>

<!DOCTYPE html>
<%!
// initialize Jedis pool
SetOperations so = new SetOperations("candidates");

// function definitions
public void makeDivCard(PrintWriter out, String $name, int $score) {
    out.println("<div class=\"card\">");
    out.println("<span class=\"score\">(" + $score + ")</span><span class=\"name\">" + $name + "</span>");
    out.println("<button class=\"votebtn\">vote</button>");
    out.println("</div>");
}

%>


<html>
    <head>
        <meta charset="utf-8">
        <title>Hello</title>
        <link rel="stylesheet" href="styles.css" charset="utf-8">
    </head>
    <body>
        <%
        session.setAttribute("setoper", so);
        %>
        <h1>hello world! redis here we go!</h1>

        <h1>Redis Voting System</h1>
	<div id="navbar">
		<a href="AddCandidate.jsp">Add candidate</a>
		<a href="ResetList.jsp">Reset Candidate List</a>
	</div>

	<div id="centerPane">
<%
		// if(so.hasContent()) {
		// 	Set<String> candidates = so.sortDesc();
		// 	for(String s : candidates) {
		// 		out.println("s = " + s);
		// 	}
		// }

        Set<String> entries = so.sortDesc();
        for(String entry : entries) {
            int score = so.getScore(entry);
            makeDivCard(new PrintWriter((Writer)out, true), entry, score);
		}

        // for (int i = 0; i < 10 ; i++) {
        //     out.println("<i>i am </i>" + i);
        // }
%>
	</div>

    <script>
        $(document).ready(function() {
               $("button").click(function() {
                   var entry = $(this).prevAll('.name').text();
                   console.log("voted for " + entry);
                   $.ajax({
                       url: 'Vote.jsp',
                       type: "POST",
                       data: { entryName: entry }  // wala laman entry?
                   }).done(function() {
                       window.location.reload();
                   });
               });
         });    // end of document.ready
    </script>

    </body>
</html>
