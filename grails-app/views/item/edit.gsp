<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="public" />
    <g:set var="entityName" value="${message(code: 'item.label', default: 'Item')}" />
    <title>Факултет Математика и Информатика</title>
</head>
<body>
<a href="#edit-item" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><g:link class="home" controller="user" action="login"><g:message code="default.home.label"/></g:link></li>
        <li><g:link class="list" controller="collection" action="show" id="${session.collection.id}">Колекция</g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
</div>
<div id="edit-item" class="content scaffold-edit" role="main">
    <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.item}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.item}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form action="update">
        <fieldset class="form">
            <input type="hidden" name="id" value="${item.id}" readonly id="id" />
            <div class='fieldcontain required'>
                <label for='name'>Име<span class='required-indicator'>*</span></label>
                <input type="text" name="name" value="${item.name}" required="" maxlength="255" id="name" />
            </div>
            <div class='fieldcontain'>
                <label for='author'>Автор</label>
                <input type="text" name="author" value="${item.author}" maxlength="255" id="author" />
            </div>
            <div class='fieldcontain'>
                <label for='theme'>Тема</label>
                <input type="text" name="theme" value="${item.theme}" maxlength="255" id="theme" />
            </div>
        </fieldset>
        <fieldset class="buttons">
            <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
        </fieldset>
    </g:form>
</div>
</body>
</html>
