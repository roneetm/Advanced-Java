<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Search</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>

<body>
    <!-- Nav Bar -->
    <nav class="navbar navbar-dark bg-dark">
        <div class="container">
            <span class="navbar-brand mb-0 h1">MIKE</span>
            <a href="index.jsp" class="btn btn-outline-danger" role="button">Logout</a>
        </div>
    </nav>

    <!--Search Form-->
    <div class="container">
        <div class="row">
            <div class="col-3 md-1"></div>
            <!-- Center Div        -->
            <div class="col-6 md-8 mt-5">
                <h1 class="h2 mb-3 fw-normal"><strong>Enter a name to search</strong></h1>
                <form action="search" class="row g-3" method="post">
                    <span style="color:red;">${errMsg}</span>

                    <div class="col-md-6">
                        <label for="inputFirstName4" class="form-label">First Name</label>
                        <input type="text" class="form-control" id="inputFirstName4" name="firstName" placeholder="John"
                            required>
                    </div>
                    <div class="col-md-6">
                        <label for="inputLastName4" class="form-label">Last Name</label>
                        <input type="text" class="form-control" id="inputLastName4" name="lastName" placeholder="Smith"
                            required>
                    </div>
                    <button type="submit" class="btn btn-primary">Search</button>
            </div>
            </form>
        </div>
        <div class="md-2"></div>
    </div>
    </div>
</body>

</html>