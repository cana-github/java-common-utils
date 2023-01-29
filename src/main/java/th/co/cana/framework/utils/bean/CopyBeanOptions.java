package th.co.cana.framework.utils.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author supot.jdev
 * @version 1.0
 */
public class CopyBeanOptions {
    private List<String> sourceIgnores;
    private List<String> targetIgnores;

    public List<String> getSourceIgnores() {
        if (sourceIgnores == null) {
            sourceIgnores = new ArrayList<>();
        }
        return sourceIgnores;
    }

    public CopyBeanOptions sourceIgnores(List<String> sourceIgnores) {
        this.sourceIgnores = sourceIgnores;
        return this;
    }

    public CopyBeanOptions sourceIgnores(String ... properties) {
        if (properties != null && properties.length > 0) {
            Collections.addAll(getSourceIgnores(), properties);
        }
        return this;
    }

    public List<String> getTargetIgnores() {
        if (targetIgnores == null) {
            targetIgnores = new ArrayList<>();
        }
        return targetIgnores;
    }

    public CopyBeanOptions targetIgnores(List<String> targetIgnores) {
        this.targetIgnores = targetIgnores;
        return this;
    }

    public CopyBeanOptions targetIgnores(String ... properties) {
        if (properties != null && properties.length > 0) {
            Collections.addAll(getTargetIgnores(), properties);
        }
        return this;
    }

    public void resetSourceIgnores() {
        if (sourceIgnores != null) {
            sourceIgnores.clear();
        }
    }
    public static CopyBeanOptions defaultOptions() {
        return new CopyBeanOptions();
    }
}
