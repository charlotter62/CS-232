public class NoMatchException extends RuntimeException {
	
	public NoMatchException(String s) {
		super("The " + s + " has no matches");
	}
	
}