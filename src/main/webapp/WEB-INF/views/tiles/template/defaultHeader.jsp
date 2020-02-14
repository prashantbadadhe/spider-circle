<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta  charset="utf-8"/>
<title>Spicle | Admin Dashboard</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport"/>
<meta content="" name="description"/>
<meta content="" name="author"/>
<!-- BEGIN GLOBAL MANDATORY STYLES -->

<link href="static/metronic/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="static/metronic/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="static/metronic/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="static/metronic/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<link href="static/metronic/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="static/metronic/global/css/components.css" rel="stylesheet" type="text/css"/>
<link href="static/metronic/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="static/metronic/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<link href="static/metronic/admin/layout/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="static/metronic/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<link href="static/css/circleapp.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="static/css/bootstraphelper/bootstrap-formhelpers.css" type="text/css" media="screen">
<link href="static/css/ngDialog/ngDialog.css" rel="stylesheet" type="text/css" />
<link href="static/css/ngDialog/ngDialog-theme-default.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="static/css/ngDialog/ngDialog-custom-width.css">
<link href="static/metronic/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />

<script src="static/metronic/global/plugins/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="static/metronic/global/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<script src="static/metronic/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
<script src="static/metronic/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="static/metronic/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="static/metronic/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="static/metronic/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="static/metronic/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="static/metronic/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="static/metronic/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
<script src="static/metronic/global/plugins/select2/select2.min.js" type="text/javascript"></script>
<script src="static/metronic/global/plugins/datatables/media/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="static/metronic/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js" type="text/javascript"></script>
<script src="static/metronic/admin/pages/scripts/table-editable.js"></script>
<script src="static/metronic/global/scripts/metronic.js" type="text/javascript"></script>
<script src="static/metronic/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script type="text/javascript" src="static/js/bootstraphelper/bootstrap-formhelpers.js"></script>
 <script src="static/js/utility/angular.js"></script>
<script src="static/js/app.js"></script>
<script src="static/js/common.js"></script>
<link href="static/css/xeditable.css" rel="stylesheet">
<script src="static/js/utility/xeditable.js"></script>
<script src="static/js/utility/ngDialog.js"></script>
     <script src="static/js/utility/angular-animate.js"></script>
    <script src="static/js/utility/angular-sanitize.js"></script>
    <script src="static/js/utility/ui-bootstrap-tpls-2.2.0.js"></script>
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="favicon.ico"/>
</head>
 
<body class="page-header-fixed page-quick-sidebar-over-content">
 <div class="page-header navbar navbar-fixed-top">
 	<div class="page-header-inner">
 		<div class="page-logo">
			<a href="index.html">
			<img src="static/metronic/admin/layout/img/logo.png" alt="logo" class="logo-default"/>
			</a>
			<div class="menu-toggler sidebar-toggler hide">
				<!-- DOC: Remove the above "hide" to enable the sidebar toggler button on header -->
			</div>
		</div>
		<a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse">
		</a>
		<!-- END RESPONSIVE MENU TOGGLER -->
		<!-- BEGIN TOP NAVIGATION MENU -->
		<div class="top-menu">
			<ul class="nav navbar-nav pull-right">
				<!-- BEGIN NOTIFICATION DROPDOWN -->
				<li class="dropdown dropdown-extended dropdown-notification" id="header_notification_bar">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
					<i class="icon-bell"></i>
					<span class="badge badge-default">
					7 </span>
					</a>
					<ul class="dropdown-menu">
						<li>
							<p>
								 You have 14 new notifications
							</p>
						</li>
						<li>
							<ul class="dropdown-menu-list scroller" style="height: 250px;">
								<li>
									<a href="#">
									<span class="label label-sm label-icon label-success">
									<i class="fa fa-plus"></i>
									</span>
									New user registered. <span class="time">
									Just now </span>
									</a>
								</li>
								<li>
									<a href="#">
									<span class="label label-sm label-icon label-danger">
									<i class="fa fa-bolt"></i>
									</span>
									Server #12 overloaded. <span class="time">
									15 mins </span>
									</a>
								</li>
							</ul>
						</li>
						<li class="external">
							<a href="#">
							See all notifications <i class="m-icon-swapright"></i>
							</a>
						</li>
					</ul>
				</li>
				<!-- END NOTIFICATION DROPDOWN -->
				<!-- BEGIN INBOX DROPDOWN -->
				<li class="dropdown dropdown-extended dropdown-inbox" id="header_inbox_bar">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
					<i class="icon-envelope-open"></i>
					<span class="badge badge-default">
					4 </span>
					</a>
					<ul class="dropdown-menu">
						<li>
							<p>
								 You have 12 new messages
							</p>
						</li>
						<li>
							<ul class="dropdown-menu-list scroller" style="height: 250px;">
								<li>
									<a href="inbox.html?a=view">
									<span class="photo">
									<img src="static/metronic/admin/layout/img/avatar2.jpg" alt=""/>
									</span>
									<span class="subject">
									<span class="from">
									Lisa Wong </span>
									<span class="time">
									Just Now </span>
									</span>
									<span class="message">
									Vivamus sed auctor nibh congue nibh. auctor nibh auctor nibh... </span>
									</a>
								</li>
							</ul>
						</li>
						<li class="external">
							<a href="inbox.html">
							See all messages <i class="m-icon-swapright"></i>
							</a>
						</li>
					</ul>
				</li>
				<!-- END INBOX DROPDOWN -->
				<!-- BEGIN TODO DROPDOWN -->
				<li class="dropdown dropdown-extended dropdown-tasks" id="header_task_bar">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
					<i class="icon-calendar"></i>
					<span class="badge badge-default">
					3 </span>
					</a>
					<ul class="dropdown-menu extended tasks">
						<li>
							<p>
								 You have 12 pending tasks
							</p>
						</li>
						<li>
							<ul class="dropdown-menu-list scroller" style="height: 250px;">
								<li>
									<a href="#">
									<span class="task">
									<span class="desc">
									New release v1.2 </span>
									<span class="percent">
									30% </span>
									</span>
									<span class="progress">
									<span style="width: 40%;" class="progress-bar progress-bar-success" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100">
									<span class="sr-only">
									40% Complete </span>
									</span>
									</span>
									</a>
								</li>
							</ul>
						</li>
						<li class="external">
							<a href="#">
							See all tasks <i class="m-icon-swapright"></i>
							</a>
						</li>
					</ul>
				</li>
				<!-- END TODO DROPDOWN -->
				<!-- BEGIN USER LOGIN DROPDOWN -->
				<li class="dropdown dropdown-user">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
					<img alt="" class="img-circle" src="static/metronic/admin/layout/img/avatar3_small.jpg"/>
					<span class="username">
					Bob </span>
					<i class="fa fa-angle-down"></i>
					</a>
					<ul class="dropdown-menu">
						<li>
							<a href="login.html">
							<i class="icon-key"></i> Log Out </a>
						</li>
					</ul>
				</li>
				<!-- END USER LOGIN DROPDOWN -->
			</ul>
		</div>
		<!-- END TOP NAVIGATION MENU -->
	</div>
	<!-- END HEADER INNER -->
</div>
<!-- END HEADER -->
<div class="clearfix">
</div>

<!-- BEGIN CONTAINER -->
<div class="page-container"> 

<!--  <a href="?mylocale=en">English </a> | <a href="?mylocale=hn">Hindi </a> -->
 