package com.spitoring.domain.spitto.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SpittoStockRepository extends JpaRepository<SpittoStock, Long> {

    List<SpittoStock> findAllByOrderBySpittoTypeAscDrawDesc();

    List<SpittoStock> findAllByInsertedAtBetweenOrderBySpittoTypeAscDrawDesc(LocalDateTime start, LocalDateTime end);
}
