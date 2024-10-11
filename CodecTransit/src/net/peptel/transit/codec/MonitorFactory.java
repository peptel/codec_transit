package net.peptel.transit.codec;

import java.util.HashMap;
import java.util.Map;

import net.peptel.transit.util.Log;

public class MonitorFactory {
	protected static Map<ActivityMode, CodecMonitor> monitors = new HashMap<ActivityMode, CodecMonitor>();

	static {
		monitors.put(ActivityMode.TASK_MODE, new CodecMonitor());
		monitors.put(ActivityMode.PAID_MODE, new CodecMonitor());
		monitors.put(ActivityMode.MESSAGE_MODE, new CodecMonitor());
		monitors.put(ActivityMode.OTHER_MODE, new CodecMonitor());
	}

	public static CodecMonitor getMonitor(ActivityMode mode) {
		return monitors.get(mode);
	}

	public static Map<ActivityMode, CodecMonitor> getMonitors() {
		return monitors;
	}

	public static void notifyCodec(ActivityMode mode) {
		Log.log.info("NOTIFY CODEC. ActivityMode " + mode);
		
		CodecMonitor monitor = monitors.get(mode);
		synchronized (monitor) {
			monitor.notifyAll();
			Log.log.info("NOTIFY CODEC SENT");
		}
	}
	
	public static void notifyAllCodec() {
		Log.log.info("NOTIFY ALL CODEC");

		for (CodecMonitor monitor : monitors.values()) {
			synchronized (monitor) {
				monitor.notifyAll();
			}
		}
		
	}

	
	public static void main(String[] args) {
		for (Object monitor : MonitorFactory.getMonitors().values()) {
			System.out.println(monitor);
		}
	}
}
