package michaelj.namespace.namespace.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Account with username already exists")
public class AccountNameInUseException extends RuntimeException {
}
