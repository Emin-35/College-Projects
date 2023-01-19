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
                <h1>Sign In</h1>
                <div>Please Sign-up to use the platform</div>
            </div>
            <form action="includes/signup.inc.php" class="login-card-form" method="post">
                    <div class="form-item">
                        <span class="form-item-icon material-symbols-rounded">person</span>
                        <input type="text" name="user_Name" placeholder="Enter Name" id="textForm" 
                        autofocus required>
                    </div>
                    <div class="form-item">
                        <span class="form-item-icon material-symbols-rounded">person</span>
                        <input type="text" name="user_Lastname" placeholder="Enter Last Name" id="textForm" 
                        autofocus required>
                    </div>
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
                    <div class="form-item">
                        <span class="form-item-icon material-symbols-rounded">lock</span>
                        <input type="password" name="user_pwdrepeat" placeholder="Re-enter Password" id="passwordForm"
                        required>
                    </div>
                    <div class="form-item-other">
                        <div class="checkbox">
                        <input type="checkbox" id="rememberMeCheckbox" checked>
                        <label for="rememberMeCheckbox">Remember me</label>
                    </div>
                    <button type="submit" name ="submit">Sign In
                    </button>
            </form>
        </div>
    </div>
</body>