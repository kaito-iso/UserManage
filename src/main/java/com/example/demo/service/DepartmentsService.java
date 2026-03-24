package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Departments;
import com.example.demo.repository.DepartmentsRepository;

import lombok.RequiredArgsConstructor;

/**
 * 部署マスタに関する業務ロジックを担当
 * @Service : コントローラーから呼び出せるようにしてロジックを担当する
 */
@Service
@RequiredArgsConstructor
public class DepartmentsService {
	
	private final DepartmentsRepository departmentsRepository;
	
	/**
	 * 部署を全件取得
     */
	public List <Departments> findAll(){
		return departmentsRepository.findAll();
	}

}
