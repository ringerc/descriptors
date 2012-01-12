package org.jboss.datasources.impl; 

import org.jboss.shrinkwrap.descriptor.spi.node.Node;
import org.jboss.shrinkwrap.descriptor.gen.TestDescriptorImpl;
import org.jboss.shrinkwrap.descriptor.api.Descriptors;import org.junit.Test;
import static org.junit.Assert.*;
import org.jboss.datasources.api.RecoverType;
import java.util.ArrayList;
import java.util.List;
import org.jboss.shrinkwrap.descriptor.api.Child;
import org.jboss.shrinkwrap.descriptor.impl.base.XMLDate;
import org.jboss.shrinkwrap.descriptor.impl.base.Strings;
import org.jboss.shrinkwrap.descriptor.api.DescriptorExporter;
import org.jboss.datasources.api.DsSecurityType;
import org.jboss.datasources.impl.DsSecurityTypeImpl;
import org.jboss.datasources.api.ExtensionType;
import org.jboss.datasources.impl.ExtensionTypeImpl;

public class RecoverTypeImplTestCase
{   
   @Test
   public void testNullArg() throws Exception
   {
      TestDescriptorImpl provider = new TestDescriptorImpl("test");
      RecoverType<TestDescriptorImpl> type = new RecoverTypeImpl<TestDescriptorImpl>(provider, "recoverType", provider.getRootNode());
      TestDescriptorImpl.testNullArgs(type);
   }
   
   @Test
   public void testRecoverCredential() throws Exception
   {
      TestDescriptorImpl provider = new TestDescriptorImpl("test");
      RecoverType<TestDescriptorImpl> type = new RecoverTypeImpl<TestDescriptorImpl>(provider, "recoverType", provider.getRootNode());
      type.getOrCreateRecoverCredential().up();
      type.removeRecoverCredential();
      assertNull(provider.getRootNode().getSingle("recover-credential"));
   }

   
   @Test
   public void testRecoverPlugin() throws Exception
   {
      TestDescriptorImpl provider = new TestDescriptorImpl("test");
      RecoverType<TestDescriptorImpl> type = new RecoverTypeImpl<TestDescriptorImpl>(provider, "recoverType", provider.getRootNode());
      type.getOrCreateRecoverPlugin().up();
      type.removeRecoverPlugin();
      assertNull(provider.getRootNode().getSingle("recover-plugin"));
   }

   
   @Test
   public void testNoRecovery() throws Exception
   {
      TestDescriptorImpl provider = new TestDescriptorImpl("test");
      RecoverType<TestDescriptorImpl> type = new RecoverTypeImpl<TestDescriptorImpl>(provider, "recoverType", provider.getRootNode());
      type.noRecovery(true);
      assertTrue(type.isNoRecovery());
      type.removeNoRecovery();
      assertFalse(type.isNoRecovery());
   }
}