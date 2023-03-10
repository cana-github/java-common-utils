package th.co.cana.framework.utils.bean;

import th.co.cana.framework.utils.BeanUtils;
import th.co.cana.framework.utils.ClassUtils;
import th.co.cana.framework.utils.ReflectionUtils;
import th.co.cana.framework.utils.Validators;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author supot.jdev
 * @version 1.0
 */
public class CopyBean<T> {
    private final Class<T> clazz;
    private final CopyBeanOptions options;
    private List<Field> targetFields;
    private Map<String, Field> sourceFields;
    private String sourceClass;

    public CopyBean(Class<T> targetClass) {
        this.clazz = targetClass;
        this.options = CopyBeanOptions.defaultOptions();
    }

    public CopyBean(Class<T> clazz, CopyBeanOptions options) {
        if (options == null) {
            options = CopyBeanOptions.defaultOptions();
        }
        this.clazz = clazz;
        this.options = options;
    }

    public List<T> copyProperties(List<?> sources, String... ignores) {
        this.initialTargetField();
        if (Validators.isEmptyOne(sources, targetFields)) {
            return null;
        }

        this.initialSourceFields(sources, ignores);
        if (Validators.isEmpty(sourceFields)) {
            return null;
        }

        List<T> items = new ArrayList<>();
        for (Object source : sources) {
            if (source == null) {
                continue;
            }
            T result = copy(source);
            items.add(result);
        }

        return items;
    }

    public T copyProperties(Object source, String... ignores) {
        this.initialTargetField();
        if (Validators.isEmptyOne(source, targetFields)) {
            return null;
        }

        this.initialSourceFields(source, ignores);
        if (Validators.isEmpty(sourceFields)) {
            return null;
        }

        return copy(source);
    }

    private T copy(Object source) {
        T result = ClassUtils.newInstance(clazz);
        for (Field field : targetFields) {
            Field sourceField = sourceFields.get(field.getName());
            if (sourceField == null) {
                continue;
            }
            Object value = BeanUtils.getValue(source, sourceField);
            if (value != null) {
                BeanUtils.setValue(result, field, value);
            }
        }
        return result;
    }

    private void initialTargetField() {
        if (targetFields == null && clazz != null) {
            targetFields = ReflectionUtils.getDeclaredFields(clazz, options.getTargetIgnores());
        }
    }

    private void initialSourceFields(List<?> objects, String... ignores) {
        Object source = objects.stream()
                .filter(Objects::nonNull)
                .findFirst().orElse(null);
        initialSourceFields(source, ignores);
    }

    private void initialSourceFields(Object source, String... ignores) {
        if (source == null || source.getClass().getName().equals(sourceClass)) {
            return;
        }

        if (ignores != null && ignores.length > 0) {
            options.resetSourceIgnores();
            options.sourceIgnores(ignores);
        }

        sourceClass = source.getClass().getName();
        sourceFields = ReflectionUtils.getDeclaredFieldsAsMap(source.getClass(), options.getSourceIgnores());
    }
}
