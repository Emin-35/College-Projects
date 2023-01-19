<?php
    include_once 'header.php';
?>

<body>
    <div class="login-card-container">
        <div class="login-card">
            <div class="login-card-logo">
                <img src="cookie.png" alt="logo">
            </div>
            <div class="login-card-header">
                <h1>Log-In</h1>
                <div>Please login to use the platform</div>
            </div>
            <form action="includes/login.inc.php" class="login-card-form" method="post">

                <div class="form-item">
                    <span class="form-item-icon material-symbols-rounded">mail</span>
                    <input type="text" name="user_Email" placeholder="Enter Email" id="emailForm" 
                    autofocus required>
                </div>

                <div class="form-item">
                    <span class="form-item-icon material-symbols-rounded">lock</span>
                    <input type="password" name="user_pwd" placeholder="Enter Password" id="passwordForm"
                    required>
                </div>

                <div class="form-item-other">
                    <div class="checkbox">
                        <input type="checkbox" id="rememberMeCheckbox" checked>
                        <label for="rememberMeCheckbox">Remember me</label>
                    </div>
                    <a href="#">I forgot my password!</a>
                </div>
                
                <button type="submit" name="submit" >Log-In</button>
            </form>
            <div class="login-card-footer">
                Don't have an account? <a href="signup.php">Create a free account.</a>
            </div>
        </div>
    </div>
</body>