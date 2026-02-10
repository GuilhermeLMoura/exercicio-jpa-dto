package school.sptech.exerciciojpavalidation.repository;

import jdk.jfr.Timestamp;
import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.exerciciojpavalidation.entity.Evento;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EventoRepository extends JpaRepository<Evento,Integer> {
    List<Evento> findByGratuitoIsTrue();
//
    List<Evento> findByDataEventoIs(LocalDate data);
    List<Evento> findByDataEventoIsGreaterThanEqualAndDataEventoIsLessThanEqual(LocalDate inicio, LocalDate fim);

    Evento findByIdEqualsAndCanceladoIsTrue(int id);
}
