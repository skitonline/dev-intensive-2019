package net.thumbtack.school.colors.v3;

public enum Color {
    RED,
    GREEN,
    BLUE;

    public static Color colorFromString(String colorString) throws ColorException{
        Color result = null;
        try {
            Color[] colors = Color.values();
            for (Color color : colors)
                if (color.toString().equals(colorString)){
                    result = color;
                    break;
                }
            if (result == null)
                throw new ColorException(ColorErrorCode.WRONG_COLOR_STRING);
        }
        catch (ColorException ex){ }
        return result;
    }
}
