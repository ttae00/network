package thread;

public class AlphabetThread extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(char s='a'; s <='z'; s++){
			System.out.print(s);
			try {
				Thread.sleep(1200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
