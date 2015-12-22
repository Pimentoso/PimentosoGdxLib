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
	
	void openMarket(String app);

	void openUrl(String url);

	void facebookShare(String text);

	void twitterShare(String text);
}