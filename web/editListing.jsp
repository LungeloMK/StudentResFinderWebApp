<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="business.ListingSB,model.Listing,util.SecurityUtil"%>
<%
    if (!SecurityUtil.requireRole(request, response, "LANDLORD")) { return; }
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
    <title>Edit Listing</title>
    <link rel="stylesheet" href="css/styling.css">
</head>
<body>
<div class="site-shell">
    <header class="topbar">
        <nav class="nav">
            <a class="brand" href="index.jsp"><img class="brand-logo" src="images/StudentResFinder-logo-header.png" alt="StudentResFinder logo"></a>
            <div class="nav-links">
                <a href="myListings.jsp">My listings</a>
                <a href="LogoutServlet.do">Logout</a>
            </div>
        </nav>
    </header>
    <main class="section">
        <section class="form-wrap">
            <% if (listing == null) { %>
                <div class="alert error">Listing not found.</div>
            <% } else { %>
            <form class="form-card" method="post" action="UpdateListingServlet.do">
                <input type="hidden" name="id" value="<%= listing.getId() %>">
                <span class="eyebrow">Update accommodation</span>
                <h1>Edit listing</h1>
                <% if (request.getAttribute("error") != null) { %>
                    <div class="alert error"><%= SecurityUtil.escapeHtml(request.getAttribute("error")) %></div>
                <% } %>
                <div class="form-grid">
                    <div class="full">
                        <label for="title">Title</label>
                        <input id="title" type="text" name="title" required maxlength="120" value="<%= SecurityUtil.escapeHtml(listing.getTitle()) %>">
                    </div>
                    <div>
                        <label for="location">Location</label>
                        <input id="location" type="text" name="location" required maxlength="100" value="<%= SecurityUtil.escapeHtml(listing.getLocation()) %>">
                    </div>
                    <div>
                        <label for="price">Monthly rent</label>
                        <input id="price" type="number" name="price" min="1" step="50" required value="<%= listing.getPrice() %>">
                    </div>
                    <div>
                        <label for="rooms">Rooms</label>
                        <input id="rooms" type="number" name="rooms" min="1" required value="<%= listing.getRooms() %>">
                    </div>
                    <div>
                        <label for="status">Status</label>
                        <select id="status" name="status">
                            <option value="ACTIVE" <%= "ACTIVE".equals(listing.getStatus()) ? "selected" : "" %>>Active</option>
                            <option value="INACTIVE" <%= "INACTIVE".equals(listing.getStatus()) ? "selected" : "" %>>Inactive</option>
                        </select>
                    </div>
                    <div>
                        <label for="imageUrl">Photo URL</label>
                        <input id="imageUrl" type="url" name="imageUrl" maxlength="250" value="<%= SecurityUtil.escapeHtml(listing.getImageUrl()) %>">
                    </div>
                    <div class="full">
                        <label for="address">Address</label>
                        <input id="address" type="text" name="address" required maxlength="180" value="<%= SecurityUtil.escapeHtml(listing.getAddress()) %>">
                    </div>
                    <div class="full">
                        <label for="description">Description</label>
                        <textarea id="description" name="description" required maxlength="1000"><%= SecurityUtil.escapeHtml(listing.getDescription()) %></textarea>
                    </div>
                    <div class="full actions">
                        <input type="submit" value="Save changes">
                        <a class="button secondary" href="myListings.jsp">Cancel</a>
                    </div>
                </div>
            </form>
            <% } %>
        </section>
    </main>
</div>
</body>
</html>



