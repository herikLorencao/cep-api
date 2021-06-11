package app.entities;

import app.exceptions.InvalidZipcodeException;

public class Zipcode {
    private String content;

    public Zipcode(String content) throws InvalidZipcodeException {
        InvalidZipcodeException invalidZipcodeException =
                new InvalidZipcodeException("Informe um zipcode no formato xxxxx-xxx ou xxxxxxxx");

        if (content == null)
            throw invalidZipcodeException;

        String zipcodeRaw = content.replace("-", "");
        Boolean isDigitCaracters = zipcodeRaw.chars().allMatch(Character::isDigit);

        if (zipcodeRaw.length() != 8 || !isDigitCaracters)
            throw invalidZipcodeException;

        this.content = zipcodeRaw;
    }

    public String getContent() {
        String prefix = content.substring(0, 5);
        String suffix = content.substring(5);
        return prefix + "-" + suffix;
    }
}
