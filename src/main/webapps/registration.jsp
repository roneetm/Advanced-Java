<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<!-- Nav Bar -->
<nav class="navbar navbar-dark bg-dark">
    <div class="container">
        <span class="navbar-brand mb-0 h1">MIKE</span>
        <span class="navbar-text">
        <a href="index.jsp" class="btn btn-outline-success" role="button">Login</a>
      </span>
    </div>
</nav>

<div class="container">

    <div class="row">

        <div class="col-3 md-1"> </div>

        <div class="col-6 md-8 pt-5">
            <span style="color:red;">
                <strong>
                <% 
                    if(null!=request.getAttribute("deleteMsg")) {
                     out.println(request.getAttribute("deleteMsg")); 
                    } 
                %>
                   </strong> 
            </span>
            <h1 class="h2 mb-3 fw-normal"> <strong>Sign Up</strong> </h1>
            <form action="registration" class="row g-3" method="post">
                
                <div class="col-md-6">
                    <label for="inputFirstName4" class="form-label">First Name</label>
                    <input type="text" class="form-control" id="inputFirstName4" name="firstName" placeholder="John" required>
                </div>
                <div class="col-md-6">
                    <label for="inputLastName4" class="form-label">Last Name</label>
                    <input type="text" class="form-control" id="inputLastName4" name="lastName" placeholder="Smith" required>
                </div>
                <div class="col-md-6">
                    <label for="inputCity" class="form-label">City</label>
                    <input type="text" class="form-control" id="inputCity" name="city">
                </div>
                <div class="col-md-4">
                    <label for="inputState" class="form-label">State</label>
                    <input type="text" class="form-control" id="inputState" name="state">
                </div>
                <div class="col-md-2">
                    <label for="inputZip" class="form-label">Zip</label>
                    <input type="text" class="form-control" name="zip" id="inputZip">
                </div>
                <div class="col-12">
                    <label for="inputAddress" class="form-label">Country</label>
                    <input type="text" class="form-control" id="inputAddress" name="country">
                </div>
                <div class="col-md-12">
                    <label for="inputFirstName4" class="form-label">Phone</label>
                    <input type="text" class="form-control" name="phone" placeholder="+91 99xxxxxx00">
                </div>
                <div class="col-md-6">
                    <label for="inputEmail4" class="form-label">Email</label>
                    <input type="email" class="form-control" id="inputEmail4" name="email" placeholder="email@xyz.com">
                    <span style="color:red;">
                        <% 
                            if(null!=request.getAttribute("errMsg")) {
                             out.println(request.getAttribute("errMsg")); 
                            } 
                        %> 
                        </span>
                </div>
                <div class="col-md-6">
                    <label for="inputPassword4" class="form-label">Password</label>
                    <input type="password" class="form-control" id="inputPassword4" name="password" placeholder="Use uppercase and lowercase" required>
                </div>

                <div class="col-12">
                    <button type="submit" class="btn btn-primary">Sign Up</button>
                </div>
            </form>
        </div>

        <div class="md-2"></div>

    </div>
</div>


</body>
</html>