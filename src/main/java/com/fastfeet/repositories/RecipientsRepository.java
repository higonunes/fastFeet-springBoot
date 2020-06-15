package com.fastfeet.repositories;

import com.fastfeet.domain.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipientsRepository extends JpaRepository<Recipient, Long> {
    List<Recipient> findAllByUserId(Integer userId);
}
