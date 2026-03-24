package com.example.demo.service;

import java.time.LocalDateTime;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.entity.UsersDepartments;
import com.example.demo.form.UserEditForm;
import com.example.demo.repository.UsersDepartmentsRepository;
import com.example.demo.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー情報変更画面に関する業務ロジックを担当
 * @Service : コントローラーから呼び出せるようにしてロジックを担当する
 */
@Service
@RequiredArgsConstructor
public class UserEditService {

	private final UsersService usersService;
	private final UsersRepository usersRepository;
	private final UsersDepartmentsRepository usersDepartmentsRepository;

	/**
	 * ユーザー情報の変更ロジック
	 * @Transactional : トランザクション
	 */
	@Transactional
	public void save(UserEditForm userEditForm) {

		// ユーザー情報取得
		Users user = usersService.findByUserId(userEditForm.getUserId())
				.orElseThrow(() -> new RuntimeException("ユーザーが見つかりません"));

		// Formの値をEntityに上書き
		user.setFamilyName(userEditForm.getFamilyName());
		user.setGivenName(userEditForm.getGivenName());
		user.setFamilyNameKana(userEditForm.getFamilyNameKana());
		user.setGivenNameKana(userEditForm.getGivenNameKana());
		user.setMail(userEditForm.getMail());
		user.setPositionCd(userEditForm.getPositionCd());
		user.setExtensionTel(userEditForm.getExtensionTel());
		user.setMobileTel(userEditForm.getMobileTel());
		user.setBirthday(userEditForm.getBirthday());
		user.setGender(userEditForm.getGender());
		user.setHireDate(userEditForm.getHireDate());
		user.setResignationDate(userEditForm.getResignationDate());
		user.setRole(userEditForm.getRole());
		user.setAccountEnabled(userEditForm.getAccountEnabled());
		user.setUpdDate(LocalDateTime.now());

		// DBへ反映
		usersRepository.save(user);

		// 現在の所属をすべて削除
		usersDepartmentsRepository.deleteByUserId(userEditForm.getUserId());

		// 部署が選択されている場合のみ、メイン部署として登録
		if (userEditForm.getMainDeptCd() != null && !userEditForm.getMainDeptCd().isEmpty()) {

			UsersDepartments newUd = new UsersDepartments();
			newUd.setUserId(userEditForm.getUserId());
			newUd.setDeptCd(userEditForm.getMainDeptCd());
			newUd.setMain(true);

			// DBへ反映
			usersDepartmentsRepository.save(newUd);
		}
	}
}
