/*
 * SonarQube
 * Copyright (C) 2009-2018 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.db.dialect;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PostgreSqlTest {

  private PostgreSql underTest = new PostgreSql();

  @Test
  public void matchesJdbcURL() {
    assertThat(underTest.matchesJdbcURL("jdbc:postgresql://localhost/sonar")).isTrue();
    assertThat(underTest.matchesJdbcURL("jdbc:hsql:foo")).isFalse();
  }

  @Test
  public void should_set_connection_properties() {
    assertThat(underTest.getConnectionInitStatements()).isEqualTo(PostgreSql.INIT_STATEMENTS);
  }

  @Test
  public void testBooleanSqlValues() {
    assertThat(underTest.getTrueSqlValue()).isEqualTo("true");
    assertThat(underTest.getFalseSqlValue()).isEqualTo("false");
  }

  @Test
  public void should_configure() {
    assertThat(underTest.getId()).isEqualTo("postgresql");
    assertThat(underTest.getDefaultDriverClassName()).isEqualTo("org.postgresql.Driver");
    assertThat(underTest.getValidationQuery()).isEqualTo("SELECT 1");
  }

  @Test
  public void testFetchSizeForScrolling() {
    assertThat(underTest.getScrollDefaultFetchSize()).isEqualTo(200);
  }

  @Test
  public void postgres_does_supportMigration() {
    assertThat(underTest.supportsMigration()).isTrue();
  }

  @Test
  public void getSqlFromDual() {
    assertThat(underTest.getSqlFromDual()).isEqualTo("");
  }
}
