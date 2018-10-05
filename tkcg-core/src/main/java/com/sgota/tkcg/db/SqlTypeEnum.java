package com.sgota.tkcg.db;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JDBC数据类型映射java数据类型
 *
 * @author tiankuo
 */
public enum SqlTypeEnum {
    /**
     * NULL.
     */
    NULL(Types.NULL, Object.class),
    /**
     * TINYINT.
     */
    TINYINT(Types.TINYINT, Integer.class),
    /**
     * SMALLINT.
     */
    SMALLINT(Types.SMALLINT, Integer.class),
    /**
     * INTEGER.
     */
    INTEGER(Types.INTEGER, Integer.class),
    /**
     * BIGINT.
     */
    BIGINT(Types.BIGINT, Long.class),
    /**
     * REAL.
     */
    REAL(Types.REAL, Double.class),
    /**
     * FLOAT.
     */
    FLOAT(Types.FLOAT, Double.class),
    /**
     * DOUBLE.
     */
    DOUBLE(Types.DOUBLE, Double.class),
    /**
     * DECIMAL.
     */
    DECIMAL(Types.DECIMAL, BigDecimal.class),
    /**
     * NUMERIC.
     */
    NUMERIC(Types.NUMERIC, BigDecimal.class),
    /**
     * BIT.
     */
    BIT(Types.BIT, Integer.class),
    /**
     * BOOLEAN.
     */
    BOOLEAN(Types.BOOLEAN, Integer.class),
    /**
     * CHAR.
     */
    CHAR(Types.CHAR, String.class),
    /**
     * VARCHAR.
     */
    VARCHAR(Types.VARCHAR, String.class),
    /**
     * LONGVARCHAR.
     */
    LONGVARCHAR(Types.LONGVARCHAR, String.class),
    /**
     * DATE.
     */
    DATE(Types.DATE, Date.class),
    /**
     * TIME.
     */
    TIME(Types.TIME, Date.class),
    /**
     * TIMESTAMP.
     */
    TIMESTAMP(Types.TIMESTAMP, Date.class);

    /**
     * sql type.
     */
    private Integer type;
    /**
     * typeName.
     */
    private Class classzz;

    /**
     * 反向查找map.
     */
    private static final Map<Integer, SqlTypeEnum> LOOKUP = new HashMap<>(16);

    static {
        SqlTypeEnum[] values = values();
        for (SqlTypeEnum value : values) {
            LOOKUP.put(value.type, value);
        }
    }

    /**
     * 构造方法.
     *
     * @param type type
     * @param classzz the classzz
     */
    SqlTypeEnum(Integer type, Class classzz){
        this.type = type;
        this.classzz = classzz;
    }

    /**
     * 反向查找.
     *
     * @param key key
     * @return enum. type enum.
     */
    public static SqlTypeEnum find(Integer key) {
        return LOOKUP.get(key);
    }

    /**
     * 反向查找.
     *
     * @param key key
     * @param defaultValue defaultValue
     * @return enum. type enum.
     */
    public static SqlTypeEnum find(Integer key, SqlTypeEnum defaultValue) {
        SqlTypeEnum valueEnum = find(key);
        return valueEnum == null ? defaultValue : valueEnum;
    }

    /**
     * Gets type
     *
     * @return the type
     */
    public Integer getType() {
        return type;
    }

    /**
     * Gets classzz
     *
     * @return the classzz
     */
    public Class getClasszz() {
        return classzz;
    }
}
