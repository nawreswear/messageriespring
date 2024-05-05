package com.messageriespring.repository;

import com.messageriespring.model.Signalement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
public interface SignalementRepository extends JpaRepository<Signalement, Long> {
  //  List<Signalement> findByEnchereId(Long _Id);

  @Query("SELECT s FROM Signalement s JOIN FETCH s.user u JOIN FETCH u.messages")
  List<Signalement> findAllWithUsersAndMessages();

  @Query("SELECT s FROM Signalement s LEFT JOIN FETCH s.user")
  List<Signalement> findAllWithUsers();
}
