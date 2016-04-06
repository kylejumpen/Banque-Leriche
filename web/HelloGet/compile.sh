CLASSPATH=./src:$JBOSS_HOME/modules/system/layers/base/javax/servlet/api/main/jboss-servlet-api_3.1_spec-1.0.0.Final.jar


javac -cp $CLASSPATH -sourcepath src -d ./WEB-INF/classes src/*.java

jar cf HelloGet.war WEB-INF helloGet.html
cp HelloGet.war $JBOSS_HOME/standalone/deployments
