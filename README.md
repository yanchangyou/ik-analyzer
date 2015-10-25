[![Build Status](https://travis-ci.org/blueshen/ik-analyzer.svg)](https://travis-ci.org/blueshen/ik-analyzer)

IKAnalyzer的作者为林良益（linliangyi2007@gmail.com），项目网站为<http://code.google.com/p/ik-analyzer/>

本版本，主要特点：

- Maven化
- 使用lucene5.3.1（5.3.0测试类报错）
- 添加org.wltea.analyzer.lucene.IKTokenizerFactory

Maven用法：

将以下依赖加入工程的pom.xml中的<dependencies>...</dependencies>部分。

    <dependency>
        <groupId>org.wltea.ik-analyzer</groupId>
        <artifactId>ik-analyzer</artifactId>
        <version>5.3.1</version>
	</dependency>

在IK Analyzer加入Maven Central Repository之前，你需要手动安装，安装到本地的repository，或者上传到自己的Maven repository服务器上。

要安装到本地Maven repository，使用如下命令，将自动编译，打包并安装：

    mvn install -Dmaven.test.skip=true

想要切换lucene版本：

    mvn -Dlucene.version=5.*.* install -Dmaven.test.skip=true


启动报错：

Assertions mismatch: -ea was not specified but -Dtests.asserts=true
NOTE: test params are: codec=null, sim=null, locale=null, timezone=(null)
NOTE: Windows 8 6.2 amd64/Oracle Corporation 1.7.0_09 (64-bit)/cpus=4,threads=1,free=227479432,total=252575744
NOTE: All tests run in this JVM: [IKAnalyzerTest]
NOTE: reproduce with: ant test  -Dtestcase=IKAnalyzerTest -Dtests.seed=84A32D5FB21D8C9C -Dtests.asserts=false -Dtests.file.encoding=UTF-8

解决：

1，添加jvm参数
-Dtestcase=IKAnalyzerTest -Dtests.seed=2B97CB880D43C76C -Dtests.asserts=false -Dtests.file.encoding=UTF-8

配置文件
    位置/ik-analyzer/src/main/resources

部署执行
1，打包
    run-> build  输入 package
      执行后，在target里面有jar包
2，添加jar包 
      添加ik-analyzer-5.3.0.jar 到 solr的/WEB-INF/lib下面（配置文件在jar包里面）
3，配置
    在\solrapps\solr-5.3.1\new_core\conf\schema.xml
  3.1 添加字段类型
    <fieldType name="text_ik" class="solr.TextField">   
         <analyzer class="org.wltea.analyzer.lucene.IKAnalyzer"/>   
    </fieldType>
  3.2 添加字段
    <field name="content" type="text_ik" indexed="true"  stored="true"  multiValued="false" /> 
4，注意
    添加的doc的字段，必须在schema.xml中定义，否则添加不进去
    
