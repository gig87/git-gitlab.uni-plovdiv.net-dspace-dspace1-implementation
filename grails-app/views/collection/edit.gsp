<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="public" />
    <g:set var="entityName" value="${message(code: 'collection.label', default: 'Collection')}" />
    <title>Факултет Математика и Информатика</title>
</head>
<body>
<a href="#edit-collection" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><g:link class="home" controller="user" action="login"><g:message code="default.home.label"/></g:link></li>
        <li><g:link class="list" controller="community" action="show" id="${session.community.id}">Общност</g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
</div>
<div id="edit-collection" class="content scaffold-edit" role="main">
    <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.collection}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.collection}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form action="update">
        <fieldset class="form">
            <input type="hidden" name="id" value="${collection.id}" readonly id="id" />
            <div class='fieldcontain required'>
                <label for='name'>Име<span class='required-indicator'>*</span></label>
                <input type="text" name="name" value="${collection.name}" required="" maxlength="255" id="name" />
            </div>
            <div class='fieldcontain'>
                <label for='shortDescription'>Описание</label>
                <textarea name="shortDescription" value="" maxlength="255" id="shortDescription">${collection.shortDescription}</textarea>
            </div>
        </fieldset>
        <fieldset class="buttons">
            <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
        </fieldset>
    </g:form>
</div>
</body>
</html>
