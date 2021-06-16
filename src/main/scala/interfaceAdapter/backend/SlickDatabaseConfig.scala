package interfaceAdapter.backend

import slick.jdbc.JdbcBackend.Database

trait SlickDatabaseConfig {

  val db: Database = Database.forConfig("slick.dbs.default.db")
  val profile      = slick.jdbc.MySQLProfile
}

object SlickDatabaseConfig extends SlickDatabaseConfig
