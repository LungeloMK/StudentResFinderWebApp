<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="business.ListingSB,model.Listing,util.SecurityUtil"%>
<%
    Listing listing = (Listing) request.getAttribute("listing");
    if (listing == null) {
        listing = new ListingSB().findById(SecurityUtil.parseLong(request.getParameter("id")));
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listing Details</title>
    <link rel="stylesheet" href="css/styling.css">
</head>
<body>
<div class="site-shell">
    <header class="topbar">
        <nav class="nav">
            <a class="brand" href="index.jsp"><img class="brand-logo" src="images/StudentResFinder-logo-header.png" alt="StudentResFinder logo"></a>
            <div class="nav-links">
                <a href="SearchServlet.do">Browse</a>
                <a href="search.jsp">Search</a>
            </div>
        </nav>
    </header>
    <main class="section">
        <div class="container">
            <% if (listing == null) { %>
                <div class="alert error">Listing not found.</div>
            <% } else { %>
                <span class="eyebrow"><%= SecurityUtil.escapeHtml(listing.getLocation()) %></span>
                <h1><%= SecurityUtil.escapeHtml(listing.getTitle()) %></h1>
                <div class="grid two">
                    <article class="card">
                        <h3>Accommodation details</h3>
                        <p class="price">R <%= String.format("%.2f", listing.getPrice()) %> / month</p>
                        <p><strong>Rooms:</strong> <%= listing.getRooms() %></p>
                        <p><strong>Address:</strong> <%= SecurityUtil.escapeHtml(listing.getAddress()) %></p>
                        <p><strong>Landlord:</strong> <%= SecurityUtil.escapeHtml(listing.getLandlordName()) %></p>
                    </article>
                    <article class="card">
                        <h3>Description</h3>
                        <p class="muted"><%= SecurityUtil.escapeHtml(listing.getDescription()) %></p>
                        <% if (listing.getImageUrl() != null && !listing.getImageUrl().trim().isEmpty()) { %>
                            <p><a class="button secondary" href="<%= SecurityUtil.escapeHtml(listing.getImageUrl()) %>" target="_blank" rel="noopener">View photo</a></p>
                        <% } %>
                    </article>
                </div>
            <% } %>
        </div>
    </main>
</div>
</body>
</html>



