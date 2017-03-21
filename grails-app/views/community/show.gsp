<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="public" />
    <g:set var="entityName" value="${message(code: 'community.label', default: 'Community')}" />
    <title>Факултет Математика и Информатика</title>
</head>
<body>
<a href="#show-community" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><g:link class="home" controller="user" action="login"><g:message code="default.home.label"/></g:link></li>
        <li><g:link class="list" action="index"><g:message code="communities.list.label"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
</div>
<div id="show-community" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <ol class="property-list community">

        <li class="fieldcontain">
            <span id="year-label" class="property-label">ID</span>
            <div class="property-value" aria-labelledby="year-label">${community.id}</div>
        </li>

        <li class="fieldcontain">
            <span id="name-label" class="property-label">Име</span>
            <div class="property-value" aria-labelledby="name-label">${community.name}</div>
        </li>

        <li class="fieldcontain">
            <span id="model-label" class="property-label">Описание</span>
            <div class="property-value" aria-labelledby="model-label">${community.shortDescription}</div>
        </li>
    </ol>

    <g:form action="delete" id="${community.id}">
        <fieldset class="buttons">
            <g:link class="edit" action="edit" id="${community.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
            <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
        </fieldset>
    </g:form>

    <br>
    <br>
    <div class="nav" role="navigation">
        <ul>
            <li><g:link class="create" controller="collection" action="create">Нова Колекция</g:link></li>
        </ul>
    </div>

    <div id="list-collection" class="content scaffold-list" role="main">
        <h1><g:message code="communities.collections.list.label"/></h1>
        <div class="list-group">
            <g:each in="${collectionsList}" var="col">
                <div class="list-group-item">
                    <g:link controller="collection" action="show" id="${col.id}">${col.name} &nbsp; ${col.shortDescription}</g:link>
                </div>
            </g:each>
        </div>
        <div class="pagination">
            <g:paginate controller="community" action="show" total="${collectionsCount ?: 0}" />
        </div>
    </div>

</div>
</body>
</html>
