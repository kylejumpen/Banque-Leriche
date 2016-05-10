Afin de pouvoir tester le projet banque-leriche:
 * Exporter la variable JBOSS_HOME
 * Lancer wildfly sur le port 8001: $JBOSS_HOME/bin/standalone.sh -Djboss.socket.binding.port-offset=-79
        ajouter l'option -b=<addressIP> pour tester sur un poste distant
 * se placer à la racine du projet et compiler: ./compile.sh
 * compiler le client:
 * Lancer le client

