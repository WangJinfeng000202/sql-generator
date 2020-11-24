import java.lang.reflect.Field;

/**
 * 单表或多表查询核心API
 * @author 王金锋
 */
public class QueryCore {

    /**
     * 左连接查询
     * @param classes 传入的vo
     * @return 左连接查询语句
     */
    public static String getLeftQuerySql(Class<?>... classes) {
        StringBuilder sql = new StringBuilder("select ");

        //拼接成员变量
        for (Class<?> clazz : classes) {
            String table = clazz.getSimpleName();
            String simplifyTable = simplify(table);
            sql.append(getField(clazz.getDeclaredFields(), simplifyTable));
        }
        //删除最后一个逗号
        sql.deleteCharAt(sql.length() - 2);
        String table0 = classes[0].getSimpleName();
        String simplifyTable0 = simplify(table0);
        sql.append("from ").append(table0).append(" ").append(simplifyTable0).append("\n");
        //拼接表名
        for (int i = 1; i < classes.length; i++) {
            Class<?> clazz = classes[i];
            String table = clazz.getSimpleName();
            sql.append("left join ").append(ToolUtils.humpToLine(table)).append(" ").append(simplify(table)).append(",\n");
        }
        //删除最后一个逗号
        sql.deleteCharAt(sql.length() - 2);

        return sql.toString();
    }

    /**
     * 自然连接查询
     * @param classes 传入的Vo
     * @return 自然连接查询
     */
    public static String getNaturalQuerySql(Class<?>... classes){
        StringBuilder sql = new StringBuilder("select ");

        //拼接成员变量
        for (Class<?> clazz : classes) {
            String table = clazz.getSimpleName();
            String simplifyTable = simplify(table);
            sql.append(getField(clazz.getDeclaredFields(), simplifyTable));
        }
        sql.append("from ");
        for (Class<?> clazz : classes) {
            String table = clazz.getSimpleName();
            sql.append(ToolUtils.humpToLine(table)).append(" ").append(simplify(table)).append(",\n");
        }
        //删除最后一个逗号
        sql.deleteCharAt(sql.length() - 2);
        return sql.toString();
    }


    public static String getSimpleQuerySql(Class<?> clazz) {
        String simpleTable = simplify(clazz.getSimpleName());
        return "select " + getField(clazz.getDeclaredFields())
                + "from " + ToolUtils.humpToLine(clazz.getSimpleName()) + " " + simpleTable + "\n";
    }

    public static String getSimpleQuerySql(Class<?>... classes) {
        StringBuilder builder = new StringBuilder();

        for (Class<?> clazz : classes) {
            builder.append(getSimpleQuerySql(clazz));
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

    private static String getField(Field[] fields, String tablePrefix) {
        StringBuilder fieldBuilder = new StringBuilder();

        for (Field field : fields) {
            String fieldName = field.getName();
            String line = ToolUtils.humpToLine(fieldName);
            fieldBuilder.append(tablePrefix).append(".").append(line).append(" as ").append(fieldName).append(",\n");
        }
        return fieldBuilder.toString();
    }

    private static String simplify(String str) {
        StringBuilder result = new StringBuilder();
        char[] tableChar = str.toCharArray();
        for (char c : tableChar) {
            if (Character.isUpperCase(c)) {
                result.append(Character.toLowerCase(c));
            }
        }

        return result.toString();
    }
}
