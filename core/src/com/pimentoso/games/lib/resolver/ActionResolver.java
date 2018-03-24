package com.pimentoso.games.lib.resolver;

public interface ActionResolver {

	public static final String BUNDLE_DATA = "DATA_0";

	boolean gameServicesAvailable();

	void gameServicesLogin();

	void gameServicesLogOut();

	//get if client is signed in to Google+
	boolean gameServicesIsSignedIn();

	String gameServicesGetPlayerId();
	String gameServicesGetPlayerName();

	//submit a score to a leaderboard
	void gameServicesSubmitScore(int score, String leaderboardId);

	//gets the scores and displays them threw googles default widget
	void gameServicesShowLeaderboard(String leaderboardId);

	void gameServicesUnlockAchievement(String achievementId);

	//gets the achievements and displays them threw googles default widget
	void gameServicesShowAchievements();

	void gameServicesSaveGame(String payload);

	void gameServicesLoadGame();

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

	interface GameServicesSignInListener {
		void onSignInFailed();
		void onSignInSucceeded();
	}

	interface GameServicesSaveListener {
		void loadSuccess(byte[] data);
		void saveSuccess();
		void loadFailed(String message);
		void saveFailed(String message);
	}
}
