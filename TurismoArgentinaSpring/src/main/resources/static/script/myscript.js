const navToggle = document.querySelector(".nav__toggle");
const navMenu = document.querySelector(".nav__menu");

navToggle.addEventListener("click", function () {
    navMenu.classList.toggle("nav__menu__visible");
    
    if(navMenu.classList.contains("nav__menu__visible")) {
        navToggle.setAttribute("aria-label", "Cerrar menú");
    } else {
        navToggle.setAttribute("aria-label", "Abrir menú");
    }
});

//Cuando la página carga, ejecuta una función
window.addEventListener('load', function () {
    //Al carousel se le pasa primero la lista y luego las opciones que queremos para este
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
});