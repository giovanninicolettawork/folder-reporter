package files.reporter.constant;

public enum CsvHeader {

	NAME("File Name"), PARENT_FOLDER("Parent Folder");

	public final String label;

	private CsvHeader(String label) {
		this.label = label;
	}
}