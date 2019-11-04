#################### BUILD STEP
FROM maven:3.6.1-jdk-8 as builder

#Working directory
WORKDIR /usr/src

#Copy project
COPY . .

#Check content
RUN ls /usr/src

#Package
RUN mvn package -DskipTests

################### RUN STP

FROM java:8-jre-alpine

#Create destination folder
RUN mkdir /destination

#Copy package
COPY --from=builder /usr/src/target/contact-0.0.1-SNAPSHOT.jar /destination/app.jar

RUN ls /destination/app.jar

#Run app
ENTRYPOINT ["java","-jar","/destination/app.jar"]
