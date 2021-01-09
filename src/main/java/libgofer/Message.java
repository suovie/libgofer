package libgofer;

import java.io.Serializable;

public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    public String type, sender, content, recipient;
    public boolean encrypted = false;

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
