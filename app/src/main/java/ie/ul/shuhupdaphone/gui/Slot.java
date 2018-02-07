package ie.ul.shuhupdaphone.gui;

/*
 *	Timeslot information: start and stop time (stored as int), day and CONTACT_TYPE  
 */

public class Slot {
	public enum DAY {MON, TUE, WED, THU, FRI, SAT, SUN};
	public enum CONTACT_TYPE {LECTURE, LAB, TUTORIAL, STUDYTIME, ALL};
	
	private DAY day;
	private Integer startHour;
	private Integer endHour;
	private CONTACT_TYPE type;
	
	public Slot (DAY slotDay, int slotStart, int slotEnd, CONTACT_TYPE slotType) {
		day = slotDay;
		startHour = slotStart;
		endHour = slotEnd;
		type = slotType;
	}

	public DAY day() {
		return day;
	}
	
	public Integer start() {
		return startHour;
	}
	
	public Integer end() {
		return endHour;
	}
	
	public CONTACT_TYPE type() {
		return type;
	}
	
}
