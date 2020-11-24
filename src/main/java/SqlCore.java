import java.lang.reflect.Field;

public class SqlCore {
    public static String getQuerySql(Class<?> clazz) {
        String simpleTable = simplify(clazz.getSimpleName());
        return "select " + getField(clazz.getDeclaredFields()) + " from " + ToolUtils.humpToLine(clazz.getSimpleName()) + " " + simpleTable + "\n";
    }

    public static String getQuerySql(Class<?>... classes) {
        StringBuilder builder = new StringBuilder();

        for (Class<?> clazz : classes) {
            builder.append(getQuerySql(clazz));
        }

        return builder.toString();
    }

    private static String getField(Field[] fields) {
        StringBuilder fieldBuilder = new StringBuilder();

        for (Field field : fields) {
            String fieldName = field.getName();
            String line = ToolUtils.humpToLine(fieldName);
            fieldBuilder.append(line).append(" as ").append(fieldName).append(",\n");
        }

        fieldBuilder.deleteCharAt(fieldBuilder.length() - 2);
        return fieldBuilder.toString();
    }

    private static String simplify(String str) {
        StringBuilder result = new StringBuilder();
        char[] tableChar = str.toCharArray();
        int var4 = tableChar.length;

        for (char c : tableChar) {
            if (Character.isUpperCase(c)) {
                result.append(Character.toLowerCase(c));
            }
        }

        return result.toString();
    }
}
