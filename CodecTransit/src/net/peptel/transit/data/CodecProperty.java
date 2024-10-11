package net.peptel.transit.data;

import net.peptel.transit.codec.ActivityMode;
import net.peptel.transit.codec.SendingMode;

public class CodecProperty {
	private String className;
	private SendingMode mode;
	private int codecType;
	private String codecName;
	private ActivityMode activity; 

	public CodecProperty(String className, SendingMode mode) {
		this.className = className;
		this.mode = mode;
	}

	public CodecProperty(String className, SendingMode mode, int codecType, String codecName) {
		this.className = className;
		this.mode = mode;
		this.codecType = codecType;
		this.codecName = codecName;
	}
	
	public CodecProperty(String className, SendingMode mode, int codecType, String codecName, ActivityMode activity) {
		this.className = className;
		this.mode = mode;
		this.codecType = codecType;
		this.codecName = codecName;
		this.activity = activity;
	}

	public ActivityMode getActivity() {
		return activity;
	}

	public void setActivity(ActivityMode activity) {
		this.activity = activity;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public SendingMode getMode() {
		return mode;
	}

	public void setMode(SendingMode mode) {
		this.mode = mode;
	}
	
	public int getCodecType() {
		return codecType;
	}

	public void setCodecType(int codecType) {
		this.codecType = codecType;
	}

	public String getCodecName() {
		return codecName;
	}

	public void setCodecName(String codecName) {
		this.codecName = codecName;
	}

	@Override
	public String toString() {
		return "CodecProperty [className=" + className + ", mode=" + mode + ", codecType=" + codecType + ", codecName="
				+ codecName + ", activity=" + activity + "]";
	}
	
}
