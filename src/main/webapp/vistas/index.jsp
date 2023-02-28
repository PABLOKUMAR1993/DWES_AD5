<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
    <title>Ra�l & Pavlo | Venta de camisetas de Star Wars</title>
</head>
<body>

    <!-- Barra de navegaci�n -->
    <jsp:include page="nav.jsp"></jsp:include>

    <!-- Portada -->
    <div id="carouselExampleSlidesOnly" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="img/portada_web.jpg" class="d-block w-100" alt="im�gen de portada para la p�gina de inicio">
                <div class="carousel-caption d-none d-md-block">
                    <h1 class="display-1">Ra�l & Pavlo</h1>
                    <h1 class="display-5">Camisetas de tu saga favorita en la mejor calidad y originales.</h1>
                </div>
            </div>
        </div>
    </div>

    <!-- T�tulo -->
    <figure class="text-center" style="padding: 50px 0 50px 0;">
        <h1 class="display-1">Lo m�s Nuevo</h1>
    </figure>

    <!-- Listado de productos -->
    <div class="container text-center">
        <div class="row">
            <div class="col">
                <c:forEach var="producto" items="${listadoImpares}" >
                        <div class="card w-100" style="margin: 0 0 25px 0;">
                            <img src="${producto.imagen}" class="card-img-top" alt="Im�gen del producto">
                            <div class="card-body">
                                <h5 class="card-title">${producto.precio} $</h5>
                                <p class="card-title">${producto.nombre}</p>
                                <p class="card-text"><small class="text-muted">${producto.descripcion}</small></p>
                            </div>
                            <div class="card-footer bg-transparent"><a href="#" class="btn btn-primary">Comprar</a></div>
                        </div>
                </c:forEach>
            </div>
            <div class="col">
                <c:forEach var="producto" items="${listadoPares}" >
                    <div class="card w-100" style="margin: 0 0 25px 0;">
                        <img src="${producto.imagen}" class="card-img-top" alt="Im�gen del producto">
                        <div class="card-body">
                            <h5 class="card-title">${producto.precio} $</h5>
                            <p class="card-title">${producto.nombre}</p>
                            <p class="card-text"><small class="text-muted">${producto.descripcion}</small></p>
                        </div>
                        <div class="card-footer bg-transparent"><a href="#" class="btn btn-primary">Comprar</a></div>
                    </div>
                </c:forEach>
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