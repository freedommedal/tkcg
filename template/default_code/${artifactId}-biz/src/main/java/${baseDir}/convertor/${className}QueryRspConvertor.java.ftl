package ${basePackage}.convertor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ${basePackage}.api.response.${className}QueryRsp;
import ${basePackage}.repository.entity.${className};

/**
 * @author ${author}
 */
public class ${className}QueryRspConvertor {

    /**
     * constructor
     */
    private ${className}QueryRspConvertor(){
        // 静态工具类
    }

    /**
     * copy.
     * 
     * @param source source
     * @param target target
     */
    public static void copy(${className} source, ${className}QueryRsp target) {
        if (source != null && target != null) {
            <#list columns as column >
            target.set${column.property?cap_first}(source.get${column.property?cap_first}());
            </#list>
        }
    }

    /**
     * copy list.
     *
     * @param sourceList sourceList
     * @return list.
     */
    public static List<${className}QueryRsp> convertList(List<${className}> sourceList) {
        if (sourceList != null) {
            return sourceList.stream().map(source -> {
                ${className}QueryRsp target = new ${className}QueryRsp();
                ${className}QueryRspConvertor.copy(source, target);
                return target;
            }).collect(Collectors.toList());
        } else {
            return new ArrayList<>(0);
        }
    }
}
