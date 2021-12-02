<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
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
            String email =(String) session.getAttribute("email"); 
            String firstName = (String) session.getAttribute("firstName"); 
            String lastName = (String) session.getAttribute("lastName"); 
            String city = (String) session.getAttribute("city"); 
            String zip = (String) session.getAttribute("zip"); 
            String state = (String) session.getAttribute("state"); 
            String country = (String) session.getAttribute("country"); 
            String phone = (String) session.getAttribute("phone"); 
        %>

          <section class="vh-70" style="background-color: #5f59f7;">
            <div class="container py-5 h-100">
              <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col col-xl-10">
                  <div class="card mb-5" style="border-radius: 15px;">
                    <div class="card-body p-4">
                      <div class="row">
                        <div class="col-9">
                          <h3 class="mb-3">
                            <%= firstName%>
                              <%= lastName%>
                          </h3>
                        </div>
                        <div class="col-1">
                          <!-- Edit Modal Open -->
                           <!-- Modal -->
                           <button type="button" class="btn btn-warning btn-sm" data-bs-toggle="modal"
                           data-bs-target="#editModal" data-bs-whatever="@getbootstrap">Edit</button>
                         <div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                           aria-hidden="true">
                           <div class="modal-dialog">
                             <div class="modal-content">
                               <div class="modal-header">
                                 <h5 class="modal-title" id="exampleModalLabel">Edit Profile</h5>
                                 <button type="button" class="btn-close" data-bs-dismiss="modal"
                                   aria-label="Close"></button>
                               </div>
                               <div class="modal-body">
                                 <form style="align-items: center;">
                                   <div class="col-md-12">
                                     <label for="inputFirstName4" class="form-label">First Name</label>
                                     <input type="text" class="form-control" id="inputFirstName4" name="firstName"
                                       placeholder="John" value=<%= firstName%> required>
                                   </div>
                                   <div class="col-md-12">
                                     <label for="inputLastName4" class="form-label">Last Name</label>
                                     <input type="text" class="form-control" id="inputLastName4" name="lastName"
                                       placeholder="Smith" value=<%= lastName%> required>
                                   </div>
                                   <div class="col-md-12">
                                     <label for="inputCity" class="form-label">City</label>
                                     <input type="text" class="form-control" id="inputCity" name="city" value=<%= city%>>
                                   </div>
                                   <div class="col-md-12">
                                     <label for="inputState" class="form-label">State</label>
                                     <input type="text" class="form-control" id="inputState" name="state" value=<%= state%>>
                                   </div>
                                   <div class="col-md-12">
                                     <label for="inputZip" class="form-label">Zip</label>
                                     <input type="text" class="form-control" name="zip" id="inputZip" value=<%= zip%>>
                                   </div>
                                   <div class="col-12">
                                     <label for="inputAddress" class="form-label">Country</label>
                                     <input type="text" class="form-control" id="inputAddress" name="country" value=<%= country%>>
                                   </div>
                                   <div class="col-md-12">
                                     <label for="inputFirstName4" class="form-label">Phone</label>
                                     <input type="text" class="form-control" name="phone" placeholder="+91 99xxxxxx00" value=<%= phone%>>
                                   </div>
                                 </form>
                               </div>
                               <div class="modal-footer">
                                 <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                 <a class="btn btn-success btn-sm" href="/editprofile" role="button">Save</a>
                               </div>
                             </div>
                           </div>
                         </div>
                          <!-- Edit Modal Close -->
                          
                        </div>
                        <div class="col-2">
                          <!-- Delete Button modal -->
                          <button type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal"
                            data-bs-target="#deleteModal">
                            Delete
                          </button>

                          <!-- Modal -->
                          <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                            aria-hidden="true">
                            <div class="modal-dialog">
                              <div class="modal-content">
                                <div class="modal-header">
                                  <h5 class="modal-title" id="exampleModalLabel">Delete Profile</h5>
                                  <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                  Are you sure you want to delete your profile? You will have to register again to
                                  access your profile.
                                </div>
                                <div class="modal-footer">
                                  <button type="button" class="btn btn-secondary btn-sm"
                                    data-bs-dismiss="modal">Close</button>
                                  <a class="btn btn-danger btn-sm" href="/delete" role="button">Delete</a>
                                </div>
                              </div>
                            </div>
                          </div>
                          <!-- Modal Close -->

                        </div>
                      </div>
                      <p class="small mb-0"><i class="far fa-star fa-lg"></i> <span class="mx-2">|</span> Email:
                        <strong>
                          <%= email%>
                        </strong>
                      </p>
                      <p class="small mb-0"><i class="far fa-star fa-lg"></i> <span class="mx-2">|</span> Phone:
                        <strong>
                          <%= phone%>
                        </strong>
                      </p>
                      <hr class="my-4">
                      <div class="d-flex justify-content-start align-items-center">
                        <p class="mb-0 text-uppercase"><i class="fas fa-cog me-2"></i> <span class="text-muted small">
                            <%= city%>
                          </span></p>
                        <p class="mb-0 text-uppercase"><i class="fas fa-cog me-2"></i> <span class="text-muted small">
                            <%= zip%>
                          </span></p>
                        <p class="mb-0 text-uppercase"><i class="fas fa-link ms-4 me-2"></i> <span
                            class="text-muted small">
                            <%= state%>
                          </span></p>
                        <p class="mb-0 text-uppercase"><i class="fas fa-ellipsis-h ms-4 me-2"></i> <span
                            class="text-muted small">
                            <%= country%>
                          </span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </section>

          <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
    </body>

    </html>