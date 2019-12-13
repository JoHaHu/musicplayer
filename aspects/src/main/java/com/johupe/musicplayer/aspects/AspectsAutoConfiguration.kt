package com.johupe.musicplayer.aspects

import org.springframework.context.annotation.AdviceMode
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableLoadTimeWeaving
import org.springframework.context.annotation.aspectj.EnableSpringConfigured
import org.springframework.scheduling.annotation.EnableAsync

@Configuration
@EnableLoadTimeWeaving
@EnableSpringConfigured
@EnableAsync(mode = AdviceMode.ASPECTJ)
@ComponentScan
class AspectsAutoConfiguration{



}
