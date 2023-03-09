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
            <img src="https://i.gyazo.com/e01cf9fc1ae8f17056242005cbdd3f2f.jpg"
                 class="d-block w-100"
                 alt="imágen de portada para la página de inicio">
        </div>
    </div>
</div>


<!-- Título -->
<figure class="text-center" style="margin-top: 50px;">
    <h1 class="display-1">Tus Direcciones</h1>
</figure>


<!-- Direccion a Modificar -->
<div class="d-flex justify-content-center" style="margin: 100px 0 100px 0;">
    <div class="container">
        <div class="card text-center">
            <div class="card-header">
                Dirección a Modificar
            </div>
            <div class="card-body" style="margin: 25px 0 25px 0;">
                <div class="container text-center d-flex justify-content-center">
                    <div class="row">
                        <div class="col">
                            <div class="card w-100" style="margin: 0 0 25px 0;">
                                <div class="card-body">
                                    <form method="post" action="/editarDireccion/${direccion.idDireccion}">
                                        <div class="modal-body">
                                            <div class="mb-3">
                                                <label for="localidad" class="form-label">
                                                    <h1 class="display-6">Localidad</h1>
                                                </label>
                                                <input type="text" class="form-control" id="localidad" name="localidad"
                                                       aria-describedby="emailHelp" value="${direccion.localidad}"
                                                       required>
                                                <div id="localidadAyuda" class="form-text">
                                                    Ciudad en la que se entregará el pedido.
                                                </div>
                                            </div>
                                            <div class="mb-3">
                                                <label for="calle" class="form-label">
                                                    <h1 class="display-6">Calle</h1>
                                                </label>
                                                <input type="text" class="form-control" id="calle" name="calle"
                                                       aria-describedby="emailHelp" value="${direccion.calle}"
                                                       required>
                                                <div id="calleAyuda" class="form-text">
                                                    Calle en la que entregar el pedido.
                                                </div>
                                            </div>
                                            <div class="mb-3">
                                                <label for="numero" class="form-label">
                                                    <h1 class="display-6">Número</h1>
                                                </label>
                                                <input type="number" class="form-control" id="numero" name="numero"
                                                       aria-describedby="emailHelp" value="${direccion.numero}"
                                                       required>
                                                <div id="numeroAyuda" class="form-text">
                                                    Número del edificio.
                                                </div>
                                            </div>
                                            <div class="mb-3">
                                                <label for="codigoPostal" class="form-label">
                                                    <h1 class="display-6">Código Postal</h1>
                                                </label>
                                                <input type="text" class="form-control" id="codigoPostal" name="codigoPostal"
                                                       aria-describedby="emailHelp" value="${direccion.codigoPostal}"
                                                       required>
                                                <div id="codigoPostalAyuda" class="form-text">
                                                    5 números que identifican tu barrio o municipio.
                                                </div>
                                            </div>
                                            <div class="mb-3">
                                                <label for="piso" class="form-label">
                                                    <h1 class="display-6">Piso</h1>
                                                </label>
                                                <input type="number" class="form-control" id="piso" name="piso"
                                                       aria-describedby="emailHelp" value="${direccion.piso}">
                                                <div id="pisoAyuda" class="form-text">
                                                    Si procede, indica en que piso vives.
                                                </div>
                                            </div>
                                            <div class="mb-3">
                                                <label for="letra" class="form-label">
                                                    <h1 class="display-6">Letra</h1>
                                                </label>
                                                <input type="text" class="form-control" id="letra" name="letra"
                                                       aria-describedby="emailHelp" value="${direccion.letra}">
                                                <div id="letraAyuda" class="form-text">
                                                    Si procede, indica la letra de tu puerta en el edificio.
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card-footer bg-transparent d-flex justify-content-center">
                                            <a href="/direcciones" class="btn btn-secondary" style="margin: 10px 10px 10px 0;">
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