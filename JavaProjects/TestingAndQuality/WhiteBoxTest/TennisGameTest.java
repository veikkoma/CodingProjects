import static org.junit.Assert.*;

import org.junit.Test;

public class TennisGameTest {
	
// Here is the format of the scores: "player1Score - player2Score"
// "love - love"
// "15 - 15"
// "30 - 30"
// "deuce"
// "15 - love", "love - 15"
// "30 - love", "love - 30"
// "40 - love", "love - 40"
// "30 - 15", "15 - 30"
// "40 - 15", "15 - 40"
// "player1 has advantage"
// "player2 has advantage"
// "player1 wins"
// "player2 wins"
	@Test
	public void testTennisGame_Start() {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Initial score incorrect", "love - love", score);		
	}
	
	@Test
	public void testTennisGame_EahcPlayerWin4Points_Score_Deuce() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player2Scored();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Tie score incorrect", "deuce", score);		
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player1WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		//Act
		//This statement should cause an exception
		game.player1Scored();
	}		


	@Test (expected = TennisGameException.class)
	public void testTennisGame_GameEndTwoPointsDifference() throws TennisGameException {
		
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player2wins() throws TennisGameException {
		
		//Arrange
		TennisGame game = new TennisGame();
		//act
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		String score = game.getScore();
		assertEquals("Game ", "player2 wins", score);
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player1wins() throws TennisGameException {
		
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		String score = game.getScore();
		assertEquals("Game ", "player1 wins", score);
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player1HasAdvantage() throws TennisGameException {
		
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player1Scored();
		
		String score = game.getScore();
		assertEquals("Game ", "player1 has advantage", score);
		}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player2HasAdvantage() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player2Scored();
		
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		
		game.player2Scored();
		
		game.player1Scored();
		game.player2Scored();
		
		String score = game.getScore();
		assertEquals("Game ", "player2 has advantage", score);
		
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player2WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		//Act 
		//Statement should cause an exception
		game.player2Scored();
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player1WinsPoint() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		
		String score = game.getScore();
		assertEquals("Player1 wins the point", "love - 15", score);
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player2WinsTwoPoint() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player2Scored();
		
		String score = game.getScore();
		assertEquals("Player2 wins the point", "15 - 15", score);
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player1WinsThirdPoint() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player2Scored();
		game.player1Scored();
		
		String score = game.getScore();
		assertEquals("Player1 wins the point", "30 - 15", score);
	}
}
	
	
	
	
	
