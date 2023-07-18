Java Maven Avro Demo
=====================

Avro: https://avro.apache.org

Avro是一个类似于thrift或者protocol buffers序列化与反序列化框架。
需要提供一个 `.avsc`文件定义schema，然后用工具生成相应的java/python代码。

它的特点是它已经成为kafka的事实标准，所以如果项目里用到了kafka，一般都会用它。

How to run:

- `maven clean compile` 以根据`.avsc`生成java model代码
- 在IDE中运行 `Hello.java`