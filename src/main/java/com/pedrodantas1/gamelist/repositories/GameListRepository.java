package com.pedrodantas1.gamelist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedrodantas1.gamelist.entities.GameList;

public interface GameListRepository extends JpaRepository<GameList, Long> {

}
