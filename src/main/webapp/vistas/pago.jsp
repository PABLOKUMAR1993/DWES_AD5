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
                <h1 class="display-1">Métodos de Pago</h1>
                <h1 class="display-5">Puedes gestionar aquí tus métodos de pago.</h1>
            </div>
        </div>
    </div>
</div>


<!-- Título -->
<figure class="text-center" style="margin-top: 50px;">
    <h1 class="display-1">Tus Tarjetas</h1>
</figure>


<!-- Alertas -->
<c:if test="${ errorParcial != null }">
    <div class="d-flex justify-content-center" style="margin: 25px 0 25px 0;">
        <div class="alert alert-danger" role="alert">
                ${ errorParcial }
        </div>
    </div>
</c:if>
<c:if test="${ error != null }">
    <div class="d-flex justify-content-center" style="margin: 25px 0 25px 0;">
        <div class="alert alert-danger" role="alert">
                ${ error }
        </div>
    </div>
</c:if>
<c:if test="${ mensajeOk != null }">
    <div class="d-flex justify-content-center" style="margin: 25px 0 25px 0;">
        <div class="alert alert-success" role="alert">
                ${ mensajeOk }
        </div>
    </div>
</c:if>


<!-- Si no tienes tarjetas -->
<c:if test="${tarjetas == '[]'}">
    <div class="d-flex justify-content-center" style="margin: 50px 0 50px 0;">
        <div class="container">
            <div class="card text-center">
                <div class="card-header">
                    Tarjetas
                </div>
                <div class="card-body" style="margin: 25px 0 25px 0;">
                    <h5 class="card-title">¡Oh vaya! parece que no has agregado ninguna tarjeta todavía.</h5>
                    <p class="card-text">Para poder hacer compras es necesario tener una tarjeta.</p>
                </div>
                <div class="card-footer text-muted">
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                            data-bs-target="#anyadirTarjeta" style="margin: 10px 0 10px 0;">
                        Añadir Tarjeta
                    </button>
                </div>
            </div>
        </div>
    </div>
</c:if>


<!-- Si tienes tarjetas -->
<c:if test="${tarjetas != '[]'}">
    <div class="d-flex justify-content-center" style="margin: 100px 0 100px 0;">
        <div class="container">
            <div class="card text-center">
                <div class="card-header">
                    Tus Tarjetas
                </div>
                <div class="card-body" style="margin: 25px 0 25px 0;">
                    <h5 class="card-title">${tarjeta.nombre}</h5>
                    <c:if test="${tarjetas != '[]'}">
                        <div class="container text-center">
                            <div class="row">
                                <div class="col">
                                    <c:forEach var="tarjeta" items="${tarjetas}">
                                        <div class="card w-100" style="margin: 0 0 25px 0;">
                                            <div class="card-body">
                                                <h5 class="card-title">${tarjeta.nombre}</h5>
                                                <p class="card-title">Número: ${tarjeta.numero}</p>
                                                <p class="card-text"><small
                                                        class="text-muted">Caduca: ${tarjeta.fechaCaducidad}</small></p>
                                                <p class="card-text"><small class="text-muted">CVC: ${tarjeta.cvv}</small>
                                                </p>
                                            </div>
                                            <div class="card-footer bg-transparent d-flex justify-content-center">
                                                <form action="/eliminarTarjeta/${tarjeta.idTarjeta}" method="post">
                                                    <button type="submit" class="btn btn-danger"
                                                            style="margin: 10px 10px 10px 0;">
                                                        Eliminar Tarjeta
                                                    </button>
                                                </form>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </div>
                <div class="card-footer text-muted">
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                            data-bs-target="#anyadirTarjeta" style="margin: 10px 0 10px 0;">
                        Añadir Tarjeta Nueva
                    </button>
                </div>
            </div>
        </div>
    </div>
</c:if>


<!-- Modal para añadir tarjetas -->
<div class="modal fade" id="anyadirTarjeta" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Añadir Tarjeta</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form method="post" action="/anyadirTarjeta">
                <div class="modal-body">

                    <div class="mb-3">
                        <label for="nombre" class="form-label">Titular</label>
                        <input type="text" class="form-control" id="nombre" name="nombre"
                               aria-describedby="emailHelp" required>
                        <div id="nombreAyuda" class="form-text">
                            Es el nombre de la persona a la que pertenece la tarjeta.
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="numero" class="form-label">Número</label>
                        <input type="text" class="form-control" id="numero" name="numero"
                               aria-describedby="emailHelp" pattern=".{16}" required>
                        <div id="numeroAyuda" class="form-text">
                            Es un número de 16 dígitos que tienes en tu tarjeta.
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="fechaCaducidad" class="form-label">Caducidad</label>
                        <input type="date" class="form-control" id="fechaCaducidad" name="fechaCaducidad"
                               aria-describedby="emailHelp" required>
                        <div id="caducidadAyuda" class="form-text">
                            Es la fecha de expiración de la tarjeta.
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="cvv" class="form-label">CVC</label>
                        <input type="text" class="form-control" id="cvv" name="cvv"
                               aria-describedby="emailHelp" required>
                        <div id="cvvAyuda" class="form-text">
                            Son 3 números que, en general aparecen en la parte trasera de la tarjeta.
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <button type="submit" class="btn btn-primary">Guardar Tarjeta</button>
                </div>
            </form>
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