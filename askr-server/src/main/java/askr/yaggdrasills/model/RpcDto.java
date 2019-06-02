package askr.yaggdrasills.model;

import lombok.Data;

@Data
public class RpcDto {
    private Class rpcServiceClass;

    private String rpcServiceName;

    private String ip;

    private Integer port;

    private Integer status;

}
