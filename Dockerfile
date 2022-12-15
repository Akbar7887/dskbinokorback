FROM openjdk:11
#WORKDIR /app
COPY target/dskbinokor.jar /dskbinokor.jar
EXPOSE 8089
ENTRYPOINT ["java","-jar","/dskbinokor.jar"]

