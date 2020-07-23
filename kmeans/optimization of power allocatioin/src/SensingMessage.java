import java.sql.Timestamp;


public class SensingMessage {
	private String from;
	private String to;
	private String message;
	Timestamp time;
	String type;
	String status;
	double channelCoefficientG;
	double zeroMeanM;
	double varianceMM;
	double amplicationfactorU;
	double phase;
	double sensingCommunicationSignalW;
	double sensingCommunicationPowerWW;
	
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getChannelCoefficientG() {
		return channelCoefficientG;
	}
	public void setChannelCoefficientG(double channelCoefficientG) {
		this.channelCoefficientG = channelCoefficientG;
	}
	public double getZeroMeanM() {
		return zeroMeanM;
	}
	public void setZeroMeanM(double zeroMeanM) {
		this.zeroMeanM = zeroMeanM;
	}
	public double getVarianceMM() {
		return varianceMM;
	}
	public void setVarianceMM(double varianceMM) {
		this.varianceMM = varianceMM;
	}
	public double getAmplicationfactorU() {
		return amplicationfactorU;
	}
	public void setAmplicationfactorU(double amplicationfactorU) {
		this.amplicationfactorU = amplicationfactorU;
	}
	public double getPhase() {
		return phase;
	}
	public void setPhase(double phase) {
		this.phase = phase;
	}
	public double getSensingCommunicationSignalW() {
		return sensingCommunicationSignalW;
	}
	public void setSensingCommunicationSignalW(double sensingCommunicationSignalW) {
		this.sensingCommunicationSignalW = sensingCommunicationSignalW;
	}
	public double getSensingCommunicationPowerWW() {
		return sensingCommunicationPowerWW;
	}
	public void setSensingCommunicationPowerWW(double sensingCommunicationPowerWW) {
		this.sensingCommunicationPowerWW = sensingCommunicationPowerWW;
	}
	
}
