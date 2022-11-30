package files.reporter.constant;

public enum GenericMessages {

	NO_CONSTRUCTOR("This is a utility class and cannot be instantiated"), ERROR("Error"), PROCESSED("Processed"),
	REPORT_FILE_NAME("report.csv");

	public final String label;

	private GenericMessages(String label) {
		this.label = label;
	}
}