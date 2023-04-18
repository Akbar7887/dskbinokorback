FROM openjdk:17
EXPOSE 8089


COPY target/dskbinokor.jar dskbinokor.jar
#ADD target/dskbinokor.jar dskbinokor.jar
#ADD entrypoint.sh entrypoint.sh


ENTRYPOINT ["java","-jar","dskbinokor.jar"]
#ENTRYPOINT ["sh", "/entrypoint.sh"]
