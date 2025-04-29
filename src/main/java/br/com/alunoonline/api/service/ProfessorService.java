package br.com.alunoonline.api.service;


import br.com.alunoonline.api.model.Aluno;
import br.com.alunoonline.api.model.Professor;
import br.com.alunoonline.api.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;
    private Professor professor;

    public void criarProfessor(Professor professor) {
        professorRepository.save(professor);


    }

    public List<Professor> listarTodosProfessores() {
        return professorRepository.findAll();

    }

    public Optional<Professor> buscarProfessorPorId(Long id) {
        return professorRepository.findById(id);
    }

    public void deletarProfessorPorId(Long id) {
        professorRepository.deleteById(id);
    }

    public void atualizarProfessorPorId(Long id, Professor professor) {
        this.professor = professor;
        //primeiro passo: ver se o professor existe no BD
        Optional<Professor> professorDoBancodeDados = buscarProfessorPorId(id);

        // e se nao existir esse professor?
        if (professorDoBancodeDados.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Professor nao encontrado no Banco de dados");

        }

    }
}