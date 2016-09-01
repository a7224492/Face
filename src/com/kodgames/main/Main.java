package com.kodgames.main;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Client client = new Client();
		client.init();
		
		boolean running = true;
		
		while (running){
			client.update();
			try{
				Thread.sleep(15);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}

}
