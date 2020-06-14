package com.fastfeet.repositories;

import com.fastfeet.domain.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipientsRepository extends JpaRepository<Recipient, Long> {

}
