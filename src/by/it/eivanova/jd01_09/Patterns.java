package by.it.eivanova.jd01_09;

public class Patterns {

    public static final String OPERATION = "[-+*/]";
    public static final String SPACES = "\\S+";
    public static final String SCALAR = "-?[0-9]+(\\.[0-9]+)?";
    public static final String VECTOR = "\\{" + SCALAR + "(," + SCALAR + ")*}";
    public static final String MATRIX = "\\{" + VECTOR + "(," + VECTOR + ")*}";
}
