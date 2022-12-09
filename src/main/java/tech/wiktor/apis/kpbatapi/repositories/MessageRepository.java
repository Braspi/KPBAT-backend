package tech.wiktor.apis.kpbatapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import tech.wiktor.apis.kpbatapi.models.Message;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Optional<Message> findById(Long id);

    @Query("Select p From messages p")
    List<Message> findAllMessages(Pageable page);
}