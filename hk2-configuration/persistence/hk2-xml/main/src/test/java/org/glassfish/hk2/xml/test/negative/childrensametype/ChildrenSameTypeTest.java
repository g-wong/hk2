/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2014-2015 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package org.glassfish.hk2.xml.test.negative.childrensametype;

import java.net.URL;

import org.junit.Assert;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.xml.api.XmlRootHandle;
import org.glassfish.hk2.xml.api.XmlService;
import org.glassfish.hk2.xml.test.utilities.Utilities;
import org.junit.Test;

/**
 * @author jwells
 *
 */
public class ChildrenSameTypeTest {

    /**
     * FooBar has two children of the same type.  Since we can not easily
     * disambiguate, at least we want to fail fast.  If the decision is
     * made to change this implementation, this test will need to be
     * removed
     * 
     * @throws Exception
     */
    @Test // @org.junit.Ignore
    public void testNegativeTwoChildrenWithSameType() throws Exception {
        ServiceLocator locator = Utilities.createLocator();
        XmlService xmlService = locator.getService(XmlService.class);
        
        URL url = getClass().getClassLoader().getResource("foobar.xml");
        
        XmlRootHandle<FooBarBean> handle = xmlService.unmarshall(url.toURI(), FooBarBean.class);
        
        FooBarBean fooBar = handle.getRoot();
        Assert.assertNotNull(fooBar);
        
        Assert.assertEquals(2, fooBar.getFoo().size());
        Assert.assertEquals("foo1", fooBar.getFoo().get(0).getData());
        Assert.assertEquals("foo2", fooBar.getFoo().get(1).getData());
        
        Assert.assertEquals(2, fooBar.getBar().size());
        Assert.assertEquals("bar1", fooBar.getBar().get(0).getData());
        Assert.assertEquals("bar2", fooBar.getBar().get(1).getData());
    }

}
