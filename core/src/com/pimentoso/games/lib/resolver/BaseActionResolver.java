package com.pimentoso.games.lib.resolver;

/**
 * Created by sako on 08/01/2017.
 */

public class BaseActionResolver implements ActionResolver {

	@Override
	public boolean gameServicesAvailable() {
		return false;
	}

	@Override
	public void gameServicesLogin() {

	}

	@Override
	public void gameServicesLogOut() {

	}

	@Override
	public boolean gameServicesIsSignedIn() {
		return false;
	}

	@Override
	public String gameServicesGetPlayerId() {
		return null;
	}

	@Override
	public String gameServicesGetPlayerName() {
		return null;
	}

	@Override
	public void gameServicesSubmitScore(int score, String leaderboardId) {

	}

	@Override
	public void gameServicesShowLeaderboard(String leaderboardId) {

	}

	@Override
	public void gameServicesUnlockAchievement(String achievementId) {

	}

	@Override
	public void gameServicesShowAchievements() {

	}

	@Override
	public void gameServicesSaveGame(GameSaveListener listener) {

	}

	@Override
	public void gameServicesLoadGame(GameSaveListener listener) {

	}

	@Override
	public void googlePlayRating() {

	}

	@Override
	public void inAppPurchase(String sku) {

	}

	@Override
	public void openUrl(String url) {

	}

	@Override
	public void facebookShowPage(String pageName) {
		openUrl("https://www.facebook.com/" + pageName);
	}

	@Override
	public void facebookShare(String title, String description, String url) {

	}

	@Override
	public void twitterShowPage(String userName, String userId) {
		openUrl("https://twitter.com/" + userName);
	}

	@Override
	public void twitterShare(String text) {

	}

	@Override
	public void startActivity(String activity) {

	}

	@Override
	public void startActivity(String activity, String data) {

	}

	@Override
	public void showToast(String text) {

	}

	@Override
	public void showAlertDialog(String title, String text) {

	}

	@Override
	public void showErrorDialog(String title, String text) {

	}
}
