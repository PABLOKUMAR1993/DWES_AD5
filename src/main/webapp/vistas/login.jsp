<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="es" data-bs-theme="dark">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <title>Raúl & Pavlo | Venta de camisetas de Star Wars</title>
</head>
<body>


<!-- Barra de navegación -->
<jsp:include page="nav.jsp"></jsp:include>


<!-- Portada -->
<div id="carouselExampleSlidesOnly" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="https://4kwallpapers.com/images/wallpapers/sith-star-wars-lightsaber-dark-background-2880x1800-5554.jpg"
                 class="d-block w-100"
                 alt="imágen de portada para la página de inicio">
            <div class="carousel-caption d-none d-md-block">
                <h1 class="display-1">Iniciar Sesión</h1>
                <h1 class="display-5">Página para iniciar sesión</h1>
            </div>
        </div>
    </div>
</div>


<!-- Mensaje emergente -->
<c:if test="${ mensajeLogin != null }">
    <div class="d-flex justify-content-center" style="margin-top: 50px;">
        <div class="alert alert-success" role="alert">
                ${ mensajeLogin }
        </div>
    </div>
</c:if>


<!-- Formulario -->
<div class="container-fluid" style="margin: 50px 0 20px 0">
    <div class="row">
        <div class="col-sm-4 col-md-4 col-lg-4"></div>
        <div class="col-sm-4 col-md-4 col-lg-4">
            <div class="card card-container">
                <h2 class="text-center" style="margin: 25px 0 25px 0">Iniciar Sesión</h2>
                <form method="post" action="/login">
                    <div class="mb-3" style="margin: 0 25px 0 25px">
                        <label for="username" class="form-label">Email</label>
                        <input type="email" class="form-control" id="username" name="username"
                               aria-describedby="emailHelp">
                    </div>
                    <div class="mb-3" style="margin: 0 25px 0 25px">
                        <label for="password" class="form-label">Contraseña</label>
                        <input type="password" class="form-control" id="password" name="password">
                    </div>
                    <div class="d-flex justify-content-center">
                        <button type="submit" class="btn btn-primary" style="margin: 25px 0 25px 0">
                            Iniciar Sesión
                        </button>
                    </div>
                </form>
            </div>
        </div>
        <div class="col-sm-4 col-md-4 col-lg-4"></div>
    </div>
</div>


<!-- Registro -->
<div class="d-flex justify-content-center">
    <blockquote class="blockquote">
        <p>¿No tienes cuenta? <cite title="Source Title"><a href="/registro">create una.</a></cite></p>
    </blockquote>
</div>


<!-- Footer -->
<jsp:include page="footer.jsp"></jsp:include>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
</body>
</html>