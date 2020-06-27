package com.fastfeet.repositories;

import com.fastfeet.domain.Creator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CreatorRepository extends JpaRepository<Creator, Integer> {

    @Transactional(readOnly = true)
    Creator findByemail(String email);

}
