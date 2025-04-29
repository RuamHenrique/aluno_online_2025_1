package br.com.alunoonline.api.service;

import br.com.alunoonline.api.model.Aluno;
import br.com.alunoonline.api.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    public void criarAluno(Aluno aluno) {

        alunoRepository.save(aluno);

    }

    public List<Aluno> listarTodosAlunos() {
        return alunoRepository.findAll();

    }

    public Optional<Aluno> buscarAlunoPorId(Long id) {
        return alunoRepository.findById(id);
    }

    public void deletarAlunoPorId(Long id) {
        alunoRepository.deleteById(id);
    }

    public void atualizarAlunoPorId(Long id, Aluno aluno){
        //primeiro passo: ver se o aluno existe no BD
        Optional<Aluno> alunoDoBancodeDados = buscarAlunoPorId(id);

        // e se nao existir esse aluno?
        if (alunoDoBancodeDados.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Aluno nao encontrado no Banco de dados");

        }
        // se chegar aqui, siginfica que existe com esse id
        // vou armazenar em uma variavel par edita-lo

        Aluno alunoParaEditar = alunoDoBancodeDados.get();

        //com esse aluno para ser editado acima, faço os sets necessarios para atualizar ps atributos dele

        alunoParaEditar.setNome(aluno.getNome());
        alunoParaEditar.setCpf(aluno.getCpf());
        alunoParaEditar.setEmail(aluno.getEmail());

        //com aluno total editado acima eu devolvo ele para atualizado para BD

        alunoRepository.save(alunoParaEditar);

    }


}
