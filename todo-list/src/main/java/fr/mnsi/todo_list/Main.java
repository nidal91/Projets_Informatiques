package fr.mnsi.todo_list;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import fr.mnsi.todo_list.model.PersonDB;
import java.sql.SQLException;
import java.lang.ClassNotFoundException;

public class Main {

    // Valeurs par défaut pour l'hôte et le port
    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_PORT = 8080;

    // Répertoire de base et ressources supplémentaires pour Tomcat
    private static final String DOC_BASE = ".";
    private static final String ADDITION_WEB_INF_CLASSES = "target/classes";
    private static final String WEB_APP_MOUNT = "/WEB-INF/classes";
    private static final String INTERNAL_PATH = "/";

    // Valeurs de configuration
    private static int port = DEFAULT_PORT;
    private static String host = DEFAULT_HOST;

    /**
     * Charge les propriétés de configuration à partir du fichier et des arguments de ligne de commande.
     * @param args Arguments de ligne de commande
     * @return Propriétés chargées
     */
    private static Properties loadConfig(String[] args) {
        try {
            // Charge le fichier de configuration par défaut
            InputStream config = Main.class.getResourceAsStream("/config.properties");
            Properties prop = new Properties();
            prop.load(config);
            config.close();

            if (args.length >= 2 && args[0].equals("-conf")) {
                // Charge le fichier de configuration spécifié dans les arguments de ligne de commande
                config = new FileInputStream(args[1]);
                prop = new Properties(prop);
                prop.load(config);
                config.close();
            } else if (args.length != 0) {
                System.out.println("Utilisez -conf <fichier> pour spécifier un fichier de configuration.");
                System.exit(1);
            }

            // Lit le port du serveur à partir de la configuration
            String sport = prop.getProperty("server.port", DEFAULT_PORT + "");
            port = Integer.parseInt(sport);
            if (port <= 0) {
                throw new Exception("Numéro de port invalide dans le paramètre server.port");
            }

            // Lit l'hôte du serveur à partir de la configuration
            host = prop.getProperty("server.host", DEFAULT_HOST);

            return prop;

        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return new Properties();
    }

    public static void main(String[] args) {
        // Charge les propriétés de configuration
        Properties prop = loadConfig(args);

        Tomcat tomcat = new Tomcat();
        try {
            // Crée un répertoire de travail temporaire pour Tomcat
            Path tmpDir = Files.createTempDirectory("tomcat." + port + ".");
            System.err.println("Définition du répertoire de travail : " + tmpDir);
            tmpDir.toFile().deleteOnExit();
            tomcat.setBaseDir(tmpDir.toString());

            // Définit l'hôte, la base de l'application et le port de Tomcat
            tomcat.setHostname(host);
            tomcat.getHost().setAppBase(DOC_BASE);
            tomcat.setPort(port);
            tomcat.getConnector();
            tomcat.enableNaming();

            String[] components = Main.class.getName().split("[.]");
            if (components.length < 2) {
                // Pas dans un package ?
                System.exit(1);
            }
            String contextPath = components[components.length - 2];

            // Ajoute l'application web à Tomcat
            Context context = tomcat.addWebapp("/" + contextPath, DOC_BASE);
            ServletContext ctx = context.getServletContext();

            // Définit la configuration de la base de données en tant qu'attributs du contexte servlet
            String[] keys = {"db.host", "db.port", "db.name", "db.user", "db.pass"};
            for (String k : keys) {
                ctx.setAttribute(k, prop.getProperty(k, ""));
            }

            // Ajoute des ressources supplémentaires au contexte de Tomcat
            File classes = new File(ADDITION_WEB_INF_CLASSES);
            String base = classes.getAbsolutePath();
            WebResourceRoot resources = new StandardRoot(context);
            resources.addPreResources(new DirResourceSet(resources, WEB_APP_MOUNT, base, INTERNAL_PATH));
            System.err.println("Ressources supplémentaires : " + resources);
            context.setResources(resources);

            // Démarre le serveur Tomcat
            tomcat.start();
            System.out.println("Serveur démarré - appuyez sur [Entrée] pour l'arrêter");
            System.in.read();
            tomcat.stop();
            System.exit(0);

        } catch (LifecycleException exception) {
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Impossible de créer le répertoire de travail de Tomcat.");
            System.exit(1);
        }
    }
}
