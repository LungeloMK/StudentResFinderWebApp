<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="util.SecurityUtil"%>
<%
    String selectedRole = request.getParameter("role");
    boolean landlordSelected = "LANDLORD".equalsIgnoreCase(selectedRole);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register | StudentResFinder</title>
    <link rel="stylesheet" href="css/styling.css">
</head>
<body>
<div class="site-shell">
    <header class="topbar">
        <nav class="nav">
            <a class="brand" href="index.jsp"><img class="brand-logo" src="images/StudentResFinder-logo-header.png" alt="StudentResFinder logo"></a>
            <div class="nav-links">
                <a href="SearchServlet.do">Browse</a>
                <a href="login.jsp">Login</a>
            </div>
        </nav>
    </header>
    <main class="section">
        <section class="form-wrap">
            <form class="form-card" method="post" action="RegisterServlet.do">
                <span class="eyebrow">Join the platform</span>
                <h1>Create account</h1>
                <p class="muted">Landlord accounts start as pending until an admin verifies them.</p>
                <% if (request.getAttribute("error") != null) { %>
                    <div class="alert error"><%= SecurityUtil.escapeHtml(request.getAttribute("error")) %></div>
                <% } %>
                <div class="form-grid">
                    <div>
                        <label for="fullname">First names</label>
                        <input id="fullname" type="text" name="fullname" required maxlength="80" value="<%= SecurityUtil.escapeHtml(request.getParameter("fullname")) %>">
                    </div>
                    <div>
                        <label for="lastname">Last name</label>
                        <input id="lastname" type="text" name="lastname" required maxlength="80" value="<%= SecurityUtil.escapeHtml(request.getParameter("lastname")) %>">
                    </div>
                    <div>
                        <label for="email">Email address</label>
                        <input id="email" type="email" name="email" required maxlength="120" value="<%= SecurityUtil.escapeHtml(request.getParameter("email")) %>">
                    </div>
                    <div>
                        <label for="phonenumber">Phone number</label>
                        <input id="phonenumber" type="tel" name="phonenumber" required pattern="[0-9+ ]{10,15}" value="<%= SecurityUtil.escapeHtml(request.getParameter("phonenumber")) %>">
                    </div>
                    <div>
                        <label for="idnumber">ID number</label>
                        <input id="idnumber" type="text" name="idnumber" required pattern="[0-9]{8,13}" value="<%= SecurityUtil.escapeHtml(request.getParameter("idnumber")) %>">
                    </div>
                    <div>
                        <label for="role">Account type</label>
                        <select id="role" name="role" required>
                            <option value="STUDENT" <%= landlordSelected ? "" : "selected" %>>Student</option>
                            <option value="LANDLORD" <%= landlordSelected ? "selected" : "" %>>Landlord</option>
                        </select>
                    </div>
                    <div class="landlord-fields <%= landlordSelected ? "" : "is-hidden" %>" <%= landlordSelected ? "" : "hidden" %>>
                        <label for="businessName">Business name</label>
                        <input id="businessName" type="text" name="businessName" maxlength="120" value="<%= SecurityUtil.escapeHtml(request.getParameter("businessName")) %>">
                    </div>
                    <div class="landlord-fields <%= landlordSelected ? "" : "is-hidden" %>" <%= landlordSelected ? "" : "hidden" %>>
                        <label for="documentNumber">Verification document number</label>
                        <input id="documentNumber" type="text" name="documentNumber" maxlength="80" value="<%= SecurityUtil.escapeHtml(request.getParameter("documentNumber")) %>">
                    </div>
                    <div>
                        <label for="password">Password</label>
                        <input id="password" type="password" name="password" required minlength="8" autocomplete="new-password">
                    </div>
                    <div>
                        <label for="confirmPassword">Confirm password</label>
                        <input id="confirmPassword" type="password" name="confirmPassword" required minlength="8" autocomplete="new-password">
                    </div>
                    <div class="full actions">
                        <input type="submit" value="Create account">
                        <a class="button secondary" href="login.jsp">Already registered</a>
                    </div>
                </div>
            </form>
        </section>
    </main>
</div>
<script>
    (function () {
        var role = document.getElementById("role");
        var fields = document.querySelectorAll(".landlord-fields");
        var inputs = document.querySelectorAll(".landlord-fields input");

        function syncLandlordFields() {
            var show = role.value === "LANDLORD";
            fields.forEach(function (field) {
                field.classList.toggle("is-hidden", !show);
                field.hidden = !show;
            });
            inputs.forEach(function (input) {
                input.required = show;
                if (!show) {
                    input.value = "";
                }
            });
        }

        role.addEventListener("change", syncLandlordFields);
        syncLandlordFields();
    }());
</script>
</body>
</html>
