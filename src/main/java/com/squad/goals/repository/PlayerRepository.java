package com.squad.goals.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.squad.goals.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long>, PlayerRepositoryCustom {
}
