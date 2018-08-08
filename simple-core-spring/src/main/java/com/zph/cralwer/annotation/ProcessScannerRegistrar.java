package com.zph.cralwer.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

import java.util.Set;

/**
 * @author zph  on 2018/7/27
 */
public class ProcessScannerRegistrar implements ImportBeanDefinitionRegistrar {


    protected Logger logger = LoggerFactory.getLogger(getClass());

    private BeanNameGenerator beanNameGenerator;

    public ProcessScannerRegistrar() {
        this.beanNameGenerator = new AnnotationBeanNameGenerator();
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry) {
        logger.info("Invoke Method registerBeanDefinitions ...");
        AnnotationAttributes annoAttrs = AnnotationAttributes.fromMap(annotationMetadata.getAnnotationAttributes(ProcessScan.class.getName()));
        String basePackage = annoAttrs.getString("value");
        ClassPathScanningCandidateComponentProvider beanScanner = new ClassPathScanningCandidateComponentProvider(false);
        TypeFilter includeFilter = new AnnotationTypeFilter(Process.class);
        beanScanner.addIncludeFilter(includeFilter);
        Set<BeanDefinition> beanDefinitions = beanScanner.findCandidateComponents(basePackage);
        for (BeanDefinition beanDefinition : beanDefinitions) {
            String beanName = beanNameGenerator.generateBeanName(beanDefinition, registry);
            registry.registerBeanDefinition(beanName, beanDefinition);
        }
    }
}
