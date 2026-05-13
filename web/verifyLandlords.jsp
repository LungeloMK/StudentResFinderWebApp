<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="business.VerificationSB,java.util.List,model.Landlord,util.SecurityUtil"%>
<%
    if (!SecurityUtil.requireRole(request, response, "ADMIN")) { return; }
    List<Landlord> landlords = new VerificationSB().pendingLandlords();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Verify Landlords</title>
    <link rel="stylesheet" href="css/styling.css">
</head>
<body>
<div class="site-shell">
    <header class="topbar">
        <nav class="nav">
            <a class="brand" href="index.jsp"><img class="brand-logo" src="images/StudentResFinder-logo-header.png" alt="StudentResFinder logo"></a>
            <div class="nav-links">
                <a href="adminDashboard.jsp">Dashboard</a>
                <a href="LogoutServlet.do">Logout</a>
            </div>
        </nav>
    </header>
    <main class="section">
        <div class="container">
            <span class="eyebrow">Admin review</span>
            <h1>Pending landlord verification</h1>
            <% if (request.getParameter("message") != null) { %>
                <div class="alert"><%= SecurityUtil.escapeHtml(request.getParameter("message")) %></div>
            <% } %>
            <% if (landlords.isEmpty()) { %>
                <div class="alert">No pending landlord applications.</div>
            <% } else { %>
                <div class="table-wrap">
                    <table>
                        <thead>
                            <tr>
                                <th>Landlord</th>
                                <th>Business</th>
                                <th>Document</th>
                                <th>Contact</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Landlord landlord : landlords) { %>
                                <tr>
                                    <td><%= SecurityUtil.escapeHtml(landlord.getUser().getDisplayName()) %></td>
                                    <td><%= SecurityUtil.escapeHtml(landlord.getBusinessName()) %></td>
                                    <td><%= SecurityUtil.escapeHtml(landlord.getDocumentNumber()) %></td>
                                    <td>
                                        <%= SecurityUtil.escapeHtml(landlord.getUser().getEmail()) %><br>
                                        <span class="muted"><%= SecurityUtil.escapeHtml(landlord.getUser().getPhone()) %></span>
                                    </td>
                                    <td>
                                        <div class="actions">
                                            <form method="post" action="VerifyLandlordServlet.do">
                                                <input type="hidden" name="id" value="<%= landlord.getId() %>">
                                                <button type="submit">Verify</button>
                                            </form>
                                            <form method="post" action="RejectLandlordServlet.do">
                                                <input type="hidden" name="id" value="<%= landlord.getId() %>">
                                                <input type="hidden" name="reason" value="Verification documents were not accepted.">
                                                <button class="warning" type="submit">Reject</button>
                                            </form>
                                        </div>
                                    </td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            <% } %>
        </div>
    </main>
</div>
</body>
</html>



