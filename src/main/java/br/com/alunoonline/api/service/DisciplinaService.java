package br.com.alunoonline.api.service;


import br.com.alunoonline.api.model.Aluno;
import br.com.alunoonline.api.model.Disciplina;
import br.com.alunoonline.api.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {


    @Autowired
    DisciplinaRepository disciplinaRepository;

    public void criarDisciplina(Disciplina disciplina) {
        disciplinaRepository.save(disciplina);


    }

    public void deletarDisciplinaPorId(Long id) {
        disciplinaRepository.deleteById(id);
    }

    public Optional<Disciplina> buscarDisciplinaPorId(Long id) {
        return disciplinaRepository.findById(id);
    }

    public List<Disciplina> listarTodasDisciplinas() {
        return disciplinaRepository.findAll();
    }

    public void atualizarDisciplinaPorId(Long id, Disciplina disciplina) {
        //primeiro passo: ver se a disciplina existe no BD
        Optional<Disciplina> disciplinaDoBancodeDados = buscarDisciplinaPorId(id);

        // e se nao existir essa disciplina?
        if (disciplinaDoBancodeDados.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Disciplina nao encontrada no Banco de dados");
        }
        // se chegar aqui, siginfica que existe com esse id
        // vou armazenar em uma variavel par edita-lo

        Disciplina disciplinaParaEditar = disciplinaDoBancodeDados.get();
        disciplinaParaEditar.setNome(disciplina.getNome());

        disciplinaRepository.save(disciplinaParaEditar);
    }


}
