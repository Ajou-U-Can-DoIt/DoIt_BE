package Ajou.ucandoit.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class ResFormat {
    private final boolean success;
    private final Long status;
    private final Object data;
}
