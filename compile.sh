mvn clean install
rm $JBOSS_HOME/standalone/deployments/AnnuaireRest-1.0.war
cp target/AnnuaireRest-1.0.war $JBOSS_HOME/standalone/deployments/
./compileClient.sh
