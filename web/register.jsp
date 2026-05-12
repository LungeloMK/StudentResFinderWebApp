<%-- register placeholder --%>
<%-- 
    Document   : register
    Created on : 07 May 2026, 4:45:09 AM
    Author     : moses
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create New Account</title>
    <style>
        body {
            background: beige;
            font-family: 'Segoe UI', Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .form-container {
            background: transparent;
            border-color: #005fa3;
            padding: 2rem 2.5rem;
            width: 350px;
        position: relative;
        backdrop-filter: blur(10px);
        box-shadow: 0 8px 32px 0 rgba(31,38,135,0.37);
        display: flex;
        flex-direction: column;
        align-items: center;
        border: #91a4c8 solid 1px;
        box-shadow: rgb(252, 252, 253) 0px 0px 10px 0px;
        }
        .form-container h2 {
            margin-bottom: 1.5rem;
            color: #333;
            text-align: center;
        }
        .form-group {
            margin-bottom: 1.2rem;
        }
        .form-group label {
            display: block;
            margin-bottom: 0.4rem;
            color: #555;
            font-size: 1rem;
            padding-left: 15px;
            
        }
        .form-group input {
            width: 300px;
        height: 30px   ;
        font-size: 16px;
        background: rgba(239, 239, 255, 0.318) ;
        color: #333;
        padding-inline: 20px 50px;
        border: 2px solid #fff;
        border-radius: 10px;
        outline: none;
        }
        .form-group input:focus {
            border-color: #0078d7;
            outline: none;
        }
        .submit-btn {
             width: 220px;
        padding: 10px;
        border-radius: 6px;
        border: none;
        background: rgb(206, 212, 232);
        color: #fff;
        font-size: 16px;
        cursor: pointer;
        margin-top: 8px; 
        }
        .submit-btn:hover {
            background: #005fa3;
        }
        .login-link {
            display: block;
            text-align: center;
            margin-top: 1rem;
            color: #0078d7;
            text-decoration: none;
            font-size: 0.95rem;
        }
        .login-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <form class="form-container" action="RegisterServlet.do" method="post">
        <h2>Create Account</h2>
        <div class="form-group">
            <label for="fullname">Full Name</label>
            <input type="text" id="fullname" name="fullname" required>
        </div>

        <div class="form-group">
            <label for="email">Email Address</label>
            <input type="email" id="email" name="email" required title="USED AS USERNAME">
        </div>
        
        <div class="form-group">
            <label for="idnumber">ID Number</label>
            <input type="text" id="idnumber" name="idnumber" required pattern="\d{13}" title="RSA ID number must be exactly 13 digits">
        </div>

        <div class="form-group">
            <label for="phonenumber">Phone Number</label>
            <input type="tel" id="phonenumber" name="phonenumber" required pattern="[0-9]{10,15}">
        </div>

            <div class="form-group">
    <label>Password:</label>
    <input type="password" id="password" name="password" required="" />
</div>

<div class="form-group">
    <label>Confirm Password:</label>
    <input type="password" id="confirmPassword" required="" />
    <small id="error-msg" style="color:red; display:none;">
        Passwords do not match
    </small>
</div>
            </div>
          </div>
<script>
    const password = document.getElementById("password");
    const confirmPassword = document.getElementById("confirmPassword");
    const errorMsg = document.getElementById("error-msg");

    function validatePassword() {
        if (password.value !== confirmPassword.value) {
            errorMsg.style.display = "block";
        } else {
            errorMsg.style.display = "none";
        }
    }

    confirmPassword.addEventListener("input", validatePassword);
    password.addEventListener("input", validatePassword);
</script>
 <input class="submit-btn" type="submit" value="Sign Up">
                    
 <p class="login-link" >Already have an account? <a href="login.html">Log in</a></p>
 
</form>
</body>
</html>
