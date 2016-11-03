package com.saferich.core.validate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.saferich.core.annotation.NotBlank;
import com.saferich.core.annotation.NotEmpty;
import com.saferich.core.annotation.NotNull;
import com.saferich.core.util.CollectionUtils;

/**
 * @author Jason
 */

public abstract class ParameterValidate {
    private static Logger logger = LoggerFactory.getLogger(ParameterValidate.class);

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ValidateResult validate() {
        try {
            Field[] fields = this.getClass().getDeclaredFields();
            if (fields.length > 0) {
                for (Field field : fields) {
                    field.setAccessible(true);

                    Annotation[] annotations = field.getAnnotations();

                    if (annotations.length > 0) {
                        for (Annotation annotation : annotations) {
                            if (annotation.annotationType() == NotNull.class) {
                                NotNull notNull = (NotNull) annotation;
                                if (notNull != null && field.get(this) == null) {
                                    return new ValidateResult(false, notNull.value());
                                }
                            }

                            if (annotation.annotationType() == NotBlank.class) {
                                NotBlank notBlank = (NotBlank) annotation;
                                if (notBlank != null && (field.get(this) == null || "".equals(field.get(this)))) {
                                    return new ValidateResult(false, notBlank.value());
                                }
                            }

                            if (annotation.annotationType() == NotEmpty.class) {
                                NotEmpty notEmpty = (NotEmpty) annotation;
                                if (notEmpty != null) {
                                    if (field.getType() == Collection.class) {
                                        Collection<List> collection = (Collection<List>) field.get(this);
                                        if (CollectionUtils.isEmpty(collection)) {
                                            return new ValidateResult(false, notEmpty.value());
                                        }
                                    } else if (field.getType() == Map.class) {
                                        Map map = (Map) field.get(this);
                                        if (MapUtils.isEmpty(map)) {
                                            return new ValidateResult(false, notEmpty.value());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            logger.error("parameter validate failed!", e);
        } catch (IllegalAccessException e) {
            logger.error("parameter validate failed!", e);
        }
        return new ValidateResult(true);
    }

    public class ValidateResult {
        public boolean success;
        public String message;

        public ValidateResult(boolean success) {
            this.success = success;
            this.message = "SUCCESS";
        }

        public ValidateResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }
}
