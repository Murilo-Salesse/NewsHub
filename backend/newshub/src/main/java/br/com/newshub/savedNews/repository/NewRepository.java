package br.com.newshub.savedNews.repository;

import br.com.newshub.savedNews.model.SavedNew;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewRepository extends JpaRepository<SavedNew, Long> {

    List<SavedNew> findByUserId(Long userId);
}
