<html>
<head>
    <meta name="layout" content="public"/>
    <title>Факултет Математика и Информатика</title>
</head>
<body>

<div id="content" role="main">

    <g:if test="${flash.message}">
        <div class="errors">${flash.message}</div>
    </g:if>
    <g:if test="${session.user}">
        Влезли сте като ${session.user} | <g:link controller="user" action="logout">Отписване</g:link>
    </g:if>
    <g:else>
        <g:form controller="user" action="login">
            <fieldset class="form">
                <div class='fieldcontain required'>
                    <label>Потребителско име<span class='required-indicator'>*</span></label><input type="text" name="username"/>
                </div>
                <div class='fieldcontain required'>
                    <label>Парола<span class='required-indicator'>*</span></label><input type="password" name="password"/>
                </div>
            </fieldset>
            <fieldset class="buttons">
                <input type="submit" value="Вход">
            </fieldset>
        </g:form>
    </g:else>
</div>

</body>
</html>