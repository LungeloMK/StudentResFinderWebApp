<%-- login placeholder --%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href='https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700;800&display=swap' rel='stylesheet'>
    <link rel="stylesheet" type="text/css" href="login.css">
    <title>Login Page</title>
    

<style>
    body {
        font-family: 'Poppins', sans-serif;
        background-color: bisque;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
        color: #fff;
       
       background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
        background-attachment: fixed;
    }
    .wrapper {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

    .login_box {
        padding: 2px 30px;
        position: relative;
        border-radius: 12px;
        backdrop-filter: blur(10px);
        box-shadow: 0 8px 32px 0 rgba(31,38,135,0.37);
        display: flex;
        flex-direction: column;
        align-items: center;
        border: #91a4c8 solid 1px;
        box-shadow: rgb(252, 252, 253) 0px 0px 10px 0px;
    }

    .login_header {
        text-align: center;
        margin: 1.5rem;
        width: 100%;
        font-family: Arial, Helvetica, sans-serif;
        font-size: 24px;
        font-weight: 600;

    }

    .input_box {
        position: relative;
        display: flex;
        flex-direction: column;
        margin: 9px 0
    }

    .input-field {
        width: 300px;
        height: 55px   ;
        font-size: 16px;
        background: transparent;
        color: #fff;
        padding-inline: 20px 50px;
        border: 2px solid #fff;
        border-radius: 10px;
        outline: none;

    }

.input-field:focus {
         border-color: #005fa3;
            outline: none;
    }

    #user{
        margin-bottom: 12px;
    }
    .label{
        position: absolute;
        top: 15px;
        left: 20px;
        transition: .2s;
        color: #fff;
        text-shadow: #fff 0px 0px 10px 0px;
        
    }

    .input-field:focus ~ .label,
    .input-field:valid ~ .label {
        position: absolute;
        padding: 0 20px;
        top: 0px;
        left: 10px;
        font-size: 12px;
        color: #fff;
        background-color: #005fa3;
        border-radius: 5px;
       
    }
   

    .remember-forgot {
        display: flex;
        justify-content:space-evenly;
        font-size: 12px;
        margin: 0.5rem 0;
         width:  150%;
        
    }
    
    .forgot {
        display: flex;
        align-items: right;
        justify-content: right;
        color: #005fa3;
    }
    .remember-me{
        display: flex;
        align-items: left;
        justify-content: left;
    }
    
    .submit-btn{
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

    .register_account {
        text-align: center;
        width: 100%;
    }
</style>
</head>
<body>
     <form method="get" action="LoginServlet.do">

        <div class="wrapper">
            <div class="login_box">
                    <div class="login_header">
                            <span>Login</span>
                    </div>
                    <div class="input_box">
                            <input type="text" name="username" class="input-field"  required="" />
                            <label for="user" class="label">Username</label>
                            
                    </div>
                    <div class="input_box">
                            <input type="text" name="password" class="input-field"  required="" />
                            <label for="password" class="label">Password</label>
                            
                    </div>
                    <div class="remember-forgot">
                            <div class="remember-me">
                                    <input type="checkbox" name="remember" />
                                    <label for="remember">Remember me</label>
                            </div>
                            
                            <div class="forgot">
                                    <a href="#">Forgot Password?</a>
                            </div>


                    </div>
                            
                             <input class="submit-btn" type="submit" value="Login">
                    
                    
                            <div class="register_account">
                                    <p>Don't have an account? <a href="register.jsp">Register</a></p>
                            </div>
                    </div>
            </div>
        
     </form>
     
</body>
</html>
