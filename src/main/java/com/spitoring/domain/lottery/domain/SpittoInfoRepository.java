package com.spitoring.domain.lottery.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpittoInfoRepository extends JpaRepository<SpittoInfo, Long> {

    List<SpittoInfo> findAllByOrderByGameIdAsc();

    Optional<SpittoInfo> findByGameId(Integer gameId);
}
