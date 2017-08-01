/*
 * Copyright 2010-2017 Boxfuse GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.flywaydb.core.internal.dbsupport.mysql;

import org.flywaydb.core.migration.ConcurrentMigrationTestCase;
import org.flywaydb.core.internal.util.jdbc.DriverDataSource;
import org.junit.ClassRule;
import org.junit.experimental.categories.Category;
import org.flywaydb.core.DbCategory;
import org.testcontainers.containers.MySQLContainer;

import javax.sql.DataSource;
import java.util.Properties;

import static org.flywaydb.core.internal.dbsupport.mysql.MySQLMigrationMediumTest.DOCKER_IMAGE_NAME;

/**
 * Test to demonstrate the migration functionality using MySQL.
 */
@Category(DbCategory.MySQL.class)
public class MySQLConcurrentMigrationMediumTest extends ConcurrentMigrationTestCase {
    @ClassRule
    public static MySQLContainer mysql = new MySQLContainer(DOCKER_IMAGE_NAME);

    @Override
    protected DataSource createDataSource(Properties customProperties) {
        return new DriverDataSource(Thread.currentThread().getContextClassLoader(), null,
                mysql.getJdbcUrl(), "root", mysql.getPassword(), null);
    }
}