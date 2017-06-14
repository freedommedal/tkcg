<#macro dolsEL value>${r"${"}${value}${r"}"}</#macro>
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>${groupId}</groupId>
    <artifactId>${artifactId}</artifactId>
    <version>${version}</version>
    <packaging>pom</packaging>

    <properties>
        <java.version>1.8</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding><@dolsEL "project.build.sourceEncoding"/></project.reporting.outputEncoding>
        <maven.compiler.source><@dolsEL "java.version"/></maven.compiler.source>
        <maven.compiler.target><@dolsEL "java.version"/></maven.compiler.target>
        <skipTests>true</skipTests>

        <spring-boot.version>2.0.5.RELEASE</spring-boot.version>
        <start-class>${basePackage}.Application</start-class>

        <api.${artifactId}.version>${version}</api.${artifactId}.version>

        <spring-boot-velocity-starter.version>1.0.0</spring-boot-velocity-starter.version>
    </properties>

    <modules>
        <module>${artifactId}-api</module>
        <module>${artifactId}-common</module>
        <module>${artifactId}-repository</module>
        <module>${artifactId}-sal</module>
        <module>${artifactId}-biz</module>
        <module>${artifactId}-web</module>
    </modules>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>com.sgota.commons</groupId>
                <artifactId>commons-dto</artifactId>
                <version>1.0.0</version>
            </dependency>

            <dependency>
                <groupId>com.sgota.commons</groupId>
                <artifactId>commons-context</artifactId>
                <version>1.0.0</version>
            </dependency>

            <dependency>
                <groupId>${groupId}</groupId>
                <artifactId>${artifactId}-api</artifactId>
                <version><@dolsEL "api.${artifactId}.version"/></version>
            </dependency>

            <dependency>
                <groupId>${groupId}</groupId>
                <artifactId>${artifactId}-common</artifactId>
                <version><@dolsEL "project.version"/></version>
            </dependency>

            <dependency>
                <groupId>${groupId}</groupId>
                <artifactId>${artifactId}-repository</artifactId>
                <version><@dolsEL "project.version"/></version>
            </dependency>

            <dependency>
                <groupId>${groupId}</groupId>
                <artifactId>${artifactId}-biz</artifactId>
                <version><@dolsEL "project.version"/></version>
            </dependency>

            <dependency>
                <groupId>${groupId}</groupId>
                <artifactId>${artifactId}-sal</artifactId>
                <version><@dolsEL "project.version"/></version>
            </dependency>

            <!-- spring -->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version><@dolsEL "spring-boot.version"/></version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>

            <!-- db -->
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>druid-spring-boot-starter</artifactId>
                <version>1.1.9</version>
            </dependency>

            <dependency>
                <groupId>org.mybatis.spring.boot</groupId>
                <artifactId>mybatis-spring-boot-starter</artifactId>
                <version>1.3.1</version>
            </dependency>

            <!-- util -->
            <dependency>
                <groupId>org.apache.commons</groupId>
                <artifactId>commons-lang3</artifactId>
                <version>3.4</version>
            </dependency>

            <dependency>
                <groupId>com.google.guava</groupId>
                <artifactId>guava</artifactId>
                <version>18.0</version>
            </dependency>

            <dependency>
                <groupId>net.sf.dozer</groupId>
                <artifactId>dozer</artifactId>
                <version>5.5.1</version>
            </dependency>

            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>fastjson</artifactId>
                <version>1.2.25</version>
            </dependency>

            <dependency>
                <groupId>com.sgota.springboot</groupId>
                <artifactId>spring-boot-velocity-starter</artifactId>
                <version><@dolsEL "spring-boot-velocity-starter.version"/></version>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <pluginManagement>
            <plugins>
                <plugin>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-maven-plugin</artifactId>
                    <version><@dolsEL "spring-boot.version"/></version>
                    <executions>
                        <execution>
                            <goals>
                                <goal>repackage</goal>
                            </goals>
                        </execution>
                    </executions>
                    <configuration>
                        <mainClass><@dolsEL "start-class"/></mainClass>
                    </configuration>
                </plugin>

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