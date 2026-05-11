<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'student.label', default: 'Student')}" />
    <title><g:message code="default.create.label" args="[entityName]" /></title>
</head>
<body>
<div id="content" role="main">
    <div class="container">
        <section class="row">
            <div id="create-student" class="col-12 content scaffold-create" role="main">
                <h1><g:message code="default.create.label" args="[entityName]" /></h1>

                <!-- Flash Messages -->
                <g:if test="${flash.message}">
                    <div class="alert alert-info">${flash.message}</div>
                </g:if>

                <!-- Validation Errors -->
                <g:hasErrors bean="${student}">
                    <ul class="alert alert-danger list-unstyled" role="alert">
                        <g:eachError bean="${student}" var="error">
                            <li><i class="bi-exclamation-circle"></i> <g:message error="${error}"/></li>
                        </g:eachError>
                    </ul>
                </g:hasErrors>

                <!-- Form -->
                <g:form controller="student" action="save" method="POST">
                    <div class="mb-3">
                        <label class="form-label">Name</label>
                        <g:textField name="name" value="${student?.name}" class="form-control"/>
                        <g:fieldError bean="${student}" field="name" class="text-danger"/>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Email</label>
                        <g:textField name="email" value="${student?.email}" class="form-control"/>
                        <g:fieldError bean="${student}" field="email" class="text-danger"/>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Student Number</label>
                        <g:textField name="studentNumber" value="${student?.studentNumber}" class="form-control"/>
                        <g:fieldError bean="${student}" field="studentNumber" class="text-danger"/>
                    </div>

                    <button class="btn btn-primary" type="submit">
                        <i class="bi-floppy"></i> <g:message code="default.button.create.label" default="Create"/>
                    </button>
                </g:form>
            </div>
        </section>
    </div>
</div>
</body>
</html>
