package net.peptel.transit.codec.test.data;

import java.util.Date;

import net.peptel.transit.data.Status;

public class StatusEmulator implements Status {
	private int status;
	private Date statusDate;
	private int codec;
	private String codecMessageID;
	
	public StatusEmulator(int status, Date statusDate, int codec, String codecMessageID) {
		this.status = status;
		this.statusDate = statusDate;
		this.codec = codec;
		this.codecMessageID = codecMessageID;
	}

	@Override
	public int getStatus() {
		return status;
	}

	@Override
	public Date getStatusDate() {
		return statusDate;
	}

	@Override
	public int getCodec() {
		return codec;
	}

	@Override
	public String getCodecMessageID() {
		return codecMessageID;
	}

}
