mvn clean install
rm $JBOSS_HOME/standalone/deployments/Banque-1.0.war
cp target/Banque-1.0.war $JBOSS_HOME/standalone/deployments/

