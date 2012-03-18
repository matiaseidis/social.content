package com.mati.demo.context;

import groovy.lang.GroovyObject;

import org.apache.log4j.Logger;
import org.springframework.aop.framework.autoproxy.AutoProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.CannotLoadBeanClassException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.ClassUtils;

/**
 * Finds all objects in the bean factory that implement GroovyObject and only GroovyObject, and sets the
 * AutoProxyUtils.PRESERVE_TARGET_CLASS_ATTRIBUTE value to true.  This will, in the case when a proxy
 * is necessary, force the creation of a CGLIB subclass proxy, rather than a dynamic JDK proxy, which
 * would create a useless proxy that only implements the methods of GroovyObject.
 *
 * @author caleb
 */
public class GroovyObjectTargetClassPreservingBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    private static final Logger logger = null; //LogFactory.getLog(GroovyObjectTargetClassPreservingBeanFactoryPostProcessor.class);

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        for (String beanDefName : beanFactory.getBeanDefinitionNames()) {
            BeanDefinition bd = beanFactory.getBeanDefinition(beanDefName);
            //ignore abstract definitions (parent beans)
            if (bd.isAbstract())
                continue;
            String className = bd.getBeanClassName();
            //ignore definitions with null class names
            if (className == null)
                continue;
            Class<?> beanClass;
            try {
                beanClass = ClassUtils.forName(className, beanFactory.getBeanClassLoader());
            }
            catch (ClassNotFoundException e) {
                throw new CannotLoadBeanClassException(bd.getResourceDescription(), beanDefName, bd.getBeanClassName(), e);
            }
            catch (LinkageError e) {
                throw new CannotLoadBeanClassException(bd.getResourceDescription(), beanDefName, bd.getBeanClassName(), e);
            }

            Class<?>[] interfaces = beanClass.getInterfaces();
            if (interfaces.length == 1 && interfaces[0] == GroovyObject.class) {
                //logger.debug("Setting attribute {} to true for bean {}", AutoProxyUtils.PRESERVE_TARGET_CLASS_ATTRIBUTE, beanDefName);
                bd.setAttribute(AutoProxyUtils.PRESERVE_TARGET_CLASS_ATTRIBUTE, true);
            }
        }
    }
}
