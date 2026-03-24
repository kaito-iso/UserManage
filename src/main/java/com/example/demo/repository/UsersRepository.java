package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Users;

/**
 * ユーザー情報の基本CRUDおよび複雑なクエリを担当するリポジトリ
 * @Repository : DB操作（データの保存、検索、削除など）を担当する
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

	/**
	 * JPQL（Java Persistence Query Language）による曖昧検索
	 * @Query: SQLに似た構文でエンティティを操作する独自クエリを記述
	 * @Param: メソッド引数をクエリ内のプレースホルダ（:name）に紐付ける
	 */
	@Query("""
			SELECT DISTINCT u FROM Users u
			LEFT JOIN FETCH u.userDepartments ud
			LEFT JOIN FETCH ud.department d
			WHERE u.familyName LIKE %:name%
			   OR u.givenName LIKE %:name%
			   OR u.familyNameKana LIKE %:name%
			   OR u.givenNameKana LIKE %:name%
			   OR d.deptName LIKE %:name%
			""")
	List<Users> findUsers(@Param("name") String name);

	// 全件取得時
	@Query("""
			SELECT DISTINCT u FROM Users u
			LEFT JOIN FETCH u.userDepartments ud
			LEFT JOIN FETCH ud.department d
			""")
	List<Users> findAllWithDepartments();

	/**
	 * 最終ログイン日時の更新
	 * @Transactional: 一連の処理をトランザクションとして、失敗時にロールバック可能にする
	 * @Modifying: SELECT以外の操作（INSERT, UPDATE, DELETE）であることをSpringに明示
	 */
	@Transactional
	@Modifying
	@Query("UPDATE Users u SET u.lastLoginDate = :date WHERE u.userId = :userId")
	void updateLastLoginDate(@Param("userId") String userId, @Param("date") LocalDateTime date);
	
	/**
	 * user_idによる一致検索
	 * @param userId 検索対象のユーザーID
	 * @return 見つからない可能性を場合は、Null安全なOptional型で返却
	 */
	@Query("""
			SELECT DISTINCT u FROM Users u
			LEFT JOIN FETCH u.userDepartments ud
			LEFT JOIN FETCH ud.department d
			WHERE u.userId = :userId
			ORDER BY ud.isMain DESC, d.displayOrder ASC
			""")
	Optional<Users> findByUserId(@Param("userId") String userId);
	
	/**
	 * メールアドレスによる一致検索
	 * @param mail 検索対処のメールアドレス
	 * @return 見つからない可能性を場合は、Null安全なOptional型で返却
	 */
	Optional<Users> findByMail(String mail);
	
	/**
	 * 
	 */
	@Transactional
	@Modifying
	@Query("DELETE Users WHERE userId = :userId")
	void deleteByUserId(@Param("userId") String userId);

}