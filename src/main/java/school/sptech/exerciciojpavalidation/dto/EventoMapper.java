package school.sptech.exerciciojpavalidation.dto;

import org.springframework.http.ResponseEntity;
import school.sptech.exerciciojpavalidation.entity.Evento;

import java.util.List;

public class EventoMapper {

    public static Evento toEntity(EventoCriacaoDto dto){
        if(dto == null) return null;

        Evento novoEvento = new Evento();
        novoEvento.setNome(dto.getNome());
        novoEvento.setLocal(dto.getLocal());
        novoEvento.setDataEvento(dto.getDataEvento());
        novoEvento.setGratuito(dto.isGratuito());


        return novoEvento;
    }


    public static EventoListarDto listar(Evento entidade){
        if(entidade == null) return null;

        EventoListarDto listarEvento = new EventoListarDto();

        listarEvento.setId(entidade.getId());
        listarEvento.setNome(entidade.getNome());
        listarEvento.setLocal(entidade.getLocal());
        listarEvento.setDataEvento(entidade.getDataEvento());
        listarEvento.setGratuito(entidade.isGratuito());
        listarEvento.setCancelado(entidade.isCancelado());

        return listarEvento;
    }


    public static EventoListarDto atualizar(Evento entidade){
        if(entidade == null) return null;

        EventoListarDto eventoListarDto = new EventoListarDto();

        eventoListarDto.setId(entidade.getId());
        eventoListarDto.setNome(entidade.getNome());
        eventoListarDto.setLocal(entidade.getLocal());
        eventoListarDto.setDataEvento(entidade.getDataEvento());
        eventoListarDto.setGratuito(entidade.isGratuito());
        eventoListarDto.setCancelado(entidade.isCancelado());

        return eventoListarDto;
    }

    public static List<EventoListarDto> listarTodos(List<Evento> entidades){
        return entidades.stream().map(EventoMapper::listar).toList();
    }
}
