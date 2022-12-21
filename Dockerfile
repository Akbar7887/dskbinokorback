FROM openjdk:11


COPY target/dskbinokor.jar dskbinokor.jar

EXPOSE 8089

ENTRYPOINT ["java","-jar","dskbinokor.jar"]

