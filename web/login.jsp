<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="util.SecurityUtil"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login | StudentResFinder</title>
    <link rel="stylesheet" href="css/styling.css">
</head>
<body>
<div class="site-shell">
    <header class="topbar">
        <nav class="nav">
            <a class="brand" href="index.jsp"><img class="brand-logo" src="images/StudentResFinder-logo-header.png" alt="StudentResFinder logo"></a>
            <div class="nav-links">
                <a href="SearchServlet.do">Browse</a>
                <a href="register.jsp">Register</a>
            </div>
        </nav>
    </header>
    <main class="auth-wrap">
        <section class="form-wrap">
            <form class="form-card" method="post" action="LoginServlet.do">
                <span class="eyebrow">Welcome back</span>
                <h1>Login</h1>
                <p class="muted">Use the GlassFish file realm user created for this project.</p>
                <% if (request.getAttribute("error") != null) { %>
                    <div class="alert error"><%= SecurityUtil.escapeHtml(request.getAttribute("error")) %></div>
                <% } %>
                <% if (request.getParameter("message") != null) { %>
                    <div class="alert"><%= SecurityUtil.escapeHtml(request.getParameter("message")) %></div>
                <% } %>
                <div class="form-grid">
                    <div class="full">
                        <label for="j_username">Realm user ID</label>
                        <input id="j_username" type="text" name="j_username" required autocomplete="username">
                    </div>
                    <div class="full">
                        <label for="j_password">Password</label>
                        <input id="j_password" type="password" name="j_password" required autocomplete="current-password">
                    </div>
                    <div class="full actions">
                        <input type="submit" value="Login">
                        <a class="button secondary" href="register.jsp">Create account</a>
                    </div>
                </div>
            </form>
        </section>
    </main>
</div>
</body>
</html>



