<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Listings</title>
    <link rel="stylesheet" href="css/styling.css">
</head>
<body>
<div class="site-shell">
    <header class="topbar">
        <nav class="nav">
            <a class="brand" href="index.jsp"><img class="brand-logo" src="images/StudentResFinder-logo-header.png" alt="StudentResFinder logo"></a>
            <div class="nav-links">
                <a href="index.jsp">Home</a>
                <a href="login.jsp">Login</a>
            </div>
        </nav>
    </header>
    <main class="section">
        <section class="form-wrap">
            <form class="form-card" action="SearchServlet.do" method="get">
                <span class="eyebrow">Find accommodation</span>
                <h1>Search listings</h1>
                <div class="form-grid">
                    <div class="full">
                        <label for="location">Location</label>
                        <input id="location" type="text" name="location" placeholder="Pretoria, Soshanguve, Arcadia">
                    </div>
                    <div>
                        <label for="minPrice">Minimum rent</label>
                        <input id="minPrice" type="number" name="minPrice" min="0" step="50">
                    </div>
                    <div>
                        <label for="maxPrice">Maximum rent</label>
                        <input id="maxPrice" type="number" name="maxPrice" min="0" step="50">
                    </div>
                    <div class="full actions">
                        <input type="submit" value="Search">
                        <a class="button secondary" href="ViewListingsServlet.do">View all listings</a>
                    </div>
                </div>
            </form>
        </section>
    </main>
</div>
</body>
</html>



