package testfx;

import java.time.LocalDate;

class DataItem implements Comparable<DataItem> {

	private String label;
	private String description;
	private LocalDate date;

	@Override
	public int compareTo(DataItem other) {
		int dates = date.compareTo(other.getDate());
		if (dates != 0) return dates;
		return getLabel().compareTo(other.getLabel());
	}

	@Override
	public String toString()
	{
		return label + " (" + date + ")";
	}

	public String getLabel() {
		return label;
	}

	public String getDescription() {
		return description;
	}

	public LocalDate getDate() {
		return date;
	}

	public void set(String _label,String _description,LocalDate _date) {
		label = _label;
		description = _description;
		date = _date;
	}

	public DataItem(String _label,String _description,LocalDate _date) {
		set(_label,_description,_date);
	}
}
