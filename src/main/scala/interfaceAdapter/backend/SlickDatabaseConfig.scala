package interfaceAdapter.backend

import slick.jdbc.JdbcBackend.Database

trait SlickDatabaseConfig {

  val db: Database = Database.forConfig("slick.dbs.default.db")
}

object SlickDatabaseConfig extends SlickDatabaseConfig
