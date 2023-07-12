package com.pedrodantas1.gamelist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedrodantas1.gamelist.entities.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

}
