Afin de pouvoir tester le projet banque-leriche:
 * Mise en place de la base de données:
        - mysql -u <user> -p
        - Dans mysql: source <cheminVersLeProjet>/Banque-Leriche/banqueSQL/banque-schema.sql
        - Dans mysql: source <cheminVersLeProjet>/Banque-Leriche/banqueSQL/banque-data.sql
        - Dans <cheminVersLeProjet>/Banque-Leriche/src/main/resources/hibernate.cfg.xml, changer le nom d'utilisateur
        et le mot de passe de votre utilisateur MySQL
 * Exporter la variable JBOSS_HOME
 * Lancer wildfly sur le port 8001: $JBOSS_HOME/bin/standalone.sh -Djboss.socket.binding.port-offset=-79
        ajouter l'option -b=<addressIP> pour tester sur un poste distant
 * se placer à la racine du projet et compiler: ./compile.sh
 * Client:      Si vous souhaitez l'executer, il suffit d'executer le ClientLeR.jar qui se trouve dans l'archive.
                Si vous souhaitez le compiler, il vous faudra installer netbeans et executer compileAppClient.sh qui 
                  se trouve à la racine du projet.
