document.addEventListener('DOMContentLoaded', () => {
    fetch('http://localhost:8080/alunos')
      .then(response => response.json())
      .then(data => {
        const tbody = document.getElementById('professor-tbody');
        data.forEach(professor => {
          const tr = document.createElement('tr');
  
          tr.innerHTML = `
            <td>${professor.nome}</td>
            <td>${professor.email}</td>
            <td>${professor.telefone}</td>
          `;
  
          tbody.appendChild(tr);
        });
      })
      .catch(error => {
        console.error('Erro ao buscar professores:', error);
        alert('Erro ao carregar a lista de professores.');
      });
  });