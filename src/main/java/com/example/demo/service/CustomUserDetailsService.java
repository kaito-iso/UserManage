package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.auth.CustomUserDetails;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー情報読み込みサービス
 * Spring Securityの認証プロセスにおいて、DBからユーザー情報を検索・取得する役割
 * @Service : コントローラーから呼び出せるようにしてロジックを担当する
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UsersService usersService;

	/**
	 * ログインボタン押下時に Spring Security から自動的に呼び出されるメソッド
	 * @param userId ログイン画面で入力されたユーザーID
	 * @return カスタマイズしたユーザー詳細情報（CustomUserDetails）
	 * @throws UsernameNotFoundException ユーザーが見つからない場合に投げる
	 */
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

		// リポジトリを使ってDBからユーザー1件を検索
		return usersService.findByUserId(userId)

				// ユーザーが見つかった場合：EntityをSpring Security専用の型に変換
				.map(getDbUser -> {
					// pring Securityは自作のEntity（Users）を直接扱えないので、Securityの規格に合わせたCustomUserDetailsに変換して返す
					return new CustomUserDetails(getDbUser);
				})

				/**
				 * ユーザーIDが見つからなかった場合に例外を投げる
				 * Spring SecurityにあるAuthenticationFailureHandlerが例外キャッチして
				 * SecurityConfigで設定した.failureUrl("/login?error")に飛ばされる
				 */
				.orElseThrow(() -> new UsernameNotFoundException("User not found: " + userId));
	}
}