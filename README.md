## Indexes
* Rules
* Dependencies

## Rules
### Directories
* 项目ROOT根目录即为jar文件存放的文件夹
* 在ROOT目录下必须有`logs`文件夹，存放日志文件
* 在ROOT目录下必须有`configs`文件夹，存放配置文件
* log4j会查找logs文件夹，并向其中写入日志内容
	* log4j的配置文件中，日志文件的配置必须使用${WORKDIR}这个变量
	* log4j.appender.DLD.File = ${WORKDIR}/logs/debug.log
	

## Dependencies
* `io.netty` `netty` `3.6.3.Final`
* `log4j` `log4j` `1.2.14`