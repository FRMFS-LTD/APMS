

 

#Dockerfile using to build the image of the app 


#maintenir of the app 
MAINTENIR Ref 
 #name of the build repo from dockerHub
FROM  maven
 #copy the pom.xml file

COPY pom.xml /root/APMS/pom.xml

COPY src /root/local/APMS/src/main


RUN \
	# download the dependency requiere for the app
	apt update -y && apt install default-jdk openjfx maven -y && \
	# construire the img with maven 
	mvn clean install dependency:go-offline -f /app/pom.xml;
	
WORKDIR /root/local/APMS

#COPY /target/APMS-1.0-SNAPSHOT.jar /apms.jar
#line for compilation
CMD ["java" , "-cp" , "/target/APMS-1.0-SNAPSHOT.jar" , "src.main.Main"]
