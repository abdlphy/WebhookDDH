package org.github.webhook;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

import io.quarkus.runtime.Startup;

import javax.sql.DataSource;
import java.sql.Connection;
@Startup
@ApplicationScoped
public class DatabaseHealthChecker {

    private static final Logger LOG = Logger.getLogger(DatabaseHealthChecker.class);

    final DataSource dataSource;

    public DatabaseHealthChecker(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void verifyConnection() {
        try (Connection conn = dataSource.getConnection()) {
            if (conn.isValid(2)) {
                LOG.info("✅ DB Connected Successfully!");
            } else {
                LOG.error("❌ DB Connection is not valid.");
            }
        } catch (Exception e) {
            LOG.error("❌ Error while connecting to DB: ", e);
        }
    }
}
