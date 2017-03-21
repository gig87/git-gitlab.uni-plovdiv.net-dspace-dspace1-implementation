<!doctype html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails + React</title>
    <asset rel="icon" href="favicon.ico" type="image/x-ico" />
</head>
<body>
    <div id="layout-content"></div>
    <g:if test="${session.user}">
        %{--<header class="header">--}%
            %{--<div class="top-bar">--}%
                %{--<p>--}%
                    %{--Влезли сте като ${session.user} | <button class="btn btn-primary"> <g:link controller="user" action="logout">Отписване</g:link> </button>--}%
                %{--</p>--}%

            %{--</div>--}%

            %{--<div class="header-main container">--}%
                %{--<h1 class="col-sm-6 col-sm-offset-5">--}%
                    %{--<img src="${assetPath(src: 'delc-logo.png')}">--}%
                %{--</h1>--}%
            %{--</div>--}%


        %{--</header>--}%
        <div id="url" url="${g.createLink(absolute:true, uri:"/")}"></div>

        <script>
            window.BASE_URL = document.getElementById("url").getAttribute("url");
        </script>

        <div id="app"></div>

        <g:if env="production">
            <script src="assets/app.bundle.js"></script>
        </g:if>
        <g:if env="development">
            <script src="http://localhost:3000/assets/app.bundle.js"></script>
        </g:if>
        <!-- The webpack-generated bundle -->
    </g:if>



</body>
</html>