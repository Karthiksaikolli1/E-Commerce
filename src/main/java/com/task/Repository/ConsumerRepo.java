package com.task.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.Entity.Consumer;

@Repository
public interface ConsumerRepo extends JpaRepository<Consumer, Long>{

	Optional<Consumer> findByUsername(String username);
}

