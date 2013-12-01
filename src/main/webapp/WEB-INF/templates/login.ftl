<html>
	<head>
	
			<meta charset="utf-8">
	        <title>Music Player</title>
	        <meta name="viewport" content="width=device-width, initial-scale=1.0">
	        <meta name="description" content="">
	        <meta name="author" content="">
	        
			<!-- CSS -->
	        <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
        	<link rel="stylesheet" href="http://torus.uck.pk.edu.pl/~mszczyg/musicplayer/resources/css/reset.css">
        	<link rel="stylesheet" href="http://torus.uck.pk.edu.pl/~mszczyg/musicplayer/resources/css/supersized.css">
        	<link rel="stylesheet" href="http://torus.uck.pk.edu.pl/~mszczyg/musicplayer/resources/css/style.css">
	        
	        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
	        <!--[if lt IE 9]>
	            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	        <![endif]-->
	        
	</head>
	<body>

		<div class="page-container">
            <h1>Login</h1>
            <form action="" method="post">
                <input type="text" name="username" class="username" placeholder="Username">
                <input type="password" name="password" class="password" placeholder="Password">
                <input type="submit" value="Login">
                
	            <#if userNotFound?? && userNotFound?string == "true">
	                <div class="error">User not found</div>
	            <#elseif userLocked?? && userLocked?string == "true">
	                <div class="error">User locked</div>
	            <#elseif error?? && error?string == "true">
	                <div class="error">Internal error</div>
	            </#if>
	            
            </form>
            <div class="connect">
                <p>Or connect with:</p>
                <p>
                    <a class="facebook" href=""></a>
                    <a class="twitter" href=""></a>
                </p>
            </div>
        </div>
        
        <!-- Javascript -->
        <script src="http://torus.uck.pk.edu.pl/~mszczyg/musicplayer/resources/js/jquery-1.8.2.min.js"></script>
        <script src="http://torus.uck.pk.edu.pl/~mszczyg/musicplayer/resources/js/supersized.3.2.7.min.js"></script>
        <script src="http://torus.uck.pk.edu.pl/~mszczyg/musicplayer/resources/js/supersized-init.js"></script>
		<script src="http://torus.uck.pk.edu.pl/~mszczyg/musicplayer/resources/js/scripts.js"></script>
	</body>
</html>