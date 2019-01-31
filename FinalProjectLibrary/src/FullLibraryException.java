public class FullLibraryException extends RuntimeException {
	public FullLibraryException(String s) {
		super("The " + s + " is full");
	}
}
