package com.sgota.tool.tkcg.provider;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.type.Type;

/**
 * 数据助手
 *
 * @author tiankuo
 */
public class JavaDataMapHelper {

    public Map<String, Map<String, Object>> loadJava(Collection<File> javaFiles) throws Exception {
        Map<String, Map<String, Object>> dataMap = new HashMap<>(16);
        FileInputStream in = null;
        try {
            for (File javaFile : javaFiles) {
                in = new FileInputStream(javaFile);
                CompilationUnit cu = JavaParser.parse(in);
                TypeDeclaration<?> typeDeclaration = cu.getTypes().get(0);
                if (typeDeclaration instanceof ClassOrInterfaceDeclaration) {
                    getClassMap(dataMap, typeDeclaration);
                }
                in.close();
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return dataMap;
    }

    public void getClassMap(Map<String, Map<String, Object>> dataMap, TypeDeclaration<?> typeDeclaration) {
        ClassOrInterfaceDeclaration classOrInterfaceDeclaration = (ClassOrInterfaceDeclaration) typeDeclaration;
        String classType = "class";
        if (classOrInterfaceDeclaration.isInterface()) {
            classType = "interface";
        }
        String className = typeDeclaration.getName().getIdentifier();
        Map<String, Object> claszz = new HashMap<>(16);
        // 获取field
        List<Map<String, String>> fieldMapList = new ArrayList<>();
        List<FieldDeclaration> fields = classOrInterfaceDeclaration.getFields();
        for (FieldDeclaration field : fields) {
            Map<String, String> fieldMap = new HashMap<>(16);
            Type elementType = field.getElementType();
            VariableDeclarator variable = field.getVariables().get(0);
            String name = variable.getName().getIdentifier();
            String type = elementType.asString();
            Optional<Comment> commentOptional = field.getComment();
            String comment = null;
            if (commentOptional.isPresent()) {
                comment = commentOptional.get().getContent().replaceAll("\\*", "").replaceAll("\\r\\n", "").trim();
            }
            fieldMap.put("name", name);
            fieldMap.put("type", type);
            fieldMap.put("comment", comment);
            fieldMapList.add(fieldMap);
        }
        // 获取method
        List<Map<String, Object>> methodMapList = new ArrayList<>();
        List<MethodDeclaration> methods = classOrInterfaceDeclaration.getMethods();
        for (MethodDeclaration method : methods) {
            Map<String, Object> methodMap = new HashMap<>(16);
            String name = method.getName().getIdentifier();
            methodMap.put("name", name);

            // 获取方法参数
            List<Map<String, String>> parameterMapList = new ArrayList<>();
            NodeList<Parameter> parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                Map<String, String> parameterMap = new HashMap<>(16);
                String parameterName = parameter.getName().getIdentifier();
                String parameterType = parameter.getType().asString();
                parameterMap.put("name", parameterName);
                parameterMap.put("type", parameterType);
                parameterMapList.add(parameterMap);
            }
            methodMap.put("parameterList", parameterMapList);

            String type = method.getType().getElementType().asString();
            methodMap.put("retrueType", type);

            // 获取注释
            Optional<Comment> commentOptional = method.getComment();
            String comment = null;
            if (commentOptional.isPresent()) {
                comment = commentOptional.get().getContent();
            }
            methodMap.put("comment", comment);
            methodMapList.add(methodMap);
        }

        claszz.put("className", className);
        claszz.put("classType", classType);
        claszz.put("fields", fieldMapList);
        claszz.put("methods", methodMapList);

        dataMap.put(className, claszz);
    }
}
