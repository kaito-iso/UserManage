package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UsersDepartments;
import com.example.demo.entity.UsersDepartmentsId;

@Repository
public interface UsersDepartmentsRepository extends JpaRepository<UsersDepartments, UsersDepartmentsId> {

	/**
	 * user_idでよる一致検索（複数部署を返す）
	 * @param userId 検索対象のユーザーID
	 */
	List<UsersDepartments> findByUserId(String userId);

	/**
	 * ユーザーIDを指定して所属部署をすべて削除
	 * @Modifying : SELECT以外のDB書き換えに必要
	 */
	@Modifying
	void deleteByUserId(String userId);
}