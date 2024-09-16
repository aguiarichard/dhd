document.addEventListener('DOMContentLoaded', () => {
    const selectTrigger = document.querySelector('#custom-select-trigger');
    const selectTriggerDipHis = document.querySelector("#custom-select-trigger-dip-his")
    const selectTriggerSituacao = document.querySelector('#custom-select-trigger-situacao');
    const optionsContainer = document.querySelector('#custom-options-nome');
    const optionsContainerDipHis = document.querySelector('#custom-options-dip-his')
    const optionsContainerSituacao = document.querySelector('#custom-options-situacao');
    const input = document.querySelector('#input-search');
    const nameSelected = document.querySelector('#name-selected')
    const liSituacao = document.querySelectorAll('.li-situacao')
    const liDipHis = document.querySelectorAll('.li-dip-his')
    let page = 0;
    let debounceTimer;

    selectTrigger.addEventListener('click', () => {    
        if (optionsContainer.style.opacity === '' || optionsContainer.style.opacity === '0') {
            optionsContainer.style.opacity = '1'
            optionsContainer.style.pointerEvents = 'initial'

            optionsContainerSituacao.style.opacity = '0'
            optionsContainerSituacao.style.pointerEvents = 'none'

            optionsContainerDipHis.style.opacity = '0'
            optionsContainerDipHis.style.pointerEvents = 'none'
            return
        } 

        optionsContainer.style.opacity = '0'
        optionsContainer.style.pointerEvents = 'none'
    });

    selectTriggerDipHis.addEventListener('click', () => {
        if (optionsContainerDipHis.style.opacity === '' || optionsContainerDipHis.style.opacity === '0') {
            optionsContainerDipHis.style.opacity = '1'
            optionsContainerDipHis.style.pointerEvents = 'initial'

            optionsContainer.style.opacity = '0'
            optionsContainer.style.pointerEvents = 'none'

            optionsContainerSituacao.style.opacity = '0'
            optionsContainerSituacao.style.pointerEvents = 'none'
            return
        } 

        optionsContainerDipHis.style.opacity = '0'
        optionsContainerDipHis.style.pointerEvents = 'none'
    });

    selectTriggerSituacao.addEventListener('click', () => {
        if (optionsContainerSituacao.style.opacity === '' || optionsContainerSituacao.style.opacity === '0') {
            optionsContainerSituacao.style.opacity = '1'
            optionsContainerSituacao.style.pointerEvents = 'initial'

            optionsContainer.style.opacity = '0'
            optionsContainer.style.pointerEvents = 'none'

            optionsContainerDipHis.style.opacity = '0'
            optionsContainerDipHis.style.pointerEvents = 'none'
            return
        } 

        optionsContainerSituacao.style.opacity = '0'
        optionsContainerSituacao.style.pointerEvents = 'none'
    });


    liSituacao.forEach(li => {

        li.addEventListener('click', () => {
            selectTriggerSituacao.children[0].textContent = li.innerHTML;
            optionsContainerSituacao.style.opacity = '0';
            optionsContainerSituacao.style.pointerEvents = 'none';
        });
    })

    liDipHis.forEach(li => {

        li.addEventListener('click', () => {
            selectTriggerDipHis.children[0].textContent = li.innerHTML;
            optionsContainerDipHis.style.opacity = '0';
            optionsContainerDipHis.style.pointerEvents = 'none';
        });
    })



    async function loadAlunos(searchTerm, page) {


        const url = searchTerm
        ? `http://localhost:8080/alunos?nome=${encodeURIComponent(searchTerm)}&page=${page}&size=10`
        : `http://localhost:8080/alunos?page=${page}&size=10`;
        
        try {
            const response = await fetch(url);
            const data = await response.json();
            
            if (page === 0) {
                optionsContainer.innerHTML = '';
            }
            
            data.content.forEach(item => {
                nameSelected.innerHTML = 'Escolher Aluno'
                const optionElement = document.createElement('li');
                optionElement.textContent = item;
                optionElement.addEventListener('click', () => {
                    selectTrigger.querySelector('span').textContent = item;
                    optionsContainer.style.opacity = '0';
                    optionsContainer.style.pointerEvents = 'none';
                });
                optionsContainer.appendChild(optionElement);
            });

            if (!searchTerm == '') {
                optionsContainer.style.opacity = '1'
                optionsContainer.style.pointerEvents = 'initial';
            }

        } catch (error) {
            console.error('Erro ao buscar os dados:', error);
        }
    }

    function debounce(func, delay) {
        return function (...args) {
            clearTimeout(debounceTimer);
            debounceTimer = setTimeout(() => func.apply(this, args), delay);
        };
    }

    const debouncedLoadAlunos = debounce((searchTerm) => {
        page = 0;
        loadAlunos(searchTerm, page);
    }, 300);

    optionsContainer.addEventListener('scroll', () => {
        if (optionsContainer.scrollTop + optionsContainer.clientHeight >= optionsContainer.scrollHeight) {
            page += 1;
            loadAlunos(input.value, page);
        }
    });

    input.addEventListener('input', function () {
        const searchTerm = input.value;
        debouncedLoadAlunos(searchTerm);
    });


    const finalizarButton = document.getElementById('finalizar-historico');

    finalizarButton.addEventListener('click', async () => {
        let diplomaHistorico = selectTriggerDipHis.children[0].innerHTML
        let ishistorico;

        if (diplomaHistorico === 'Diploma') {
            ishistorico = false
        } else {
            ishistorico = true
        }

        let situacao = selectTriggerSituacao.querySelector('span').innerHTML

        if (situacao === 'Escolher Situação') return

        if (situacao === 'Pronto para acervo') {
            situacao = 3
            console.log('entrou 3');

        }

        if (situacao === 'Finalizar') {
            situacao = 4
            console.log('entrou 4');

        }

        console.log(selectTriggerSituacao.querySelector('span'));


        const nomeSelecionado = selectTrigger.querySelector('span').textContent;

        const nomeFiltrado = nomeSelecionado.split('-', 1)[0].trim()

        if (nomeFiltrado && nomeFiltrado !== 'Escolher Aluno') {

            if (ishistorico) {

                try {
                    const response = await fetch(`http://localhost:8080/atualizar-historico?nome=${encodeURIComponent(nomeFiltrado)}&situacao=${situacao}`, {
                        method: 'POST'
                    });

                    if (response.ok) {
                        if (situacao === 3) {
                            alert(`Histórico pronto para acervo: ${nomeFiltrado}`)
                            return
                        }

                        alert(`Histórico finalizado para: ${nomeFiltrado}`);
                    } else {
                        if (situacao === 3) {
                            alert(`Erro ao deixar histórico pronto para acervo: ${nomeFiltrado}`)
                            return
                        }

                        alert('Erro ao finalizar o histórico');
                    }
                } catch (error) {
                    console.error('Erro na requisição:', error);
                    alert('Erro ao finalizar o histórico');
                }

                return
            }

            try {
                const response = await fetch(`http://localhost:8080/atualizar-diploma?nome=${encodeURIComponent(nomeFiltrado)}&situacao=${situacao}`, {
                    method: 'POST'
                });

                if (response.ok) {
                    if (situacao === 3) {
                        alert(`Diploma pronto para acervo: ${nomeFiltrado}`)
                        return
                    }

                    alert(`Diploma finalizado para: ${nomeFiltrado}`);
                } else {
                    if (situacao === 3) {
                        alert(`Erro ao deixar Diploma pronto para acervo: ${nomeFiltrado}`)
                        return
                    }

                    alert('Erro ao finalizar o Diploma');
                }
            } catch (error) {
                console.error('Erro na requisição:', error);
                alert('Erro ao finalizar o Diploma');
            }


        } else {
            alert('Selecione um nome primeiro.');
        }
    });

    loadAlunos('', page);
});