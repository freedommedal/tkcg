package ${basePackage}.convertor;

import ${basePackage}.api.request.${className}CreateReq;
import ${basePackage}.api.request.${className}UpdateReq;
import ${basePackage}.repository.entity.${className};

/**
 * @author ${author}
 */
public class ${className}Convertor {

    /**
     * constructor
     */
    private ${className}Convertor(){
        // 静态工具类
    }

    /**
     * copy.
     *
     * @param source source
     * @param target target
     */
    public static void copy(${className}CreateReq source,${className} target) {
        if (source != null && target != null) {
            <#list columns as column >
            target.set${column.property?cap_first}(source.get${column.property?cap_first}());
            </#list>
        }
    }

    /**
     * copy.
     *
     * @param source source
     * @param target target
     */
    public static void copy(${className}UpdateReq source,${className} target) {
        if (source != null && target != null) {
            <#list columns as column >
            target.set${column.property?cap_first}(source.get${column.property?cap_first}());
            </#list>
        }
    }
}
