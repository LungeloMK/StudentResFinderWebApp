<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="business.VerificationSB,model.Landlord,model.User,util.SecurityUtil"%>
<%
    if (!SecurityUtil.requireRole(request, response, "LANDLORD")) { return; }
    User currentUser = SecurityUtil.currentUser(request);
    VerificationSB verificationSB = new VerificationSB();
    Landlord landlord = verificationSB.findByUserId(currentUser.getId());
    String status = landlord == null ? "PENDING" : landlord.getVerificationStatus();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Landlord Dashboard</title>
    <link rel="stylesheet" href="css/styling.css">
</head>
<body>
<div class="site-shell">
    <header class="topbar">
        <nav class="nav">
            <a class="brand" href="index.jsp"><img class="brand-logo" src="images/StudentResFinder-logo-header.png" alt="StudentResFinder logo"></a>
            <div class="nav-links">
                <a href="myListings.jsp">My listings</a>
                <a href="addListing.jsp">Add listing</a>
                <a href="LogoutServlet.do">Logout</a>
            </div>
        </nav>
    </header>
    <main class="section">
        <div class="container">
            <span class="eyebrow">Landlord dashboard</span>
            <h1>Welcome, <%= SecurityUtil.escapeHtml(currentUser.getFullName()) %></h1>
            <p class="muted">Verification status: <span class="badge <%= "REJECTED".equals(status) ? "rejected" : ("PENDING".equals(status) ? "pending" : "") %>"><%= SecurityUtil.escapeHtml(status) %></span></p>
            <% if (!"VERIFIED".equals(status)) { %>
                <div class="alert">Only verified landlords can create listings. Please wait for admin approval.</div>
            <% } %>
            <div class="grid">
                <article class="card">
                    <h3>Add accommodation</h3>
                    <p class="muted">Create a clear listing with rent, rooms, location and address.</p>
                    <a class="button" href="addListing.jsp">Add listing</a>
                </article>
                <article class="card">
                    <h3>Manage listings</h3>
                    <p class="muted">Update prices, mark rooms inactive, or remove old listings.</p>
                    <a class="button secondary" href="myListings.jsp">My listings</a>
                </article>
                <article class="card">
                    <h3>Public view</h3>
                    <p class="muted">See how students browse available accommodation.</p>
                    <a class="button secondary" href="SearchServlet.do">Browse listings</a>
                </article>
            </div>
        </div>
    </main>
</div>
</body>
</html>



