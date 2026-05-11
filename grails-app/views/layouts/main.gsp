<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:layoutTitle default="University App"/></title>

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

</head>

<body>

<!-- NAVBAR -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark px-3">
    <a class="navbar-brand" href="/">University</a>

    <div class="navbar-nav">
        <a class="nav-link" href="/student">Students</a>
        <a class="nav-link" href="/course">Courses</a>
        <a class="nav-link" href="/enrollment">Enrollments</a>
        <a class="nav-link" href="/dashboard">Dashboard</a>
    </div>
</nav>

<!-- FLASH MESSAGE -->
<div class="container mt-3">

    <g:if test="${flash.message}">
        <div class="alert alert-info">
            ${flash.message}
        </div>
    </g:if>

    <!-- PAGE CONTENT -->
    <g:layoutBody/>

</div>

<!-- FOOTER -->
<footer class="text-center mt-5 p-3 bg-light">
    <small>© University App</small>
</footer>

</body>
</html>