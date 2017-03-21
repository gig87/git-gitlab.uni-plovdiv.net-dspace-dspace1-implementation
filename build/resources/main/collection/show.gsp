<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="public" />
    <g:set var="entityName" value="${message(code: 'collection.label', default: 'Collection')}" />
    <title>Факултет Математика и Информатика</title>
</head>
<body>
<a href="#show-collection" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><g:link class="home" controller="user" action="login"><g:message code="default.home.label"/></g:link></li>
        <li><g:link class="list" controller="community" action="show" id="${session.community.id}">Общност</g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
</div>
<div id="show-collection" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <ol class="property-list collection">

        <li class="fieldcontain">
            <span id="year-label" class="property-label">ID</span>
            <div class="property-value" aria-labelledby="year-label">${collection.id}</div>
        </li>

        <li class="fieldcontain">
            <span id="name-label" class="property-label">Име</span>
            <div class="property-value" aria-labelledby="name-label">${collection.name}</div>
        </li>

        <li class="fieldcontain">
            <span id="model-label" class="property-label">Описание</span>
            <div class="property-value" aria-labelledby="model-label">${collection.shortDescription}</div>
        </li>
    </ol>

    <g:form action="delete" id="${collection.id}">
        <fieldset class="buttons">
            <g:link class="edit" action="edit" id="${collection.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
            <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
        </fieldset>
    </g:form>

    <br>
    <br>
    <div class="nav" role="navigation">
        <ul>
            <li><g:link class="create" controller="item" action="create">Нов обект</g:link></li>
        </ul>
    </div>

    <div id="list-item" class="content scaffold-list" role="main">
        <h1><g:message code="collections.items.list.label"/></h1>
        <div class="list-group">
            <g:each in="${itemsList}" var="item">
                <div class="list-group-item">
                    <g:link controller="item" action="show" id="${item.id}">${item.name}</g:link>
                </div>
            </g:each>
        </div>
        <div class="pagination">
            <g:paginate controller="collection" action="show" total="${itemsCount ?: 0}" />
        </div>
    </div>


</div>
</body>
</html>
