package school.sptech.exerciciojpavalidation.controller;

import jakarta.validation.Valid;
import jdk.jfr.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.exerciciojpavalidation.dto.EventoCriacaoDto;
import school.sptech.exerciciojpavalidation.dto.EventoListarDto;
import school.sptech.exerciciojpavalidation.dto.EventoMapper;
import school.sptech.exerciciojpavalidation.dto.EventoPutDto;
import school.sptech.exerciciojpavalidation.entity.Evento;
import school.sptech.exerciciojpavalidation.repository.EventoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/eventos")
public class EventoController {
    @Autowired
    private EventoRepository eventoRepository;

    @PostMapping
    public ResponseEntity criar(@RequestBody @Valid EventoCriacaoDto novoEvento){

        Evento evento = EventoMapper.toEntity(novoEvento);

        Evento eventoSalvo = eventoRepository.save(evento);

        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<EventoListarDto>> listar(){
        List<Evento> eventos = eventoRepository.findAll();

        if(eventos.isEmpty()) return ResponseEntity.status(204).build();

        List<EventoListarDto> eventosDto = EventoMapper.listarTodos(eventos);

        return ResponseEntity.status(200).body(eventosDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoListarDto> listarPorId(@PathVariable int id){
        Optional<Evento> evento = eventoRepository.findById(id);
        if(evento.isEmpty()) return ResponseEntity.status(404).build();

        EventoListarDto eventoListarDto = EventoMapper.listar(evento.get());

        return ResponseEntity.status(200).body(eventoListarDto);

    }

    @GetMapping("/gratuitos")
    public ResponseEntity<List<EventoListarDto>> listarGratuitos() {
        List<Evento> eventosGratuitos = eventoRepository.findByGratuitoIsTrue();

        if (eventosGratuitos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<EventoListarDto> listarDtos = EventoMapper.listarTodos(eventosGratuitos);

        return ResponseEntity.status(200).body(listarDtos);
    }

    @GetMapping("/ocorrencia")
    public ResponseEntity<List<Evento>> listarPorDataOcorrencia(@RequestParam LocalDate data) {
        List<Evento> eventos = eventoRepository.findByDataEventoIs(data);

        if (eventos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(eventos);
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<EventoListarDto>> listarEntreDatas(@RequestParam LocalDate inicio, @RequestParam LocalDate fim){
        List<Evento> eventos = eventoRepository.findByDataEventoIsGreaterThanEqualAndDataEventoIsLessThanEqual(inicio,fim);
        if(eventos.isEmpty()) return ResponseEntity.status(204).build();

        List<EventoListarDto> eventoListarDtos = EventoMapper.listarTodos(eventos);

        return ResponseEntity.status(200).body(eventoListarDtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable int id){
        if(eventoRepository.existsById(id)){
            eventoRepository.deleteById(id);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

//
    @PutMapping("/{id}")
    public ResponseEntity<Evento> atualizar(@PathVariable int id, @RequestBody @Valid Evento novoEvento){
        if(eventoRepository.existsById(id)){
            Evento evento = eventoRepository.save(novoEvento);
            return ResponseEntity.status(200).body(evento);
        }
        return ResponseEntity.status(404).build();
    }

    @PutMapping("/{id}/cancelamento")
    public ResponseEntity<Evento> cancelamento(@PathVariable int id){
        if(eventoRepository.existsById(id)){
            Evento evento = eventoRepository.findByIdEqualsAndCanceladoIsTrue(id);
            if(evento != null){
                if(!evento.getDataEvento().isAfter(LocalDate.now())){
                    evento.setCancelado(true);
                    Evento eventoSalvo = eventoRepository.save(evento);
                    return ResponseEntity.status(200).body(eventoSalvo);
                }
            }
        }
        return ResponseEntity.status(404).build();
    }


}
