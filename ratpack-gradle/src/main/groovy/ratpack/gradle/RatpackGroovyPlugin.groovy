/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ratpack.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.GroovyPlugin

class RatpackGroovyPlugin implements Plugin<Project> {

  @Override
  void apply(Project project) {
    project.plugins.apply(RatpackPlugin)
    project.plugins.apply(GroovyPlugin)

    project.mainClassName = "ratpack.groovy.GroovyRatpackMain"

    def ratpackExtension = project.extensions.getByType(RatpackExtension)

    def configurationContainer = project.configurations
    (configurationContainer.findByName("implementation") ?: configurationContainer.findByName("compile")).
      dependencies.add(ratpackExtension.groovy)

    (configurationContainer.findByName("testImplementation") ?: configurationContainer.findByName("testCompile")).
      dependencies.add(ratpackExtension.groovyTest)
  }

}
