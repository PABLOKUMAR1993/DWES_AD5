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
                <h1 class="display-1">Direcciones de Envío</h1>
                <h1 class="display-5">Puedes gestionar aquí tus direcciones de envío.</h1>
            </div>
        </div>
    </div>
</div>

<!-- Título -->
<figure class="text-center" style="margin-top: 50px;">
    <h1 class="display-1">Tus Direcciones</h1>
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

<!-- Si no tienes direcciones -->
<c:if test="${direcciones == '[]'}">
    <div class="d-flex justify-content-center" style="margin: 50px 0 50px 0;">
        <div class="container">
            <div class="card text-center">
                <div class="card-header">
                    Direcciones
                </div>
                <div class="card-body" style="margin: 25px 0 25px 0;">
                    <h5 class="card-title">¡Oh vaya! Parece que no has agregado ninguna dirección todavía.</h5>
                    <p class="card-text">
                        Para poder completar una compra es necesario que nos indiques dónde quieres recibirlo.
                    </p>
                </div>
                <div class="card-footer text-muted">
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                            data-bs-target="#anyadirTarjeta" style="margin: 10px 0 10px 0;">
                        Añadir Dirección
                    </button>
                </div>
            </div>
        </div>
    </div>
</c:if>

<!-- Si tienes direcciones -->
<c:if test="${direcciones != '[]'}">
    <div class="d-flex justify-content-center" style="margin: 100px 0 100px 0;">
        <div class="container">
            <div class="card text-center">
                <div class="card-header">
                    Direcciones
                </div>
                <div class="card-body" style="margin: 25px 0 25px 0;">
                    <div class="container text-center">
                        <div class="row">
                            <div class="col">
                                <c:forEach var="direccion" items="${direcciones}">
                                    <div class="card w-100" style="margin: 0 0 25px 0;">
                                        <div class="card-body">
                                            <h5 class="card-title">${direccion.localidad}.</h5>
                                            <p class="card-title">Localidad: ${direccion.localidad}.</p>
                                            <p class="card-title">Calle: ${direccion.calle}, ${direccion.numero}.</p>
                                            <c:if test="${direccion.piso != ''}">
                                                <p class="card-title">
                                                    Piso: ${direccion.piso}, Letra: ${direccion.letra}.
                                                </p>
                                            </c:if>
                                            <p class="card-title">Código Postal: ${direccion.codigoPostal}.</p>
                                        </div>
                                        <div class="card-footer bg-transparent d-flex justify-content-center">
                                            <form action="/eliminarDireccion/${direccion.idDireccion}" method="post">
                                                <button type="submit" class="btn btn-danger"
                                                        style="margin: 10px 10px 10px 0;">
                                                    Eliminar Dirección
                                                </button>
                                            </form>
                                            <!--<button type="button" class="btn btn-warning" data-bs-toggle="modal"
                                                    data-bs-target="#editarDireccion" style="margin: 10px 0 10px 0;">
                                                Editar Dirección-->
                                            </button>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card-footer text-muted">
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                            data-bs-target="#anyadirDireccion" style="margin: 10px 0 10px 0;">
                        Añadir Dirección Nueva
                    </button>
                </div>
            </div>
        </div>
    </div>
</c:if>

<!-- Modal para añadir direcciones -->
<div class="modal fade" id="anyadirDireccion" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Añadir Dirección</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form method="post" action="/anyadirDireccion">
                <div class="modal-body">

                    <div class="mb-3">
                        <label for="localidad" class="form-label">Localidad</label>
                        <input type="text" class="form-control" id="localidad" name="localidad"
                               aria-describedby="emailHelp" required>
                        <div id="localidadAyuda" class="form-text">
                            Ciudad en la que se entregará el pedido.
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="calle" class="form-label">Calle</label>
                        <input type="text" class="form-control" id="calle" name="calle"
                               aria-describedby="emailHelp" required>
                        <div id="calleAyuda" class="form-text">
                            Calle en la que entregar el pedido.
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="numero" class="form-label">Número</label>
                        <input type="number" class="form-control" id="numero" name="numero"
                               aria-describedby="emailHelp" required>
                        <div id="numeroAyuda" class="form-text">
                            Número del edificio.
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="codigoPostal" class="form-label">Código Postal</label>
                        <input type="text" class="form-control" id="codigoPostal" name="codigoPostal"
                               aria-describedby="emailHelp" required>
                        <div id="codigoPostalAyuda" class="form-text">
                            5 números que identifican tu barrio o municipio.
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="piso" class="form-label">Piso</label>
                        <input type="number" class="form-control" id="piso" name="piso"
                               aria-describedby="emailHelp" required>
                        <div id="pisoAyuda" class="form-text">
                            Si procede, indica en que piso vives.
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="letra" class="form-label">Letra</label>
                        <input type="text" class="form-control" id="letra" name="letra"
                               aria-describedby="emailHelp">
                        <div id="letraAyuda" class="form-text" required>
                            Si procede, indica la letra de tu puerta en el edificio.
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <button type="submit" class="btn btn-primary">Guardar Dirección</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Modal para editar direcciones -->
<!-- <div class="modal fade" id="editarDireccion" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabelEditar">Añadir Dirección</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form method="post" action="/editarDireccion">
                <div class="modal-body">

                    <div class="mb-3">
                        <label for="localidadEditar" class="form-label">Localidad</label>
                        <input type="text" class="form-control" id="localidadEditar" name="localidad"
                               aria-describedby="emailHelp" required>
                        <div id="localidadAyudaEditar" class="form-text">
                            Ciudad en la que se entregará el pedido.
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="calleEditar" class="form-label">Calle</label>
                        <input type="text" class="form-control" id="calleEditar" name="calle"
                               aria-describedby="emailHelp" required>
                        <div id="calleAyudaEditar" class="form-text">
                            Calle en la que entregar el pedido.
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="numeroEditar" class="form-label">Número</label>
                        <input type="number" class="form-control" id="numeroEditar" name="numero"
                               aria-describedby="emailHelp" required>
                        <div id="numeroAyudaEditar" class="form-text">
                            Número del edificio.
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="codigoPostalEditar" class="form-label">Código Postal</label>
                        <input type="text" class="form-control" id="codigoPostalEditar" name="codigoPostal"
                               aria-describedby="emailHelp" required>
                        <div id="codigoPostalAyudaEditar" class="form-text">
                            5 números que identifican tu barrio o municipio.
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="pisoEditar" class="form-label">Piso</label>
                        <input type="number" class="form-control" id="pisoEditar" name="piso"
                               aria-describedby="emailHelp">
                        <div id="pisoAyudaEditar" class="form-text">
                            Si procede, indica en que piso vives.
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="letraEditar" class="form-label">Letra</label>
                        <input type="text" class="form-control" id="letraEditar" name="letra"
                               aria-describedby="emailHelp">
                        <div id="letraAyudaEditar" class="form-text">
                            Si procede, indica la letra de tu puerta en el edificio.
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <button type="submit" class="btn btn-primary">Actualizar Dirección</button>
                </div>
            </form>
        </div>
    </div>
</div> -->

<!-- Footer -->
<jsp:include page="footer.jsp"></jsp:include>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
</body>
</html>