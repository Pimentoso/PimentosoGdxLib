package com.pimentoso.games.lib.resolver;

public interface ActionResolver {
	
	public abstract boolean gameServicesAvailable();
	
	public abstract void gameServicesLogin();

	public abstract void gameServicesLogOut();

	//get if client is signed in to Google+
	public abstract boolean gameServicesIsSignedIn();

	//submit a score to a leaderboard
	public abstract void gameServicesSubmitScore(int score, String leaderboardId);

	//gets the scores and displays them threw googles default widget
	public abstract void gameServicesGetScores(String leaderboardId);

	public abstract void gameServicesUnlockAchievement(String achievementId);

	//gets the achievements and displays them threw googles default widget
	public abstract void gameServicesGetAchievements();

	public abstract void gameServicesSubmitAllScores();
	
	public abstract void openMarket(String app);

}