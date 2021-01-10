package libgofer;

import java.io.Serializable;

public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    public String type, sender, content, recipient;
    public boolean encrypted = false;

    public static final String TYPE_MESSAGE = "MESSAGE";
    public static final String TYPE_CONNECT = "CONNECT";
    public static final String TYPE_WHOIS = "WHOIS";
    public static final String TYPE_LOGIN = "LOGIN";
    public static final String TYPE_SIGNUP = "SIGNUP";
    public static final String TYPE_NEW_USER = "NEWUSER";
    public static final String TYPE_UPLOAD = "UPLOAD";
    public static final String TYPE_DOWNLOAD = "DOWNLOAD";

    public static final String TO_ALL = "ALL";

    public Message(String t, String s, String c, String r) {
        this.type = t;
        this.sender = s;
        this.content = c;
        this.recipient = r;
    }

    @Override
    public String toString() {
        return "{type='" + this.type
                + "', sender='" + sender
                + "', content='" + content
                + "', recipient='" + recipient
            + "'}";
    }
}
