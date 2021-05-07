#linux image that i will use


 #name of the build repo from dockerHub
FROM  maven
 #copy the pom.xml file

COPY pom.xml /root/APMS/pom.xml

COPY src /root/local/APMS/src/main

WORKDIR /root/local/APMS

#COPY /target/APMS-1.0-SNAPSHOT.jar /apms.jar
#line for compilation
CMD ["java" , "-cp" , "/target/APMS-1.0-SNAPSHOT.jar" , "src.main.Main"]