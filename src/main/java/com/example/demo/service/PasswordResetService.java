package com.example.demo.service;

import java.util.Optional;

import jakarta.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.entity.Users;
import com.example.demo.exception.UserEditException;
import com.example.demo.form.PasswordResetForm;
import com.example.demo.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

/**
 * パスワード再設定に関する業務ロジックを担当
 * @Service : コントローラーから呼び出せるようにしてロジックを担当する
 */
@Service
@RequiredArgsConstructor
public class PasswordResetService {

	private final UsersRepository usersRepository;
	private final UsersService usersService;
	private final PasswordEncoder passwordEncoder;

	/**
	 * メール送信先を確認するために、メールアドレスでユーザーの存在をチェックする
	 */
	public Optional<Users> findByMail(String mail) {

		// リポジトリから取得した結果を変数に格納
		Optional<Users> userOpt = usersRepository.findByMail(mail);

		// 変数に値があるか確認
		if (userOpt.isPresent()) {
			// TODO : メールを送信するロジック（ここに来るのはユーザーが見つかった時だけ）

		}
		return userOpt;
	}

	@Transactional
	public void save(PasswordResetForm form) {

		Users user = usersService.findByUserId(form.getUserId())
				.orElseThrow(() -> new RuntimeException("ユーザーが見つかりません"));

		// 入力値の存在があるか確認
		boolean hasNewPassword = StringUtils.hasText(form.getNewPassword1())
				|| StringUtils.hasText(form.getNewPassword2());

		// 現在のパスワードが入力されている場合
		if (StringUtils.hasText(form.getCurrentPassword())) {
			// パスワード一致チェック
			if (!passwordEncoder.matches(form.getCurrentPassword(), user.getPassword())) {
				throw new UserEditException("現在のパスワードが正しくありません", "currentPassword");
			}

			// 新しいパスワードの不一致チェック
			if (hasNewPassword) {
				if (!form.getNewPassword1().equals(form.getNewPassword2())) {
					throw new UserEditException("パスワードと確認用が一致しません", "newPassword1");
				}
				// ハッシュ化してセット
				user.setPassword(passwordEncoder.encode(form.getNewPassword1()));
			}
		} else if (hasNewPassword) {
			// 現在のパスワードが未入力なのに新パスワードだけある場合
			throw new UserEditException("パスワード変更には、現在のパスワード入力が必須です", "currentPassword");
		}
		
		usersRepository.save(user);
	}
}