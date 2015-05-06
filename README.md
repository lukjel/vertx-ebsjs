# vertx-ebsjs
Problem with EventBus and SockJS

Hot to reproduce error:

1. Start t-srv (first build - mvn clean package, run as fat-jar: java -jar target/t-srv-1.0.0-SNAPSHOT-fat.jar)
2. In another terminal start t-con (first build - mvn clean package, run as fat-jar: java -jar target/t-con-1.0.0-SNAPSHOT-fat.jar)
3. Open web browser: localhost:8080/test/index.html
4. You should see:
Here result:
{"name":"tim","age":587}

5. Kill t-con (just Ctrl-C). Wait a little (15 seconds).
6. Start again t-con. Wait again 15 seconds.
7. Refresh web browser... first time - there is line with json. After second refresh - it works.
8. When you repeat steps 5-6 - refreshing webpage gives strange results. 1st - no json, 2nd - ok, 3rd - no json, 4th - ok... etc.

My system:
OSX, 
java version "1.8.0_45"
Java(TM) SE Runtime Environment (build 1.8.0_45-b14)
Java HotSpot(TM) 64-Bit Server VM (build 25.45-b02, mixed mode)
Web-Browser - safari 8.0.5 (10600.5.17).
