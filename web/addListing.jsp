<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User,util.SecurityUtil"%>
<%
    if (!SecurityUtil.requireRole(request, response, "LANDLORD")) { return; }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Listing</title>
    <link rel="stylesheet" href="css/styling.css">
</head>
<body>
<div class="site-shell">
    <header class="topbar">
        <nav class="nav">
            <a class="brand" href="index.jsp"><img class="brand-logo" src="images/StudentResFinder-logo-header.png" alt="StudentResFinder logo"></a>
            <div class="nav-links">
                <a href="landlordDashboard.jsp">Dashboard</a>
                <a href="myListings.jsp">My listings</a>
                <a href="LogoutServlet.do">Logout</a>
            </div>
        </nav>
    </header>
    <main class="section">
        <section class="form-wrap">
            <form class="form-card" method="post" action="AddListingServlet.do">
                <span class="eyebrow">New accommodation</span>
                <h1>Add listing</h1>
                <% if (request.getAttribute("error") != null) { %>
                    <div class="alert error"><%= SecurityUtil.escapeHtml(request.getAttribute("error")) %></div>
                <% } %>
                <div class="form-grid">
                    <div class="full">
                        <label for="title">Title</label>
                        <input id="title" type="text" name="title" required maxlength="120">
                    </div>
                    <div>
                        <label for="location">Location</label>
                        <input id="location" type="text" name="location" required maxlength="100">
                    </div>
                    <div>
                        <label for="price">Monthly rent</label>
                        <input id="price" type="number" name="price" min="1" step="50" required>
                    </div>
                    <div>
                        <label for="rooms">Rooms</label>
                        <input id="rooms" type="number" name="rooms" min="1" required>
                    </div>
                    <div>
                        <label for="imageUrl">Photo URL</label>
                        <input id="imageUrl" type="url" name="imageUrl" maxlength="250">
                    </div>
                    <div class="full">
                        <label for="address">Address</label>
                        <input id="address" type="text" name="address" required maxlength="180">
                    </div>
                    <div class="full">
                        <label for="description">Description</label>
                        <textarea id="description" name="description" required maxlength="1000"></textarea>
                    </div>
                    <div class="full actions">
                        <input type="submit" value="Create listing">
                        <a class="button secondary" href="myListings.jsp">Cancel</a>
                    </div>
                </div>
            </form>
        </section>
    </main>
</div>
</body>
</html>



