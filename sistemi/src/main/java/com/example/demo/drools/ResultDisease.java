package com.example.demo.drools;

public class ResultDisease {

	private Long diseaseId;
	private double per;
	private boolean third=false;
	
	public ResultDisease(Long diseaseId, double per, boolean third) {
		super();
		this.diseaseId = diseaseId;
		this.per = per;
		this.third = third;
	}
	
	public ResultDisease() {
		this.diseaseId = 0L;
		this.per = 0;
		this.third = false;
	}
	
	public Long getDiseaseId() {
		return diseaseId;
	}

	public void setDiseaseId(Long diseaseId) {
		this.diseaseId = diseaseId;
	}

	public double getPer() {
		return per;
	}

	public void setPer(double per) {
		this.per = per;
	}

	public boolean isThird() {
		return third;
	}

	public void setThird(boolean third) {
		this.third = third;
	}

	@Override
	public String toString() {
		return "ResultDisease [diseaseId=" + diseaseId + ", per=" + per + ", third=" + third + "]";
	}
	
	
	
}
