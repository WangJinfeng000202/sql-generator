package top.wangjf.generator.update;

import top.wangjf.generator.utils.ToolUtils;

import java.lang.reflect.Field;

public class UpdateCore {

    /**
     * 更新语句
     * @param o 传入要修改的值
     * @return 修改查询
     */
    public static String update(Object o){
        Class<?> clazz = o.getClass();
        StringBuilder updateSql = new StringBuilder();
        updateSql.append("update *** set \n");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object filedValue = null;
            try {
                filedValue = field.get(o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            updateSql.append(ToolUtils.humpToLine(fieldName)).append(" = '").append(filedValue).append("',\n");
        }
        updateSql.deleteCharAt(updateSql.length()-2);
        return updateSql.toString();
    }
}
