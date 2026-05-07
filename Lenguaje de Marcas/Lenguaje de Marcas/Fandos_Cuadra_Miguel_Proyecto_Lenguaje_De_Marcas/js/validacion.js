document.getElementById("formulario").addEventListener("submit", function (e) {

    e.preventDefault(); // evita que se envíe el formulario

    let nombre = document.getElementById("nombre").value.trim();
    let email = document.getElementById("email").value.trim();
    let telefono = document.getElementById("telefono").value.trim();
    let mensaje = document.getElementById("mensaje").value.trim();

    let error = document.getElementById("mensajeError");
    let ok = document.getElementById("mensajeOk");

    error.textContent = "";
    ok.textContent = "";

    // VALIDACIONES

    // Nombre obligatorio
    if (nombre === "") {
        error.textContent = "El nombre es obligatorio.";
        return;
    }

    // Email obligatorio y formato correcto
    let regexEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (email === "") {
        error.textContent = "El email es obligatorio.";
        return;
    }

    if (!regexEmail.test(email)) {
        error.textContent = "El email no tiene un formato válido.";
        return;
    }

    // Teléfono (opcional pero si se pone, válido)
    let regexTelefono = /^[0-9]{9}$/;

    if (telefono !== "" && !regexTelefono.test(telefono)) {
        error.textContent = "El teléfono debe tener 9 números.";
        return;
    }

    // Mensaje obligatorio
    if (mensaje === "") {
        error.textContent = "El mensaje no puede estar vacío.";
        return;
    }

    // Si todo está correcto
    ok.textContent = "Formulario enviado correctamente ✅";

    // Limpiar formulario
    document.getElementById("formulario").reset();
});