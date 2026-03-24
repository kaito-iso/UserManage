package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Position;
import com.example.demo.repository.PositionRepository;

import lombok.RequiredArgsConstructor;

/**
 * 役職マスタに関する業務ロジックを担当
 * @Service : コントローラーから呼び出せるようにしてロジックを担当する
 */
@Service
@RequiredArgsConstructor
public class PositionService {
	
	private final PositionRepository positionRepository;

	/**
	 * 役職を全件取得
     */
	public List<Position> findAll(){
		return positionRepository.findAll();
	}
}
