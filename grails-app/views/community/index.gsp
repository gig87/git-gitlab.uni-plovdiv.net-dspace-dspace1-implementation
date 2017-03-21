<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="public" />
    <g:set var="entityName" value="${message(code: 'community.label', default: 'Community')}" />
    <title>Факултет Математика и Информатика</title>
</head>
<body>
%{--<a href="#list-community" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>--}%
<div class="nav" role="navigation">
    <ul>
        <li><g:link class="home" controller="user" action="login"><g:message code="default.home.label"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
</div>
<div id="list-community" class="content scaffold-list" role="main">
    <h1><g:message code="communities.list.label"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <div class="list-group">
        <g:each in="${communitiesList}" var="c">
            <div class="list-group-item">
            <g:link action="show" id="${c.id}">${c.name} &nbsp; ${c.shortDescription}</g:link>
            </div>
        </g:each>
    </div>
    <div class="pagination">
        <g:paginate controller="community" action="index" total="${communitiesCount ?: 0}" />
    </div>
</div>
</body>
</html>
