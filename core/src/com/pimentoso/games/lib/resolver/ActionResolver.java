package com.pimentoso.games.lib.resolver;

public interface ActionResolver {

	boolean gameServicesAvailable();

	void gameServicesLogin();

	void gameServicesLogOut();

	//get if client is signed in to Google+
	boolean gameServicesIsSignedIn();

	//submit a score to a leaderboard
	void gameServicesSubmitScore(int score, String leaderboardId);

	//gets the scores and displays them threw googles default widget
	void gameServicesGetScores(String leaderboardId);

	void gameServicesUnlockAchievement(String achievementId);

	//gets the achievements and displays them threw googles default widget
	void gameServicesGetAchievements();

	void gameServicesSubmitAllScores();

	void gameServicesSaveGame(GameSaveListener listener);

	void gameServicesLoadGame(GameSaveListener listener);

	void googlePlayRating();

	void inAppPurchase(String sku);

	void openUrl(String url);

	void facebookShowPage(String name);

	void facebookShare(String title, String description, String url);

	void twitterShowPage(String userName, String userId);

	void twitterShare(String text);

	void startActivity(String activity);

	void startActivity(String activity, String data);

	void showToast(String text);

	void showAlertDialog(String title, String text);

	void showErrorDialog(String title, String text);

	interface GameSaveListener {
		void loadSuccess(byte[] data);
		void saveSuccess();
		void loadFail();
		void saveFail();
		void error(String message);
	}
}
