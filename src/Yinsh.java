import java.util.Random;


public class Yinsh {
	
	public enum color{
		BLACK,
		WHITE;
	}
	
	public Yinsh()
	{
		
	}
	
	public color current_color()
	{
		Random rand = new Random();
		int r = rand.nextInt(2);
		if(r==0) return color.WHITE;
		else return color.BLACK;
	}

}
