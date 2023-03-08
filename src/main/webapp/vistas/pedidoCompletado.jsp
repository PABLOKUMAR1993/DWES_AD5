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
                <h1 class="display-1">Gracias!</h1>
                <h1 class="display-5">Tu pedido se ha realizado correctamente.</h1>
            </div>
        </div>
    </div>
</div>


<!-- Título -->
<figure class="text-center" style="margin-top: 50px;">
    <h1 class="display-1">¡Gracias por tu Pedido!</h1>
</figure>


<!-- Alertas -->
<c:if test="${ mensajePedidoOk != null }">
    <div class="d-flex justify-content-center" style="margin: 25px 0 25px 0;">
        <div class="alert alert-danger" role="alert">
                ${ mensajePedidoOk }
        </div>
    </div>
</c:if>


<!-- Tarjeta con mensaje de pedido completado -->
<div class="d-flex justify-content-center" style="margin-bottom: 50px;">
    <div class="container">
        <div class="card text-center">
            <div class="card-header">
                Pedido Completado
            </div>
            <div class="card-body" style="margin: 25px 0 25px 0;">
                <h5 class="card-title">Tu número de pedido es el: 01</h5>
                <p class="card-text">Datos adicionales.</p>
            </div>
            <div class="card-footer text-muted">
                <a class="btn btn-success" href="/direccionEnvioPedido" style="margin: 10px 5px 10px 0;">
                    ir a tus pedidos
                </a>
                <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                        data-bs-target="#anyadirTarjeta" style="margin: 10px 0 10px 0;">
                    Seguir Comprando
                </button>
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