<%-- <<%@page import="com.narola.hotelbooking.customer.Customer"%>  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@page import="com.narola.hotelbooking.Utility.AdminURLConstant"%>
<!-- Main Sidebar Container -->
  <aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="index3.html" class="brand-link">
      <img src="admin/layouts/dist/img/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
      <span class="brand-text font-weight-light">OnlineHotelBooking</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
      <!-- Sidebar user panel (optional) -->
      <div class="user-panel mt-3 pb-3 mb-5 " style="padding-left: 10px;">
      	
        <i class="fa fa-user-circle-o" style="font-size:30px;color: white;"></i> &nbsp;&nbsp;
         <big style="font-size:20px;color: white;" ><b><c:out value="${sessionScope.user.firstName}"/> <c:out value="${sessionScope.user.lastName}"/></b></big> 
        
      </div>

      <!-- SidebarSearch Form -->
      <!-- <div class="form-inline">
        <div class="input-group" data-widget="sidebar-search">
          <input class="form-control form-control-sidebar" type="search" placeholder="Search" aria-label="Search">
          <div class="input-group-append">
            <button class="btn btn-sidebar">
              <i class="fas fa-search fa-fw"></i>
            </button>
          </div>
        </div>
      </div> -->

      <!-- Sidebar Menu -->
      <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
          <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
          <li class="nav-item">
          	
            <a href="<%=request.getContextPath()%>/admindashboard" class="nav-link ">            
              <i class="nav-icon fas fa-tachometer-alt"></i>
              <big>
                Dashoard
              </big>
            </a>
          </li>
          <li class="nav-item">
            <a href="<%=request.getContextPath() + AdminURLConstant.DISPLAYALL_HOTELS_URL%>" class="nav-link">
              <i class="nav-icon fas fa-hotel"></i>
              <big>
                Hotels
              </big>
            </a>
          </li>
          <li class="nav-item">
            <a href="<%=request.getContextPath() + AdminURLConstant.DISPLAYALL_CITIES_URL%>" class="nav-link">            
              <i class="nav-icon fas fa-city"></i>
              <big>
                Cities
              </big>
            </a>
          </li>
          <li class="nav-item">
            <a href="<%=request.getContextPath() + AdminURLConstant.DISPLAYALL_BOOKING_URL%>" class="nav-link">
              <i class="nav-icon fas fa-list"></i>
              <big>
                Bookings
              </big>
            </a>
          </li>
        </ul>
      </nav>
      <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
  </aside>
