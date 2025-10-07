document.addEventListener('DOMContentLoaded', () => {
  const apiUrl = 'http://localhost:8080/escola';
  const tbody = document.getElementById('escola-tbody');
  const form = document.getElementById('escola-form');
  const escolaId = document.getElementById('escola-id');
  const nomeInput = document.getElementById('nome');
  const emailInput = document.getElementById('email');
  const telefoneInput = document.getElementById('telefone');
  const cnpjInput = document.getElementById('cnpj');
  const componentesInput = document.getElementById('componentes');
  const turmasInput = document.getElementById('turmas');
  const statusAlunoInput = document.getElementById('statusAluno');

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
            <td>${escola.cnpj || ''}</td>
            <td>
              <button class="btn btn-warning btn-sm me-2"
                onclick="editarEscola(${escola.id}, '${escola.nome}', '${escola.email}', '${escola.telefone}', '${escola.cnpj || ''}', '${escola.componentes || ''}', '${escola.turmas || ''}', ${escola.statusAluno})">
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

  form.addEventListener('submit', (e) => {
    e.preventDefault();

    const escola = {
      nome: nomeInput.value,
      email: emailInput.value,
      telefone: telefoneInput.value,
      cnpj: cnpjInput.value,
      componentes: componentesInput.value,
      turmas: turmasInput.value,
      statusAluno: statusAlunoInput.value === 'true'
    };

    if (escolaId.value) {
      fetch(`${apiUrl}/${escolaId.value}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(escola)
      })
      .then(() => {
        alert('Escola atualizada com sucesso!');
        form.reset();
        escolaId.value = '';
        carregarEscolas();
      });
    } else {
      fetch(apiUrl, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(escola)
      })
      .then(() => {
        alert('Escola cadastrada com sucesso!');
        form.reset();
        carregarEscolas();
      });
    }
  });

  window.editarEscola = (id, nome, email, telefone, cnpj, componentes, turmas, statusAluno) => {
    escolaId.value = id;
    nomeInput.value = nome;
    emailInput.value = email;
    telefoneInput.value = telefone;
    cnpjInput.value = cnpj;
    componentesInput.value = componentes;
    turmasInput.value = turmas;
    statusAlunoInput.value = statusAluno;
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

  carregarEscolas();
});