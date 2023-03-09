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
            <img src="https://i.gyazo.com/a31fc6b222841e2d292e4d0d8436db40.jpg"
                 class="d-block w-100"
                 alt="imágen de portada para la página de inicio">
        </div>
    </div>
</div>


<!-- Título -->
<figure class="text-center" style="margin-top: 50px;">
    <h1 class="display-1">Tu Tarjeta para Editar</h1>
</figure>


<!-- Tarjeta a Modificar -->
<div class="d-flex justify-content-center" style="margin: 100px 0 100px 0;">
    <div class="container">
        <div class="card text-center">
            <div class="card-header">
                Tarjeta a Modificar
            </div>
            <div class="card-body" style="margin: 25px 0 25px 0;">
                <div class="container text-center d-flex justify-content-center">
                    <div class="row">
                        <div class="col">
                            <div class="card w-100" style="margin: 0 0 25px 0;">
                                <div class="card-body">
                                    <form method="post" action="/editarTarjeta/${tarjeta.idTarjeta}">
                                        <div class="modal-body">

                                            <div class="mb-3">
                                                <label for="nombre" class="form-label">Titular</label>
                                                <input type="text" class="form-control" id="nombre" name="nombre"
                                                       aria-describedby="emailHelp" value="${tarjeta.nombre}" required>
                                                <div id="nombreAyuda" class="form-text">
                                                    Es el nombre de la persona a la que pertenece la tarjeta.
                                                </div>
                                            </div>
                                            <div class="mb-3">
                                                <label for="numero" class="form-label">Número</label>
                                                <input type="text" class="form-control" id="numero" name="numero"
                                                       aria-describedby="emailHelp" pattern=".{16}"
                                                       value="${tarjeta.numero}"required>
                                                <div id="numeroAyuda" class="form-text">
                                                    Es un número de 16 dígitos que tienes en tu tarjeta.
                                                </div>
                                            </div>
                                            <div class="mb-3">
                                                <label for="fechaCaducidad" class="form-label">Caducidad</label>
                                                <input type="date" class="form-control" id="fechaCaducidad" name="fechaCaducidad"
                                                       aria-describedby="emailHelp" value="${tarjeta.fechaCaducidad}"
                                                       required>
                                                <div id="caducidadAyuda" class="form-text">
                                                    Es la fecha de expiración de la tarjeta.
                                                </div>
                                            </div>
                                            <div class="mb-3">
                                                <label for="cvv" class="form-label">CVC</label>
                                                <input type="text" class="form-control" id="cvv" name="cvv"
                                                       aria-describedby="emailHelp" value="${tarjeta.cvv}" required>
                                                <div id="cvvAyuda" class="form-text">
                                                    Son 3 números que, en general aparecen en la parte trasera de la tarjeta.
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card-footer bg-transparent d-flex justify-content-center">
                                            <a href="/tarjetas" class="btn btn-secondary" style="margin: 10px 10px 10px 0;">
                                                Volver
                                            </a>
                                            <button type="submit" class="btn btn-primary"
                                                    style="margin: 10px 0px 10px 10px;">
                                                Guardar Cambios
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- Footer -->
<jsp:include page="footer.jsp"></jsp:include>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
</body>
</html>