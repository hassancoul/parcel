package com.devhas.customer;


import com.devhas.client.application.annotation.UseCase;
import com.devhas.client.application.useCases.BaseUseCase;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class ArchitectureTest {

    @Test
    public void le_domain_ne_doit_dependre_d_aucun_autre_package() {
        JavaClasses domainClasses = new ClassFileImporter().importPackages("com.devhas.client");


        ArchRule regle = noClasses()
                .that()
                .resideInAPackage("..domain..")
                .should()
                .dependOnClassesThat()
                .resideInAnyPackage("..application..", "..infrastructure..", "..interfaces..");


        regle.check(domainClasses);
    }

    @Test
    public void le_domain_ne_doit_pas_avoir_de_dependance_spring() {
        JavaClasses domainClasses = new ClassFileImporter().importPackages("com.devhas.client");


        ArchRule regle = noClasses()
                .that()
                .resideInAPackage("..domain..")
                .should()
                .dependOnClassesThat()
                .resideInAnyPackage("org.springframework");

        regle.check(domainClasses);
    }

    @Test
    public void les_use_case_doivent_etendre_base_use_case_et_etre_annote_avec_use_case() {
        JavaClasses domainClasses = new ClassFileImporter().importPackages("com.devhas.client");

        ArchRule regle = classes()
                .that()
                .resideInAPackage("..application.useCases")
                .and()
                .areNotInterfaces()
                .should()
                .implement(BaseUseCase.class)
                .andShould()
                .beAnnotatedWith(UseCase.class);

        regle.check(domainClasses);
    }
}
