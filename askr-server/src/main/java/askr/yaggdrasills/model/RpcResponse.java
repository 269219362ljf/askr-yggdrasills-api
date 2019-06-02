package askr.yaggdrasills.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class RpcResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private Throwable error;
    private Object result;



}
