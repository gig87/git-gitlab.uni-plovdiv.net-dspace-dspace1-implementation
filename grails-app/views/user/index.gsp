<html>
<head>
    <meta name="layout" content="main"/>
    <title>Факултет Математика и Информатика</title>
</head>
<body>

<div id="content-user-index" role="main">

    <g:if test="${flash.message}">
        <div class="errors">${flash.message}</div>
    </g:if>
    <div id="url" url="${g.createLink(absolute:true, uri:"/")}"></div>

    <script>
        window.BASE_URL = document.getElementById("url").getAttribute("url");
    </script>

    <div id="login"></div>

    <g:if env="production">
        <script src="assets/login.bundle.js"></script>
    </g:if>
    <g:if env="development">
        <script src="http://localhost:3000/assets/login.bundle.js"></script>
    </g:if>
</div>

</body>
</html>