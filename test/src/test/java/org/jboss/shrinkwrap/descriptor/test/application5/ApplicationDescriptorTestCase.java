/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.shrinkwrap.descriptor.test.application5;

import static org.jboss.shrinkwrap.descriptor.test.util.XmlAssert.assertPresenceUsingXPath;

import java.util.logging.Logger;

import junit.framework.Assert;

import org.jboss.shrinkwrap.descriptor.api.Descriptors;
import org.jboss.shrinkwrap.descriptor.api.application5.ApplicationDescriptor;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Tests to ensure that the API contract for {@link ApplicationDescriptor}
 * is working as expected
 *
 * @author <a href="mailto:aslak@redhat.com">Aslak Knutsen</a>
 * @author <a href="mailto:alr@jboss.org">Andrew Lee Rubinger</a>
 */
//TODO This is a placeholder and POC.  Must be expanded upon greatly for coverage
public class ApplicationDescriptorTestCase
{
   //-------------------------------------------------------------------------------------||
   // Class Members ----------------------------------------------------------------------||
   //-------------------------------------------------------------------------------------||

   /**
    * Logger
    */
   private static final Logger log = Logger.getLogger(ApplicationDescriptorTestCase.class.getName());

   /**
    * Name of the fully-populated XML
    */
   private static final String NAME_EAR_XML = "xml/application50/Ear5xEverything_testEverything.xml";

   //-------------------------------------------------------------------------------------||
   // Instance Members -------------------------------------------------------------------||
   //-------------------------------------------------------------------------------------||

   /**
    * Default, empty application descriptor
    */
   private ApplicationDescriptor emptyDescriptor;

   /**
    * Fully-populated application descriptor
    */
   private ApplicationDescriptor fullyPopulatedDescriptor;

   //-------------------------------------------------------------------------------------||
   // Lifecycle --------------------------------------------------------------------------||
   //-------------------------------------------------------------------------------------||   

   @Before
   public void createEmptyDescriptor()
   {
      emptyDescriptor = Descriptors.create(ApplicationDescriptor.class);
   }

   @Before
   public void createFullyPopulatedDescriptor()
   {
      fullyPopulatedDescriptor = Descriptors.importAs(ApplicationDescriptor.class).from(
            Thread.currentThread().getContextClassLoader().getResourceAsStream(NAME_EAR_XML));
      log.info(fullyPopulatedDescriptor.exportAsString());
   }

   //-------------------------------------------------------------------------------------||
   // Tests ------------------------------------------------------------------------------||
   //-------------------------------------------------------------------------------------||

   @Test
   public void shouldCreateDefaultName() throws Exception
   {
      Assert.assertEquals("application.xml", emptyDescriptor.getDescriptorName());
   }

   @Test
   public void shouldBeAbleToSetName() throws Exception
   {
      final String nonDefaultName = "test.xml";
      Assert.assertEquals(nonDefaultName, Descriptors.create(ApplicationDescriptor.class, nonDefaultName)
            .getDescriptorName());
   }

   @Test
   public void shouldBeAbleToAddDescription() throws Exception
   {
      final String descName = "Description";
      final String desc = emptyDescriptor.description(descName).exportAsString();
      assertPresenceUsingXPath(desc, "/application/description", descName);
   }

   @Test
   public void shouldBeAbleToAddDisplayName() throws Exception
   {
      final String displayName = "Display Name";
      final String desc = emptyDescriptor.displayName(displayName).exportAsString();
      assertPresenceUsingXPath(desc, "/application/display-name", displayName);
   }

   @Test
   public void shouldBeAbleToAddLibraryDirectory() throws Exception
   {
      final String libraryDir = "Library Dir";
      final String desc = emptyDescriptor.libraryDirectory(libraryDir).exportAsString();
      assertPresenceUsingXPath(desc, "/application/library-directory", libraryDir);
   }

   @Ignore
   //TODO From porting; should setting EJB Module be supported?
   @Test
   public void shouldBeAbleToAddAEJBModule() throws Exception
   {
      //      String desc = emptyDescriptor.setEjbModule(moduleName).exportAsString();
      //
      //      assertXPath(desc, "/application/module/ejb", moduleName);
   }

   @Ignore
   //TODO From porting; should setting Connector Module be supported?
   @Test
   public void shouldBeAbleToAddAConnectorModule() throws Exception
   {
      //      String desc = emptyDescriptor.connectorModule(moduleName).exportAsString();
      //
      //      assertXPath(desc, "/application/module/connector", moduleName);
   }

   @Ignore
   //TODO From porting; should setting Java Module be supported?
   @Test
   public void shouldBeAbleToAddAJavaModule() throws Exception
   {
      //      String desc = emptyDescriptor.javaModule(moduleName).exportAsString();
      //
      //      assertXPath(desc, "/application/module/java", moduleName);
   }

   @Ignore
   //TODO From porting; should setting Web Module be supported?
   @Test
   public void shouldBeAbleToAddAWebModule() throws Exception
   {
      
      //      String desc = emptyDescriptor.webModule(moduleName, contextRoot).exportAsString();
      //
      //      assertXPath(desc, "/application/module/web/web-uri", moduleName);
      //      assertXPath(desc, "/application/module/web/context-root", contextRoot);
   }

   @Test
   public void shouldBeAbleToAddASecurityRole() throws Exception
   {
      final String roleName = "Security Role";
      String desc = emptyDescriptor.createSecurityRole().roleName(roleName).up().exportAsString();
      assertPresenceUsingXPath(desc, "/application/security-role/role-name", roleName);
   }

   @Test
   public void shouldBeAbleToAddASecurityRoleWithDescription() throws Exception
   {
      final String roleDescription = "Security Description";
      String desc = emptyDescriptor.createSecurityRole().description(roleDescription).up().exportAsString();
      assertPresenceUsingXPath(desc, "/application/security-role/description", roleDescription);
   }

}
