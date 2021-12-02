<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="javax.servlet.http.HttpServletRequest" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>
    <!-- Nav Bar -->
<nav class="navbar navbar-dark bg-dark">
    <div class="container">
        <span class="navbar-brand mb-0 h1">MIKE</span>
        <a href="logout" class="btn btn-outline-danger" role="button">Logout</a>
    </div>
</nav>

 <!-- Profile Page -->
 <div class="col-lg-8 mx-auto p-3 py-md-5">
    <header class="d-flex align-items-center pb-3 mb-5 border-bottom">
        <span class="fs-4">Welcome to your Profile</span>
    <br>
    </header>
    <% 
            String email = (String) session.getAttribute("email");
            String firstName = (String) session.getAttribute("firstName");
            String lastName = (String) session.getAttribute("lastName");
            String city = (String) session.getAttribute("city");
            String zip = (String) session.getAttribute("zip");
            String state = (String) session.getAttribute("state");
            String country = (String) session.getAttribute("country");
            String phone = (String) session.getAttribute("phone");
            %>
    <div class="field">
        First Name: <%= firstName%>
        <br>
        Last Name: <%= lastName%>
        <br>
        Email: <%= email%>
        <br>
        City: <%= city%>
        <br>
        Zip: <%= zip%>
        <br>
        State: <%= state%>
        <br>
        Country: <%= country%>
        <br>
        Phone: <%= phone%>
        <br>
    </div>
    

<section class="vh-50" style="background-color: #5f59f7;">
    <div class="container py-5 h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col col-xl-10">
          <div class="card mb-5" style="border-radius: 15px;">
            <div class="card-body p-4">
              <h3 class="mb-3"><%= firstName%> <%= lastName%></h3>
              <p class="small mb-0"><i class="far fa-star fa-lg"></i> <span class="mx-2">|</span> Email: <strong><%= email%></strong></p>
              <p class="small mb-0"><i class="far fa-star fa-lg"></i> <span class="mx-2">|</span> Phone: <strong><%= phone%></strong></p>
              <hr class="my-4">
              <div class="d-flex justify-content-start align-items-center">
                <p class="mb-0 text-uppercase"><i class="fas fa-cog me-2"></i> <span class="text-muted small"><%= city%></span></p>
                <p class="mb-0 text-uppercase"><i class="fas fa-cog me-2"></i> <span class="text-muted small"><%= zip%></span></p>
                <p class="mb-0 text-uppercase"><i class="fas fa-link ms-4 me-2"></i> <span class="text-muted small"><%= state%></span></p>
                <p class="mb-0 text-uppercase"><i class="fas fa-ellipsis-h ms-4 me-2"></i> <span class="text-muted small"><%= country%></span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</body>
</html>