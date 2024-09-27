package com.devhas.client.infrastructure.config;

import com.devhas.client.application.annotation.TransactionalUseCase;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@Aspect
@Component
public class TransactionalAspect {
    private final PlatformTransactionManager transactionManager;

    public TransactionalAspect(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Around("@annotation(transactionalUseCase)")
    public Object manageTransaction(ProceedingJoinPoint joinPoint, TransactionalUseCase transactionalUseCase) throws Throwable {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.setReadOnly(transactionalUseCase.readOnly());
        if (transactionalUseCase.timeout() > 0) {
            transactionTemplate.setTimeout(transactionalUseCase.timeout());
        }

        return transactionTemplate.execute(status -> {
            try {
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                for (String className : transactionalUseCase.rollbackFor()) {
                    try {
                        Class<?> rollbackClass = Class.forName(className);
                        if (rollbackClass.isInstance(throwable)) {
                            status.setRollbackOnly();
                            break;
                        }
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
                throw new RuntimeException(throwable);
            }
        });
    }
}
