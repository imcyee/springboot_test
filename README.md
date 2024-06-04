## 20240602 Fix conflict error for JAVA RUNTIME
### Error `org/springframework/boot/maven/RunMojo has been compiled by a more recent version of the Java Runtime (class file version 61.0), this version of the Java Runtime only recognizes class file versions up to 55.0`
- tried changing default java environment
- update maven
- potential mismatch usr/system java environment

Solved: Fixed with change JAVA_HOME env (usr config mismatch)
### Fix: export JAVA_HOME=/usr/lib/jvm/java-18-openjdk-amd64


## Most of it follow tutorial 
https://spring.io/guides/gs/rest-service

## and entity framework using this
https://spring.io/guides/gs/accessing-data-jpa


