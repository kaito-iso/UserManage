/**
 * 成功メッセージを数秒後に消去する共通処理
 */
window.addEventListener('DOMContentLoaded', () => {
    const message = document.getElementById('success-message');
    if (message) {
        // 3秒（3000ms）待機してからフェードアウト開始
        setTimeout(() => {
            message.style.transition = 'opacity 0.5s ease';
            message.style.opacity = '0';

            // アニメーションが終わる0.5秒後に、要素を完全に消去（スペースを詰める）
            setTimeout(() => {
                message.remove();
            }, 500);
        }, 1500);
    }
});