document.addEventListener('DOMContentLoaded', () => {
  const apiUrl = 'http://localhost:8080/escola';
  const tbody = document.getElementById('escola-tbody');
  const form = document.getElementById('escola-form');
  const alunoId = document.getElementById('escola-id');
  const nomeInput = document.getElementById('nome');
  const emailInput = document.getElementById('email');
  const telefoneInput = document.getElementById('telefone');

  // Função para carregar escolas
  function carregarEscolas() {
    fetch(apiUrl)
      .then(res => res.json())
      .then(data => {
        tbody.innerHTML = '';
        data.forEach(escola => {
          const tr = document.createElement('tr');
          tr.innerHTML = `
            <td>${escola.nome}</td>
            <td>${escola.email}</td>
            <td>${escola.telefone}</td>
            <td>
              <button class="btn btn-warning btn-sm me-2"
                onclick="editarEscola(${escola.id}, '${escola.nome}', '${escola.email}', '${escola.telefone}')">
                <i class="fa-solid fa-pen-to-square"></i>
              </button>
              <button class="btn btn-danger btn-sm" onclick="excluirEscola(${escola.id})">
                <i class="fa-solid fa-trash"></i>
              </button>
            </td>
          `;
          tbody.appendChild(tr);
        });
      })
      .catch(err => {
        console.error('Erro ao buscar escolas:', err);
        alert('Erro ao carregar escolas.');
      });
  }

  // Função salvar (cadastrar ou atualizar)
  form.addEventListener('submit', (e) => {
    e.preventDefault();

    const escola = {
      nome: nomeInput.value,
      email: emailInput.value,
      telefone: telefoneInput.value
    };

    if (escolaId.value) {
      // Atualizar
      fetch(`${apiUrl}/${escolaId.value}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(escola)
      })
      .then(() => {
        alert('escola atualizado com sucesso!');
        form.reset();
        alunoId.value = '';
        carregarEscolas();
      });
    } else {
      // Criar
      fetch(apiUrl, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(escola)
      })
      .then(() => {
        alert('Escola cadastrado com sucesso!');
        form.reset();
        carregarEscolas();
      });
    }
  });

  // Expor funções globais
  window.editarEscola = (id, nome, email, telefone) => {
    escolaId.value = id;
    nomeInput.value = nome;
    emailInput.value = email;
    telefoneInput.value = telefone;
  };

  window.excluirEscola = (id) => {
    if (confirm('Deseja excluir esta escola?')) {
      fetch(`${apiUrl}/${id}`, { method: 'DELETE' })
        .then(() => {
          alert('Escola excluída!');
          carregarEscolas();
        });
    }
  };

  // Inicializar
  carregarEscolas();
});