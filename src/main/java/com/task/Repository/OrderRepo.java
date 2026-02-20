package com.task.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.Entity.Consumer;
import com.task.Entity.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long>{

	List<Order> findByConsumer(Consumer consumer);
}
