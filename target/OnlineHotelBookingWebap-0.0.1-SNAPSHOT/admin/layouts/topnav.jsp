<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  
  <!-- Preloader -->
   <div class="preloader flex-column justify-content-center align-items-center">
	    <img class="animation__shake" src="admin/layouts/dist/img/AdminLTELogo.png" alt="AdminLTELogo" height="60" width="60">
   </div> 
	  
  <!-- Navbar -->
  <nav class="main-header navbar navbar-expand navbar-white">
    <!-- Left navbar links -->
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
      </li>
      <li class="nav-item d-none d-sm-inline-block">
        <a href="index3.html" class="nav-link">Home</a>
      </li>
      <li class="nav-item d-none d-sm-inline-block">
        <a href="#" class="nav-link">Contact</a>
      </li>
    </ul>

    <!-- Right navbar links -->
    <ul class="navbar-nav ml-auto">     
	
	<!-- Notifications Dropdown Menu -->
	  <li class="nav-item d-none d-sm-inline-block dropdown">
        <a href="#" class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        	<i class="fa fa-user-circle-o" style="font-size:25px;"></i>&nbsp;&nbsp;
        	<b><c:out value="${sessionScope.user.firstName}"/> <c:out value="${sessionScope.user.lastName}"/></b>
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#">Profile</a>
          <a class="dropdown-item" href="<%=request.getContextPath()%>/adminlogout">Logout</a>
        </div>
      </li>
      <%-- <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
         <i class="fa fa-user-circle-o" style="font-size:36px;color: black"></i>&nbsp;&nbsp;
         <!-- <big style="color: black"><b> --><c:out value="${sessionScope.user.firstname}"/> <c:out value="${sessionScope.user.lastname}"/><!-- </b></big> --> 
        &nbsp;</a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#">Profile</a>
          <a class="dropdown-item" href="<%=request.getContextPath()%>/logout">Logout</a>
          </div>
      </li> --%>      
      
      <li class="nav-item">
        <a class="nav-link" data-widget="fullscreen" href="#" role="button">
          <i class="fas fa-expand-arrows-alt"></i>
        </a>
      </li>
   
    </ul>
  </nav>
  <!-- /.navbar -->