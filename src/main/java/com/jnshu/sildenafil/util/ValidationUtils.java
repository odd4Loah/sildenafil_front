package com.jnshu.sildenafil.util;

import com.jnshu.sildenafil.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author feifei
 */
@Slf4j
public class ValidationUtils {
    /**
     * 使用hibernate的注解来进行验证
     * 生成一个验证类；
     */
    private static Validator validator = Validation
            .byProvider(HibernateValidator.class)
            .configure()
            //快速失败，启用的话验证结果有错就停止
            .failFast(false)
            .buildValidatorFactory()
            .getValidator();

    /** 对有标注的bean进行校验
     * @param obj 待验证的bean
     * @param groups 定义的验证组
     * @param <T> 泛型
     * @throws ServiceException 抛出自定义异常
     */
    public static <T> void validate(T obj,Class<?>... groups) throws ServiceException {
        //校验的结果集，groups为校验组；
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj,groups);

        if (constraintViolations.size() > 0) {
            //使用lambda循环拼接校验错误信息
            throw new ServiceException(
                    String.format("args validation error:%s",
                            constraintViolations.stream().map(ConstraintViolation::getMessage)
                            .collect(Collectors.joining(","))));
        }
    }
}
