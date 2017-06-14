package ${basePackage}.api.response;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * dto
 *
 * @author ${author}
 */
public class ${className}QueryRsp implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
<#list columns as column >
    /**
     * ${column.comment}.
     */
    private ${column.javaType} ${column.property};
</#list>
<#list columns as column>

    /**
     * Gets ${column.property}
     *
     * @return the ${column.property}
     */
    public ${column.javaType} get${column.property?cap_first}() {
        return ${column.property};
    }

    /**
     * Sets ${column.property}
     *
     * @param ${column.property} the ${column.property}
     */
    public void set${column.property?cap_first}(${column.javaType} ${column.property}) {
        this.${column.property} = ${column.property};
    }
</#list>
}
