const navToggle = document.querySelector(".nav__toggle");
const navMenu = document.querySelector(".nav__menu");

if (navToggle) {
    navToggle.addEventListener("click", function () {
        navMenu.classList.toggle("nav__menu__visible");

        if (navMenu.classList.contains("nav__menu__visible")) {
            navToggle.setAttribute("aria-label", "Cerrar menú");
        } else {
            navToggle.setAttribute("aria-label", "Abrir menú");
        }
    });
}

//Cuando la página carga, ejecuta una función
window.addEventListener('load', function () {
    //Al carousel se le pasa primero la lista y luego las opciones que queremos para este
    if (document.querySelector('.carousel__lista')) {
        new Glider(document.querySelector('.carousel__lista'), {
            slidesToShow: 1, //Cuantos slides mostrar en el carousel
            slidesToScroll: 1, //Cuantos slides desplazarse al apretar un botón
            rewind: true, //Hace que vuelva al inicio una vez apretar siguiente en el final
            draggable: true, //Para que se pueda arrastrar con el mouse
            dragVelocity: 1,
            dots: '.carousel__indicadores', //Indicadores
            arrows: {
                prev: '.carousel__anterior',
                next: '.carousel__siguiente'
            },
            responsive: [
                {
                    //Pantalla >= 800px
                    breakpoint: 800,
                    settings: {
                        slidesToShow: 2,
                        slidesToScroll: 2
                    }
                }, {
                    //Pantalla >= 1024px
                    breakpoint: 1024,
                    settings: {
                        slidesToShow: 3,
                        slidesToScroll: 3
                    }
                }
            ]
        });
    }
});

//Habilitar/deshabilitar inputs de la página cuenta
//Username
var usernameInput = document.getElementById("usernameInput");
var nombreSi = document.getElementById("nombrePositivo");
var nombreNo = document.getElementById("nombreNegativo");
if (nombreSi) {
    nombreSi.addEventListener('click', function (e) {
        usernameInput.disabled = false;
    });
}
if (nombreNo) {
    nombreNo.addEventListener('click', function (e) {
        usernameInput.disabled = true;
    });
}

//Email
var emailInput = document.getElementById("emailInput");
var emailSi = document.getElementById("emailPositivo");
var emailNo = document.getElementById("emailNegativo");
if (emailSi) {
    emailSi.addEventListener('click', function (e) {
        emailInput.disabled = false;
    });
}
if (emailNo) {
    emailNo.addEventListener('click', function (e) {
        emailInput.disabled = true;
    });
}

//Password
var passwordInput = document.getElementById("passwordInput");
var passwordSi = document.getElementById("passwordPositivo");
var passwordNo = document.getElementById("passwordNegativo");
if (passwordSi) {
    passwordSi.addEventListener('click', function (e) {
        passwordInput.disabled = false;
    });
}
if (passwordNo) {
    passwordNo.addEventListener('click', function (e) {
        passwordInput.disabled = true;
    });
}

//Boton para cambiar credenciales
var cambiar = document.getElementById("cambiar");
if (usernameInput) {
    usernameInput.addEventListener('change', function (e) {
        cambiar.disabled = false;
    });
}
if (emailInput) {
    emailInput.addEventListener('change', function (e) {
        cambiar.disabled = false;
    });
}
if (passwordInput) {
    passwordInput.addEventListener('change', function (e) {
        cambiar.disabled = false;
    });
}

//Eliminar cuenta
var eliminar = document.getElementById("eliminar");
var eliminarSi = document.getElementById("eliminarPositivo");
var eliminarNo = document.getElementById("eliminarNegativo");
if (eliminarSi) {
    eliminarSi.addEventListener('click', function (e) {
        eliminar.disabled = false;
    });
}
if (eliminarNo) {
    eliminarNo.addEventListener('click', function (e) {
        eliminar.disabled = true;
    });
}