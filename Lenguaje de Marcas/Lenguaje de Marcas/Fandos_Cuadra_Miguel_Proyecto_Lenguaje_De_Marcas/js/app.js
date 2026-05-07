let datos = [];
let actividadesMostradas = [];
let paginaActual = 1;
const tarjetasPorPagina = 4;

fetch("json/salas_actividades.json")
    .then(res => res.json())
    .then(data => {
        datos = data.salas || [];
        actividadesMostradas = obtenerTodasLasActividades(datos);
        mostrarPaginadas(actividadesMostradas);
    })
    .catch(error => console.log("Error al cargar JSON:", error));

function obtenerTodasLasActividades(salas) {
    let lista = [];

    salas.forEach(sala => {
        let actividades = sala.actividadesProgramadas || [];

        actividades.forEach(act => {
            lista.push({
                sala: sala.nombre,
                capacidad: sala.capacidad,
                nombreActividad: act.nombreActividad,
                horario: act.horario,
                entrenador: act.entrenador || "No disponible",
                plazasLibres: act.plazasLibres || "No disponible"
            });
        });
    });

    return lista;
}

function mostrarPaginadas(lista) {
    let contenedor = document.getElementById("contenedor");
    contenedor.innerHTML = "";

    let inicio = (paginaActual - 1) * tarjetasPorPagina;
    let fin = inicio + tarjetasPorPagina;

    let pagina = lista.slice(inicio, fin);

    pagina.forEach(act => {
        let tarjeta = document.createElement("div");
        tarjeta.className = "tarjeta";

        tarjeta.innerHTML = `
            <h3>${act.nombreActividad}</h3>
            <p><strong>Sala:</strong> ${act.sala}</p>
            <p><strong>Fecha:</strong> ${act.horario}</p>
            <p><strong>Entrenador:</strong> ${act.entrenador}</p>
            <p><strong>Capacidad:</strong> ${act.capacidad}</p>
            <p><strong>Plazas libres:</strong> ${act.plazasLibres}</p>
        `;

        contenedor.appendChild(tarjeta);
    });

    actualizarPaginacion(lista.length);
}

function actualizarPaginacion(total) {
    let paginacion = document.getElementById("paginacion");
    paginacion.innerHTML = "";

    let totalPaginas = Math.ceil(total / tarjetasPorPagina);

    let btnAnterior = document.createElement("button");
    btnAnterior.textContent = "Anterior";
    btnAnterior.disabled = paginaActual === 1;
    btnAnterior.onclick = function () {
        paginaActual--;
        mostrarPaginadas(actividadesMostradas);
    };

    let btnSiguiente = document.createElement("button");
    btnSiguiente.textContent = "Siguiente";
    btnSiguiente.disabled = paginaActual === totalPaginas || totalPaginas === 0;
    btnSiguiente.onclick = function () {
        paginaActual++;
        mostrarPaginadas(actividadesMostradas);
    };

    let info = document.createElement("span");
    info.textContent = ` Página ${paginaActual} de ${totalPaginas || 1} `;

    paginacion.appendChild(btnAnterior);
    paginacion.appendChild(info);
    paginacion.appendChild(btnSiguiente);
}

function filtrar() {
    let salaFiltro = document.getElementById("filtroSala").value.toLowerCase();
    let fechaFiltro = document.getElementById("filtroFecha").value.toLowerCase();

    let todas = obtenerTodasLasActividades(datos);

    actividadesMostradas = todas.filter(act =>
        act.sala.toLowerCase().includes(salaFiltro) &&
        act.horario.toLowerCase().includes(fechaFiltro)
    );

    paginaActual = 1;
    mostrarPaginadas(actividadesMostradas);
}