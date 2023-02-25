package ca.mcmaster.cas.se2aa4.a2.generator;

public class Color {
    private String colorCode;

    public Color(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getColorCode() {
        return colorCode;
    }

    public static void setColor(Point p, String pc) {
        Color c = new Color(pc);
        p.setColor(c);
    }

    public static void setColor(Segment s) {
        Color p1 = s.getStart().getColor();
        Color p2 = s.getEnd().getColor();
        s.setColor(combineColor(p1,p2));

    }

    private static Color combineColor(Color p1, Color p2) {
        String c1 = p1.colorCode;
        String c2 = p2.colorCode;
        int[] p1c = extractColor_Number(c1);
        int[] p2c = extractColor_Number(c2);
        int[] ac = {(p1c[0] + p2c[0]) / 2, (p1c[1] + p2c[1]) / 2, (p1c[2] + p2c[2]) / 2, (p1c[3] + p2c[3]) / 2};
        String colorCode = ac[0] + "," + ac[1] + "," + ac[2] + "," + ac[3];
        Color sc = new Color(colorCode);
        return sc;

    }

    private static int[] extractColor_Number(String val) {
        String[] raw = val.split(",");
        int red = Integer.parseInt(raw[0]);
        int green = Integer.parseInt(raw[1]);
        int blue = Integer.parseInt(raw[2]);
        int alpha = Integer.parseInt(raw[3]);
        return new int[]{red, green, blue, alpha};
    }
}


