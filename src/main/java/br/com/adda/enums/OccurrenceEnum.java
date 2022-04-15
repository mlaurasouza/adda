package br.com.adda.enums;

public enum OccurrenceEnum {
	
	O1("Assédio"),
	O2("Machismo"),
	O3("Racismo"),
	O4("Homofobia"),
	O5("Xenofobia"),
	O6("Transfobia"),
	O7("Gordofobia"),
	O8("Outro");
	
	private int occurenceId;
	private String occurenceName;
	
	OccurrenceEnum(String occurrenceName){
		this.occurenceName = occurrenceName;
	}

	public int getOccurenceId() {
		return occurenceId;
	}

	public String getOccurenceName() {
		return occurenceName;
	}

}
