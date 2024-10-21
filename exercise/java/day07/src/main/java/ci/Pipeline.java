package ci;

import ci.dependencies.Config;
import ci.dependencies.Emailer;
import ci.dependencies.Logger;
import ci.dependencies.Project;

public class Pipeline {
    public static final String STATUS_SUCCESS = "success";
    private final Config config;
    private final Emailer emailer;
    private final Logger log;

    public Pipeline(Config config, Emailer emailer, Logger log) {
        this.config = config;
        this.emailer = emailer;
        this.log = log;
    }

    public void run(Project project) {
        if (!hasTestsPassed(project)) {
            sendEmail("Tests failed", log, config);
            return;
        }

        if (!hasDeploymentSuccessful(project)) {
            sendEmail("Deployment failed", log, config);
            return;
        }

        sendEmail("Deployment completed successfully", log, config);
    }

    private void sendEmail(String message, Logger log, Config config) {
        if (!config.sendEmailSummary()) {
            log.info("Email disabled");
            return;
        }

        log.info("Sending email");
        emailer.send(message);
    }

    private boolean hasDeploymentSuccessful(Project project) {
        if (STATUS_SUCCESS.equals(project.deploy())) {
            log.info("Deployment successful");
            return true;
        } else {
            log.error("Deployment failed");
            return false;
        }
    }

    private boolean hasTestsPassed(Project project) {
        if (!project.hasTests()) {
            log.info("No tests");
            return true;
        }

        if (STATUS_SUCCESS.equals(project.runTests())) {
            log.info("Tests passed");
            return true;
        } else {
            log.error("Tests failed");
            return false;
        }
    }
}
