<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User,util.SecurityUtil"%>
<%
    if (!SecurityUtil.requireRole(request, response, "STUDENT")) { return; }
    User currentUser = SecurityUtil.currentUser(request);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Dashboard</title>
    <link rel="stylesheet" href="css/styling.css">
</head>
<body>
<div class="site-shell">
    <header class="topbar">
        <nav class="nav">
            <a class="brand" href="index.jsp"><img class="brand-logo" src="images/StudentResFinder-logo-header.png" alt="StudentResFinder logo"></a>
            <div class="nav-links">
                <a href="SearchServlet.do">Browse</a>
                <a href="LogoutServlet.do">Logout</a>
            </div>
        </nav>
    </header>
    <main class="section">
        <div class="container">
            <span class="eyebrow">Student dashboard</span>
            <h1>Hello, <%= SecurityUtil.escapeHtml(currentUser.getFullName()) %></h1>
            <p class="muted">Search verified accommodation and compare options near campus.</p>
            <div class="grid">
                <article class="card">
                    <h3>Search by location</h3>
                    <p class="muted">Find residences near your preferred area.</p>
                    <a class="button" href="search.jsp">Start search</a>
                </article>
                <article class="card">
                    <h3>Verified landlords</h3>
                    <p class="muted">Listings are posted by landlords that admins have approved.</p>
                    <a class="button secondary" href="SearchServlet.do">View all</a>
                </article>
                <article class="card">
                    <h3>Account security</h3>
                    <p class="muted">Your session expires after inactivity to protect your account.</p>
                    <a class="button secondary" href="LogoutServlet.do">Logout</a>
                </article>
            </div>
        </div>
    </main>
</div>
</body>
</html>



