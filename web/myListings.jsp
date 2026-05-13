<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="business.ListingSB,java.util.List,model.Listing,model.User,util.SecurityUtil"%>
<%
    if (!SecurityUtil.requireRole(request, response, "LANDLORD")) { return; }
    User currentUser = SecurityUtil.currentUser(request);
    List<Listing> listings = new ListingSB().findByLandlord(currentUser.getId());
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Listings</title>
    <link rel="stylesheet" href="css/styling.css">
</head>
<body>
<div class="site-shell">
    <header class="topbar">
        <nav class="nav">
            <a class="brand" href="index.jsp"><img class="brand-logo" src="images/StudentResFinder-logo-header.png" alt="StudentResFinder logo"></a>
            <div class="nav-links">
                <a href="landlordDashboard.jsp">Dashboard</a>
                <a href="addListing.jsp">Add listing</a>
                <a href="LogoutServlet.do">Logout</a>
            </div>
        </nav>
    </header>
    <main class="section">
        <div class="container">
            <span class="eyebrow">Landlord tools</span>
            <h1>My listings</h1>
            <% if (request.getParameter("message") != null) { %>
                <div class="alert"><%= SecurityUtil.escapeHtml(request.getParameter("message")) %></div>
            <% } %>
            <% if (listings.isEmpty()) { %>
                <div class="alert">You have not added listings yet.</div>
                <a class="button" href="addListing.jsp">Add first listing</a>
            <% } else { %>
                <div class="grid">
                    <% for (Listing listing : listings) { %>
                        <article class="card listing-card">
                            <span class="badge"><%= SecurityUtil.escapeHtml(listing.getStatus()) %></span>
                            <h3><%= SecurityUtil.escapeHtml(listing.getTitle()) %></h3>
                            <div class="price">R <%= String.format("%.2f", listing.getPrice()) %> / month</div>
                            <p class="muted"><%= SecurityUtil.escapeHtml(listing.getLocation()) %></p>
                            <div class="actions">
                                <a class="button secondary" href="UpdateListingServlet.do?id=<%= listing.getId() %>">Edit</a>
                                <form method="post" action="DeleteListingServlet.do">
                                    <input type="hidden" name="id" value="<%= listing.getId() %>">
                                    <button class="warning" type="submit">Delete</button>
                                </form>
                            </div>
                        </article>
                    <% } %>
                </div>
            <% } %>
        </div>
    </main>
</div>
</body>
</html>



