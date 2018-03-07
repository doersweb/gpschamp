package track.gpschamp.com.gpschamp.utils;

/**
 * Created by sudhirharit on 06/03/18.
 */

public class SVGParseException extends RuntimeException {

    public SVGParseException(String s) {
        super(s);
    }

    public SVGParseException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public SVGParseException(Throwable throwable) {
        super(throwable);
    }
}
