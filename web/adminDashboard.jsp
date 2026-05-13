<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="business.AuthSB,business.ListingSB,business.VerificationSB,util.SecurityUtil"%>
<%
    if (!SecurityUtil.requireRole(request, response, "ADMIN")) { return; }
    VerificationSB verificationSB = new VerificationSB();
    ListingSB listingSB = new ListingSB();
    AuthSB authSB = new AuthSB();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="css/styling.css">
</head>
<body>
<div class="site-shell">
    <header class="topbar">
        <nav class="nav">
            <a class="brand" href="index.jsp"><img class="brand-logo" src="images/StudentResFinder-logo-header.png" alt="StudentResFinder logo"></a>
            <div class="nav-links">
                <a href="verifyLandlords.jsp">Verify landlords</a>
                <a href="SearchServlet.do">Browse</a>
                <a href="LogoutServlet.do">Logout</a>
            </div>
        </nav>
    </header>
    <main class="section">
        <div class="container">
            <span class="eyebrow">Admin dashboard</span>
            <h1>Trust and verification centre</h1>
            <p class="muted">Review landlord applications and keep unsafe listings off the platform.</p>
            <div class="grid">
                <article class="card stat"><strong><%= verificationSB.countPending() %></strong><span>Pending landlords</span></article>
                <article class="card stat"><strong><%= verificationSB.countVerified() %></strong><span>Verified landlords</span></article>
                <article class="card stat"><strong><%= listingSB.countListings() %></strong><span>Total listings</span></article>
            </div>
            <div class="section">
                <a class="button" href="verifyLandlords.jsp">Review applications</a>
                <a class="button secondary" href="SearchServlet.do">View public listings</a>
            </div>
        </div>
    </main>
</div>
</body>
</html>



