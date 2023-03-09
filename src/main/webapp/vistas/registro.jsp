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
            <img src="https://i.gyazo.com/3cf3690632b09842203a4de8acd4cd46.jpg"
                 class="d-block w-100"
                 alt="imágen de portada para la página de inicio">
        </div>
    </div>
</div>

<!-- Título -->
<figure class="text-center" style="padding: 50px 0 50px 0;">
    <h1 class="display-1">Create una Cuenta</h1>
</figure>

<!-- Mensaje de Error -->
<c:if test="${ mensajeEdad != null }">
    <div class="d-flex justify-content-center" style="margin-top: 50px;">
        <div class="alert alert-danger" role="alert">
            ${ mensajeEdad }
        </div>
    </div>
</c:if>
<c:if test="${ mensajeEmail != null }">
    <div class="d-flex justify-content-center" style="margin-top: 50px;">
        <div class="alert alert-danger" role="alert">
                ${ mensajeEmail }
        </div>
    </div>
</c:if>


<!-- Formulario -->
<div class="container-fluid" style="margin: 50px 0 20px 0">
    <div class="row">
        <div class="col-sm-4 col-md-4 col-lg-4"></div>
        <div class="col-sm-4 col-md-4 col-lg-4">
            <div class="card card-container">
                <h2 class="text-center" style="margin: 25px 0 25px 0">Formulario de Registro</h2>
                <form method="post" action="/crearCliente">
                    <div class="mb-3" style="margin: 0 25px 0 25px">
                        <label for="nombre" class="form-label">Nombre</label>
                        <input type="text" class="form-control" id="nombre" name="nombre">
                    </div>
                    <div class="mb-3" style="margin: 0 25px 0 25px">
                        <label for="apellidos" class="form-label">Apellidos</label>
                        <input type="text" class="form-control" id="apellidos" name="apellidos">
                    </div>
                    <div class="mb-3" style="margin: 0 25px 0 25px">
                        <label for="fechaNacimiento" class="form-label">Fecha de Nacimiento</label>
                        <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento">
                        <div class="form-text">Sólo mayores de 18 años</div>
                    </div>
                    <div class="mb-3" style="margin: 0 25px 0 25px">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" name="email" required>
                    </div>
                    <div class="mb-3" style="margin: 0 25px 0 25px">
                        <label for="contrasenya" class="form-label">Contraseña</label>
                        <input type="password" class="form-control" id="contrasenya" name="contrasenya">
                    </div>
                    <div class="d-flex justify-content-center">
                        <button type="submit" class="btn btn-primary" style="margin: 25px 0 25px 0">
                            Registrarme
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
        <p>¿Ya tienes una cuenta? <cite title="Source Title"><a href="/login">Inicia Sesión.</a></cite></p>
    </blockquote>
</div>


<!-- Footer -->
<jsp:include page="footer.jsp"></jsp:include>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
</body>
</html>