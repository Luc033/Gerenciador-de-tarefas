// Espera o documento carregar
document.addEventListener("DOMContentLoaded", function () {
    // Procura pelo nosso toast
    var toastElement = document.getElementById('toast-sucesso')

    if (toastElement) {
        // ...espera 3 segundos (3000 ms) e o fecha.
        setTimeout(function () {
            // Usa a API do Bootstrap para fechar com fade-out
            var bsAlert = new bootstrap.Alert(toastElement);
            bsAlert.close();
        }, 3000);
    }else{
        console.log("Erro no toast")
    }
});