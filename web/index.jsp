<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User,util.SecurityUtil"%>
<%
    User currentUser = SecurityUtil.currentUser(request);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>StudentResFinderWebApp</title>
    <link rel="stylesheet" href="css/styling.css">
</head>
<body>
<div class="site-shell home-shell">
    <header class="topbar">
        <nav class="nav">
            <a class="brand" href="index.jsp"><img class="brand-logo" src="images/StudentResFinder-logo-header.png" alt="StudentResFinder logo"></a>
            <div class="nav-links">
                <a href="SearchServlet.do">Browse</a>
                <% if (currentUser == null) { %>
                    <a href="login.jsp">Login</a>
                    <a class="button" href="register.jsp">Register</a>
                <% } else if (currentUser.hasRole("ADMIN")) { %>
                    <a href="adminDashboard.jsp">Dashboard</a>
                    <a href="LogoutServlet.do">Logout</a>
                <% } else if (currentUser.hasRole("LANDLORD")) { %>
                    <a href="landlordDashboard.jsp">Dashboard</a>
                    <a href="LogoutServlet.do">Logout</a>
                <% } else { %>
                    <a href="studentDashboard.jsp">Dashboard</a>
                    <a href="LogoutServlet.do">Logout</a>
                <% } %>
            </div>
        </nav>
    </header>

    <main class="home-page">
        <section class="hero home-hero">
            <div class="hero-inner home-hero-grid">
                <div class="hero-copy">
                    <span class="eyebrow">Safe student accommodation</span>
                    <h1>Find student housing that feels close, safe, and affordable.</h1>
                    <p>Search verified rooms near campus, compare rent quickly, and only contact landlords who have passed platform checks.</p>
                    <div class="home-badges">
                        <span>Campus-close</span>
                        <span>Verified landlords</span>
                        <span>Budget filters</span>
                    </div>
                    <div class="actions">
                        <a class="button" href="SearchServlet.do">Browse listings</a>
                        <a class="button light" href="register.jsp">Create account</a>
                    </div>
                </div>

                <form class="quick-search" action="SearchServlet.do" method="get">
                    <span class="eyebrow">Start searching</span>
                    <h2>Check available rooms</h2>
                    <label for="home-location">Location</label>
                    <input id="home-location" type="text" name="location" placeholder="Arcadia, Sunnyside, Soshanguve">
                    <div class="mini-grid">
                        <div>
                            <label for="home-min">Min rent</label>
                            <input id="home-min" type="number" name="minPrice" min="0" step="50" placeholder="1500">
                        </div>
                        <div>
                            <label for="home-max">Max rent</label>
                            <input id="home-max" type="number" name="maxPrice" min="0" step="50" placeholder="4500">
                        </div>
                    </div>
                    <button type="submit">Search now</button>
                </form>
            </div>
        </section>

        <section class="stat-strip">
            <div class="container stat-grid">
                <article>
                    <strong>3</strong>
                    <span>secure user roles</span>
                </article>
                <article>
                    <strong>24/7</strong>
                    <span>listing access</span>
                </article>
                <article>
                    <strong>100%</strong>
                    <span>admin-approved landlords</span>
                </article>
                <article>
                    <strong>Fast</strong>
                    <span>rent and area filtering</span>
                </article>
            </div>
        </section>

        <section class="section feature-band">
            <div class="container section-heading">
                <span class="eyebrow">Built for the full process</span>
                <h2>One system for students, landlords, and admins.</h2>
            </div>
            <div class="container grid">
                <article class="card feature-card student-card">
                    <span class="badge">Students</span>
                    <h3>Search with confidence</h3>
                    <p class="muted">Filter by location and rent, then open a listing to view room details and landlord contact information.</p>
                </article>
                <article class="card feature-card landlord-card">
                    <span class="badge">Landlords</span>
                    <h3>Manage listings</h3>
                    <p class="muted">Create an account, wait for approval, then add rooms, update prices, and remove unavailable spaces.</p>
                </article>
                <article class="card feature-card admin-card">
                    <span class="badge">Admins</span>
                    <h3>Keep it trusted</h3>
                    <p class="muted">Review landlord accounts before they can publish listings, keeping suspicious providers away from students.</p>
                </article>
            </div>
        </section>

        <section class="section areas-section">
            <div class="container split-section">
                <div>
                    <span class="eyebrow">Popular student areas</span>
                    <h2>Compare places by campus distance, rent, and safety.</h2>
                    <p class="muted">The platform is ready for common student accommodation areas and can grow as more landlords add verified rooms.</p>
                    <div class="actions">
                        <a class="button" href="SearchServlet.do">View all rooms</a>
                        <a class="button secondary" href="login.jsp">Login</a>
                    </div>
                </div>
                <div class="area-list">
                    <article class="area-sunnyside">
                        <strong>Sunnyside</strong>
                        <span>Shared rooms, apartments, and quick transport routes.</span>
                    </article>
                    <article class="area-arcadia">
                        <strong>Arcadia</strong>
                        <span>Central options near libraries, shops, and study spaces.</span>
                    </article>
                    <article class="area-soshanguve">
                        <strong>Soshanguve</strong>
                        <span>Budget-friendly rooms for students who need reliable access.</span>
                    </article>
                    <article class="area-west">
                        <strong>Pretoria West</strong>
                        <span>Affordable listings with space for future platform growth.</span>
                    </article>
                </div>
            </div>
        </section>

        <section class="section trust-section">
            <div class="container split-section reverse">
                <div class="trust-panel">
                    <span class="badge">Security basics</span>
                    <h3>GlassFish realm login plus role checks</h3>
                    <p>The system uses file-realm users for authentication and app roles for student, landlord, and admin access. Protected actions still check permissions on the server.</p>
                    <ul class="check-list">
                        <li>Admins approve landlords before listings can be managed.</li>
                        <li>Sessions use HTTP-only cookies.</li>
                        <li>Inputs are cleaned before important account and listing actions.</li>
                    </ul>
                </div>
                <div>
                    <span class="eyebrow">Trust matters</span>
                    <h2>Students should not have to guess who is real.</h2>
                    <p class="muted">The project now focuses on a simple, practical security model from the notes: authenticate users, separate roles, and block pages that the current account should not open.</p>
                    <div class="actions">
                        <% if (currentUser == null) { %>
                            <a class="button" href="register.jsp">Join the platform</a>
                        <% } else if (currentUser.hasRole("ADMIN")) { %>
                            <a class="button" href="adminDashboard.jsp">Open dashboard</a>
                        <% } else if (currentUser.hasRole("LANDLORD")) { %>
                            <a class="button" href="landlordDashboard.jsp">Open dashboard</a>
                        <% } else { %>
                            <a class="button" href="studentDashboard.jsp">Open dashboard</a>
                        <% } %>
                    </div>
                </div>
            </div>
        </section>

        <section class="cta-band">
            <div class="container cta-content">
                <div>
                    <span class="eyebrow">Ready when you are</span>
                    <h2>Find a room, publish a listing, or review pending landlords.</h2>
                </div>
                <div class="actions">
                    <a class="button" href="SearchServlet.do">Browse</a>
                    <a class="button secondary" href="register.jsp">Register</a>
                </div>
            </div>
        </section>
    </main>

    <footer class="footer rich-footer">
        <div class="container footer-grid">
            <div>
                <strong>StudentResFinderWebApp</strong>
                <p class="muted">Student accommodation search, listing management, and admin verification in one Java EE system.</p>
            </div>
            <div>
                <strong>Quick links</strong>
                <a href="SearchServlet.do">Browse listings</a>
                <a href="login.jsp">Login</a>
                <a href="register.jsp">Register</a>
            </div>
        </div>
    </footer>
</div>
</body>
</html>
