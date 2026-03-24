package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Announcements;
import com.example.demo.form.AnnouncementsForm;
import com.example.demo.repository.AnnouncementsRepository;

import lombok.RequiredArgsConstructor;

/**
 * お知らせの作成&変更画面に関する業務ロジックを担当
 * @Service : コントローラーから呼び出せるようにしてロジックを担当する
 */
@Service
@RequiredArgsConstructor
public class AnnouncementsService {

	private final AnnouncementsRepository announcementsRepository;

	/**
	 * お知らせを全件取得
	 */
	public List<Announcements> findAll() {
		return announcementsRepository.findAllWithCategory();
	}

	/**
	 * 公開範囲日時のお知らせ取得
	 */
	public List<Announcements> publicFindAll() {
		return announcementsRepository.publicFindAll();
	}

	/**
	 * お知らせ1件取得
	 */
	public Announcements findById(Integer id) {
		return announcementsRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("指定されたお知らせ(ID:" + id + ")は見つかりませんでした。"));
	}

	/**
	 * お知らせ削除
	 */
	public void deleteById(Integer id) {
		announcementsRepository.deleteById(id);
	}

	@Transactional
	public void save(String userId, AnnouncementsForm form) {

		Announcements announcements;

		if (form.getId() != null) {

			// 既存データがあれば取得
			announcements = announcementsRepository.findById(form.getId())
					.orElseThrow(() -> new RuntimeException("該当のお知らせが見つかりません"));
		} else {

			// IDがnullの場合は新規登録
			announcements = new Announcements();

			announcements.setAddDate(LocalDateTime.now());
			announcements.setCreateUserId(userId);
		}

		announcements.setTitle(form.getTitle());
		announcements.setContent(form.getContent());
		announcements.setCategoryCd(form.getCategoryCd());
		announcements.setPublishDate(form.getPublishDate());
		announcements.setExpiresDate(form.getExpiresDate());
		announcements.setUpdDate(LocalDateTime.now());

		// DBに反映
		announcementsRepository.save(announcements);

	}

}
