<#macro dolsEL value>${r"${"}${value}${r"}"}</#macro>
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns="http://maven.apache.org/POM/4.0.0"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>${groupId}</groupId>
    <artifactId>${artifactId}-api</artifactId>
    <version>${version}</version>

    <properties>
        <java.version>1.8</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding><@dolsEL "project.build.sourceEncoding"/></project.reporting.outputEncoding>
        <maven.compiler.source><@dolsEL "java.version"/></maven.compiler.source>
        <maven.compiler.target><@dolsEL "java.version"/></maven.compiler.target>
        <skipTests>true</skipTests>
    </properties>

    <dependencies>
        <dependency>
            <groupId>com.sgota.commons</groupId>
            <artifactId>commons-dto</artifactId>
            <version>1.0.0</version>
        </dependency>
    </dependencies>

    <build>
        <pluginManagement>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-source-plugin</artifactId>
                    <executions>
                        <execution>
                            <id>attach-sources</id>
                            <phase>verify</phase>
                            <goals>
                                <goal>jar-no-fork</goal>
                            </goals>
                        </execution>
                    </executions>
                </plugin>
            </plugins>
        </pluginManagement>

        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-source-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>