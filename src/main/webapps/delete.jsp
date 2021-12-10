<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Delete Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>

<body>

    <!-- Nav Bar -->
    <nav class="navbar navbar-dark bg-dark">
        <div class="container">
            <span class="navbar-brand mb-0 h1">MIKE</span>
            <a href="/logout" class="btn btn-outline-success" role="button">Logout</a>
        </div>
    </nav>

    <div class="col-lg-8 mx-auto p-3 py-md-5">
        <header class="d-flex align-items-center pb-3 mb-5 border-bottom">
            <span class="fs-4">Are you sure you want to delete your profile?</span>
            <br>
        </header>
        <div class="d-grid gap-2 d-md-block">
            <a class="btn btn-outline-success btn-sm" href="/profile.jsp" role="button">Cancel</a>
            <a class="btn btn-danger btn-sm" href="/delete" role="button">Delete</a>
        </div>
    </div>
   

</body>

</html>