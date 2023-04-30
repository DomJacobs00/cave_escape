package age491.cave_escape;

import java.util.Random;

public class MathQuestionsGenerator {
	private int difficulty;
	private Random random;
	private int num1;
	private int num2;
	private char operator;
	private int correctAnswer;
	
	public MathQuestionsGenerator(int difficulty)
	{
		this.difficulty = difficulty;
		this.random = new Random();
		generateQuestion();
	}
	public void generateQuestion()
	{
		switch(difficulty)
		{
		case 1:
			num1 = random.nextInt(10);
			num2 = random.nextInt(10);
			break;
		case 2:
			num1 = random.nextInt(20);
			num2 = random.nextInt(20);
			break;
		default:
			num1 = random.nextInt(30);
			num2 = random.nextInt(30);
			break;
		}
		
		int operatorIndex = random.nextInt(2);
		operator = operatorIndex == 0 ? '+' : '-';
		correctAnswer = operator == '+' ? num1 + num2 : num1 - num2;
	}
	public String getQuestion()
	{
		return num1 + " " + operator + " " + num2 + " = ?";
	}
	public boolean checkAnswer(int answer)
	{
		return answer == correctAnswer;
	}
	
	
}
