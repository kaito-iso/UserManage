package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー情報の検索・保存・更新といった共通の業務ロジックを担当
 * @Service : コントローラーから呼び出せるようにしてロジックを担当する
 */
@Service
@RequiredArgsConstructor
public class UsersService {

	private final UsersRepository usersRepository;

	/**
	 * ユーザー一覧を取得する（名前の曖昧検索）
	 */
	public List<Users> findUsers(String usersName) {

		// 検索条件がない場合(空文字)は全件取得
		if (usersName.isEmpty()) {
			return usersRepository.findAllWithDepartments();
		}
		return usersRepository.findUsers(usersName);
	}

	/** ユーザーIDによる一致検索 */
	public Optional<Users> findByUserId(String userId) {
		return usersRepository.findByUserId(userId);
	}

	/** メールアドレスによる一致検索 */
	public Optional<Users> findByMail(String mail) {
		return usersRepository.findByMail(mail);
	}
	
	/** ユーザーIDによるユーザー削除 */
	public void deleteByUserId(String userId) {
		usersRepository.deleteByUserId(userId);
	}
}