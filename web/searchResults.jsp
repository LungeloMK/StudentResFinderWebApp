<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="business.SearchSB,java.util.List,model.Listing,util.SecurityUtil"%>
<%
    List<Listing> listings = (List<Listing>) request.getAttribute("listings");
    if (listings == null) {
        listings = new SearchSB().allActiveListings();
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Results</title>
    <link rel="stylesheet" href="css/styling.css">
</head>
<body>
<div class="site-shell">
    <header class="topbar">
        <nav class="nav">
            <a class="brand" href="index.jsp"><img class="brand-logo" src="images/StudentResFinder-logo-header.png" alt="StudentResFinder logo"></a>
            <div class="nav-links">
                <a href="search.jsp">Search</a>
                <a href="index.jsp">Home</a>
            </div>
        </nav>
    </header>
    <main class="section">
        <div class="container">
            <span class="eyebrow">Available rooms</span>
            <h1>Search results</h1>
            <% if (listings.isEmpty()) { %>
                <div class="alert">No listings match your search yet.</div>
            <% } else { %>
                <div class="grid">
                    <% for (Listing listing : listings) { %>
                        <article class="card listing-card">
                            <span class="badge"><%= SecurityUtil.escapeHtml(listing.getLocation()) %></span>
                            <h3><%= SecurityUtil.escapeHtml(listing.getTitle()) %></h3>
                            <div class="price">R <%= String.format("%.2f", listing.getPrice()) %> / month</div>
                            <p class="muted"><%= SecurityUtil.escapeHtml(listing.getDescription()) %></p>
                            <p><strong>Rooms:</strong> <%= listing.getRooms() %></p>
                            <a class="button" href="ViewListingDetailsServlet.do?id=<%= listing.getId() %>">View details</a>
                        </article>
                    <% } %>
                </div>
            <% } %>
        </div>
    </main>
</div>
</body>
</html>



