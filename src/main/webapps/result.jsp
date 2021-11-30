<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <meta charset="UTF-8">
    <title>Search Result</title>
</head>

<body>
    <!-- Nav Bar -->
    <nav class="navbar navbar-dark bg-dark">
        <div class="container">
            <span class="navbar-brand mb-0 h1">MIKE</span>
            <a href="registration.html" class="btn btn-outline-danger" role="button">Logout</a>
        </div>
    </nav>

    <!--Business Logic-->
    <div class="container">
        <div class="row">
            <div class="card col-3">
                <div class="card-body">
                    <%
                        // retrieve your list from the request, with casting
                            ArrayList<String> list = (ArrayList<String>) request.getAttribute("searchResult");
                            Iterator iterator = list.iterator();
                            // print the information about every category of the list
                            while(iterator.hasNext())
                            out.println(iterator.next());
                    %>
                </div>
            </div>
        </div>

    </div>
</body>

</html>