configuration
-------------
Skip if you using pre-configured zip archive
- go to project root
- build: mvn clean install
- copy target/*war to the tomcat webapps folder and start tomcat

running
-------
- start the tomcat server
- the monitor application will be available at:
http://localhost:8080/monitor-web-1.0-SNAPSHOT/
- the health status of the application will be available at:
http://localhost:8080/monitor-web-1.0-SNAPSHOT/status
- the manifest version details will be available at:
http://localhost:8080/monitor-web-1.0-SNAPSHOT/manifest



