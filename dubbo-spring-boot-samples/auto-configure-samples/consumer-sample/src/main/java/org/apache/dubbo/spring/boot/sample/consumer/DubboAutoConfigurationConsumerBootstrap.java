/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.spring.boot.sample.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Dubbo Auto Configuration Consumer Bootstrap
 *
 * @since 2.7.0
 */
@SpringBootApplication
@EnableAutoConfiguration
public class DubboAutoConfigurationConsumerBootstrap  extends SpringBootServletInitializer {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(getClass());
    }

    public static void main(String[] args) { // Run as the generic Spring Boot Web(Servlet) Application
        SpringApplication application = new SpringApplication(DubboAutoConfigurationConsumerBootstrap.class);
        application.setWebApplicationType(WebApplicationType.SERVLET);
        application.run(args);
        
    }
}
