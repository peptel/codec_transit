package net.peptel.transit.codec;

public class CodecMonitor {
	private boolean running;
	
	public CodecMonitor() {
		running = true;
	}

	public boolean isRunning() {
		return running;
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}
}
