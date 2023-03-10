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
    <title>Ra?l & Pavlo | Venta de camisetas de Star Wars</title>
</head>
<body>


<!-- Barra de navegaci?n -->
<jsp:include page="nav.jsp"></jsp:include>


<!-- Portada -->
<div id="carouselExampleSlidesOnly" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="https://i.gyazo.com/75db68e1a276a06e5bded12ba71a3249.jpg"
                 class="d-block w-100" alt="im?gen de portada para la p?gina de inicio">
        </div>
    </div>
</div>


<!-- T?tulo -->
<figure class="text-center" style="padding: 50px 0 50px 0;">
    <h1 class="display-1">Producto Individual</h1>
</figure>


<!-- Breadcrumbs -->
<div class="d-flex justify-content-center" style="margin-bottom: 50px;">
    <div class="container">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="/">Inicio</a></li>
                <li class="breadcrumb-item"><a href="/catalogo">Cat?logo</a></li>
                <li class="breadcrumb-item active" aria-current="page">Producto ${producto.idProducto}</li>
            </ol>
        </nav>
    </div>
</div>


<!-- Alertas -->
<c:if test="${ mensajeCarritoError != null }">
    <div class="d-flex justify-content-center" style="margin: 25px 0 25px 0;">
        <div class="alert alert-danger" role="alert">
                ${ mensajeCarritoError }
        </div>
    </div>
</c:if>
<c:if test="${ mensajeCarritoOk != null }">
    <div class="d-flex justify-content-center" style="margin: 25px 0 25px 0;">
        <div class="alert alert-success" role="alert">
                ${ mensajeCarritoOk }
        </div>
    </div>
</c:if>


<!-- Botones -->
<div class="d-flex justify-content-center" style="margin-bottom: 50px;">
    <div class="container" style="margin: 50px 0 50px 0;">
        <form method="get" action="/anyadirCarrito/${producto.idProducto}/producto">
            <button type="submit" class="btn btn-primary">A?adir al Carrito</button>
        </form>
    </div>
</div>


<!-- Detalles de producto -->
<div class="d-flex justify-content-center" style="margin-bottom: 50px;">
    <div class="container">
        <div class="card">
            <div class="card-body">
                <h3 class="card-title">${producto.precio} $</h3>
                <h5 class="card-title">${producto.nombre}</h5>
                <p class="card-text">${producto.descripcion}</p>
                <p class="card-text"><small class="text-muted">Qued?n ${producto.stock} unidades</small></p>
                <p class="card-text"><small class="text-muted">ID: ${producto.idProducto}</small></p>
            </div>
            <img src="${producto.imagen}" class="card-img-bottom" alt="...">
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