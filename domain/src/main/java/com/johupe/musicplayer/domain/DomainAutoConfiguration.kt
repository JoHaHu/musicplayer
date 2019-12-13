package com.johupe.musicplayer.domain

import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@ComponentScan
@EnableJpaRepositories
@ImportAutoConfiguration(classes =[JpaRepositoriesAutoConfiguration::class, HibernateJpaAutoConfiguration::class])
class DomainAutoConfiguration
