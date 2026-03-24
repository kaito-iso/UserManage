package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.AnnouncementCategory;
import com.example.demo.repository.AnnouncementCategoryRepository;

import lombok.RequiredArgsConstructor;

/**
 * お知らせのカテゴリーマスタに関する業務ロジックを担当
 * @Service : コントローラーから呼び出せるようにしてロジックを担当する
 */
@Service
@RequiredArgsConstructor
public class AnnouncementCategoryService {

	private final AnnouncementCategoryRepository announcementCategoryRepository;

	/**
	 * 役職を全件取得
	 */
	public List<AnnouncementCategory> findAll() {
		return announcementCategoryRepository.findAll();
	}

}
