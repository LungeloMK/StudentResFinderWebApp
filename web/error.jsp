<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<%@page import="util.SecurityUtil"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Something went wrong</title>
    <link rel="stylesheet" href="css/styling.css">
</head>
<body>
<div class="site-shell">
    <header class="topbar">
        <nav class="nav">
            <a class="brand" href="index.jsp"><img class="brand-logo" src="images/StudentResFinder-logo-header.png" alt="StudentResFinder logo"></a>
            <div class="nav-links"><a href="index.jsp">Home</a></div>
        </nav>
    </header>
    <main class="auth-wrap">
        <section class="form-wrap">
            <div class="form-card">
                <span class="eyebrow">Error</span>
                <h1>We could not complete that request.</h1>
                <p class="muted">Please check that the Derby database is running and that the SQL script has been executed.</p>
                <% if (exception != null) { %>
                    <div class="alert error"><%= SecurityUtil.escapeHtml(exception.getMessage()) %></div>
                <% } %>
                <a class="button" href="index.jsp">Return home</a>
            </div>
        </section>
    </main>
</div>
</body>
</html>



