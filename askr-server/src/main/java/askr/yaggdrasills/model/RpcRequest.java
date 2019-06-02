package askr.yaggdrasills.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class RpcRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    private String className;
    private String methodName;
    private Class<?>[] paramTypes;
    private Object[] params;
}
