package com.pedrodantas1.gamelist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pedrodantas1.gamelist.dto.GameListDTO;
import com.pedrodantas1.gamelist.entities.GameList;
import com.pedrodantas1.gamelist.projections.GameMinProjection;
import com.pedrodantas1.gamelist.repositories.GameListRepository;
import com.pedrodantas1.gamelist.repositories.GameRepository;

@Service
public class GameListService {
	
	@Autowired
	private GameListRepository gameListRepository;
	
	@Autowired
	private GameRepository gameRepository;

	@Transactional(readOnly = true)
	public List<GameListDTO> findAll() {
		List<GameList> result = gameListRepository.findAll();
		return result.stream().map(x -> new GameListDTO(x)).toList();
	}
	
	@Transactional
	public void move(Long listId, int sourceIndex, int destinationIndex) {
		List<GameMinProjection> list = gameRepository.searchByList(listId);
		GameMinProjection obj = list.remove(sourceIndex);
		list.add(destinationIndex, obj);
		
		//Atualiza no banco
		int minValue = Integer.min(sourceIndex, destinationIndex);
		int maxValue = Integer.max(sourceIndex, destinationIndex);
		
		for (int i = minValue; i <= maxValue; i++) {
			gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
		}
	}
}
