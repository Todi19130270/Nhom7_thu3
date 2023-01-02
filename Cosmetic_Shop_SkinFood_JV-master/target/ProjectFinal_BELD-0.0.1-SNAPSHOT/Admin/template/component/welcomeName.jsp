<ul class="navbar-nav">
	<li class="nav-item font-weight-semibold d-none d-lg-block ms-0">
		<h1 class="welcome-text">
			Good Morning, <span class="text-black fw-bold">${cookie['loginId'] ? sessionScope.login.getFullname() : "ADMIN" }</span>
		</h1>
		<h3 class="welcome-sub-text">Your performance summary this week</h3>
	</li>
</ul>