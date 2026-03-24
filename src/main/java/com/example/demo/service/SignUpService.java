package com.example.demo.service;

import java.time.LocalDateTime;

import jakarta.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.entity.UsersDepartments;
import com.example.demo.form.SignUpForm;
import com.example.demo.repository.UsersDepartmentsRepository;
import com.example.demo.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー情報新規登録画面に関する業務ロジックを担当
 * @Service : コントローラーから呼び出せるようにしてロジックを担当する
 */
@Service
@RequiredArgsConstructor
public class SignUpService {

	private final UsersRepository usersRepository;
	private final PasswordEncoder passwordEncoder;
	private final UsersDepartmentsRepository usersDepartmentsRepository;

	/**
	 * ユーザー情報の登録ロジック
	 * @Transactional : トランザクション
	 */
	@Transactional
	public void save(SignUpForm userForm) {

		// ユーザーをインスタンス生成
		Users user = new Users();

		// UserFormをUsersにセット
		user.setUserId(userForm.getUserId());
		user.setRole(userForm.getRole());
		user.setFamilyName(userForm.getFamilyName());
		user.setGivenName(userForm.getGivenName());
		user.setFamilyNameKana(userForm.getFamilyNameKana());
		user.setGivenNameKana(userForm.getGivenNameKana());
		user.setMail(userForm.getMail());
		user.setPositionCd(userForm.getPositionCd());
		user.setExtensionTel(userForm.getExtensionTel());
		user.setMobileTel(userForm.getMobileTel());
		user.setBirthday(userForm.getBirthday());
		user.setGender(userForm.getGender());
		user.setHireDate(userForm.getHireDate());
		user.setAccountEnabled(true);
		user.setAddDate(LocalDateTime.now());
		user.setUpdDate(LocalDateTime.now());

		// パスワードをハッシュ化
		user.setPassword(passwordEncoder.encode(userForm.getPassword()));

		// DBへ反映
		usersRepository.save(user);

		// 部署が選択されてるときのみ
		if (userForm.getDeptCd() != null && !userForm.getDeptCd().isEmpty()) {

			// UsersDepartmentsをインスタンス
			UsersDepartments usersDepartments = new UsersDepartments();

			// UserFormをUsersDepartmentsにセット
			usersDepartments.setDeptCd(userForm.getDeptCd());
			usersDepartments.setUserId(userForm.getUserId());
			usersDepartments.setMain(true);

			// DBへ反映
			usersDepartmentsRepository.save(usersDepartments);
		}
	}

}
