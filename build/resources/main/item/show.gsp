<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="public" />
    <g:set var="entityName" value="${message(code: 'item.label', default: 'Item')}" />
    <title>Факултет Математика и Информатика</title>
</head>
<body>
<a href="#show-item" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><g:link class="home" controller="user" action="login"><g:message code="default.home.label"/></g:link></li>
        <li><g:link class="list" controller="collection" action="show" id="${session.collection.id}">Колекция</g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
</div>
<div id="show-item" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <ol class="property-list item">

        <li class="fieldcontain">
            <span id="id-label" class="property-label">ID</span>
            <div class="property-value" aria-labelledby="year-label">${item.id}</div>
        </li>

        <li class="fieldcontain">
            <span id="name-label" class="property-label">Име</span>
            <div class="property-value" aria-labelledby="name-label">${item.name}</div>
        </li>

        <li class="fieldcontain">
            <span id="author-label" class="property-label">Автор</span>
            <div class="property-value" aria-labelledby="author-label">${item.author}</div>
        </li>

        <li class="fieldcontain">
            <span id="theme-label" class="property-label">Тема</span>
            <div class="property-value" aria-labelledby="theme-label">${item.theme}</div>
        </li>
    </ol>

    <g:form action="delete" id="${item.id}">
        <fieldset class="buttons">
            <g:link class="edit" action="edit" id="${item.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
            <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
        </fieldset>
    </g:form>

    <br>
    <div id="list-metadata" class="content scaffold-list" role="main">
        <h1>Метаданни</h1>
        <div class="list-group">
            <g:each in="${metadatasList}" var="metadata">
                <div class="list-group-item">
                    <p><g:message code="${metadata.key}"/><g:message code="dc.colon"/>&nbsp;${metadata.value}</p>
                </div>
            </g:each>
        </div>
    </div>

    <br>
    <div id="list-bitstreams" class="content scaffold-list" role="main">
        <h1>Файлове</h1>
        <g:uploadForm action="upload">
            <fieldset class="form">
                <input type="file" name="file" />
            </fieldset>
            <fieldset class="buttons">
                <g:submitButton name="upload" class="save" value="Upload" />
            </fieldset>
        </g:uploadForm>
        <div class="list-group">
            <g:each in="${bitstreamsList}" var="bitstream">
                <div class="list-group-item">
                    <p>${bitstream.name} &nbsp; ${bitstream.description} &nbsp; ${bitstream.format} &nbsp; ${bitstream.sizeBytes} &nbsp; <button onclick="window.location.href='http://localhost:9090${bitstream.retrieveLink}'">Отвори</button></p>
                </div>
            </g:each>
        </div>
    </div>

</div>
</body>
</html>
