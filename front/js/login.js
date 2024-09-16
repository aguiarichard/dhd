document.querySelector('button').addEventListener('click', function (event) {
    event.preventDefault(); // Impede o envio normal do formulário

    const username = document.querySelector('input[type="text"]').value;
    const password = document.querySelector('input[type="password"]').value;

    fetch('http://localhost:8080/login', {
        method: 'POST',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'X-Requested-With': 'XMLHttpRequest'
        },
        body: new URLSearchParams({
            'username': username,
            'password': password
        }),
        credentials: 'include' // Inclui cookies com a requisição
    }).then(response => {
        if (response.ok) {
            window.location.href = "/home";
        } else {
            return response.text().then(text => {
                alert(`Falha no login. Verifique suas credenciais. Mensagem: ${text}`);
            });
        }
    }).catch(error => {
        console.error('Erro:', error);
    });
});